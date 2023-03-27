<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Students</title>
    </head>
    <body>
        <h1>Grouped by ${filter}</h1>
        <div id="container">
            <c:if test="${filtered.size()>0}">
                <table class="list">
                    <tr>
                        <th>Name</th>
                        <th>Surname</th>
                    </tr>
                    <c:forEach var ="student" items="${filtered}">
                        <tr>
                            <td><c:out value="${student.getName()}"/></td>
                            <td><c:out value="${student.getSurname()}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </body>
</html>
