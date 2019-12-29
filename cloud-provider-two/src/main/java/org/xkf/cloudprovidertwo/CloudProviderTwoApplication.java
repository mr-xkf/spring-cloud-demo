package org.xkf.cloudprovidertwo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.retry.annotation.EnableRetry;
import org.xkf.cloudprovidertwo.service.RetryService;

@SpringBootApplication
@MapperScan(basePackages = {"org.xkf.cloudprovidertwo.mapper"})
@EnableRetry
@Slf4j
public class CloudProviderTwoApplication implements CommandLineRunner {

	@Autowired
	private RetryService retryService;

	@Autowired
	private JdbcTemplate jdbcTemplate;


	public static void main(String[] args) {
		SpringApplication.run(CloudProviderTwoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		jdbcTemplate.execute("select * from seckill");
		log.info("执行成功！");

	}


}
