package com.edu.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.board.domain.BoardVO;
import com.edu.board.service.BoardService;

//-------------------------------------------------------------------------------------------------
// 게시글 관련 컨트롤러
// @RequestMapping("/board") => url의 시작이 /board로 시작하는 것을 담당하는 컨트롤러
//-------------------------------------------------------------------------------------------------
@Controller
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService boardService;

	//-------------------------------------------------------------------------------------------------
	// 게시글 작성 : GET
	//-------------------------------------------------------------------------------------------------
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public void getWrite() throws Exception {
		logger.info("BoardController getWrite() GET");
	}

	//-------------------------------------------------------------------------------------------------
	// 게시글 작성 : POST
	// public String postWrite(BoardVO boardVO)
	// 	==>	write.jsp 페이지의 input들의 name이 BoardVO에 정의된 이름과 같으면 알아서 boardVO에 들어온다.
	//-------------------------------------------------------------------------------------------------
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String postWrite(BoardVO boardVO) throws Exception {
		
		logger.info("BoardController postWrite() => " + boardVO);
		boardService.write(boardVO);
		
		return "redirect:/board/list";
		
	} // End - public String postWrite(BoardVO boardVO)
	
	//-------------------------------------------------------------------------------------------------
	// 게시물 목록
	//-------------------------------------------------------------------------------------------------
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void getList(Model model) throws Exception {
		
		logger.info("BoardController getList() ");
		
		List<BoardVO> list = null;
		list = boardService.list("sboard"); // 작업할 테이블명을 매개변수로 넘겨준다.
		logger.info("BoardController getList() return Value ==> " + list);
		
		model.addAttribute("list", list);
		
	} // End - public void getList(Model model)
	
	//-------------------------------------------------------------------------------------------------
	// 글번호에 해당하는 게시글의 상세정보를 가져온다.
	//-------------------------------------------------------------------------------------------------
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public void getView(@RequestParam("bno") int bno, Model model) throws Exception {
		
		logger.info("BoardController getView(bno) ==> " + bno);
		
		BoardVO boardVO = null;
		boardVO = boardService.view(bno);
		logger.info("BoardController return Value ==> " + boardVO);
		
		model.addAttribute("view", boardVO);
		
	} // End - public void getView(@RequestParam("bno") int bno, Model model)

	//-------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글의 정보를 수정한다. : GET
	//-------------------------------------------------------------------------------------------------
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public void getUpdate(@RequestParam("bno") int bno, Model model) throws Exception {
		
		logger.info("BoardController getUpdate(bno) ==> " + bno);
		BoardVO boardVO = boardService.view(bno);
		logger.info("BoardController return Value ==> " + boardVO);
		
		model.addAttribute("view", boardVO);
		
	} // End - public void getUpdate(@RequestParam("bno") int bno, Model model)
	
	//-------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글의 정보를 수정한다. : POST
	//-------------------------------------------------------------------------------------------------
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String postUpdate(BoardVO boardVO) throws Exception {
		
		logger.info("BoardController postUpdate(BoardVO boardVO) ==> " + boardVO);
		boardService.update(boardVO);
		
		return "redirect:/board/list";
	} // End - public String postUpdate(BoardVO boardVO)

	//-------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글의 정보를 삭제한다. : GET
	//-------------------------------------------------------------------------------------------------
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public void getDelete(@RequestParam("bno") int bno, Model model) throws Exception {
		
		logger.info("BoardController getDelete(bno) ==> " + bno);
		BoardVO boardVO = null;
		boardVO = boardService.view(bno);
		
		model.addAttribute("view", boardVO);
		
	} // End - public void getDelete(@RequestParam("bno") int bno, Model model)

	//-------------------------------------------------------------------------------------------------
	// 게시글 번호에 해당하는 게시글의 정보를 삭제한다. : POST
	//-------------------------------------------------------------------------------------------------
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String postDelete(BoardVO boardVO) throws Exception {
		
		logger.info("BoardController postDelete(BoardVO boardVO) ==> " + boardVO);
		boardService.delete(boardVO.getBno());
		
		// /WEB-INF/views + /board/list + .jsp
		return "redirect:/board/list";
		
	}
	
	
} // End - public class BoardController

















