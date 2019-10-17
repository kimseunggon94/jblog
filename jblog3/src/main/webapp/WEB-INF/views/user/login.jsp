<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/menu.jsp" />

		<form:form modelAttribute="userVo" class="login-form" method="post" action="${pageContext.servletContext.contextPath }/user/auth">
			<label>아이디</label>
			<form:input path="id" />


			<label>패스워드</label>
			<form:password path='password' />
			
			<c:if test='${logresult == "fail" }'>
				<p>로그인이 실패 했습니다.</p>
			</c:if>

			<input type="submit" value="로그인">
		</form:form>
	</div>
</body>
</html>
