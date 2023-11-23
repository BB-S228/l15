<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Клиенты</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Clients</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <!-- jQuery -->
    <script defer src="js/jquery-3.6.4.js"></script>
    <!-- Bootstrap JS + Popper JS -->
    <script defer src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid back-container">
    <jsp:include page="/views/header.jsp" />
    <div class="container-fluid back-container">
        <div class="row justify-content-start ">
            <div class="col-8 border bg-light px-4">
                <h3>Список клиентов:</h3>
                <table class="table">
                    <thead>
                    <th scope="col">Код</th>
                    <th scope="col">ФИО</th>
                    <th scope="col">Инн</th>
                    <th scope="col">Тип</th>
                    <th scope="col">Дата регистрации</th>
                    <th scope="col">Редактировать</th>
                    <th scope="col">Удалить</th>
                    </thead>
                    <tbody>
                    <c:forEach var="person" items="${persons}">
                        <tr>
                            <td>${person.getPersonId()}</td>
                            <td>${person.getFio()}</td>
                            <td>${person.getInn()}</td>
                            <td>${person.getType()}</td>
                            <td>${person.getDate()}</td>
                            <td width="20"><a href="<c:url value="/editPerson?id=${person.getPersonId()}" />" role="button"
                                              class="btn btn-outline-primary">
                                <img alt="Редактировать"
                                     src="img/edit.png"></a>
                            </td>
                            <td width="20"><a href="<c:url value="/deletePerson?id=${person.getPersonId()}" />" role="button"
                                              class="btn btn-outline-primary">
                                <img alt="Удалить"
                                     src="img/delete.png"
                                     onclick="return confirm('Удалить клиента с ID: ' +${person.getPersonId()} + '?')"></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4 a-color">
                <form method="POST" action="">
                    <h3>Новый клиент:</h3>
                    <br>
                    <div class="mb-3">
                        <label for="inputFio"
                                    class="col-sm-3 col-form-label">ФИО клиента</label>
                        <div class="col-sm-6">
                            <input type="text" name="fio"
                                   class="form-control" id="inputFio" />
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="inputInn"
                               class="col-sm-3 col-form-label">Инн клиента</label>
                        <div class="col-sm-6">
                            <input type="text" name="inn"
                                   class="form-control" id="inputInn" />
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="inputType"
                                    class="col-sm-3 col-form-label">Тип клиента</label>
                        <div class="col-sm-6">
                            <input type="text" name="type"
                                   class="form-control" id="inputType" />
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="inputDate"
                               class="col-sm-3 col-form-label">Дата регистрации клиента</label>
                        <div class="col-sm-6">
                            <input type="text" name="date"
                                   class="form-control" id="inputDate" />
                        </div>
                    </div>
                    <p>
                        <br> <br> <br>
                        <button type="submit"
                                class="btn btn-primary">Добавить</button>
                        <br>
                    </p>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="/views/footer.jsp" />
</div>
</body>
</html>