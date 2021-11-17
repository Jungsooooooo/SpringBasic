package com.edu.basic.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

//-----------------------------------------------------------------------------------------------------------
// public class MediaUtils
//-----------------------------------------------------------------------------------------------------------
public class MediaUtils {

	//-----------------------------------------------------------------------------------------------------------
	// static 으로 되어 있기 때문에 프로그램을 실행할 때
	// private static Map<String, MediaType> mediaMap; 과 static{} 은 바로 메모리에 로딩된다.
	//-----------------------------------------------------------------------------------------------------------
	private static Map<String, MediaType> mediaMap;
	
	// 클래스를 로딩할 때 제일 먼저 실행되는 코드
	static {
		mediaMap	= new HashMap<>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 이미지 파일인지 아닌지 구분하게 하는 메서드
	// 이미지 파일을 업로드하면 썸네일 파일을 만들고, 이미지 파일이 아니면 그대로 업로드한다.
	// getMediaType(String type)을 호출하여 위의 static{}에 있으면 이미지 파일이고, 아니면 일반파일이다. 
	//-----------------------------------------------------------------------------------------------------------
	public static MediaType getMediaType(String type) {
		
		return mediaMap.get(type.toUpperCase());
		
	}

} // End - public class MediaUtils




















