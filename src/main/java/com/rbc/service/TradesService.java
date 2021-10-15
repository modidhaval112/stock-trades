package com.rbc.service;

import static com.rbc.constants.ApplicationConstants.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rbc.model.Trades;
import com.rbc.repo.TradesRepository;
import com.rbc.transformation.TradeMapper;

@Service
public class TradesService {

	private static final Logger log = Logger.getLogger(TradesService.class.getName());

	@Autowired
	TradeMapper tradeMapper;

	@Autowired
	TradesRepository tradesRepository;

	public int upload(MultipartFile file) throws IOException {
		try {
			InputStream inputStream = file.getInputStream();
			List<Trades> listTrades = new ArrayList<>();

			new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines().skip(1)
					.forEach(s -> tradeMapper.map(s, listTrades));
			tradesRepository.deleteAll();
			tradesRepository.insert(listTrades);

			log.info(listTrades.size() + " " + SUCC_MSG_RECORDS);

			return listTrades.size();

		} 
		catch (IOException e) 
		{
			log.info(ERR_MSG_INSERT_RECORDS + e.getMessage());
			throw e;
		}
	}

	public int addTrade(Trades trade) {
		try 
		{
			tradesRepository.save(trade);
			log.info("1 " + SUCC_MSG_RECORD);
			return 1;
		} 
		catch (Exception e) 
		{
			log.info(ERR_MSG_INSERT_RECORD + e.getMessage());
			throw e;
		}
	}

	public List<Trades> findTrades(Trades trade) {
		try 
		{
			Example<Trades> exmp = Example.of(trade);
			return tradesRepository.findAll(exmp);
		} 
		catch (Exception e) 
		{
			log.info(ERR_MSG_FIND_RECORD + e.getMessage());
			throw e;
		}
	}

}
