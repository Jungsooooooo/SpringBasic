package com.edu.basic.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//-----------------------------------------------------------------------------------------------------------
// public class BookTypeDTO
// lombok 설치 후 The value of the field BookTypeDTO.id is not used
//-----------------------------------------------------------------------------------------------------------
@Setter
@Getter
@ToString
public class BookTypeDTO {
	
	private	String	id;
	private	String	name;
	
	/*
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "BookTypeDTO [id=" + id + ", name=" + name + "]";
	}
	*/
	
} // End - public class BookTypeDTO
