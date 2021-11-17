package com.edu.board2.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.edu.board2.domain.BoardDTO;
import com.edu.board2.domain.Criteria;
import com.edu.board2.domain.FileDTO;
import com.edu.board2.domain.PageMaker;
import com.edu.board2.domain.SearchCriteria;
import com.edu.board2.service.Board2Service;

import lombok.extern.java.Log;


//-------------------------------------------------------------------------------------------------
// 게시글에 파일을 첨부하여 등록할 때 발생하는 문제점
// ERROR: jdbc.audit - 2. PreparedStatement.execute() INSERT INTO FILES (FNO, BNO, FILENAME, FILEORINAME, FILEURL) VALUES (0, 0, 'K9qKOzKkTYl6ELm4lTAU1qSw4josqiKx.jpg', 
// '아이 더워 01.jpg', 'C:/upload/') 
// java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`bookshopdb`.`files`, CONSTRAINT `files_ibfk_1` FOREIGN KEY (`bno`) REFERENCES `board` (`bno`))
// 게시글을 등록할 때 board table에  useGeneratedKeys="true" keyProperty="bno"를 사용하지 않으면
// 파일테이블에 데이터를 등록할 때 참조할 bno가 없기 때문에 Error가 발생한다.
//-------------------------------------------------------------------------------------------------


//-------------------------------------------------------------------------------------------------
// public class Board2Controller
// MemberDAOImpl에 @Repository 어노테이션이 설정되어 있더라도 스프링에서 해당 패키지를 스캔하지 않으면
// 스프링 빈으로 등록이 되지 않는다. 그래서 root-context.xml에 Bean을 등록해야 한다.
//-------------------------------------------------------------------------------------------------
@Log
@Controller
@RequestMapping("/board2")
public class Board2Controller {
	
	// @Resource(name="com.edu.board2.service.BoardService2")
	@Inject
	Board2Service board2Service;
	
	//-------------------------------------------------------------------------------------------------
	//웹 브라우저에서 http://localhost:8088/board2/insert 로 호출한다.
	//-------------------------------------------------------------------------------------------------
	@RequestMapping("/insert")
	private String boardInsertForm() {
		System.out.println("Controller insert......");
		return "/board2/insert";
	}
	
	//-------------------------------------------------------------------------------------------------
	// Controller 에서 Multipart 를 @RequestPart 어노테이션을 통해 별도의 설정없이 사용할 수 있다.
	//-------------------------------------------------------------------------------------------------
	@RequestMapping("/insertProc")
	private String boardInsertProc(HttpServletRequest request, @RequestPart MultipartFile files) throws Exception {
		
		//게시글 등록 화면에서 입력한 값들을 실어나르기 위해 BoardVO를 생성한다.
		BoardDTO board 	= new BoardDTO();
		FileDTO	file	= new FileDTO();
		
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		board.setWriter (request.getParameter("writer"));
		
		if(files.isEmpty()) {	// 업로드할 파일이 없는 경우
			board2Service.boardInsert(board);	// 게시글만 올린다.
		} else {	// 업로드할 파일이 있는 경우
			//FilenameUtils : commons-io defendency를 사용.
			String 	fileName = files.getOriginalFilename();
			String 	fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
			File	destinationFile;
			String	destinationFileName;
			// fileUrl = "uploadFiles 폴더의 위치";
			// uploadFiles 폴더의 위치 확인 : uploadFiles 우클릭 -> Properties -> Resource - > Location
			// String	fileUrl = "C:/workspaceSP/sboard/src/main/webapp/WEB-INF/uploadFiles/"; 
			String	fileUrl = "C:/upload/"; 
			
			do {
				destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
				destinationFile     = new File(fileUrl + destinationFileName);
			} while(destinationFile.exists());
			
			
			// MultipartFile.transferTo() : 요청 시점의 임시 파일을 로컬 파일 시스템에 영구적으로 복사해준다.
			destinationFile.getParentFile().mkdirs();
			files.transferTo(destinationFile);
			// 파일 올리기 완료
			
			board2Service.boardInsert(board);	// 게시글 올리기
			
			// 파일관련 자료를 Files 테이블에 등록한다.
			file.setBno(board.getBno());
			file.setFileName(destinationFileName);
			file.setFileOriName(fileName);
			file.setFileUrl(fileUrl);
			
			board2Service.fileInsert(file);
		}
		
		return "redirect:/board2/list";
	}
	
	//-------------------------------------------------------------------------------------------------
	//게시글 목록 보여주기
	//-------------------------------------------------------------------------------------------------
	@RequestMapping("/list")
	private String boardList(Model model) throws Exception {
		log.info("board2 BoardController list ==> ");
		
		//게시글 목록 보여주기 화면으로 가기 전에 보여줄 데이터를 가져와서 model에 담든다.
		List<BoardDTO> boardList = board2Service.boardList();
		model.addAttribute("list", boardList);
		//model.addAttribute("list", boardService2.boardList());
		return "/board2/list";
	}
	
	//-------------------------------------------------------------------------------------------------
	//게시글 번호에 해당하는 상세정보화면
	//-------------------------------------------------------------------------------------------------
	@RequestMapping("/detail/{bno}")
	private String boardDetail(@PathVariable int bno, Model model) throws Exception {
		//bno에 해당하는 자료를 찾아와서 model에 담는다.
		model.addAttribute("detail", board2Service.boardDetail(bno));	//게시글의 정보를 가져와서 저장한다.
		model.addAttribute("files" , board2Service.fileDetail(bno));	//파일의 정보를 가져와서 저장한다.
		return "/board2/detail";
	}
	
	//-------------------------------------------------------------------------------------------------
	//댓글을 달 수 있는 상세 정보 화면
	// http://localhost:8090/board2/detailComment/1
	//-------------------------------------------------------------------------------------------------
	@RequestMapping("/detailComment/{bno}")
	private String boardDetailComment(@PathVariable int bno, Model model) throws Exception {
		//bno에 해당하는 자료를 찾아와서 model에 담는다.
		model.addAttribute("detail", board2Service.boardDetail(bno));	//게시글의 정보를 가져와서 저장한다.
		model.addAttribute("files" , board2Service.fileDetail(bno));	//파일의 정보를 가져와서 저장한다.
		return "/board2/detailComment";
	}
	
	//-------------------------------------------------------------------------------------------------
	//게시글 수정 화면
	//-------------------------------------------------------------------------------------------------
	@RequestMapping("/update/{bno}")
	private String boardUpdateForm(@PathVariable int bno, Model model) throws Exception {
		model.addAttribute("detail", board2Service.boardDetail(bno));
		return "/board2/update";
	}
	
	//-------------------------------------------------------------------------------------------------
	//게시글 수정화면에서 수정한 자료를 업데이트 한다.
	//-------------------------------------------------------------------------------------------------
	@RequestMapping("/updateProc")
	private String boardUpdateProc(HttpServletRequest request) throws Exception {
		//서비스에게 넘계줄 자료를 저장한다.
		BoardDTO board = new BoardDTO();
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		board.setBno(Integer.parseInt(request.getParameter("bno")));
		
		board2Service.boardUpdate(board);
		
		return "redirect:/board2/detail/" + request.getParameter("bno");
	}
	
	//-------------------------------------------------------------------------------------------------
	//글 번호에 해당하는 자료를 삭제한다.
	//-------------------------------------------------------------------------------------------------
	@RequestMapping("/delete/{bno}")
	private String boardDelete(@PathVariable int bno) throws Exception {
		board2Service.boardDelete(bno);
		return "redirect:/board2/list";
	}
	
	//-------------------------------------------------------------------------------------------------
	//파일 다운로드
	//-------------------------------------------------------------------------------------------------
	@RequestMapping("/fileDown/{bno}")
    private void fileDown(@PathVariable int bno, HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        request.setCharacterEncoding("UTF-8");
        FileDTO fileDTO = board2Service.fileDetail(bno);
        
        //파일 업로드된 경로 
        try {
            String fileUrl = fileDTO.getFileUrl();
             fileUrl += "/";
            String savePath = fileUrl;
            String fileName = fileDTO.getFileName();
            
            //실제 내보낼 파일명 
            String oriFileName = fileDTO.getFileOriName();
            InputStream in = null;
            OutputStream os = null;
            File file = null;
            boolean skip = false;
            String client = "";
            
            
            //파일을 읽어 스트림에 담기  
            try{
                file = new File(savePath, fileName);
                in = new FileInputStream(file);
            } catch (FileNotFoundException fe) {
            	skip = true;
            }
            
            client = request.getHeader("User-Agent");
            
            //파일 다운로드 헤더 지정 
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Description", "JSP Generated Data");
            
            if (!skip) {
                // IE
                if (client.indexOf("MSIE") != -1) {
                    response.setHeader("Content-Disposition", "attachment; filename=\""
                            + java.net.URLEncoder.encode(oriFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
                    // IE 11 이상.
                } else if (client.indexOf("Trident") != -1) {
                    response.setHeader("Content-Disposition", "attachment; filename=\""
                            + java.net.URLEncoder.encode(oriFileName, "UTF-8").replaceAll("\\+", "\\ ") + "\"");
                } else {
                    // 한글 파일명 처리
                    response.setHeader("Content-Disposition",
                            "attachment; filename=\"" + new String(oriFileName.getBytes("UTF-8"), "ISO8859_1") + "\"");
                    response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
                }
                response.setHeader("Content-Length", "" + file.length());
                os = response.getOutputStream();
                byte b[] = new byte[(int) file.length()];
                int leng = 0;
                while ((leng = in.read(b)) > 0) {
                    os.write(b, 0, leng);
                }
            } else {
                response.setContentType("text/html;charset=UTF-8");
                System.out.println("<script language='javascript'>alert('파일을 찾을 수 없습니다');history.back();</script>");
            }
            in.close();
            os.close();
        } catch (Exception e) {
            System.out.println("ERROR : " + e.getMessage());
        }
        
    }
	

	//-------------------------------------------------------------------------------------------------
	//게시글 목록 보여주기(Date Picker)
	//-------------------------------------------------------------------------------------------------
	@RequestMapping("/listDate")
	private String listDate(Model model) throws Exception {
		//게시글 목록 보여주기 화면으로 가기 전에 보여줄 데이터를 가져와서 model에 담든다.
		model.addAttribute("list", board2Service.boardList());
		return "/board2/listDate";
	}
	
	//-------------------------------------------------------------------------------------------------
	//게시글 목록 보여주기(Date Picker)
	//-------------------------------------------------------------------------------------------------
	@RequestMapping("/normal")
	private String normal(Model model) throws Exception {
		//게시글 목록 보여주기 화면으로 가기 전에 보여줄 데이터를 가져와서 model에 담든다.
		model.addAttribute("list", board2Service.boardList());
		return "/board2/normal";
	}
	
	//-------------------------------------------------------------------------------------------------
	//게시글 목록 보여주기(Paging 처리)
	//-------------------------------------------------------------------------------------------------
	@RequestMapping(value="/boardList", method = RequestMethod.GET)
	public ModelAndView openBoardList(SearchCriteria cri) throws Exception {
	        
		log.info("---------------------------------------------------------------------");
		log.info("Board2Controller openBoardList CRI ==> " + cri);
		log.info("---------------------------------------------------------------------");

		ModelAndView mav = new ModelAndView("/board2/boardList");
	        
	    PageMaker pageMaker = new PageMaker();
	    // cri <= 검색조건과 검색할 값
	    pageMaker.setCri(cri);
	    // cri(검색할 조건과 값)을 가지고 검색한 총 건수를 TotalCount 변수에 저장한다.
	    pageMaker.setTotalCount(board2Service.boardListTotalCount(cri));
	        
	    log.info("Board2Controller openBoardList pageMaker.getTotalCount(cri) ==> " + pageMaker.getTotalCount());

	    List<BoardDTO>  list = board2Service.boardListPaging(cri);
	    mav.addObject("list", list);
	    mav.addObject("pageMaker", pageMaker);
	        
	    return mav;
	        
	}

	//-------------------------------------------------------------------------------------------------
	//게시글 목록 보여주기(Paging 처리)
	//-------------------------------------------------------------------------------------------------
	/*
	@RequestMapping(value="/boardList0", method = RequestMethod.GET)
	public ModelAndView openBoardList0(Criteria cri) throws Exception {
	        
		log.info("---------------------------------------------------------------------");
		log.info("Board2Controller openBoardList CRI ==> " + cri);
		log.info("---------------------------------------------------------------------");

		ModelAndView mav = new ModelAndView("/board2/boardList");
	        
	    PageMaker pageMaker = new PageMaker();
	    pageMaker.setCri(cri);
	    pageMaker.setTotalCount(board2Service.boardListTotalCount());
	        
	    List<BoardDTO>  list = board2Service.boardListPaging(cri);
	    mav.addObject("list", list);
	    mav.addObject("pageMaker", pageMaker);
	        
	    return mav;
	}
	*/
	

	
} // End - public class BoardController2























