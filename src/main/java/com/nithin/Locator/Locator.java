package com.nithin.Locator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.gemfire.config.annotation.CacheServerApplication;
import org.springframework.data.gemfire.config.annotation.EnableLocator;
import org.springframework.data.gemfire.config.annotation.EnableManager;
import org.springframework.data.gemfire.config.annotation.LocatorApplication;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@LocatorApplication
@Slf4j
public class Locator {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext ctx = SpringApplication.run(Locator.class, args);

		CountDownLatch countDownLatch = ctx.getBean(CountDownLatch.class);


		Runtime.getRuntime().addShutdownHook(new Thread(()->{
			log.info("Shutting down Locator");
		}));

		countDownLatch.await();


	}

}

@Configuration
class ConfigDev {

	@Bean
	public CountDownLatch getLatch(){
		return new CountDownLatch(1);
	}

}

