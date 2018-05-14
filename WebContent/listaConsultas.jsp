<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Agenda</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	</head>
	<body>
		<div class="container">
		
			<h2 class="text-center mb-5">Agenda de consultas</h2>	
		
			<div class="row">
				<div class="col-12">
					<table class="table">
						<thead>
							<tr>
								<th>Id</th>
								<th>Medico</th>
								<th>CRM</th>
								<th>Paciente</th>
								<th>CPF</th>
								<th>Convênio</th>
								<th>Data e hora</th>
								<th colspan=2>Ação</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${consultas}" var="consulta">				
								<tr>
									<td><c:out value="${consulta.id }" /></td>
									<td><c:out value="${consulta.medico.nome }" /></td>
									<td><c:out value="${consulta.medico.crm }" /></td>
									<td><c:out value="${consulta.paciente.nome }" /></td>
									<td><c:out value="${consulta.paciente.cpf }" /></td>
									<td><c:out value="${consulta.paciente.convenio.nome }" /></td>
									<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${consulta.data}" /></td>
									<td><a href="ConsultaController?action=edit&id=<c:out value="${consulta.id}"/>">Editar</a></td>
		                    		<td><a href="ConsultaController?action=delete&id=<c:out value="${consulta.id}"/>">Deletar</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			
			</div>
			
			
			<p><a href="ConsultaController?action=insert">Adicionar consulta</a></p>
			
		</div>
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
	</body>
</html>