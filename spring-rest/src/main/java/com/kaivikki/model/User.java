package com.kaivikki.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="user_details")
public class User{
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@JsonProperty("user_name")
	@Size(min = 2, message = "name should have atleast two characters")
	private String name;
	
	@JsonProperty("birth_date")
	@Past(message = "birth date should be in past")
	@Column(name = "birth_date")
	private LocalDate birthDate;
}
