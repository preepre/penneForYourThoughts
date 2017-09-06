package com.libertymutual.goforcode.penneForYourThoughts.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
		generator=ObjectIdGenerators.PropertyGenerator.class,
		property="id"
	)

@Entity
public class Instruction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	 
	@Column(length=500, nullable=false)
	private String step;
	
}
