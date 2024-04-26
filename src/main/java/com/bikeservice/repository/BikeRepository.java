package com.bikeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bikeservice.entity.Bike;

@Repository
public interface BikeRepository extends JpaRepository<Bike, String> {
	//SBike findByBikeChassisNumber(int chassisno);
	
	Bike findByBikeRegistrationNumber(String registationno);

}