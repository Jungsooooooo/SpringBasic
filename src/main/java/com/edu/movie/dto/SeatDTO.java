package com.edu.movie.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//-----------------------------------------------------------------------------------------------------------
// public class SeatDTO
//-----------------------------------------------------------------------------------------------------------
@Getter
@Setter
@ToString
public class SeatDTO {

	int		movieID;	// 영화
	String	userID;		// 예매자
	int		seatID;		// 예매좌석
	
	//-----------------------------------------------------------------------------------------------------------
	// 생성자
	//-----------------------------------------------------------------------------------------------------------
	public SeatDTO(int movieID, String userID, int seatID) {
		
		this.movieID	= movieID;
		this.userID		= userID;
		this.seatID		= seatID;
		
	} // End - public SeatDTO(int movieID, String userID, int seatID)
	
} // End - public class SeatDTO









