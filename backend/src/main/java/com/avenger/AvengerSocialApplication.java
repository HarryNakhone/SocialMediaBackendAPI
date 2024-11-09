package com.avenger;



import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class AvengerSocialApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvengerSocialApplication.class, args);
    }
}
