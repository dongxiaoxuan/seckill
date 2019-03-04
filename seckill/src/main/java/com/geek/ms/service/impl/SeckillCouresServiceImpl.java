package com.geek.ms.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.geek.ms.mapper.CouresInfoMapper;
import com.geek.ms.mapper.StudentCourseInfoMapper;
import com.geek.ms.pojo.vo.CourseModel;
import com.geek.ms.pojo.vo.StudentCourseInfo;
import com.geek.ms.service.SeckillCouresService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class SeckillCouresServiceImpl implements SeckillCouresService{
	
	private Logger logger = LoggerFactory.getLogger(SeckillCouresServiceImpl.class);

	private static final int SIZE = 50;
	
	@Autowired
	@Qualifier("stringRedisTemplate")
	private RedisTemplate<String, ?> redisTemplate;
	
	@Autowired
	private CouresInfoMapper couresInfoMapper;
	
	@Autowired
	private StudentCourseInfoMapper studentCouresInfoMapper;
	
	@Autowired
	private JedisPool jedisPool;
	
	@Override
	//开启一个新线程
	@Async
	public void saveStudentCouresInfo(int couresInfoId) {
		Long start = System.currentTimeMillis();
		BoundListOperations<String, ?> ops = redisTemplate.boundListOps("coures_student_list_" + couresInfoId);
		Long size = ops.size();
		Long times = size % SIZE ==0 ? size/SIZE : size/SIZE + 1;
		//最终操作数据数量
		int count = 0;
		List<StudentCourseInfo> studentCouresInfos = new ArrayList<>(SIZE);
		for(int i = 0; i < times; i ++) {
			//临时列表
			List<?> temporaries = null;
			if(i == 0) {
				temporaries = ops.range(i * SIZE, (i + 1) * SIZE);
			}else {
				temporaries = ops.range(i * SIZE + 1, (i + 1) * SIZE);
			}
			studentCouresInfos.clear();
			//开始保存
			for (int j = 0; j < temporaries.size(); j ++) {
				String list = temporaries.get(j).toString();
				int studentId = Integer.parseInt(list);
				Timestamp time = new Timestamp(new Date().getTime());
				
				StudentCourseInfo studentCouresInfo = new StudentCourseInfo();
				studentCouresInfo.setUserId(studentId);
				studentCouresInfo.setTimestamp(time);
				studentCouresInfo.setCouresInfoId(couresInfoId);
				studentCouresInfos.add(studentCouresInfo);
			}
			//操作数据库
			int length = studentCouresInfos.size();
			couresInfoMapper.desAllAmount(length, couresInfoId);//更新数据库选课量
			count = studentCouresInfoMapper.addAllStudentCoures(studentCouresInfos);//插入所有选课信息
			logger.info("导入成功");
		}
		redisTemplate.delete("coures_student_list_" + couresInfoId);
		Long end = System.currentTimeMillis();
		logger.info("用时:" + (end - start) + ",数据量：" + count);
	}

	String script = "local listKey = 'coures_student_list_'..KEYS[1] \n"
			+ "local couresStudent = 'coures_student_'..KEYS[1] \n"
			+ "local amount = tonumber(redis.call('hget', couresStudent, 'amount')) \n"
			+ "if amount <= 0 then return 0 end \n"
			+ "amount = amount - 1 \n"
			+ "redis.call('hset', couresStudent, 'amount', tostring(amount)) \n"
			+ "redis.call('rpush', listKey, ARGV[1]) \n"
			+ "if amount == 0 then return 2 end \n"
			+ "return 1 \n";
	String sha1 = null;
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, readOnly=false, rollbackFor={Exception.class})
	public int seckillCoures(int couresInfoId, int studentId) {
		String args = studentId + "";
		int result = -1;
		Jedis jedis = jedisPool.getResource();
		try {
			if(sha1 == null) {
				sha1 = jedis.scriptLoad(script);
			}
			Object resultObj = jedis.evalsha(sha1, 1, couresInfoId + "", args);
			Long resultlong = (Long)resultObj;
			result = resultlong.intValue();
			if (result == 2) {
				logger.info("准备导入数据库...");
				saveStudentCouresInfo(couresInfoId); 
			}
		}finally {
			if (jedis != null && jedis.isConnected()) {
				jedis.close();
			}
		}
		return result;
	}

	@Override
	public List<CourseModel> loadCourse(Integer start, Integer length, Integer id) {
		return studentCouresInfoMapper.loadCourse(start, length, id);
	}
	
	public Integer getCourseCount() {
		return studentCouresInfoMapper.getCourseCount();
	}

	@Override
	public boolean isOpen() {
		int flag = (int)studentCouresInfoMapper.isOpen();
		if(flag == 1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int openOrClose(Integer open) {
		return studentCouresInfoMapper.openOrClose(open);
	}

	@Override
	public Integer dropCourseById(Integer userId, Integer ciid) {
		return studentCouresInfoMapper.dropCourseById(userId, ciid);
	}

	@Override
	public void saveDataWhenClose(int couresInfoId) {
		saveStudentCouresInfo(couresInfoId);
	}

	@Override
	public String getAmountFromRedis(int courseId) {
		String amount = (String) redisTemplate.opsForHash().get("coures_student_" + courseId, "amount");
		return amount;
	}

	@Override
	public void setAmountToRedis(int courseId, int amount) {
		redisTemplate.opsForHash().put("coures_student_" + String.valueOf(courseId), "amount", String.valueOf(amount));
	}
	
	@Override
	public List<?> getCourseStudentListFromRedis(int courseId) {
		Long size = redisTemplate.opsForList().size("coures_student_list_" + String.valueOf(courseId));
		List<?> csList = (List<?>) redisTemplate.opsForList().range("coures_student_list_" + String.valueOf(courseId), 0, size);
		return csList;
	}
	
	@Override
	public List<Integer> loadAllCourseId() {
		return studentCouresInfoMapper.loadAllCourseId();
	}

	@Override
	public int clearAllAmount() {
		return couresInfoMapper.clearAllAmount();
	}

}