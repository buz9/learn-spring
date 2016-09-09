<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@	taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="body">
		<h1>Add User</h1>
		<form:form method="POST" action="/account/save">
				Tài Khoản: <form:input type="text" path="username" name="username" /><br/>
				Mật khẩu: <form:input type="password" path="password" name="password" /><br/>
				Email: <form:input type="text" path="email" name="email" /><br/>
				Tuổi: <form:input type="text" path="age" name="age" /><br/>
				Nhóm: <form:select id="group" path="groupId" name="group">
					<form:options items="${groups}"/>
				</form:select>
				<br/>
				<input type="submit" name="add" value="Add" /><br/>
		</form:form>
		<h1>User List</h1>
		<table>
			<tr>
				<td colspan="2">
					<form method="GET" action="/account">
					Tìm theo groupId: <input type="text" name="q">
					</form>
				</td>
			</tr>
			<tr>
				<td>Name</td>
				<td>Age</td>
			</tr>
			<c:forEach items="${users}" var="item" varStatus="loop">
				<tr>
					<td><a href="/account/detail/${item.username}">${item.username}</a></td>
					<td>${item.age}</td>
					<td>${item.groupId}</td>
					<td><a href="/account/delete-${item.username}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</tiles:putAttribute>
</tiles:insertDefinition>