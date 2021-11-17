package com.edu.basic.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.edu.basic.dto.BookTypeDTO;
import com.edu.basic.service.BookTypeServiceImpl;

//-----------------------------------------------------------------------------------------------------------
// public class BookTypeDAOImpl implements BookTypeDAO
//-----------------------------------------------------------------------------------------------------------
@Repository
public class BookTypeDAOImpl implements BookTypeDAO {

	private static final Logger logger = LoggerFactory.getLogger(BookTypeDAOImpl.class);
	
	//-----------------------------------------------------------------------------------------------------------
	// SqlSession 을 주입받는다.
	//-----------------------------------------------------------------------------------------------------------
	@Inject
	private SqlSession sqlSession;
	
	//-----------------------------------------------------------------------------------------------------------
	// 연결할 매퍼의 정보
	//-----------------------------------------------------------------------------------------------------------
	private	static final String Namespace = "com.edu.basic.dto.booktypeMapper";
	
	//-----------------------------------------------------------------------------------------------------------
	// public List<BookTypeDTO> selectBookType()
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<BookTypeDTO> selectBookType() throws Exception {
		
		logger.info("BookTypeDAOImpl selectBookType() Start....");
		return sqlSession.selectList(Namespace + ".selectBookType");
	} // End - public List<BookTypeDTO> selectBookType()

} // End - public class BookTypeDAOImpl implements BookTypeDAO




