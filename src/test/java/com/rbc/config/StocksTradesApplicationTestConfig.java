package com.rbc.config;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rbc.controller.TradesController;
import com.rbc.service.TradesService;

@SpringBootApplication
public class StocksTradesApplicationTestConfig {
	
	@Bean
	public TradesController tradesController() {
		return new TradesController();
	}

}
