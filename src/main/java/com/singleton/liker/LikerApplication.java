package com.singleton.liker;

import com.singleton.liker.service.LikeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LikerApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(LikerApplication.class, args);
        LikeService likeService = ctx.getBean("likeServiceImpl", LikeService.class);
        System.out.println(likeService.getLikes("Hello"));
        likeService.like("Hello");
        likeService.like("Hello");
        likeService.like("Hello");
        System.out.println(likeService.getLikes("Hello"));
    }
}
