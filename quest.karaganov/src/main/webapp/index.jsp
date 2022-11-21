<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quest</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<h2>Quest game!</h2>
<p>Какие то слова с описанием игры и сюжета</p>
<form action="gameServlet">
    <p>Введите имя<input type="text" name="userName" ></p>
    <p><input type="submit" value="Start"></p>
</form>


<hr>
<%@ include file="session_info.jsp"%>
</body>
</html>
