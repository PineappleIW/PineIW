<%@ include file="../fragments/header.jspf" %>







<div class="datos1">
	<form>
	<table class="table table-condensed">
		<!-- On rows -->
		<c:forEach items="${i}" var="a">
		<tr>
		  <td class="active">${a}</td>				
		  <td class="success"> <button type="submit" class="btn btn-danger" value="id" name="borrado">Borrar</button> <button type="button" class="btn btn-info">Editar</button>
		</tr>
			<td></td>
		<tr>
		</c:forEach>
	</table>
	</form>

</div>



<%@ include file="../fragments/footer.jspf" %>