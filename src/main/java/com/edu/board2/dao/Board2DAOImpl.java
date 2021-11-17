package com.edu.board2.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edu.board2.domain.BoardDTO;
import com.edu.board2.domain.Criteria;
import com.edu.board2.domain.FileDTO;
import com.edu.board2.domain.SearchCriteria;

import lombok.extern.java.Log;

//-------------------------------------------------------------------------------------------------
// public class Board2DAOImpl
//-------------------------------------------------------------------------------------------------
@Log
@Repository
public class Board2DAOImpl implements Board2DAO {

	@Inject // 의존관계 주입(Defendency Injection, DI)
	SqlSession sqlSession;
	
	// namespace 조심하자 : xml의 namespace와 동일하여야 한다.(소대문자 주의할 것)
	// private static String namespace = "com.edu.board2.mappers.boardMapper2";
	private static String namespace = "com.edu.board2.mappers.boardMapper2";
	
	//-------------------------------------------------------------------------------------------------
	// 게시글 등록
	//-------------------------------------------------------------------------------------------------
	@Override
	public int boardInsert(BoardDTO boardDTO) throws Exception {
		log.info("Board2DAOImpl Insert() => " + boardDTO);
		return sqlSession.insert(namespace + ".boardInsert", boardDTO);
	}

	//-------------------------------------------------------------------------------------------------
	// 게시글 목록
	//-------------------------------------------------------------------------------------------------
	@Override
	public List<BoardDTO> boardList() throws Exception {
		log.info("Board2DAOImpl boardList() => ");
		//return sqlSession.selectList(namespace + ".boardList");
		return sqlSession.selectList("com.edu.board2.mappers.boardMapper2.boardList");
	}

	//-------------------------------------------------------------------------------------------------
	//게시글 목록 보기 (Paging 처리) + 검색 추가(Criteria를 SearchCriteria로 변경)
	//-------------------------------------------------------------------------------------------------
	@Override
	@SuppressWarnings("unchecked")
	//public List<BoardDTO>  boardListPaging(Criteria cri) throws Exception {
	public List<BoardDTO>  boardListPaging(SearchCriteria cri) throws Exception {
		log.info("*** Board2DAOImpl Criteria ==> " + cri);
		//log.info("*** boardDAOImpl Criteria ==> " + cri.getPageStart());
		//log.info("*** boardDAOImpl Criteria ==> " + cri.getPerPageNum());
		log.info("***** boardDAOImpl Criteria cri.getSearchType() ==> " + cri.getSearchType());
		
	    return sqlSession.selectList(namespace + ".boardListPaging", cri);
	}

	//-------------------------------------------------------------------------------------------------
	// 전체 게시글 수 구하기 (Paging 처리)
	//-------------------------------------------------------------------------------------------------
	@Override
	public int boardListTotalCount(SearchCriteria cri) throws Exception {
		return sqlSession.selectOne(namespace + ".boardListTotalCount", cri);
	}

	//-------------------------------------------------------------------------------------------------
	// 게시글 상세 조회
	//-------------------------------------------------------------------------------------------------
	@Override
	public BoardDTO boardDetail(int bno) throws Exception {
		log.info("Board2DAOImpl boardDetail() => ");
		return sqlSession.selectOne(namespace + ".boardDetail", bno);
	}

	//-------------------------------------------------------------------------------------------------
	// 게시글 수정
	//-------------------------------------------------------------------------------------------------
	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		log.info("Board2DAOImpl boardUpdate() => " + boardDTO);
		return sqlSession.update(namespace + ".boardUpdate", boardDTO);
	}

	//-------------------------------------------------------------------------------------------------
	// 게시글 삭제
	//-------------------------------------------------------------------------------------------------
	@Override
	public int boardDelete(int bno) throws Exception {
		log.info("Board2DAOImpl boardDelete() => " + bno);
		return sqlSession.delete(namespace + ".boardDelete", bno);
	}

	//-------------------------------------------------------------------------------------------------
	// 파일 등록
	//-------------------------------------------------------------------------------------------------
	@Override
	public int fileInsert(FileDTO file) throws Exception {
		log.info("Board2DAOImpl fileInsert => " + file);
		return sqlSession.insert(namespace + ".fileInsert", file);
	}

	//-------------------------------------------------------------------------------------------------
	// 파일 상세 정보
	//-------------------------------------------------------------------------------------------------
	@Override
	public FileDTO fileDetail(int bno) throws Exception {
		log.info("Board2DAOImple 파일 상세 정보 : " + bno);
		return sqlSession.selectOne(namespace + ".fileDetail", bno);
	}

} // End - public class BoardDAOImpl
