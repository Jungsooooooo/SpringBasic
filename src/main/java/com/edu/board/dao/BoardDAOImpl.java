package com.edu.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.edu.board.domain.BoardVO;

//-----------------------------------------------------------------------------------------------------------
// public class BoardDAOImpl implements BoardDAO
//-----------------------------------------------------------------------------------------------------------
@Repository
public class BoardDAOImpl implements BoardDAO {

	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	// 의존관계 주입(DI : Defendency Injection)
	@Inject
	SqlSession sqlSession;
	
	// namespace 이름 정확하게 작성할 것 : 조심하자!
	private static String namespace = "com.edu.board.mappers.boardMapper";
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 작성
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public void write(BoardVO boardVO) throws Exception {

		logger.info("BoardDAOImpl write() => " + boardVO);
		sqlSession.insert(namespace + ".insert", boardVO);

	} // End - public void write(BoardVO boardVO)

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 목록
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<BoardVO> list(String tbl) throws Exception {

		logger.info("BoardDAOImpl list() => ");
		return sqlSession.selectList(namespace + ".list", tbl);
		
	} // ENd - public List<BoardVO> list(String tbl)

	//-----------------------------------------------------------------------------------------------------------
	// 글번호에 해당하는 게시글의 상세정보를 가져온다.
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public BoardVO view(int bno) throws Exception {

		logger.info("BoardDAOImpl view(bno) => " + bno);
		return sqlSession.selectOne(namespace + ".view", bno);
		
	} // End - public BoardVO view(int bno)

	//-------------------------------------------------------------------------------------------------
	// 글번호에 해당하는 게시글의 내용을 수정한다.
	//-------------------------------------------------------------------------------------------------
	@Override
	public void update(BoardVO boardVO) throws Exception {

		logger.info("BoardDAOImpl update(BoardVO boardVO) => " + boardVO);
		sqlSession.update(namespace + ".update", boardVO);
		
	}

	//-------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글의 정보를 삭제한다. : POST
	//-------------------------------------------------------------------------------------------------
	@Override
	public void delete(int bno) throws Exception {

		logger.info("BoardDAOImpl delete(int bno) => " + bno);
		sqlSession.delete(namespace + ".delete", bno);
		
	}
	
	

	
} // End - public class BoardDAOImpl implements BoardDAO















