package com.edu.movie.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.edu.movie.dao.MovieDAO;
import com.edu.movie.dto.SeatDTO;

//-----------------------------------------------------------------------------------------------------------
// public class MovieServiceImpl implements MovieService
//-----------------------------------------------------------------------------------------------------------
@Service
public class MovieServiceImpl implements MovieService {

	private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);
	
	// 의존 관계 주입 (DI : Defendency Injection)
	@Inject
	MovieDAO movieDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 영화 예매 좌석 목록 가져오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<SeatDTO> getSeatList(int movieID) throws Exception {

		logger.info("MovieServiceImpl getSeatList(int movieID).....");
		return movieDAO.getSeatList(movieID);
		
	} // End - public List<SeatDTO> getSeatList(int movieID)
	
	//-------------------------------------------------------------------------------------------------
	// 좌석 예약
	//-------------------------------------------------------------------------------------------------
	@Override
	public int insertSeatID(int seatID) throws Exception {
		
		return movieDAO.insertSeatID(seatID);
		
	} // End - public int insertSeatID(int seatID)



} // End - public class MovieServiceImpl implements MovieService





