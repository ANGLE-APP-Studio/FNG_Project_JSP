<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import= "maria_connection.ConnectDB" %>
<%
	request.setCharacterEncoding("utf-8");
	SimpleDateFormat tm = new SimpleDateFormat("yyyy-MM-dd");

	String UID= request.getParameter("UID");
	String PSW = request.getParameter("PSW");
	String EML = request.getParameter("EML");
	String PNB = request.getParameter("PNB");
	String NCK = request.getParameter("NCK");
	String BTD = request.getParameter("BTD");
	String GND = request.getParameter("GND");
	String RNK = request.getParameter("RNK");
	
	ConnectDB connectDB = ConnectDB.getInstance();
	String returns = connectDB.connectionDB(UID,PSW,EML,PNB,NCK,BTD,GND,RNK);
	System.out.println(UID+"\n"+PSW+"\n"+EML+"\n"+PNB+"\n"+NCK+"\n"+BTD+"\n"+GND+"\n"+RNK +"\n");
	System.out.println("요청 결과 : " + returns);
%>