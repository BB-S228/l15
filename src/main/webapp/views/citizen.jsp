<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Физ.лица</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Citizens</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/style.css">
    <!-- jQuery -->
    <script defer src="js/jquery.min.js"></script>
    <!-- Bootstrap JS + Popper JS -->
    <script defer src="js/bootstrap.min.js"></script>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.mi
n.js"></script>
</head>
<body>
<div class="container-fluid back-container">
    <jsp:include page="/views/header.jsp" />
    <div class="container-fluid back-container">
        <div class="row justify-content-start ">
            <div class="col-8 border bg-light px-4">
                <h3>Список физ.лиц:</h3>
                <table class="table">
                    <thead>
                    <th scope="col">Код</th>
                    <th scope="col">Клиент</th>
                    <th scope="col">Документ</th>
                    <th scope="col">ФИО</th>
                    <th scope="col">Редактировать</th>
                    <th scope="col">Удалить</th>
                    </thead>
                    <tbody>
                    <c:forEach var="citizen" items="${citizens}">
                        <tr>
                            <td>${citizen.getCitizenId()}</td>
                            <td>${citizen.getPerson().getFio()}</td>
                            <td>${citizen.getDocument().getNumber()}</td>
                            <td>${citizen.getShifer()}</td>
                            <td width="20"><a href="<c:url value="/editCitizen?id=${citizen.getCitizenId()}" />" role="button"
                                              class="btn btn-outline-primary">
                                <img alt="Редактировать"
                                     src="img/edit.png"></a>
                            </td>
                            <td width="20"><a href="<c:url value="/deleteCitizen?id=${citizen.getCitizenId()}" />" role="button"
                                              class="btn btn-outline-primary">
                                <img alt="Удалить"
                                     src="img/delete.png"
                                     onclick="return confirm('Удалить физ.лицо с ID: ' +${citizen.getCitizenId()} + '?')"></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4 a-color">
                <form method="POST" action="">
                    <h3>Новое физ.лицо:</h3>
                    <br>
                    <div class="mb-3 row">
                        <label for="inputPerson"
                               class="col-sm-3 col-form-label">Клиент</label>
                        <div class="col-sm-7">
                            <select name="person" class="form-control">
                                <option>Выберите клиента</option>
                                <c:forEach var="person" items="${persons}">
                                    <option value="${person}">
                                        <c:out value="${person.getFio()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputDocument"
                               class="col-sm-3 col-form-label">Документ</label>
                        <div class="col-sm-7">
                            <select name="document" class="form-control">
                                <option>Выберите документ</option>
                                <c:forEach var="document" items="${documents}">
                                    <option value="${document}">
                                        <c:out value="${document.getNumber()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="mb-3 row">
                        <label for="inputShifer"
                               class="col-sm-3 col-form-label">Шифр клиента</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control"
                                   id="inputShifer" name="shifer" />
                        </div>
                    </div>
                    <p> <br>
                        <button type="submit"
                                class="btn btn-primary">Добавить</button>
                    </p>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="/views/footer.jsp" />
</div>
</body>
</html>