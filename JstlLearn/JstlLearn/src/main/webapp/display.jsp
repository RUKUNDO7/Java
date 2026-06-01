<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<c:forEach var="student" items="${students}">

    ${student.firstName}
    ${student.lastName}
    <c:choose>
        <c:when test="${student.age<15}">OLevel</c:when>
        <c:when test="${student.age>=15 && student.age<20}">ALevel</c:when>
        <c:otherwise>University</c:otherwise>
    </c:choose>
</c:forEach>

</body>
</html>