package com.bikeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikeservice.entity.Bike;
import com.bikeservice.exception.ResourceNotFoundException;
import com.bikeservice.service.BikeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bikeservice")
public class BikeController {
	@Autowired
	private BikeService bikeService;
	
	@PostMapping("/save")
	public ResponseEntity<?> createBike(@Valid @RequestBody Bike bike){
		try {
		Bike addedBike = bikeService.addBike(bike);
		return ResponseEntity.status(HttpStatus.CREATED).body(addedBike);
		//return ResponseEntity.ok(addedBike);
		}
		catch (ResourceNotFoundException ex) {
			//System.out.println(ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
			
		}
		catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
}
	@GetMapping("/{bikeRegNo}")
	public ResponseEntity<?> getById(@PathVariable String bikeRegNo){
		try {
			Bike bike1 = bikeService.getBike(bikeRegNo);
			return ResponseEntity.status(HttpStatus.OK).body(bike1);
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		
	}
	
	@PutMapping("/{bikeRegNo}")
	public ResponseEntity<?> updateById(@RequestBody Bike bike , @PathVariable String bikeRegNo){
		
		try {
			Bike updateBike = bikeService.updateBike(bike, bikeRegNo);
			return ResponseEntity.status(HttpStatus.CREATED).body(updateBike);
			
		}catch (ResourceNotFoundException ex ) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		} 
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	
	
	@DeleteMapping("/{bikeRegNo}")
	public ResponseEntity<?> deleteBike(@PathVariable String bikeRegNo){
		try {
			bikeService.deletBike(bikeRegNo);
			return ResponseEntity.accepted().body("bike deleted successfully");
		}
		catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
		catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());		}
		
	}

}
