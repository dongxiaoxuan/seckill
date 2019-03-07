package com.geek.ms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.geek.ms.Intercepter.CacheLockKeyGenerator;
import com.geek.ms.mapper.CacheKeyGenerator;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.geek.ms.mapper")
public class SeckillApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeckillApplication.class, args);
	}

	@Bean
    public CacheKeyGenerator cacheKeyGenerator() {
        return new CacheLockKeyGenerator();
    }
	
}
	

