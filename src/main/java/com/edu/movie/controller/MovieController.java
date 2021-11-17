package com.edu.movie.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.movie.dto.SeatDTO;
import com.edu.movie.dto.SeatStatusDTO;
import com.edu.movie.service.MovieService;

//-----------------------------------------------------------------------------------------------------------
// public class MovieController
//-----------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping("/movie")
public class MovieController {

	private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
	
	// 의존 관계 주입 (DI : Defendency Injection)
	@Inject
	MovieService	movieService;
	
	//-----------------------------------------------------------------------------------------------------------
	// 영화관 좌석 예약 화면 보이기 : GET
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/seatReservation", method=RequestMethod.GET)
	public String seatReservation(@ModelAttribute("movieID") String mID, Model model) throws Exception {
		
		logger.info("영화 좌석 예약 화면.....");
		
		int	seatTotal	= 200;	// 좌석의 수
		int reserveOK	= 0;
		int	reserveNO	= 0;
		int	movieID		= 0;
		
		// 영화 아이디 정보가 넘어 왔다면
		if(mID != null) {
			movieID = Integer.parseInt(mID);
		}
		
		if(movieID < 0) {
			logger.info("영화 정보가 유효한 값이 아닙니다.");
		} else {
			// 예약된 좌석의 정보를 추출한다.
			List<SeatDTO> list = movieService.getSeatList(movieID);
			
			ArrayList<SeatStatusDTO> result = new ArrayList<SeatStatusDTO>();
			
			// 영화관의 좌석수(seatTotal)가 200개라 성정하고 작성한다.
			// 좌석수가 변동이 된다면 변수를 사용해야 한다.
			// 좌석 예약 테이블(reservation_seat)에 값이 있으면 true, 아니면 false로 만든다.
			for(int seatNumber = 1; seatNumber <= seatTotal; seatNumber++) { // 200개의 좌석에 대한 작업
				boolean findSeatID = false;
				for(int j = 0; j < list.size(); j++) {	// 예약된 좌석 수만큼 반복 작업한다.
					if(seatNumber == list.get(j).getSeatID()) {	// 예약된 좌석번호와 예약여부 배열변수가 맞으면
						findSeatID = true;
						reserveOK++;	// 예약 건수를 1증가 시킨다.
						// 좌석번호가 일치하면 안쪽 for문을 벗어난다.
						break;
					}
				}
				// 예약된 좌석번호라면 SeatStatus상태를 true로 만들고,
				// 예약된 좌석번호가 아니라면 SeatStatus상태를 false로 만든다.
				if(findSeatID)	result.add(new SeatStatusDTO(seatNumber, true));
				else			result.add(new SeatStatusDTO(seatNumber, false));
			}
			reserveNO	= seatTotal - reserveOK;	// 예약이 않된 좌석수
			
			// 영화 좌석 예약에서 필요한 데이터를 model에 만든다.
			model.addAttribute("SeatList",		result);
			model.addAttribute("reserveOK", 	reserveOK);
			model.addAttribute("reserveNO", 	reserveNO);
			
			logger.info("예약된    좌석수 : " + reserveOK);
			logger.info("예약 않된 좌석수 : " + reserveNO);
		}

		return "/movie/seatReservation";
		
	} // End - public String seatReservation(@ModelAttribute("movieID") String mID, Model model)
	
	
	//-----------------------------------------------------------------------------------------------------------
	// 영화관 좌석 예약하기 : POST
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/seatReservation", method = RequestMethod.POST)
	@ResponseBody
	public int seatReservationOK(@RequestParam int seatID, Model model) throws Exception {
		
		logger.info("영화 좌석 예약 번호 ==> " + seatID);
		return movieService.insertSeatID(seatID);
		
	}

} // End - public class MovieController
















