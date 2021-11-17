package com.edu.board2.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.board2.domain.CommentDTO;
import com.edu.board2.service.CommentService;

import lombok.extern.java.Log;

//-------------------------------------------------------------------------------------------------
// public class CommentController
//-------------------------------------------------------------------------------------------------
@Log
@Controller
@RequestMapping("/comment")
public class CommentController {
	
	// @Resource(name="com.edu.board2.service.CommentService")
	@Inject
	CommentService commentService;
	
	//-------------------------------------------------------------------------------------------------
	//댓글 등록
	//-------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping("/insert")
	private int mCommentServiceInsert(@RequestParam int bno, @RequestParam String content) throws Exception {
		System.out.println("CommentController insert() Start.....");
		System.out.println("bno["+bno+"]");
		System.out.println("content["+content+"]");
		
		CommentDTO comment = new CommentDTO();
		comment.setBno(bno);
		comment.setContent(content);
		comment.setWriter("user");
		
		return commentService.commentInsert(comment);
	}

	//-------------------------------------------------------------------------------------------------
	//댓글 리스트
	//-------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping("/list/{bno}") 
    private List<CommentDTO> mCommentServiceList(@PathVariable int bno, Model model) throws Exception{
		log.info("CommentController mCommentServiceList().....");
		System.out.println(commentService.commentList(bno));
       return commentService.commentList(bno);
    }
	
	//-------------------------------------------------------------------------------------------------
	//댓글 삭제
	//-------------------------------------------------------------------------------------------------
	@RequestMapping("/delete/{cno}")
	@ResponseBody
	private int mCommentServiceDelete(@PathVariable int cno) throws Exception {
		System.out.println("commentService Delete.....");
		return commentService.commentDelete(cno);
	}
	
	//-------------------------------------------------------------------------------------------------
	//댓글 수정
	//-------------------------------------------------------------------------------------------------
	@RequestMapping("/update")
	@ResponseBody
	private int mCommentServiceUpdateProc(@RequestParam int cno, @RequestParam String content) throws Exception {
		System.out.println("commentService UpdateProc.....");

		CommentDTO comment = new CommentDTO();
		comment.setCno(cno);
		comment.setContent(content);
		
		return commentService.commentUpdate(comment);
	}
	
} // End - public class CommentController

