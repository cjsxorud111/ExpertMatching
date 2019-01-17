<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.SQLException" %>


<%@ page import="javax.naming.Context"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@ page import="javax.sql.DataSource"%>

<% request.setCharacterEncoding("utf-8"); %>

<% response.setContentType("text/html; charset=utf-8"); %>
<html>
<head>

</head>
<body>

<%
DataSource dataSource = null;


//Connection 객체를 자동완성으로 import할 때는 com.mysql.connection이 아닌
//java 표준인 java.sql.Connection 클래스를 import해야 한다.
Connection conn = null;
PreparedStatement pstmt = null;

String meid = request.getParameter("meid"); //ajax로 넘긴 유저아이디
String comment = request.getParameter("comment"); //ajax로 넘긴 유저아이디
String u2id = request.getParameter("u2id"); //ajax로 넘긴 유저아이디

System.out.println(meid+"테스트");
System.out.println(comment+"테스트");
System.out.println(u2id+"테스트");
try{
Class.forName("com.mysql.jdbc.Driver");

String url = "jdbc:mysql://13.124.205.192/RareAnimalLove2?useSSL=false";
String id = "root";
String pw = "01042372120";

conn = DriverManager.getConnection(url, id, pw);
String sql = "insert into chatting values(?,?,?,?)";        // sql 쿼리

pstmt = conn.prepareStatement(sql);                          // prepareStatement에서 해당 sql을 미리 컴파일한다.

pstmt.setString(1, meid);

pstmt.setString(2, comment);

pstmt.setString(3, u2id);

pstmt.setString(4, null);

pstmt.executeUpdate();                                        // 쿼리를 실행한다.
System.out.println(" 테이블에 새로운 레코드 추가에 성공했습니다.");

}catch(Exception e){                                                    // 예외가 발생하면 예외 상황을 처리한다.

e.printStackTrace();

System.out.println("테이블에 새로운 레코드 추가에 실패했습니다.");

}finally{                                                            // 쿼리가 성공 또는 실패에 상관없이 사용한 자원을 해제 한다. (순서중요)

if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}            // PreparedStatement 객체 해제

if(conn != null) try{conn.close();}catch(SQLException sqle){}            // Connection 해제

}


%>
 
</body>
</html>




