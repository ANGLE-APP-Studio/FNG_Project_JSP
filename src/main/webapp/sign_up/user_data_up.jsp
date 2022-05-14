<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import= "maria_connection.ConnectDB" %>
<%
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");

	ConnectDB connectDB = ConnectDB.getInstance();
	String returns = connectDB.connectionDB(id,pwd);
	System.out.println("요청 결과 : " + returns);
%>