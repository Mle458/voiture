package com.arkeup.poc.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arkeup.poc.data.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	
	Car findByModel(String model);

}
