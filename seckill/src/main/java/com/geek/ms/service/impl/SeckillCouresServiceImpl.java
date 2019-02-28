package com.geek.ms.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.geek.ms.mapper.StudentCouresInfoMapper;
import com.geek.ms.model.CourseModel;
import com.geek.ms.model.StudentCouresInfo;
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
	private StudentCouresInfoMapper studentCouresInfoMapper;
	
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
		List<StudentCouresInfo> studentCouresInfos = new ArrayList<>(SIZE);
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
				String[] listArr = temporaries.get(j).toString().split("-");
				int studentId = Integer.parseInt(listArr[0]);
				Timestamp time = new Timestamp(Long.parseLong(listArr[1]));
				
				StudentCouresInfo studentCouresInfo = new StudentCouresInfo();
				studentCouresInfo.setUserId(studentId);
				studentCouresInfo.setTimestamp(time);
				studentCouresInfo.setCouresInfoId(couresInfoId);
				studentCouresInfos.add(studentCouresInfo);
			}
			//操作数据库
			int length = studentCouresInfos.size();
			couresInfoMapper.desAllAmount(length, couresInfoId);
			count = studentCouresInfoMapper.addAllStudentCoures(studentCouresInfos);
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
		String args = studentId + "-" + System.currentTimeMillis();
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
	public List<CourseModel> loadCourse(int start, int length) {
		return studentCouresInfoMapper.loadCourse(start, length);
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
	
}