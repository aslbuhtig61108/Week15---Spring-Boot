package com.promineotech.jeep.dao;

import java.util.List;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

// W15 - Coding Assignment - ITEM#1a and b. - create this interface in the com.promineotech.jeep.dao package
public interface JeepSalesDao {

	/**
	 * 
	 * @param model
	 * @param trim
	 * @return
	 */
	List<Jeep> fetchJeeps (JeepModel model, String trim);
}
