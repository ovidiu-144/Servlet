<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<head>
		<title>Informatii studenti</title>
		<meta charset="UTF-8" />
	</head>
	<body>
		<h3>Lista studenti din baza de date</h3>
        <c:forEach var="student" items="${listaStudenti}">
            <div style="margin-bottom: 25px">
                <li>Nume: <jsp:getProperty name="student" property="nume" /></li>
                <li>Prenume: <jsp:getProperty name="student" property="prenume" /></li>
                <li>Varsta: <jsp:getProperty name="student" property="varsta" /></li>
                <li>anNastere <jsp:getProperty name="student" property="anNastere" /></li>
            </div>
        </c:forEach>
	</body>
</html>