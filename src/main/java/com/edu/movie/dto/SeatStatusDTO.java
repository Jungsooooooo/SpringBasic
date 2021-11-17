package com.edu.movie.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//-----------------------------------------------------------------------------------------------------------
// 좌석 예약 상태
//-----------------------------------------------------------------------------------------------------------
@Getter
@Setter
@ToString
public class SeatStatusDTO {
	
	int		seatID;	// 좌석번호
	boolean	status;	// 좌석예약상태 : true(예약 중), false(빈 좌석)

	//-----------------------------------------------------------------------------------------------------------
	// 생성자
	//-----------------------------------------------------------------------------------------------------------
	public SeatStatusDTO(int seatID, boolean status) {
		
		super();
		this.seatID	= seatID;
		this.status	= status;
		
	}

} // End - public class SeatStatusDTO



