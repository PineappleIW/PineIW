<%@ include file="../fragments/header.jspf" %> 
      
<script src="http://code.jquery.com/jquery-2.1.4.min.js" type="text/javascript"></script>
<div class="cuadrobusqueda">
<div class="container">
      <div class="input-group"> <span class="input-group-addon">Buscar</span>
        <input id="filtrar" type="text" class="form-control" placeholder="Ingrese el usuario o concepto que desee buscar...">
      </div>
</div></div>



<div class=letra>Usuarios</div>

	<form method="post">
	<table class="table table-hover">
		<!-- On rows -->
		<c:forEach items="${u}" var="a">
		<tbody class="buscar">
		<tr>
		  <td class="admin">${a.login}</td>				
		  <td class="success"> <button type="submit" value="${a.login}" name="borradous">Del</button>
		</tr>
		</tbody>
		</c:forEach>
	</table>
	</form>


<div class=letra>Conceptos denunciados</div>

	<form method="post">
	<table class="table table-hover">
		<!-- On rows -->
		<c:forEach items="${o}" var="a">
		<tbody class="buscar">
		<tr>
		  <td class="admin">${a.nombre} - ${a.user.login}</td>				
		  <td class="success"> <button type="submit" value="${a.nombre}" name="borrado">Del</button>
		  <td class="success"> <button type="submit" value="${a.nombre}" name="editaconcep">Edit</button>
		</tr>
		</tbody>
		</c:forEach>
	</table>
	</form>




<div class=letra>Contenidos</div>

	<form method="post">
	<table class="table table-hover">
		<!-- On rows -->
		<c:forEach items="${i}" var="a">
		<tbody class="buscar">
		<tr>
		  <td class="admin">${a.nombre} - ${a.user.login}</td>			
		  <td class="success"> <button type="submit" value="${a.nombre}" name="borrado">Del</button>
		  <td class="success"> <button type="submit" value="${a.nombre}" name="editaconcep">Edit</button>
		</tr>
		</tbody>
		</c:forEach>
	</table>
	</form>






<%@ include file="../fragments/footer.jspf" %>