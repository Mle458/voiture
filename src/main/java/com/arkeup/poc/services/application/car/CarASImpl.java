package com.arkeup.poc.services.application.car;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.arkeup.poc.common.utils.CarMapper;
import com.arkeup.poc.data.dto.CarDTO;
import com.arkeup.poc.data.entity.Car;
import com.arkeup.poc.services.repository.CarRepository;

@Service
public class CarASImpl implements CarAS {
	
	@Autowired
	CarRepository carRepository;
	
	@Autowired
	CarMapper carMapper;

	@Override
	public void saveCar(CarDTO dto) {
		Car car = carRepository.findByModel(dto.getModel());
		if (car == null) {
			car = new Car();
		} else {
			dto.setId(car.getId());
		}
		
		car = carMapper.map(dto);
		carRepository.save(car);
		
	}

	@Override
	public List<CarDTO> getCarList(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging;
		if (StringUtils.isEmpty(sortBy)) {
			sortBy = "id";
		}
		paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
		Page<Car> carsPaged = carRepository.findAll(paging);
		if (carsPaged.hasContent()) {
			return carMapper.map(carsPaged.getContent());
		} else {
			return new ArrayList<CarDTO>();
		}
	}
	
	@PostConstruct
	public void init() {
		importDefaultVehicle();
	}

	public void importDefaultVehicle() {
		List<CarDTO> cars = new ArrayList<>();
		cars.add(new CarDTO("Urvan", "Nissan"));
		cars.add(new CarDTO("Terios", "Daihatsu"));
		cars.add(new CarDTO("Crafter", "Mercedes"));
		cars.add(new CarDTO("Twingo", "Renault"));
		for (CarDTO car : cars) {
			saveCar(car);
		}
	}

}
