package com.bikeservice.service;


import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikeservice.entity.Bike;
import com.bikeservice.exception.ResourceNotFoundException;
import com.bikeservice.repository.BikeRepository;

@Service
public class BikeService {
	
	@Autowired
	private BikeRepository bikeRepository;
	
	public Bike addBike(Bike bike) throws ResourceNotFoundException {
		if(bikeRepository.findByBikeRegistrationNumber(bike.getBikeRegistrationNumber())!= null) {
			
			throw new ResourceNotFoundException("bike with this no is already registered :" + bike.getBikeRegistrationNumber());
		}
		
		try {
			bike.setCreatedDateTime(new Timestamp(new Date().getTime()));
			return bikeRepository.save(bike);
		}
		catch(Exception e) {
			throw new RuntimeException("Failed to create");
		}	
	}
	
	public Bike getBike(String bikeRegNo) throws ResourceNotFoundException {
		if(bikeRepository.findByBikeRegistrationNumber(bikeRegNo)==null) {
			throw new ResourceNotFoundException("no bike found with this id "+bikeRegNo);
		}
		try { 
			return bikeRepository.findByBikeRegistrationNumber(bikeRegNo);
		} catch (Exception e) {
			throw new RuntimeException("Failed to get bike");
		}
		
	}
	
	public Bike updateBike(Bike bike,String bikeRegNo) throws ResourceNotFoundException{
		Bike oldBike = bikeRepository.findByBikeRegistrationNumber(bikeRegNo);
		if(oldBike==null) {
			throw new ResourceNotFoundException("no bike registered with the given id "+bikeRegNo);
		}
	    if( !bike.getBikeRegistrationNumber().equals(bikeRegNo)) {
			throw new ResourceNotFoundException("bike regNo cant be updated "+bike.getBikeRegistrationNumber());
			}
		
	    
		try {
			//oldBike.setBikeMake(bike.getBikeMake());
			//oldBike.setBikeName(bike.getBikeName());
			//oldBike.setBikeChassisNumber(bike.getBikeChassisNumber());
			//oldBike.setBikeRegistrationNumber(bike.getBikeRegistrationNumber());
			oldBike.setIssues(bike.getIssues());
			oldBike.setCost(bike.getCost());
			//oldBike.setGivenDate(bike.getGivenDate());
			oldBike.setExpectedDeliveryDate(bike.getExpectedDeliveryDate());
			//oldBike.setCreatedDateTime(bike.getCreatedDateTime());
			oldBike.setUpdatedDateTime(new Timestamp(new Date().getTime()));
			oldBike.setAddress(bike.getAddress());
			oldBike.setPhoneNumber(bike.getPhoneNumber());
			return bikeRepository.save(oldBike);
			
		}
		catch (Exception e) {
			throw new RuntimeException("failed to update the details");
		}
	}
	
	
	public void deletBike(String bikeRegNo) throws ResourceNotFoundException{
		Bike bike1 = bikeRepository.findByBikeRegistrationNumber(bikeRegNo);
		if(bike1== null) {
			throw new ResourceNotFoundException("no bike is registered with the given registration no "+ bikeRegNo);
		}
		try {
			bikeRepository.delete(bike1);
		} catch (Exception e) {
			throw new RuntimeException("Failed to delete");
			
		}
		
	}
	
	

}
