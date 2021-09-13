<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Autor</title>
<link
  href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
  rel="stylesheet"
  integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
  crossorigin="anonymous">
</head>

<body class="container">
  <h1 class="text-center">Cadastro de Autores</h1>
  <form action="<c:url value="/AutorServlet"/>" method="POST">

    <div class="form-group">
      <label for="nome">Nome</label>
      <input id="nome" class="form-control mb-2"
        name="nome" required autofocus>
    </div>
    <div class="form-group">
      <label for="email">E-mail</label>
      <input type="email" id="email"
        class="form-control mb-2" name="email" required
        placeholder="email@provedor.com">
    </div>
    <div class="input-group">
      <div class="form-group">
        <label for="dataNascimento">Data de Nascimento</label>
        <input
          type="date" id="dataNascimento" class="form-control mb-2"
          name="dataNascimento" required>
      </div>
    </div>

    <div class="form-group">
      <label for="miniCurriculo">Mini Currículo</label>
      <textarea id="miniCurriculo" class="form-control mb-2"
        name="miniCurriculo" rows="4" cols="50">
      </textarea>
    </div>

    <input type="submit" value="Gravar" class="btn-primary mt-1">

  </form>

  <h3 class="text-center mt-3">Listagem de Autores</h3>
  <table class="table table-hover table-striped table-bordered">
    <thead class="table-dark">
      <tr>
        <th>Nome</th>
        <th>E-mail</th>
        <th class="d-flex justify-content-center">Data de Nascimento</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${listagemAutores}" var="a">
        <tr>
          <td>${a.nome}</td>
          <td>${a.email}</td>
          <td class="d-flex justify-content-center">${a.dataNascimento}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</body>
</html>