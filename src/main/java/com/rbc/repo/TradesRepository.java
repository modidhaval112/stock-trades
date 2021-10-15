package com.rbc.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rbc.model.Trades;

@Repository
public interface TradesRepository extends MongoRepository<Trades, String>{
	
}
