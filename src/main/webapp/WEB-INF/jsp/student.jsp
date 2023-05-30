<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
        integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
        integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
        crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<style>
    .flex {
        display: flex;
        flex-direction: column;
        height: 100vh;
    }

    body {
        background-color: grey;
        margin: 0;
        padding: 0
    }
    .h100 {

        flex-grow: 3
    }


</style>
<html>
<head>
    <title>Главная страница</title>
</head>
<body>
<div class="container-fluid flex">
    <div class="row">
        <div class="col-md-12">
            <jsp:include page="header.jsp"></jsp:include>
        </div>
    </div>
    <div class="row h-100">
        <div class="col-md-12 h-100">
            <form method="post" action="/controller?command=addStudent">
                <label for="s1">Имя студента</label>
                <input id="s1" name="name" type="text" required>
                <br>
                <label for="s2">Номер группы</label>
                <input id="s2" name="group" type="text" required>
                <br>
                <label for="s3">Оценка</label>
                <input id="s3" name="mark" type="number" max="5" required>
                <br>
                <br>
                <button type="submit">Добавить студента</button>
            </form>
            <c:if test="${not empty requestScope.student}">
                <form method="post" action="/controller?command=updateS">
                    <label for="s11">Имя студента</label>
                    <input id="s11" name="name" type="text" value="${requestScope.student.name}" required>
                    <br>
                    <label for="s22">Номер группы</label>
                    <input id="s22" name="group" value="${requestScope.student.group}" type="text" required>
                    <br>
                    <label for="s33">Оценка</label>
                    <input id="s33" name="mark" type="number" value="${requestScope.student.mark}" max="5" required>
                    <br>
                    <br>
                    <input hidden="hidden" name="id" value="${requestScope.student.id}">
                    <button type="submit">Обновить</button>
                </form>
            </c:if>
            <div class="row">
                <div class="col-md-12">
                    <jsp:include page="footer.jsp"></jsp:include>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
