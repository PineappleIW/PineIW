<%@ include file="../fragments/header.jspf" %>
	


	<div class="titulo"> <h2>solución de ${e:forHtml(solucion.user.nombre)}</h2></div>
	
	
  <div class="row">
    <div class="divbusqueda">
    
    <div id="marked-mathjax-preview2">${e:forHtml(solucion.contenido)}</div>
				  
				  
	<div class="votos">Puntuación: ${e:forHtml(solucion.puntuacion)}</div>
				  	<div class="valoracion12">
					  	<a href="home?solucionvoto=${e:forHtml(solucion.nombre)}&votos=true">
					  		<img width="4%" src="${prefix}resources/img/like.jpg">
					  	</a>
					  	<a href="home?solucionvoto=${e:forHtml(solucion.nombre)}&votos=false">
					  		<img width="4%" src="${prefix}resources/img/nolike.jpg">
					  	</a>
				  	</div>				  
				  
						

 </div>
 
 	
 

	<form>
    <div class="col-md-3">
      <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="#">Conceptos Relacionados</a></li>
        <c:forEach items="${sols}" var="s">
        <td class="success"> <button type="submit" value="${s.nombre}" name="sol">solución de ${e:forHtml(s.user.nombre)}   puntuacion:${e:forHtml(s.puntuacion)}</button>
        </c:forEach>
      </ul>
      </div>
   </form>
    <div class="clearfix visible-lg"></div>
  </div>
</div>


<%@ include file="../fragments/footer.jspf" %>