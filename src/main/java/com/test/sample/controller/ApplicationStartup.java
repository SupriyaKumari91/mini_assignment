package com.test.sample.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		System.out.println("Started : onApplicationEvent");
		try {

			InputStream ioStream = this.getClass().getClassLoader().getResourceAsStream("files/movies.csv");
			try (InputStreamReader isr = new InputStreamReader(ioStream); 
	                BufferedReader br = new BufferedReader(isr);) 
	        {
	            String line;
	            int count =0;
	            while ((line = br.readLine()) != null) {
	                System.out.println(line);
	                if(count!=0) {
	                	
	                }
	                count++;
	            }
	            ioStream.close();
	        }

		} catch (Exception e) {
			System.out.println("Exception : onApplicationEvent");
			e.printStackTrace();
		}
		System.out.println("Finished : onApplicationEvent");
	}
}
