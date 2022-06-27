package com.example.demo23;

import com.example.demo23.server.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class Demo23Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo23Application.class, args);
		Server.startServer();
	}


}
