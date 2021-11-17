package com.edu.board2.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.edu.board2.dao.Board2DAO;
import com.edu.board2.domain.BoardDTO;
import com.edu.board2.domain.Criteria;
import com.edu.board2.domain.FileDTO;
import com.edu.board2.domain.SearchCriteria;

import lombok.extern.java.Log;

//-------------------------------------------------------------------------------------------------
// public class Board2ServiceImpl
//-------------------------------------------------------------------------------------------------
@Log
@Service
public class Board2ServiceImpl implements Board2Service {

	@Inject
	Board2DAO board2DAO;
	
	//-------------------------------------------------------------------------------------------------
	// 게시글 등록
	//-------------------------------------------------------------------------------------------------
	@Override
	public int boardInsert(BoardDTO board) throws Exception {
		return board2DAO.boardInsert(board);
	}

	//-------------------------------------------------------------------------------------------------
	// 게시글 목록
	//-------------------------------------------------------------------------------------------------
	@Override
	public List<BoardDTO> boardList() throws Exception {
		//log.info("Board2ServiceImpl boardList() ==> ");
		return board2DAO.boardList();
	}
	
	//-------------------------------------------------------------------------------------------------
	//게시글 목록 보기 (Paging 처리)
	//-------------------------------------------------------------------------------------------------
	@Override
	//public List<BoardDTO> boardListPaging(Criteria cri) throws Exception {
	public List<BoardDTO> boardListPaging(SearchCriteria cri) throws Exception {
		log.info("*** Board2ServiceImpl Criteria ==> " + cri);
		return board2DAO.boardListPaging(cri);
	}

	//-------------------------------------------------------------------------------------------------
	// 전체 게시글 수 구하기 (Paging 처리)
	//-------------------------------------------------------------------------------------------------
	@Override
	public int boardListTotalCount(SearchCriteria cri) throws Exception {
		return board2DAO.boardListTotalCount(cri);
	}

	//-------------------------------------------------------------------------------------------------
	// 게시글 상세 정보
	//-------------------------------------------------------------------------------------------------
	@Override
	public BoardDTO boardDetail(int bno) throws Exception {
		return board2DAO.boardDetail(bno);
	}

	//-------------------------------------------------------------------------------------------------
	// 게시글 수정
	//-------------------------------------------------------------------------------------------------
	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		return board2DAO.boardUpdate(boardDTO);
	}

	//-------------------------------------------------------------------------------------------------
	// 게시글 삭제
	//-------------------------------------------------------------------------------------------------
	@Override
	public int boardDelete(int bno) throws Exception {
		return board2DAO.boardDelete(bno);
	}

	//-------------------------------------------------------------------------------------------------
	// 파일 등록
	//-------------------------------------------------------------------------------------------------
	@Override
	public int fileInsert(FileDTO file) throws Exception {
		return board2DAO.fileInsert(file);
	}

	//-------------------------------------------------------------------------------------------------
	// 파일 상세 정보
	//-------------------------------------------------------------------------------------------------
	@Override
	public FileDTO fileDetail(int bno) throws Exception {
		return board2DAO.fileDetail(bno);
	}

} // End - public class BoardServiceImpl
