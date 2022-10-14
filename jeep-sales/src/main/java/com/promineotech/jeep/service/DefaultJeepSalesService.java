package com.promineotech.jeep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import com.promineotech.jeep.dao.JeepSalesDao;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

// W15 - Coding Assignment - ITEM# 1c and 1d [LINE 26] 
public class DefaultJeepSalesService implements JeepSalesService {

	@Autowired
	private JeepSalesDao jeepSalesDao;
	
	@Override
	public List<Jeep> fetchJeeps(JeepModel model, String trim) {
		// List<Jeep> jeeps = new ArrayList<>(); // W15 - Coding Assignment - ITEM#2 not relevant to the assignment
		log.info("The fetchJeeps() method was called with model={} and trim={}", model, trim);
		
		return jeepSalesDao.fetchJeeps(model, trim);  // W15 - ITEM#2 states 'return the value in the 'jeeps' variable (we will add to this later).
	}

}