package com.ayush.subscription.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.TimeZone;



@EnableCaching
@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
    @Bean
    CommandLineRunner cacheRunner(CacheManager cacheManager) {
        return args -> {
            System.out.println("CACHE MANAGER = " + cacheManager.getClass().getName());
        };
    }

}
