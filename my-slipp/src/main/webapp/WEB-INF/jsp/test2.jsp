<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.SQLException"%>

<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@ page import="javax.sql.DataSource"%>

<%
	request.setCharacterEncoding("utf-8");
%>

<%
	response.setContentType("text/html; charset=utf-8");
%>
<html>
<head>
<title>JSP Example</title>
<style type="text/css">
#cont1 {
	white-space: normal;
	width: 18rem;
	background-color: white;
	border-radius: 6px;
	padding-top: 5px;
	padding-left: 10px;
	padding-bottom: 5px;
	margin-top: 1rem;
	margin-left: 1rem;
}

#cont2 {
	/* float: right;  */
	white-space: normal;
	width: 18rem;
	background-color: #33CCFF;
	color: white;
	border-radius: 6px;
	padding-top: 5px;
	padding-bottom: 5px;
	padding-left: 10px;
	margin-top: 1rem;
	margin-left: 3rem;
}

#tri1 {
	/* border: 1px solid red;  */
	position: relative;
	left: 0.3rem;
	top: 2rem;
	width: 0px;
	height: 0px;
	border-left: 18px solid transparent;
	/*border의 성질을 이용해 오른쪽테두리만 투명으로 만든다. */
	border-bottom: 14px solid #ffffff; /*border를 사용해 두께를 만든다.*/
}

#tri2 {
	/* border: 1px solid red;  */
	position: relative;
	left: 20.8rem;
	top: 2rem;
	width: 0px;
	height: 0px;
	border-right: 18px solid transparent;
	/*border의 성질을 이용해 오른쪽테두리만 투명으로 만든다. */
	border-bottom: 14px solid #33CCFF; /*border를 사용해 두께를 만든다.*/
}
#photo {

	position: relative;
	top: 1.7rem;
	width: 44px;
	
    height: 44px;
    object-fit: cover;
    object-position: top;
    border-radius: 50%;
    
    margin-left:1rem;
}
#photo1 {

	position: relative;
	top: 1.7rem;
	left: 25rem;
	width: 44px;
	
    height: 44px;
    object-fit: cover;
    object-position: top;
    border-radius: 50%;
    
    margin-left:1rem;
}
#fle {
/* border:1px solid red; */
	display: flex;
	flex-direction: row;
}

</style>

</head>
<body>

	<div id="sivq">

		<%
			DataSource dataSource = null;

			//Connection 객체를 자동완성으로 import할 때는 com.mysql.connection이 아닌
			//java 표준인 java.sql.Connection 클래스를 import해야 한다.
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			//String mysessionid = request.getParameter("mysessionid");
			String mysessionid = request.getParameter("meid");
			String userid = request.getParameter("userid"); //ajax로 넘긴 유저아이디
			String userr = request.getParameter("userr"); 

			HashMap<String, String> maap = new HashMap<String, String>();
			maap.put("mysessionid", mysessionid);

			ResultSet resultSet = null;
			System.out.println(userid + "유저아이디임");
			int num = 0;
			try {
				Class.forName("com.mysql.jdbc.Driver");

				String url = "jdbc:mysql://13.124.205.192/RareAnimalLove2?useSSL=false";
				String id1 = "root";
				String pw = "01042372120";

				conn = DriverManager.getConnection(url, id1, pw);

				System.out.println("셀렉1");
				String query = "select * from chatting";

				pstmt = conn.prepareStatement(query);
				resultSet = pstmt.executeQuery();

				/* Object ido=session.getAttribute("id");
				String myid=(String)ido; */
				
				while (resultSet.next()) {

					String id = resultSet.getString("id");
					String content = resultSet.getString("content");
					String u2id = resultSet.getString("u2id");
					
					
					System.out.println(id);
					System.out.println(content);
					System.out.println(u2id);
					System.out.println("이거이거거이거"+id);
					if (id.equals(mysessionid) && u2id.equals(userid)
							|| id.equals(userid) && u2id.equals(mysessionid)) {
						num++;
						System.out.println("이거이거"+id);
						if (id.equals(mysessionid)) {
								%>
								<div id="fle">
								<% 
								if(userr.equals("userr")){
								%>
									<img alt="photo" id="photo" src="http://naitestudio.com/wp-content/uploads/2018/02/20170509-5%EC%9D%B8%EA%B0%80%EC%A1%B1.jpg">
								<% 
								}else{
								%>
									<img alt="photo" id="photo" src="https://animalsfacts.net/wp-content/uploads/2017/05/700x400-bird-martin-purple-2648.jpg">
								<% 
								}
								%>
									<div id="infle">
										<div id="tri1"></div>
										<div id="gap1">
											<div id="cont1">
												<%=(content)%>
											</div>
										</div>
									</div>
								</div>
								<%
							} else {
								%>
								<div id="fle">
								<% 
								if(userr.equals("userr")){
								%>
									<img alt="photo" id="photo1" src="https://animalsfacts.net/wp-content/uploads/2017/05/700x400-bird-martin-purple-2648.jpg">
								<% 
								}else{
								%>
									<img alt="photo" id="photo1" src="http://naitestudio.com/wp-content/uploads/2018/02/20170509-5%EC%9D%B8%EA%B0%80%EC%A1%B1.jpg">
								<% 
								}
								%>
									<div id="infle">
										<div id="tri2"></div>
										<div id="gap2">
											<div id="cont2">
												<%=(content)%>
											</div>
										</div>
									</div>	
								</div>
								<%
							}
						}
				}
			} catch (Exception e) { // 예외가 발생하면 예외 상황을 처리한다.
				e.printStackTrace();

			} finally { // 쿼리가 성공 또는 실패에 상관없이 사용한 자원을 해제 한다. (순서중요)

				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException sqle) {
					} // PreparedStatement 객체 해제

				if (conn != null)
					try {
						conn.close();
					} catch (SQLException sqle) {
					} // Connection 해제
			}
			
			String cnum = Integer.toString(num);
			System.out.println("1");
			System.out.println("cnum은 "+cnum);
			
			String ccnumm = null;
			ccnumm = (String)session.getAttribute("cnumm");
			System.out.println("지금이거"+ccnumm);
			String id = "";
			
			if(ccnumm != null){
				ccnumm = (String)session.getAttribute("cnumm");
			    System.out.println("ccnumm은"+ccnumm);
			    if (!ccnumm.equals(cnum)){
			    	%>
					<script type="text/javascript">
					$("#sivv").scrollTop($("#sivv")[0].scrollHeight); 
					
					</script>
					<% 
					System.out.println("다름 스크롤 아래로");
			    } else {
			    	System.out.println("같음 스크롤 그대로");
			    }
			}
			
			session.setAttribute("cnumm", cnum);
		    System.out.println("3"); 
		%>

	</div>
</body>
</html>