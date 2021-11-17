package com.edu.board2.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//-----------------------------------------------------------------------------------------------------------
// public class SearchCriteria
//-----------------------------------------------------------------------------------------------------------
@Getter
@Setter
@ToString
public class SearchCriteria extends Criteria {

    private String searchType;	// 검색 타입
    private String keyword;		// 검색 키워드

} // End - public class SearchCriteria
