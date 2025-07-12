package com.myapp;
//Use SLF4J + Logback — which Spring Boot includes by default.
import org.slf4j.Logger; //
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.myapp.dao")
//@EntityScan(basePackages = "com.myapp.entity")  // Your entity package
public class SpringBootRunner implements CommandLineRunner {

   Logger logger = LoggerFactory.getLogger(SpringBootRunner.class);

    public static void main(String[] args) {
        // to bootstrap this application { explain TODO}
        SpringApplication.run(SpringBootRunner.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info(" !!!!!!!!!!!!...Spring Boot Application Started Successfully @@@@@@@@@@@@@@@@@@@@................ ");
        logger.info(" !!!!!!!!!!!!...Employee Registration Service is up and running @@@@@@@@@@@@@@@@@@@@................ .");
        logger.info(" !!!!!!!!!!!!...Listening on port 444 → http://localhost:444/ @@@@@@@@@@@@@@@@@@@@................ ");
    
    
    }
    
}
