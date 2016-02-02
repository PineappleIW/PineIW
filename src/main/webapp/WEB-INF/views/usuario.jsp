<%@ include file="../fragments/header.jspf" %>

<div class="datos1">
	<h1>Datos personales</h1>
	<div class="foto1">
		<img src="http://restrepoospina.com.ec/consultas/img/registrado.png">
	</div>
	<table class="table table-condensed">
		<!-- On rows -->
		<tr>
		  <td class="active">Nombre</td>
		  <td class="success">${e:forHtml(user.nombre)}</td>
		</tr>
		</tr>
			<td></td>
		<tr>
		<tr>
		  <td class="active">Apellidos</td>
		  <td class="success">${e:forHtml(user.apellidos)}</td>
		</tr>
		<tr>
		</tr>
			<td></td>
		<tr>
		  <td class="active">Email</td>
		  <td class="success">${e:forHtml(user.email)}</td>
		</tr>
	</table>
	<h1>Estadísticas de examenes</h1>
	<table class="table table-condensed">
		<thead>
		  <td class="active">NOMBRE </td>
		  <td class="warning">DENUNCIADO</td>
		  <td class="danger">SOLUCIONES</td>
		  <td class="info">PUNTUACIÓN</td>
		</thead>
		
		<c:forEach items="${lista}" var="a" varStatus="status">
		<thead>
		   <td class="active">${e:forHtml(a.nombre)}</td>
		   <td class="warning">${e:forHtml(a.denuncia)}</td>
		   <td class="danger">${e:forHtml(numsols[status.index])}</td>
		   <td class="info">${e:forHtml(votos[status.index])}</td>
		</thead>
		</c:forEach>

	</table>
</div>
<%@ include file="../fragments/footer.jspf" %>