package com.bikeservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.bikeservice.entity.Bike;
import com.bikeservice.exception.ResourceNotFoundException;
import com.bikeservice.repository.BikeRepository;


class BikeServiceTest {
	
	@Mock
	private BikeRepository bikeRepository;
	
	@InjectMocks
	private BikeService bikeService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAddBike() {
		Bike bike=new Bike();
		bike.setBikeRegistrationNumber("MH12CA0011");
		
		Mockito.when(bikeRepository.findByBikeRegistrationNumber("MH12CA0011")).thenReturn(null);
		Mockito.when(bikeRepository.save(Mockito.any(Bike.class))).thenReturn(bike);
		
		try {
			Bike addedBike = bikeService.addBike(bike);
			assertThat(addedBike).isNotNull();
			Assertions.assertEquals("MH12CA0011", addedBike.getBikeRegistrationNumber());
		}
		catch (ResourceNotFoundException e) {
			Assertions.fail("ResourceNotFoundException in testing.");
		}
	}
	
	@Test
	public void testAddBike_WithRegisteredBike() {
		Bike bike =new Bike();
		bike.setBikeRegistrationNumber("MH12CA0022");
		
		Mockito.when(bikeRepository.findByBikeRegistrationNumber("MH12CA0022")).thenReturn(bike);
	
		Assertions.assertThrows(ResourceNotFoundException.class,()->{bikeService.addBike(bike);});
		}

	@Test
	void testGetBike() {
		Bike bike = new Bike();
		bike.setBikeRegistrationNumber("MH12CA0021");
		
		Mockito.when(bikeRepository.findByBikeRegistrationNumber("MH12CA0021")).thenReturn(bike);
		try {
			Bike bike2 = bikeService.getBike("MH12CA0021");
			assertThat(bike2).isNotNull();
			Assertions.assertEquals("MH12CA0021", bike2.getBikeRegistrationNumber());
		} catch (Exception e) {
			Assertions.fail(e);
			
		}
		}
	
	@Test
	void testGetBike_failure() {
		Bike bike = new Bike();
		bike.setBikeRegistrationNumber("MH12CA0021");
		
		Mockito.when(bikeRepository.findByBikeRegistrationNumber("MH12CA0021")).thenReturn(bike);
		try {
			Bike bike2 = bikeService.getBike("MH12CA0021");
			Assertions.assertNotEquals("MH12CA0022", bike2.getBikeRegistrationNumber());
		} catch (Exception e) {
			Assertions.fail(e);
			
		}
		}
	
	@Test
	void testGetBike_failure1() {
		Bike bike = new Bike();
		bike.setBikeRegistrationNumber("MH12CA0021");
		
		Mockito.when(bikeRepository.findByBikeRegistrationNumber("MH12CA0022")).thenReturn(null);
		Assertions.assertThrows(ResourceNotFoundException.class,()->{bikeService.getBike("MH12CA0022");});
		}

	@Test
	void testUpdateBike() {
		
		Bike bike=new Bike();
		bike.setBikeRegistrationNumber("MH12CA0011");
		bike.setPhoneNumber("7869041111");
		
		Bike updatedBike=new Bike();
		updatedBike.setPhoneNumber("7869042222");
		
		Mockito.when(bikeRepository.findByBikeRegistrationNumber("MH12CA0011")).thenReturn(bike);
		//Mockito.when(bike.getBikeRegistrationNumber()).thenReturn(bike.setBikeRegistrationNumber("MH12CA0011"));
		Mockito.when(bikeRepository.save(bike)).thenReturn(updatedBike);
		
		Bike actuaBike = bikeService.updateBike(bike, "MH12CA0011");
		Assertions.assertEquals("7869042222",actuaBike.getPhoneNumber());
	}

	@Test
	void testDeletBike() {
		Bike bike =new Bike();
		bike.setBikeRegistrationNumber("MH12CA0011");
		Mockito.when(bikeRepository.findByBikeRegistrationNumber("MH12CA0011")).thenReturn(bike);
		assertAll(()->bikeService.deletBike("MH12CA0011"));
		Mockito.verify(bikeRepository,times(1)).delete(bike);
		
	}

}
