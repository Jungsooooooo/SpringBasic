package com.edu.board.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.edu.board.controller.BoardController;
import com.edu.board.dao.BoardDAO;
import com.edu.board.domain.BoardVO;

//-----------------------------------------------------------------------------------------------------------
// public class BoardServiceImpl implements BoardService
//-----------------------------------------------------------------------------------------------------------
@Service
public class BoardServiceImpl implements BoardService {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardDAO boardDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 작성
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public void write(BoardVO boardVO) throws Exception {

		logger.info("BoardServiceImple write() => " + boardVO);
		boardDAO.write(boardVO);

	} // End - public void write(BoardVO boardVO)

	//-----------------------------------------------------------------------------------------------------------
	// 게시물 목록
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public List<BoardVO> list(String tbl) throws Exception {

		logger.info("BoardServiceImple list() => ");
		return boardDAO.list(tbl);
		
	} // End - public List<BoardVO> list(String tbl)

	//-------------------------------------------------------------------------------------------------
	// 글번호에 해당하는 게시글의 상세정보를 가져온다.
	//-------------------------------------------------------------------------------------------------
	@Override
	public BoardVO view(int bno) throws Exception {

		logger.info("BoardServiceImple view() => ");
		return boardDAO.view(bno);
		
	} // End - public BoardVO view(int bno)

	//-------------------------------------------------------------------------------------------------
	// 글번호에 해당하는 게시글의 내용을 수정한다.
	//-------------------------------------------------------------------------------------------------
	@Override
	public void update(BoardVO boardVO) throws Exception {

		logger.info("BoardServiceImpl update(BoardVO boardVO) ==> " + boardVO);
		boardDAO.update(boardVO);
		
	} // End - public void update(BoardVO boardVO)

	//-------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글의 정보를 삭제한다. : POST
	//-------------------------------------------------------------------------------------------------
	@Override
	public void delete(int bno) throws Exception {

		logger.info("BoardServiceImpl delete(int bno) ==> " + bno);
		boardDAO.delete(bno);
		
	} // End - public void delete(int bno)
	
	
	

} // End - public class BoardServiceImpl implements BoardService







