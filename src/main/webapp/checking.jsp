<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="co.edu.unbosque.TiendaVirtual.dao.LoginDao"%> 
<jsp:useBean id="obj" class="co.edu.unbosque.TiendaVirtual.modelo.Usuarios"/> 
<jsp:setProperty property="*" name="obj"/>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Espere un momento...</title>
</head>
<body>

<%  
	boolean status = LoginDao.validar(obj);  
	if(status){  
		out.println("You r successfully logged in");  
		session.setAttribute("session","TRUE");  
	}  
	else {  
	out.print("Sorry, email or password error");  
	}
%>  


</body>
</html>