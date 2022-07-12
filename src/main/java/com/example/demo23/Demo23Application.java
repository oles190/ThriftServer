package com.example.demo23;


import com.example.demo23.server.Server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Demo23Application {

    static private Server server;

    public Demo23Application(Server server) {
        this.server = server;
    }

    public static void main(String[] args) {
        SpringApplication.run(Demo23Application.class, args);
        server.startServer();
    }


}
