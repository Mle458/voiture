package com.arkeup.poc.services.application.car;

import java.util.List;

import com.arkeup.poc.data.dto.CarDTO;

public interface CarAS {
	
	public void saveCar (CarDTO dto);

	public List<CarDTO> getCarList(Integer pageNo, Integer pageSize, String sortBy);

}