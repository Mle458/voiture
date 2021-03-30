package com.arkeup.poc.common.utils;

import java.util.List;

import org.mapstruct.Mapper;

import com.arkeup.poc.data.dto.CarDTO;
import com.arkeup.poc.data.entity.Car;


@Mapper(componentModel = "spring")
public interface CarMapper {

	CarDTO mp(Car car);

	Car map(CarDTO dto);

	List<CarDTO> map(List<Car> cars);
}
