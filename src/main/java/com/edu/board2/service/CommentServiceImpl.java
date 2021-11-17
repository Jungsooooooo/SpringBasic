package com.edu.board2.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.edu.board2.dao.CommentDAO;
import com.edu.board2.domain.CommentDTO;

import lombok.extern.java.Log;

//-------------------------------------------------------------------------------------------------
// public class commentServiceImpl
//-------------------------------------------------------------------------------------------------
@Log
@Service
public class CommentServiceImpl implements CommentService {

	@Inject 
	CommentDAO commentDAO;
	
	//-------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------
	@Override
	public List<CommentDTO> commentList(int bno) throws Exception {
		return commentDAO.commentList(bno);
	}

	//-------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------
	@Override
	public int commentInsert(CommentDTO comment) throws Exception {
		return commentDAO.commentInsert(comment);
	}

	//-------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------
	@Override
	public int commentUpdate(CommentDTO comment) throws Exception {
		return commentDAO.commentUpdate(comment);
	}

	//-------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------
	@Override
	public int commentDelete(int cno) throws Exception {
		return commentDAO.commentDelete(cno);
	}

} // End - public class commentServiceImpl
