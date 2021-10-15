package com.rbc.transformation;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rbc.model.Trades;

@Component
public class TradeMapper {
	
	@Autowired
	Trades trade;

	public void map(String line, List<Trades> listTrades) {

		List<String> values = Arrays.asList(line.split(","));
		trade = new Trades(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), values.get(5),
				values.get(6), values.get(7), values.get(8), values.get(9), values.get(10), values.get(11),
				values.get(12), values.get(13), values.get(14), values.get(15));

		listTrades.add(trade);

	}
	
}
