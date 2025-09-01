<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<%@ include file="topbar.jsp" %>
    <h1>Welcome, ${sessionScope.account.userName}!</h1>
</body>
</html>
