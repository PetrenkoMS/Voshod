package org.Voshod.Voshod;


import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VoshodApplication {

	public static void main(String[] args) {
		BasicConfigurator.configure(); //Для работы logger
		SpringApplication.run(VoshodApplication.class, args);
	}

}
