package com.bikeservice.entity;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bike {
	@NotEmpty(message = "BikeMake should not be empty")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "BikeMake must not contain special characters")
	private String bikeMake;
	
	@NotEmpty(message = "BikeName should not be empty")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "BikeName must not contain special characters")
	private String bikeName;
	
	@Id
	@NotEmpty(message = "BikeRegistrationNumber should not be empty")
	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "BikeRegistrationNumber must not contain special characters")
	private String bikeRegistrationNumber;
	
	@NotNull(message = "BikeChassisNumber should not be null")
	private int bikeChassisNumber;
	
	@NotEmpty(message = "Issues should not be empty")
	@Pattern(regexp = "^[a-zA-Z0-9,]*$", message = "issue must not contain special characters")
	private String issues;
	
	@NotNull(message = "Cost should not be null")
	private int cost;
	
	@NotNull(message = "GivenDate should not be null")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss")
    private Timestamp givenDate;
	
	//@NotNull(message = "DeliveryDate should not be null")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date expectedDeliveryDate;
	
	//@NotNull(message = "Field should not be empty")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss")
    private Timestamp createdDateTime;
	
	//@NotNull(message = "Field should not be empty")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy HH:mm:ss")
    private Timestamp updatedDateTime;
	
	@NotEmpty(message = "Address should not be empty")
	private String address;
	
	@NotEmpty(message = "PhoneNumber should not be empty")
	@Size(min = 10 , max = 10 , message = "Phone number must have 10 digits only")
	@Pattern(regexp = "^[6789][0-9]*$", message = "phone number must start with 6/7/8/9 ; No special characters/alphabets")
	private  String phoneNumber;

}
