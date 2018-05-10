<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	</head>
	<body>
				
   		<div class="container">
   			
   			<h2 class="text-center mb-5">Agendamento de consultas</h2>	
		
			<form method="post" action="ConsultaController" name="formConsulta">
			  <div class="form-group">
			  	
			  	<div class="row">
			  		<div class="col-3">
			  			<label for="id">Id consulta</label>
				    	<input type="text" readonly="readonly" class="form-control" id="id" name="id" value="<c:out value="${consulta.id}" />">
			  		</div>
			  		<div class="col-3">
			  			<label for="medico">Médico</label>
				    	<input type="text" class="form-control" id="medico" placeholder="Medico" name="id_medico" value="<c:out value="${consulta.medico}" />">
			  		</div>
			  		<div class="col-3">
			  			 <label for="paciente">Paciente</label>
				    	<input type="text" class="form-control" id="paciente" placeholder="Paciente" name="id_paciente" value="<c:out value="${consulta.paciente}" />">	
			  		</div>
			  		<div class="col-3">
			  			<label for="data">Data</label>
				    	<input type="text" class="form-control" id="data" placeholder="Data" name="data" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${consulta.data}" />" />
			  		</div>
			  	</div>
			  </div>
			  <div class="row">
			  	<div class="col-12">
			  		<button type="submit" class="btn btn-primary">Salvar</button>
			  	</div>
			  	
			  </div>	
			  <div class="row mt-2">
			  	<div class="col-12">
			  		<p><a href="ConsultaController?action=listaConsultas">Visualizar agenda</a></p>  		
			  	</div>
			  	
			  </div>
			</form>
		</div>
		
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script>
	        $(function() {
	            $('#data').datepicker();
	        });
    	</script>
	</body>
	
</html>