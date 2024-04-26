package com.bikeservice.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bikeservice.entity.Bike;
import com.bikeservice.exception.ResourceNotFoundException;
import com.bikeservice.service.BikeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
class BikeControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private BikeService bikeService;
	
	@InjectMocks
	private BikeController bikeController;
	
	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp(){
		 mockMvc = MockMvcBuilders.standaloneSetup(bikeController).build();
	     objectMapper = new ObjectMapper();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateBike() throws Exception {
		Bike bike1=new Bike();  //mock data
		bike1.setBikeMake("Hero");
		bike1.setBikeName("Hunk");
		bike1.setBikeRegistrationNumber("MH12CA4723");
		bike1.setBikeChassisNumber(12345);
		bike1.setAddress("Wakad , Pune");
		bike1.setIssues("oil");
		bike1.setCost(500);
		bike1.setGivenDate(new Timestamp(new Date().getTime()));
		bike1.setCreatedDateTime(new Timestamp(new Date().getTime()));
		//bike.setExpectedDeliveryDate(new Date());
		bike1.setPhoneNumber("6789012345");
		
		//when(bikeService.addBike(bike1)).thenReturn(bike1);
		
		
		MvcResult mvcResult= mockMvc.perform(post("/bikeservice/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(bike1)))
				.andExpect(status().isCreated())
		        .andReturn();
		Bike bike =objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Bike.class);
		Assertions.assertEquals("6789012345", bike.getPhoneNumber());
//				.andExpect(status().isOk())
//		        .andExpect(jsonPath("$.content.bikeMake").value("Hero"))
//		        .andExpect(jsonPath("$.chassisNumber").value(12345))
//		        .andExpect(jsonPath("$.phoneNumber").value("6789012345"));
//		             
//		
	}
	
	@Test
	void testCreateBike_WithRegisteredBike() throws Exception {
		Bike bike1=new Bike();  //mock data
		bike1.setBikeMake("Hero");
		bike1.setBikeName("Hunk");
		bike1.setBikeRegistrationNumber("MH12CA4723");
		bike1.setBikeChassisNumber(12345);
		bike1.setAddress("Wakad , Pune");
		bike1.setIssues("oil");
		bike1.setCost(500);
		bike1.setGivenDate(new Timestamp(new Date().getTime()));
		bike1.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bike1.setExpectedDeliveryDate(null);
		bike1.setPhoneNumber("6789012345");
		
		
		when(bikeService.addBike(bike1)).thenThrow(new ResourceNotFoundException("Bike already registered"));
		//doReturn(bike1).when(bikeService.addBike(bike1));
		mockMvc.perform(post("/bikeservice/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(bike1)))
		        .andExpect(status().isBadRequest());
		       // .andExpect((ResultMatcher) content().string("Bikee already registered"));
		       
		 verify(bikeService).addBike(bike1);
	}


	@Test
	void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateById() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteBike() {
		fail("Not yet implemented");
	}

}
