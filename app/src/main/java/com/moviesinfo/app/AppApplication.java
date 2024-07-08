package com.moviesinfo.app;

import com.moviesinfo.app.main.MainStreamingInfo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		MainStreamingInfo mainStreamingInfo = new MainStreamingInfo();
		mainStreamingInfo.showMenu();

	}
}
