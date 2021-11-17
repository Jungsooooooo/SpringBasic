package com.edu.basic.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.edu.basic.util.MediaUtils;
import com.edu.basic.util.UploadFileUtils;

//-----------------------------------------------------------------------------------------------------------
// 파일을 영역에 떨어뜨려 파일 올리기에 사용되는 컨트롤러
//-----------------------------------------------------------------------------------------------------------
@Controller
public class AjaxUploadController {

	//-----------------------------------------------------------------------------------------------------------
	// 로그 메시지를 사용하기 위한 변수
	//-----------------------------------------------------------------------------------------------------------
	private static final Logger logger = LoggerFactory.getLogger(AjaxUploadController.class);
	
	//-----------------------------------------------------------------------------------------------------------
	// 업로드될 디렉토리는 servlet-context.xml에 설정이 되어있다.
	//-----------------------------------------------------------------------------------------------------------
	@Resource(name = "uploadPath")
	String uploadPath;
	
	//-----------------------------------------------------------------------------------------------------------
	// 업로드 파일 페이지로 이동한다.
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/upload/uploadAjax", method=RequestMethod.GET)
	public String uploadAjax() {
		
		logger.info("AjaxUploadController uploadAjax() Start.....");
		return "/upload/uploadAjax";
		
	} // End - public String uploadAjax()
	
	//-----------------------------------------------------------------------------------------------------------
	// 파일을 Drag해서 전송구역에 떨어뜨렸을 경우 진행되는 메서드
	// 업로드한 파일은 MultipartFile 변수에 저장된다.
	// @ResponseBody : jsp로 넘어가는 것이 아니라 데이터 자체를 돌려주는 것이다.
	// ResponseEntity<String> : 메시지와 에러코드를 같이 돌려준다.
	//-----------------------------------------------------------------------------------------------------------
	@ResponseBody	// json형식으로 리턴된다.
	@RequestMapping(value="/upload/uploadAjax", method=RequestMethod.POST, produces="text/plain;charset=utf-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		
		// 파일 정보를 로그에 출력한다.
		logger.info("파일의 이름 : " + file.getOriginalFilename());
		logger.info("파일의 크기 : " + file.getSize());
		logger.info("컨텐트 타입 : " + file.getContentType());
		
		return new ResponseEntity<String> (
				UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.OK);
		
	} // End - public ResponseEntity<String> uploadAjax(MultipartFile file)
	
	//-------------------------------------------------------------------------------------------------
	// 이미지를 표시하는 메서드 
	//-------------------------------------------------------------------------------------------------
	@ResponseBody // View가 아닌 data를 리턴한다.
	@RequestMapping("/upload/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		
		// 서버의 파일을 다운로드하기 위한 스트림
		InputStream				in		= null; 
		ResponseEntity<byte[]>	entity	= null;
		
		try {
			// 확장자 검사
			String 		formatName 	= fileName.substring(fileName.lastIndexOf(".") + 1); 
			MediaType	mType		= MediaUtils.getMediaType(formatName);
			
			// 헤더 구성 객체를 생성한다.
			HttpHeaders	headers		= new HttpHeaders();
			
			// InputStream을 생성한다.
			in	= new FileInputStream(uploadPath + fileName);
			
			if(mType != null) {	// 이미지 파일이면
				headers.setContentType(mType);
			} else {			// 이미지 파일이 아니라면
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				
				// 다운로드용 컨텐트 타입
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // 컨텐트 타입
				
				// 파일이름에 한글이 들어간 경우 스트링.getBytes("언어셑") 스트링을 바이트배열로 변환
				// new String(바이트 배열, "언어셑") 문자열의 인코딩을 변경한다.
				
				// 아래의 2줄과 동일하다.
				//headers.add("Content-Disposition", 
				//			"attachment; filename=\"" + new String(fileName.getBytes("utf-8"), "iso-8859-1") + "\"");
				
				// fileName을 utf-8로 읽어서 서유럽언어 형식으로 바꾸어서 넘긴다.
				fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
				headers.add("Content-Disposition", "attachment; filename=\"" + "\"");
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			if(in != null)
				in.close();	// 스트림 닫기
		}
		return entity;
		
	} // End - public ResponseEntity<byte[]> displayFile(String fileName)
	
	//-------------------------------------------------------------------------------------------------
	// 파일을 삭제하는 메서드
	//-------------------------------------------------------------------------------------------------
	@ResponseBody
	@RequestMapping(value="/upload/deleteFile", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName) {
		
		// 확장자 검사
		// fileName에는 이미지 파일의 경우 썸네일 파일 이름이 넘어온다.
		String		formatName	= fileName.substring(fileName.lastIndexOf(".") + 1);
		MediaType	mType		= MediaUtils.getMediaType(formatName);
		
		// \2021\08\05\5849d198-b70c-40a7-ae9d-76cebe78efba_cat01.jpg
		// 이미지 파일이면 썸네일 파일을 삭제한다.
		if(mType != null) {
			String	front	= fileName.substring(0, 12);
			String	end		= fileName.substring(14);
			
			// 썸네일을 삭제한다.
			new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
		}
		
		// 원본 파일을 삭제한다.
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		
		// DB에 데이터가 있다면, 데이터를 삭제해야 한다.
		// boardService.deleteFile(fileName);
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
		
	} // End - public ResponseEntity<String> deleteFile(String fileName)

	
} // End - public class AjaxUploadController



















