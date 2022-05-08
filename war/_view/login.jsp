<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>CS320 Library Login</title>
		<style type="text/css">
		.error {
			color: red;
		}
		
		td.label {
			text-align: right;
		}
		</style>
	</head>

	<body style = "background-color: #070057; color: #FFFFFF;">
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		<div style = "float: left; width: 30%; margin-right: 20%">
			<div style = "font-size:32px; margin-bottom: 24px;">
				Login / Register
			</div>
			<div>
				<form action="${pageContext.servletContext.contextPath}/login" method="post">
					<table>
						<tr>
							<td class="label">User Name:</td>
							<td><input type="text" name="username" size="12" value="${username}" /></td>
						</tr>
						<tr>
							<td class="label">Password:</td>
							<td><input type="password" name="password" size="12" value="${password}" /></td>
						</tr>
					</table>
					<input type="Submit" name="submit" value="Login">
					<input type="Submit" name="Register" value="Register">
					
				</form>
			</div>
		</div>
		<img style="float:left;" src="images/chessboard.png" alt="chessboard.png" width=50% height=50%>

	</body>
</html>