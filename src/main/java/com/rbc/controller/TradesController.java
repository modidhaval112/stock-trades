package com.rbc.controller;

import static com.rbc.constants.ApplicationConstants.INTERNAL_SERVER_ERROR;
import static com.rbc.constants.ApplicationConstants.SUCC_MSG_RECORD;
import static com.rbc.constants.ApplicationConstants.SUCC_MSG_RECORDS;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rbc.model.Trades;
import com.rbc.service.TradesService;

@RestController
@RequestMapping("/trades")
public class TradesController {
	
	@Autowired
	TradesService tradeService;

	@PostMapping("/upload")
	public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) throws IOException {
		try {
			int recordSize = tradeService.upload(file);
			return ResponseEntity.status(HttpStatus.OK).body(recordSize + " " + SUCC_MSG_RECORDS);
		} 
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(INTERNAL_SERVER_ERROR);
		}
			
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> addTrade(@RequestBody Trades trade) throws IOException {
		try {
			int recordSize = tradeService.addTrade(trade);
			return ResponseEntity.status(HttpStatus.OK).body(recordSize + " " + SUCC_MSG_RECORD);
		} 
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/find")
	public  ResponseEntity<Object> findTrades(@RequestBody Trades trade) throws IOException {
		
		try {
			List<Trades> listTrades = tradeService.findTrades(trade);
			return ResponseEntity.status(HttpStatus.OK).body(listTrades);
		} 
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(INTERNAL_SERVER_ERROR);
		}
	}

}
