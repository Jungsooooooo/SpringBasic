package com.edu.basic.controller;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

//-----------------------------------------------------------------------------------------------------------
// 파일 올리기 컨트롤러
//-----------------------------------------------------------------------------------------------------------
@Controller
public class UploadController {

	//-----------------------------------------------------------------------------------------------------------
	// 로그 메시지를 위한 설정
	//-----------------------------------------------------------------------------------------------------------
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	//-----------------------------------------------------------------------------------------------------------
	// servlet-context.xml에서 선언한 String Bean을 참조한다.
	//-----------------------------------------------------------------------------------------------------------
	@Resource(name = "uploadPath")
	String uploadPath; // 공통으로 사용하기 위해서
	// String uploadPath = "c:/upload"; <= 직접 기술해도 된다.
	
	//-----------------------------------------------------------------------------------------------------------
	// 업로드 화면 페이지를 이동한다.(불러들인다.)
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/upload/uploadFile", method=RequestMethod.GET)
	public String getUploadFile() {
		
		logger.info("UploadController getUploadFile().....");
		return "upload/uploadFile";
		
	} // End - public String getUploadFile()
	
	//-----------------------------------------------------------------------------------------------------------
	// 업로드된 파일을 처리하는 메서드
	// MultipartFile file : 업로드된 파일이 저장된다.(임시 경로)
	// ModelAndView : Model(데이터) 과 View(화면)을 동시에 사용한다.
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/upload/uploadFile", method=RequestMethod.POST)
	public ModelAndView uploadFile(MultipartFile file, ModelAndView mav) throws Exception {
		
		logger.info("파일의 이름 : " + file.getOriginalFilename());
		logger.info("파일의 크기 : " + file.getSize());
		logger.info("컨텐트 타입 : " + file.getContentType());
		
		String	savedName	= file.getOriginalFilename();
		savedName			= uploadFileMake(savedName, file.getBytes());
		
		mav.setViewName("upload/uploadResult");	// 뷰의 이름 => 이동할 페이지
		mav.addObject("savedName", savedName);	// 전달할 데이터
		return mav;	// upload/uploadResult.jsp로 포워딩된다.
		
	} // End - public ModelAndView uploadFIile(MultipartFile file, ModelAndView mav)
	
	//-----------------------------------------------------------------------------------------------------------
	// 업로드된 파일의 이름이 중복되지 않도록 처리하는 메서드
	//-----------------------------------------------------------------------------------------------------------
	String uploadFileMake(String originalName, byte[] fileData) throws Exception {
	
		// UUID(Universal Unique Identifier(범용 고유 식별자) : 코드를 랜덤하게 만든다.
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		logger.info("UploadFileMake uid ==> " + savedName);
		// 1cae9dae-e09b-4f1b-b8a7-fba8f291af40_cat01.jpg
		// 83082e1b-35e3-40f7-a193-4e652d8f427c_cat01.jpg
		
		// new File(디렉토리, 파일이름)
		File target = new File(uploadPath, savedName);
		
		// 첨부파일을 임시디렉토리에서 사용자가 지정한 디렉토리로 복사한다.
		FileCopyUtils.copy(fileData, target);
		
		return savedName; // 복사한 파일이름을 되돌려 준다.
		
	} // End - String uploadFileMake(String originalName, byte[] fileData)
	


} // End - public class UploadController












