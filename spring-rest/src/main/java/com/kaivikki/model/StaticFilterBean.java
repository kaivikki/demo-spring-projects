package com.kaivikki.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StaticFilterBean {
	@JsonProperty("field_1")
	private String field1;
	@JsonIgnore
	private String field2;
	private String field3;
}
