 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>
<html>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>
	<img alt="image"  src="<c:url value="/res/images/logo.png" />">

	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="_csrf"
			value="0d0a7b5b-e8ba-4832-9047-496a691bfd6e" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<h2>
			Welcome : <sec:authentication property="principal.username" />, you do have the role "role_admin" | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</sec:authorize>

</body>
</html>