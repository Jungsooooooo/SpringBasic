package com.edu.member.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//-----------------------------------------------------------------------------------------------------------
// public class MemberDTO
//-----------------------------------------------------------------------------------------------------------
@Getter
@Setter
@ToString
public class MemberDTO {

	private	String	id;
	private	String	passwd;
	private	String	repasswd;
	private	String	name;
	private	Date	reg_date;
	private	String	tel;
	private	String	address;

} // End - public class MemberDTO
