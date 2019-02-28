package com.geek.ms.config;

import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidConfig {
	
	private Logger logger = LoggerFactory.getLogger(DruidConfig.class);
	@Value("${spring.datasource.initialSize}")
    private int initialSize;
    @Value("${spring.datasource.minIdle}")
    private int minIdle;
    @Value("${spring.datasource.maxActive}")
    private int maxActive;
    @Value("${spring.datasource.maxWait}")
    private int maxWait;
    @Value("${spring.datasource.filters}")
    private String filters;
    
    @Bean(destroyMethod = "close",initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource
    public DataSource dataSource() {
    	DruidDataSource dataSource = new DruidDataSource();
    	dataSource.setInitialSize(initialSize);
    	dataSource.setMinIdle(minIdle);
    	dataSource.setMaxActive(maxActive);
    	dataSource.setMaxWait(maxWait);
    	try {
    		dataSource.setFilters(filters);
    	}catch (SQLException e) {
			// TODO: handle exception
    		logger.error("druid configuration initialization filter", e);
		}
    	return dataSource;
    }
    
	@Bean
	public ServletRegistrationBean<Servlet> druidServlet() {
		
		ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		servletRegistrationBean.addInitParameter("loginPassword", "admin");
		servletRegistrationBean.addInitParameter("allow", "192.168.3.7,127.0.0.1");
		servletRegistrationBean.addInitParameter("deny", "192.168.1.7");
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
	}
	
	@Bean
	public FilterRegistrationBean<Filter> filterRegistrationBean() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
	
}
