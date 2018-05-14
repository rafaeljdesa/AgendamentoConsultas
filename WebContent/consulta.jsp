<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agendamento de consultas</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="css/jquery.datetimepicker.css" type="text/css">
</head>
<body>

	<div class="container">

		<h2 class="text-center mb-5">Agendamento de consultas</h2>

		<form method="post" action="ConsultaController" name="formConsulta">


			<div class="row">
				<div class="col-2">
					<label for="id">Id consulta</label> <input type="text"
						readonly="readonly" class="form-control" id="id" name="id"
						value="<c:out value="${consulta.id}" />">
				</div>
			</div>


			<div class="row">

				<div class="col-3">

					<label for="medico">Id Médico</label>
					<div class="input-group">
						<input type="text" readonly="readonly" class="form-control"
							id="medico" placeholder="Id" name="id_medico"
							value="<c:out value="${consulta.medico.id}" />">
						<div class="input-group-append">
							<input type="button" value="+" class="btn btn-primary"
								data-toggle="modal" data-target="#modalMedico" />
						</div>
					</div>

				</div>

				<div class="col-3">

					<label for="medico">Nome</label>
					<div class="input-group">
						<input type="text" readonly="readonly" class="form-control"
							placeholder="Nome" name="nome_medico"
							value="<c:out value="${consulta.medico.nome}" />">
					</div>

				</div>

				<div class="col-3">

					<label for="medico">CRM</label>
					<div class="input-group">
						<input type="text" readonly="readonly" class="form-control"
							placeholder="CRM" name="crm_medico"
							value="<c:out value="${consulta.medico.crm}" />">
					</div>

				</div>

				<div class="col-3">

					<label for="medico">Especialidade</label>
					<div class="input-group">
						<input type="text" readonly="readonly" class="form-control"
							placeholder="Especialidade" name="especialidade_medico"
							value="<c:out value="${consulta.medico.especialidade}" />">
					</div>

				</div>

			</div>



			<div class="row">
				<div class="col-3">
					<label for="paciente">Id Paciente</label>
					<div class="input-group">
						<input type="text" readonly="readonly" class="form-control"
							id="paciente" placeholder="Paciente" name="id_paciente"
							value="<c:out value="${consulta.paciente.id}" />">
						<div class="input-group-append">
							<input type="button" value="+" class="btn btn-primary"
								data-toggle="modal" data-target="#modalPaciente" />
						</div>
					</div>
				</div>

				<div class="col-3">
					<label for="paciente">Nome paciente</label>
					<div class="input-group">
						<input type="text" readonly="readonly" class="form-control"
							placeholder="Nome paciente" name="nome_paciente"
							value="<c:out value="${consulta.paciente.nome}" />">
					</div>
				</div>

				<div class="col-3">
					<label for="paciente">CPF paciente</label>
					<div class="input-group">
						<input type="text" readonly="readonly" class="form-control"
							placeholder="CPF paciente" name="cpf_paciente"
							value="<c:out value="${consulta.paciente.cpf}" />">
					</div>
				</div>

				<div class="col-3">
					<label for="paciente">Convênio</label>
					<div class="input-group">
						<input type="text" readonly="readonly" class="form-control"
							placeholder="Convênio" name="convenio"
							value="<c:out value="${consulta.paciente.convenio.nome}" />">
					</div>
				</div>


			</div>

			<div class="row">

				<div class="col-3">
					<label for="data">Data consulta</label> 
					<%-- <input type="text" class="form-control" id="data" placeholder="Data consulta"
						   name="data"
						   value="<fmt:formatDate pattern="dd/MM/yyyy" value="${consulta.data}" />" /> --%>
						   
					<input id="datetimepicker" type="text" class="form-control" id="data" placeholder="Data consulta"
						   name="data"
						   value="<fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${consulta.data}" />" />
					
						   
				</div>


			</div>

			<div class="row mt-2">
				<div class="col-12">
					<button type="submit" class="btn btn-primary">Salvar</button>
				</div>

			</div>
			<div class="row mt-2">
				<div class="col-12">
					<p>
						<a href="ConsultaController?action=listaConsultas">Visualizar
							agenda</a>
					</p>
				</div>

			</div>
		
		
		
		</form>

		<div class="modal" id="modalMedico" tabindex="-1" role="dialog">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Médicos</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">


						<div class="row">
							<div class="col-12">
								<table class="table">
									<thead>
										<tr>
											<th>Id</th>
											<th>Nome</th>
											<th>CRM</th>
											<th>Especialidade</th>
											<th>Ação</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${medicos}" var="medico">
											<tr>
												<td><c:out value="${medico.id }" /></td>
												<td><c:out value="${medico.nome }" /></td>
												<td><c:out value="${medico.crm }" /></td>
												<td><c:out value="${medico.especialidade }" /></td>

												<td><a
													href="ConsultaController?action=selecionaMedico&id=<c:out value="${medico.id}"/>">Selecionar</a></td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Fechar</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal" id="modalPaciente" tabindex="-1" role="dialog">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Pacientes</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">


						<div class="row">
							<div class="col-12">
								<table class="table">
									<thead>
										<tr>
											<th>Id</th>
											<th>Nome</th>
											<th>CPF</th>
											<th>Convênio</th>
											<th>Ação</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${pacientes}" var="paciente">
											<tr>
												<td><c:out value="${paciente.id }" /></td>
												<td><c:out value="${paciente.nome }" /></td>
												<td><c:out value="${paciente.cpf }" /></td>
												<td><c:out value="${paciente.convenio.nome }" /></td>

												<td><a
													href="ConsultaController?action=selecionaPaciente&id=<c:out value="${paciente.id}"/>">Selecionar</a></td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Fechar</button>
					</div>
				</div>
			</div>
		</div>

		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
			crossorigin="anonymous"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
			integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
			integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
			crossorigin="anonymous"></script>
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script src="js/jquery.js"></script>
		<script src="js/jquery.datetimepicker.full.min.js"></script>
		
		<script>
			/* $(function() {
				$('#data').datepicker();								
							
			}); */
			
 			jQuery.datetimepicker.setLocale('pt');
			
			jQuery('#datetimepicker').datetimepicker({
				format:'d/m/Y H:i'			  	
			});
			
			
			
		</script>
</body>

</html>