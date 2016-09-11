<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="body">
		<h1>Login to Application</h1>
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<form method="post" action="/login">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
			<input type="text" name="username" placeholder="Username" />
			<input type="password" name="password" placeholder="Password" />
			<input type="submit" name="commit" value="Login"/>
		</form>
		
		<form name="facebookSocialLoginForm" action="/auth/facebook" method="POST">
			<input type="hidden" name="scope" value="public_profile, email, user_about_me" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button type="submit">
				<i>Sign In With Facebook</i>
			</button>
			<div class="clear"></div>
		</form>
	</tiles:putAttribute>
</tiles:insertDefinition>