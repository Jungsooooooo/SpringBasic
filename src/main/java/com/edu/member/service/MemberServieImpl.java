package com.edu.member.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.edu.member.controller.MemberController;
import com.edu.member.dao.MemberDAO;
import com.edu.member.dto.MemberDTO;

//-----------------------------------------------------------------------------------------------------------
// public class MemberServieImpl implements MemberService
//-----------------------------------------------------------------------------------------------------------
@Service
public class MemberServieImpl implements MemberService {

	private static final Logger logger = LoggerFactory.getLogger(MemberServieImpl.class);
	
	//-----------------------------------------------------------------------------------------------------------
	// 컨트롤러는 서비스를 호출하고, 서비스는 DAO를 호출하고, DAO는 SqlSession을 호출한다.
	//-----------------------------------------------------------------------------------------------------------
	@Inject
	private MemberDAO memberDAO;

	//-------------------------------------------------------------------------------------------------
	// public List<MemberDTO> selectMember() : 회원 목록
	//-------------------------------------------------------------------------------------------------
	@Override
	public List<MemberDTO> selectMember() throws Exception {
		return memberDAO.selectMember();
	} // End - public List<MemberDTO> selectMember()

	//-------------------------------------------------------------------------------------------------
	// 회원 상세 정보
	//-------------------------------------------------------------------------------------------------
	@Override
	public MemberDTO view(String id) throws Exception {
		logger.info("MemberServiceImpl view(String id).....");
		return memberDAO.view(id);
	}

	//-------------------------------------------------------------------------------------------------
	// 회원 정보 수정
	//-------------------------------------------------------------------------------------------------
	@Override
	public void update(MemberDTO memberDTO) throws Exception {
		logger.info("MemberServiceImpl update(MemberDTO memberDTO).....");
		memberDAO.update(memberDTO);
	}

	//-------------------------------------------------------------------------------------------------
	// 회원 정보 삭제
	//-------------------------------------------------------------------------------------------------
	@Override
	public void delete(String id) throws Exception {
		logger.info("MemberServiceImpl delete(String id).....");
		memberDAO.delete(id);
	}

	//-----------------------------------------------------------------------------------------------------------
	// 회원 아이디 중복 검사
	//-----------------------------------------------------------------------------------------------------------
	@Override
	public int idCheck(MemberDTO memberDTO) throws Exception {
		
		logger.info("MemberServieImpl 아이디 중복 검사()");
		int result = memberDAO.idCheck(memberDTO);
		return result;
		
	} // End - public int idCheck(MemberDTO memberDTO)
	
	//-------------------------------------------------------------------------------------------------
	// 회원 가입
	//-------------------------------------------------------------------------------------------------
	@Override
	public int memberInsert(MemberDTO memberDTO) throws Exception {
		logger.info("MemberServiceImpl memberInsert(MemberDTO memberDTO).....");
		
		if(!memberDTO.getPasswd().equals(memberDTO.getRepasswd())) {
			return -1;
		}
		int result = memberDAO.memberInsert(memberDTO);
		return result;
	}

	//-------------------------------------------------------------------------------------------------
	// 로그인
	//-------------------------------------------------------------------------------------------------
	@Override
	public MemberDTO login(MemberDTO memberDTO) throws Exception {
		return memberDAO.login(memberDTO);
	}

	//-------------------------------------------------------------------------------------------------
	// 회원가입 POST (Ajax)
	//-------------------------------------------------------------------------------------------------
	@Override
	public int register(MemberDTO memberDTO) throws Exception {
		return memberDAO.register(memberDTO);
	}
	


} // End - public class MemberServieImpl implements MemberService
