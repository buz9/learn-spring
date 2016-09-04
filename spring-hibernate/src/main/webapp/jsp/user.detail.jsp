<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="body">
		<h1>User</h1>
		<p>Tài khoản: ${user.username}</p>
		<p>Mật khẩu: ${user.password}</p>
		<p>Tuổi: ${user.age}</p>
		<p>Nhóm: ${user.group}</p>
	</tiles:putAttribute>
</tiles:insertDefinition>