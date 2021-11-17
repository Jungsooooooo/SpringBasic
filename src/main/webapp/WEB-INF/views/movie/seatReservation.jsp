<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c"			uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"			uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="layoutTag"	tagdir="/WEB-INF/tags" %>

<layoutTag:layout>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>영화 좌석 예약</title>
</head>
<body>

<div class="container">
	<div class="row" align="center">
		<div class="col-sm-offset-2 col-sm-8">
			<h2>좌석 예약</h2>
		</div>
		<div class="col-sm-offset-3 col-sm-8">
			<label class="control-label col-sm-2">남은좌석수</label>
			<div id="seatCount1" class="col-sm-1" style="font-size:1.2em; width:80px; background-color:2F70A9; color:FFFFFF; height; border:1px solid; float:left;">
				${reserveNO}
			</div>
			<label class="control-label col-sm-2">예약좌석수</label>
			<div id="seatCount2" class="col-sm-1" style="font-size:1.2em; width:80px; background-color:D77875; color:FFFFFF; height; border:1px solid; float:left;">
				${reserveOK}
			</div>
			<!-- reserveNO 와 reserveOK 는 자바스크립트에서 직접적으로 수정할 수 없기에 input을 이용했다. -->
			<input type="hidden" class="form-control" id="numNO" name="numNO" value="${reserveNO}"/>
			<input type="hidden" class="form-control" id="numOK" name="numOK" value="${reserveOK}"/>
		</div>
	</div>
	
	<hr>
	<div class="row" align="center">
		<form action="" method="post" class="pt-3" style="max-width:1720px;">
			<c:forEach var="list" items="${SeatList}" begin="0" end="99" varStatus="status">
				<c:choose>
					<c:when test="${list.status == false}">
						<button type="button" class="btn btn-primary seatNo" style="width:50px;" value="${list.seatID}">${list.seatID}</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-danger seatNo" style="width:50px;" value="${list.seatID}" disabled>${list.seatID}</button>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${status.count % 12 == 0}">
						<br/>
					</c:when>
				</c:choose>
			</c:forEach>
			<br><br><br>
			<c:forEach var="list" items="${SeatList}" begin="100" end="199" varStatus="status">
				<c:choose>
					<c:when test="${list.status == false}">
						<button type="button" class="btn btn-primary seatNo" style="width:50px;" value="${list.seatID}">${list.seatID}</button>
					</c:when>
					<c:otherwise>
						<button type="button" class="btn btn-danger seatNo" style="width:50px;" value="${list.seatID}" disabled>${list.seatID}</button>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${status.count % 16 == 0}">
						<br/>
					</c:when>
				</c:choose>
			</c:forEach>
		</form>
	</div>
</div>

<script>
// 빈좌석을 클릭하면 예약업무를 실행한다.
$('.seatNo').on('click', function() {
	
	// 클릭한 버튼의 인덱스번호를 찾아낸다.
	var idx = $('.seatNo').index(this);
	// alert("Click ==> " + idx);
	
	// 예약 확정/취소를 선택한다.
	if(!confirm("좌석 " + $('.seatNo').eq(idx).val() + "번을 예약합니다.")) {
		// 취소(아니오) 버튼을 눌렀을 경우
		alert("예약을 취소합니다.");
	} else { // 예약버튼을 누른 경우
		// alert("예약을 시작합니다.");
		$.ajax({
			url:		"/movie/seatReservation",
			type:		"post",
			dataType:	"json",
			data : 		{"seatID" : $(".seatNO").eq(idx).val()}, // 클릭한 좌석번호
			success:	function(data) {
				// alert("Return Value ==> " + data);
				if(data == 1) {
					alert("좌석예매가 완료되었습니다.");
					// 예약이 완료되었으므로 버튼의 색상을 변경한다.
					$('.seatNO').eq(idx).addClass('btn-danger').removeClass('btn-primary');
					
					// 예약이 완료되었으므로 버튼을 누르지 못하도록 설정한다.
					$('.seatNO').eq(idx).attr('disabled', true);
					
					// 예매가 성공하였으므로 남은 좌석은 -1로 계산하여 화면에 보여준다.
					// parseInt(문자) : 더하기의 경우 문자 뒤에 숫자가 붙기 때문에
					//          숫자로 계산하기 위해서 사용한다. 빼기는 상관없음.
					let remainCount = parseInt(document.getElementById('numNO').value) - 1;
					$("#seatCount1").text(remainCount);
					document.getElementById('numNO').value = remainCount;
					
					// 예매가 성공하였으므로 예약 좌석은 +1로 계산하여 화면에 보여준다.
					// parseInt(문자) : 더하기의 경우 문자 뒤에 숫자가 붙기 때문에
					//                  숫자로 계산하기 위해서 사용한다.
					// let reserveCount = document.getElementById('numOK').value;
					//reserveCount = parseInt(reserveCount) + 1;
					let reserveCount = parseInt(document.getElementById('numOK').value) + 1;
					$("#seatCount2").text(reserveCount);
					document.getElementById('numOK').value = reserveCount;

				} else {
					alert("좌석예매 중에 장애가 발생하였습니다. 잠시 후에 다시 해주십시오.");
				}
			}
		});
	}
});
</script>


<script>
// 빈좌석을 클릭하면 예약업무를 실행한다.
$('.seatNoNoNo').on('click', function() {
	
	// 클릭한 버튼의 인덱스번호를 찾아낸다.
	var idx = $('.seatNo').index(this);
	// alert("Click ==> " + idx);
	
	// 예약 확정/취소를 선택한다.
	if(!confirm("좌석 " + $('.seatNo').eq(idx).val() + "번을 예약합니다.")) {
		// 취소(아니오) 버튼을 눌렀을 경우
		alert("예약을 취소합니다.");
	} else { // 예약버튼을 누른 경우
		// alert("예약을 시작합니다.");
		$.ajax({
			url:		"/movie/seatReservation",
			type:		"post",
			dataType:	"json",
			data : 		{"seatID" : $(".seatNO").eq(idx).val()}, // 클릭한 좌석번호
			success:	function(data) {
				// alert("Return Value ==> " + data);
				if(data == 1) {
					alert("좌석예매가 완료되었습니다.");
					// 예약이 완료되었으므로 버튼의 색상을 변경한다.
					$('.seatNO').eq(idx).addClass('btn-danger').removeClass('btn-primary');
					
					// 예약이 완료되었으므로 버튼을 누르지 못하도록 설정한다.
					$('.seatNO').eq(idx).attr('disabled', true);
					
					// 예매가 성공하였으므로 남은 좌석은 -1로 계산하여 화면에 보여준다.
					// let remainCount = ${reserveNO} - 1;
					let remainCount = document.getElementById('numNO').value - 1;
					alert("remainCount : " + remainCount)
					$("#seatCount1").text(remainCount);
					document.getElementById('numNO').value = remainCount;
					
					// 예매가 성공하였으므로 예약 좌석은 +1로 계산하여 화면에 보여준다.
					// let reserveCount = ${reserveOK} + 1;
					let reserveCount = document.getElementById('numOK').value + 1;
					alert("reserveCount" + reserveCount);
					$("#seatCount2").text(reserveCount);
					document.getElementById('numOK').value = reserveCount;

				} else {
					alert("좌석예매 중에 장애가 발생하였습니다. 잠시 후에 다시 해주십시오.");
				}
			}
		});
	}
});
</script>

</body>
</html>

</layoutTag:layout>






