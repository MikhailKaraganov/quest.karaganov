<%@ page import="java.time.*" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.format.FormatStyle" %>
<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
  <meta charset="UTF-8">
</head>
<body>

<table frame="border">
  <caption>Session info</caption>
  <tr>
    <td>Name:</td>
    <td><%=session.getAttribute("name")%></td>
  </tr>
  <tr>
    <td>ID:</td>
    <td><%=session.getId()%></td>
  </tr>
  <tr>
    <td>IP:</td>
    <td><%=request.getRemoteAddr()%></td>
  </tr>
  <tr>
    <td>Init date:</td>
    <%
        ZonedDateTime zonedDateTime = Instant.ofEpochMilli(session.getCreationTime()).atZone(ZoneId.systemDefault());
        String formatDateTime = zonedDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
    %>
    <td><%=formatDateTime%></td>
  </tr>
</table>
</body>
</html>
