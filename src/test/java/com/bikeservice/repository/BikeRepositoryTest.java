package com.bikeservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bikeservice.entity.Bike;

@DataJpaTest
class BikeRepositoryTest {
	
	@Autowired
	private BikeRepository bikeRepository;
	
	Bike bike;
	
	@BeforeEach
    public void setUp() {
		bike=new Bike();
		bike.setBikeMake("Hero");
		bike.setBikeName("Hunk");
		bike.setBikeRegistrationNumber("MH12CA4723");
		bike.setBikeChassisNumber(12345);
		bike.setAddress("Wakad , Pune");
		bike.setIssues("oil");
		bike.setCost(500);
		bike.setGivenDate(new Timestamp(new Date().getTime()));
		bike.setCreatedDateTime(new Timestamp(new Date().getTime()));
		//bike.setExpectedDeliveryDate(new Date());
		bike.setPhoneNumber("6789012345");
		bikeRepository.save(bike);
		
	}
	
	 @AfterEach
	 public void tearDown() {
		/// bikeRepository.deleteAll();
	 }

	@Test
	void testFindByBikeRegistrationNumber_Success() {
		Bike actualResult = bikeRepository.findByBikeRegistrationNumber("MH12CA4723");
		//assertEquals(actualResult, bike);
		assertThat(actualResult).isNotNull();
		assertThat(actualResult.getBikeChassisNumber()).isEqualTo(bike.getBikeChassisNumber());
		assertThat(actualResult.getPhoneNumber()).isEqualTo(bike.getPhoneNumber());
		
	}
	
	@Test
	void testFindByBikeRegistrationNumber_Successcross() {
		Bike actualResult = bikeRepository.findByBikeRegistrationNumber("MH12CA4723");
		//assertEquals(actualResult, bike);
		assertThat(actualResult.getBikeName()).isNotEqualTo("yamaha");
	}
	
	@Test
	void testFindByBikeRegistrationNumber_Failure () {
		Bike actualResult = bikeRepository.findByBikeRegistrationNumber("MH12CA4722");
		assertThat(actualResult).isNull();
		
	}

}
