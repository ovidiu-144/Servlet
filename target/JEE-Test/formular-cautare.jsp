<html xmlns:jsp="http://java.sun.com/JSP/Page">
	<head>
		<title>Formular student</title>
		<meta charset="UTF-8" />
	</head>
	<body>
		<h3>Formular student</h3>
		Introduceti numele pe care vreti sa il cautati:
		<form action="./search-name" method="post">
			Nume: <input type="text" name="nume" />
			<br />
			<br />
			<button type="submit" name="submit">Confirm</button>
		</form>
	</body>
</html>