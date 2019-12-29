package org.xkf.cloudeurekahigh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CloudEurekaHighApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudEurekaHighApplication.class, args);
	}

}
