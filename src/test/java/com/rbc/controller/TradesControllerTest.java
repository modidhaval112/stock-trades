package com.rbc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.rbc.config.StocksTradesApplicationTestConfig;
import com.rbc.model.Trades;
import com.rbc.service.TradesService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {StocksTradesApplicationTestConfig.class})
@EnableAutoConfiguration
public class TradesControllerTest {
	
	@MockBean
	protected TradesService tradeSerivce;
	
	@Autowired
	protected TradesController tradesController;
	
	@Test
	public void insertAllSuccess() throws IOException {
		doReturn(750).when(tradeSerivce).upload(any());
		ResponseEntity<Object> response = tradesController.upload(any());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("750 records inserted successfully", response.getBody());
	}
	
	@Test
	public void insertAllFailure() throws IOException {
		doThrow(new RuntimeException()).when(tradeSerivce).upload(any());
		ResponseEntity<Object> response = tradesController.upload(any());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Unexpected Internal Error", response.getBody());
	}
	
	@Test
	public void addTradeSuccess() throws IOException {
		doReturn(1).when(tradeSerivce).addTrade(any());
		ResponseEntity<Object> response = tradesController.addTrade(any());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("1 record inserted successfully", response.getBody());
	}
	
	@Test
	public void addTradeFailure() throws IOException {
		doThrow(new RuntimeException()).when(tradeSerivce).addTrade(any());
		ResponseEntity<Object> response = tradesController.addTrade(any());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Unexpected Internal Error", response.getBody());
	}
	
	@Test
	public void findTradesSuccess() throws IOException {
		
		Trades trade1 = new Trades("1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1");
		Trades trade2 = new Trades("2","2","2","2","2","2","2","2","2","2","2","2","2","2","2","2");
		List<Trades> expected = new ArrayList<Trades>();
		expected.add(trade1);
		expected.add(trade2);
		
		doReturn(expected).when(tradeSerivce).findTrades(any());
		List<Trades> actual = tradeSerivce.findTrades(any());
		
		assertEquals(expected.size(), actual.size());
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void findTradesFailure() throws IOException {
		doThrow(new RuntimeException()).when(tradeSerivce).findTrades(any());
		ResponseEntity<Object> response = tradesController.findTrades(any());
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
		assertEquals("Unexpected Internal Error", response.getBody());
	}
	
	
}