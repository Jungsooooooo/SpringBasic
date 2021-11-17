package com.edu.movie.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.edu.movie.dto.SeatDTO;

//-----------------------------------------------------------------------------------------------------------
// public class MovieDAOImpl implements MovieDAO
//-----------------------------------------------------------------------------------------------------------
@Repository
public class MovieDAOImpl implements MovieDAO {

	private static final Logger logger = LoggerFactory.getLogger(MovieDAOImpl.class);
	
	// 의존 관계 주입 (DI : Defendency Injection)
	@Inject
	SqlSession sqlSession;
	
	private static String namespace = "com.edu.movie.mappers.movieMapper";
	
	//-----------------------------------------------------------------------------------------------------------
	// 영화 예매 좌석 목록 가져오기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<SeatDTO> getSeatList(int movieID) throws Exception {
		
		logger.info("MovieDAOImpl getSeatList(int movieID) Start");
		return sqlSession.selectList(namespace + ".getSeatList", movieID);
		
	} // End - public List<SeatDTO> getSeatList(int movieID)
	
	//-------------------------------------------------------------------------------------------------
	// 좌석 예약
	//-------------------------------------------------------------------------------------------------
	@Override
	public int insertSeatID(int seatID) throws Exception {
		return sqlSession.insert(namespace + ".insertSeatID", seatID);
	}



} // End - public class MovieDAOImpl implements MovieDAO
