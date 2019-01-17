<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("utf-8");
%>

<%
	response.setContentType("text/html; charset=utf-8");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>jquery Ajax test</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<jsp:include page="scriptag.jsp"></jsp:include>

<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		time = setInterval(db_back, 1000); // 1초에 한번씩 받아온다.

		$("#button").click(function() {
			callAjax();
		});
	});

	var a = 0;
	function db_back() {
		if (a < 2) {
			$("#sivv").scrollTop($("#sivv")[0].scrollHeight);
		}
		$.ajax({
			type : "post",
			url : "http://localhost:8080/test2",
			data : {
				userid : $('#userid').html(),
				meid : $('#meid').html(),
				userr : $('#userr').html()
			},
			success : function(chatlist) {
				$('#sivv').html(chatlist);
			},
			error : whenError1
		});
		a = a + 1;
	}
	function callAjax() {
		$.ajax({
			type : "post",
			url : "http://localhost:8080/test3",
			data : {
				meid : $('#meid').html(),
				comment : $('#comment').val(),
				u2id : $('#userid').html()
			},
			success : whenSuccess,
			error : whenError
		});
	}

	function whenSuccess() {
		console.log("success");
		setTimeout($("#sivv").scrollTop($("#sivv")[0].scrollHeight), 3000);
		clearText();
	}
	function whenError1() {
		/* alert("Error1"); *///임시 주석처리
	}
	function whenError() {
		alert("Error");
	}
	function clearText() {
		/* document.getElementById("comment").reset(); */
		$('#comment').val('');
	}
</script>
<style type="text/css">
#sivv {
	background-color: #EEEEEE;
	overflow: scroll;
	height: 28rem;
}

body {
	background-color: #EEEEEE;
}

#indiv {
	width: 500px;
	margin: auto;
	margin-top: 1rem;
}

#divcon {
	/* border: 1px solid red; */
	width: 44rem;
	background-color: #999999;
}

#rand {
	display: flex;
	flex-direction: row
}
</style>
</head>
<body>
	<div class="container" id="divcon">
		<div id="indiv">
			<%
				String userid = request.getParameter("expertid");
				String myid = request.getParameter("myid");
				String userr = request.getParameter("userr");
			%>
			내 아이디:
			<div id="meid"><%=myid%></div>
			상대방 아이디:
			<div id="userid"><%=userid%></div>
			<div id="userr"><%=userr%></div>
			<!-- 여기부터 채팅창 출력 -->
			<div id="sivv"></div>

			<form id="frm">
				<%
					Object ido = session.getAttribute("id");
					String id = (String) ido;
				%>

				<div style="margin-top: 20px;">

					<textarea class="form-control" placeholder="메시지를 입력하세요..."
						name="comment" id="comment"></textarea>
				</div>
				<div id="rand">
					<!-- <div>
						<label>내 아이디 :</label><input name="meid" id="myyid" >
					</div>
					<div>
						<label>상대방 아이디 :</label><input name="u2id" id="u2id" >
					</div> -->
					<div id="sub">
						<button type="button" class="btn btn-primary pull-right"
							id="button">보내기</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>