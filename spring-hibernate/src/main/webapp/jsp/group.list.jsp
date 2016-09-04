<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="body">
		<h1>Add Group</h1>	
		<form:form method="post" action="/group/add">
			<p>
				<form:input type="text" path="name" name="name" value="${groupName}" />
			</p>
			<p class="submit">
				<input type="submit" name="add" value="Add">
			</p>
		</form:form>
		<h1>Group List</h1>
		<table>
			<tr>
				<td>Name</td>
				<td>#</td>
			</tr>
			<tr>
				<td colspan="2">
					<form method="GET" action="/group">
						<input type="text" name="q">
					</form>
				</td>
			</tr>
			<%-- <c:forEach items="${groups}" var="item" varStatus="loop">
				<tr>
					<td>
						<a href="/account?group=${item.id}">${item.name}</a>
						<ul>
							<c:forEach items="${item.users}" var="user" varStatus="loop">
								<li>${user.username} - ${user.age}</li>
							</c:forEach>
						</ul>
					</td>
					<td><a href="/group/delete/${item.id}">Delete</a></td>
					<td><a href="/group/edit?id=${item.id}">Update</a></td>
				</tr>
			</c:forEach> --%>
		</table>
	</tiles:putAttribute>
</tiles:insertDefinition>