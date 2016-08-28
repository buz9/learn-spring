<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Boot Application</title>
</head>
<body>
	<table border="1" cellpadding="2" cellspacing="2" align="center">
		<tr>
			<td height="30" >Welcome to Java Spring Clazz
			</td>
		</tr>
		<tr>
			<td>
				<a href="/">Trang Chủ</a>
				<sec:authorize access="!hasAnyRole('ROLE_USER')">
					<a href="/dang-nhap" style="margin-left: 30px">Đăng Nhập</a>
				</sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_USER')">
					<a href="/nguoi-dung" style="margin-left: 30px">Cá Nhân</a>
					<a href="/logout" style="margin-left: 30px">Đăng xuất</a>
				</sec:authorize>
			</td>
		</tr>
		<tr>
			<td><tiles:insertAttribute name="body" /></td>
		</tr>
		
	</table>
</body>
</html>