package com.example.pet_house;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.pet_house.web.CatService;

@SpringBootApplication
public class PetHouseApplication {

	public static void main(String[] args) {
		 var c = new AnnotationConfigApplicationContext(ProjectConfig.class);
	     var catService = c.getBean(CatService.class);
	     System.out.println(catService);     
		SpringApplication.run(PetHouseApplication.class, args);
	}

}
