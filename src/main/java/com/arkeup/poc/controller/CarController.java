package com.arkeup.poc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arkeup.poc.data.dto.CarDTO;
import com.arkeup.poc.services.application.car.CarAS;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/car")
public class CarController {
	
	@Autowired
	CarAS carAS;

	@PreAuthorize("permitAll()")
	@ApiOperation(value = "Paged list of all cars with no filter", notes = "Paged list of all cars with no filter")
	@GetMapping("/list")
	public ResponseEntity<List<CarDTO>> getListVehicle(
			@ApiParam(name = "pageNo") @RequestParam(defaultValue = "0") Integer pageNo,
			@ApiParam(name = "pageSize") @RequestParam(defaultValue = "10") Integer pageSize)
			throws Exception {
		try {
			return new ResponseEntity<List<CarDTO>>(carAS.getCarList(pageNo, pageSize, null), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<List<CarDTO>>(new ArrayList<CarDTO>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
