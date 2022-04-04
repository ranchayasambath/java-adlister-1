<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ranchayasambath
  Date: 4/4/22
  Time: 3:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ads </title>
</head>
<body>
<c:forEach var="ad" items="${adsListing}">
    <div class = "ad">
        <h3>${ad.title}</h3>
        <p>${ad.description}</p>
    </div>
</c:forEach>
</body>
</html>
