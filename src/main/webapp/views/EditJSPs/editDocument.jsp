<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Документы</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Documents</title>
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
                <h3>Список документов:</h3>
                <table class="table">
                    <thead>
                    <th scope="col">Код</th>
                    <th scope="col">Название</th>
                    <th scope="col">Серия</th>
                    <th scope="col">Номер</th>
                    <th scope="col">Орган</th>
                    <th scope="col">Дата выдачи</th>
                    </thead>
                    <tbody>
                    <c:forEach var="document" items="${documents}">
                        <tr>
                            <td>${document.getDocumentId()}</td>
                            <td>${document.getName()}</td>
                            <td>${document.getSerial()}</td>
                            <td>${document.getNumber()}</td>
                            <td>${document.getOrgan()}</td>
                            <td>${document.getDate()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-4 border px-4 a-color">
                <form method="POST" action="">
                    <h3>Редактировать документ:</h3>
                    <br>
                    <div class="mb-3">
                        <label for="idDocument"
                               class="col-sm-3 col-form-label">Код документа</label>
                        <div class="col-sm-6">
                            <input type="text" readonly
                                   class="form-control" id="idDocument" value="${docEdit.getDocumentId()}"/>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="inputName"
                               class="col-sm-3 col-form-label">Название документа</label>
                        <div class="col-sm-6">
                            <input type="text" name="name"
                                   class="form-control" id="inputName" value="${docEdit.getName()}"/>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="inputSerial"
                               class="col-sm-3 col-form-label">Серия документа</label>
                        <div class="col-sm-6">
                            <input type="text" name="serial"
                                   class="form-control" id="inputSerial" value="${docEdit.getSerial()}"/>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="inputNumber"
                               class="col-sm-3 col-form-label">Номер документа</label>
                        <div class="col-sm-6">
                            <input type="text" name="number"
                                   class="form-control" id="inputNumber" value="${docEdit.getNumber()}"/>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="inputOrgan"
                               class="col-sm-3 col-form-label">Орган, выдавший документ</label>
                        <div class="col-sm-6">
                            <input type="text" name="organ"
                                   class="form-control" id="inputOrgan" value="${docEdit.getOrgan()}"/>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="inputDate"
                               class="col-sm-3 col-form-label">Дата выдачи документа</label>
                        <div class="col-sm-6">
                            <input type="text" name="date"
                                   class="form-control" id="inputDate" value="${docEdit.getDate()}"/>
                        </div>
                    </div>
                    <p>
                        <br> <br> <br>
                        <button type="submit"
                                class="btn btn-primary">Редактировать</button>
                        <a href='<c:url value="/document" />' role="button" class="btn btn-secondary">Отменить</a>
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