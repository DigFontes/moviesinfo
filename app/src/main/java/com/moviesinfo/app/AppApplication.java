package com.moviesinfo.app;

import com.moviesinfo.app.models.SeriesData;
import com.moviesinfo.app.service.APIConsumer;
import com.moviesinfo.app.service.DataConverter;
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
		System.out.println("\nFist project Spring webless!\n");
		var apiConsumer = new APIConsumer();
		var json  = apiConsumer.getData("https://www.omdbapi.com/?t=House%20of%20the%20Dragon&apikey=d996cde6");

		System.out.println("\n" + json + "\n");

		DataConverter dataConverter = new DataConverter();

		SeriesData data = dataConverter.getData(json, SeriesData.class);

		System.out.println("\n" + data + "\n");


	}
}
