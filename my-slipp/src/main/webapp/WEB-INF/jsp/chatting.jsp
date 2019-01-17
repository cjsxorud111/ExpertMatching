<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page session = "true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% request.setCharacterEncoding("utf-8"); %>

<% response.setContentType("text/html; charset=utf-8"); %>

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
		calltest();
		/* var objDiv = document.getElementById("#siv"); 
		objDiv.scrollTop = objDiv.scrollHeight; */
		/* document.body.scrollTop = document.body.scrollHeight; */
		
		 
		
	});
	
	function calltest() {
		
		$.ajax({
			type : "post",
			url : "http://localhost:8080/test",
			data : {
				userid : $('#userid').html(), //필요없음
				meid : $('#meid').html() //필요없음
			},
			/* contentType: "application/x-www-form-urlencoded; charset=UTF-8", */

			success : function(chatlist) {
				 $('#siv1').html(chatlist);
				
			},
			error : whenError1
		});
	}

	
	function callAjax() {
		$.ajax({
			type : "post",
			url : "http://localhost:8080/test3",
			data : {
				meid : $('#myyid').val(),
				comment : $('#comment').val(),
				u2id : $('#u2id').val()
			},
			/* contentType: "application/x-www-form-urlencoded; charset=UTF-8", */
			success : whenSuccess,
			error : whenError
		});

	}
	
	
</script>

<style>

	
</style>
<script type="text/javascript">
	
</script>

</head>
<body>

<%
/* Object tempUser = session.getAttribute("sessionedUser");  */
/* User user = (User) tempUser;  */ 
/* session.setAttribute("sessionedUser","hee");*/
/* String user = (String)session.getAttribute("sessionedUser");  */

/* System.out.println("세션값"+tempUser); */  
%>
	<div style="width: 500px; margin: auto; margin-top: 1rem;">
		<% String userid = request.getParameter("expertid"); %>
		
		상대방 아이디: <div id="userid"><%=userid%></div>
		<div class="container">
			
			<div id="sivv"></div>
			<!-- 여기부터 표 출력 -->

		</div>
		
		<form id="frm">
			<% 
			Object ido=session.getAttribute("id");
			String id=(String)ido;
			
			%>
			
			내 아이디: <div id="meid">user</div>
			<div style="margin-top: 20px;">
			
				<!-- <input name="comment" id="comment"> -->
				<!-- <textarea name="textarea" name="comment" id="comment" rows="2" cols="40">Write something here</textarea> -->
				<textarea class="form-control" placeholder="메시지를 입력하세요..."
					ame="comment" id="comment"></textarea>
			</div>
			
			<div style="margin-top: 20px;">
				<!-- <input id="button" class="btn btn-default pull-right" type="button" value="버튼"> -->
				<button type="button" class="btn btn-default pull-right" id="button">보내기</button>
			</div>
			
			<div style="margin-top: 20px;">
				<label>내 아이디</label><input name="meid" id="myyid">
			</div>
			
			<div style="margin-top: 20px;">
				<label>상대방 아이디</label><input name="u2id" id="u2id">
			</div>
			
		</form>
	</div>
</body>
</html>



