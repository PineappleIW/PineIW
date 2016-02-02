<%@ include file="../fragments/header.jspf" %>
	

	<div class="titulo"> <h2>${e:forHtml(concep.nombre)}</h2></div>
	
	
  <div class="row">
    <div class="divbusqueda">
    
    <div id="marked-mathjax-preview2">${e:forHtml(concep.descripcion)}</div>
				  
			<c:choose>
				<c:when test="${concep.tipo eq 'teoria'}">
					
					<div class="votos">Puntuación: ${e:forHtml(concep.puntuacion)}</div>
				  	<div class="valoracion12">
					  	<a href="home?conceptovoto=${e:forHtml(concep.nombre)}&votos=true">
					  		<img width="4%" src="${prefix}resources/img/like.jpg">
					  	</a>
					  	<a href="home?conceptovoto=${e:forHtml(concep.nombre)}&votos=false">
					  		<img width="4%" src="${prefix}resources/img/nolike.jpg">
					  	</a>
				  	</div>
				  	
			    </c:when>
				<c:when test="${concep.tipo eq 'examen'}">
					<form>
					<div class="texto_solucion">
		    
					<div class="votos">Puntuación: ${e:forHtml(concep.puntuacion)}</div>
					
					
				  	<div class="valoracion12">
					  	<a href="home?conceptovoto=${e:forHtml(concep.nombre)}&votos=true">
					  		<img width="4%" src="${prefix}resources/img/like.jpg">
					  	</a>
					  	<a href="home?conceptovoto=${e:forHtml(concep.nombre)}&votos=false">
					  		<img width="4%" src="${prefix}resources/img/nolike.jpg">
					  	</a>
				  	</div>
					<h4>Tu solución</h4>
					
				  <textarea class="textarea1" id="marked-mathjax-input"
				          onkeyup="Preview.Update()"
				          name="comment"
				          "autofocus">
				   </textarea>
				
				  <div class="hint">Use 
				    <a href="http://en.wikibooks.org/wiki/LaTeX/Mathematics">$\LaTeX$</a>
				    to type formulæ 
				    and <a href="https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet">markdown</a> to format text.
				  </div>
				
				  <div class="preview" id="marked-mathjax-preview"></div>
				  <div class="preview" id="marked-mathjax-preview-buffer" 
				       style="display:none;
				              position:absolute; 
				              top:0; left: 0"></div>
				
				<script>
				Preview.Init();
				Preview.Update();
				</script>

		</div>
				<button type="submit" class="btn btn-default" name="enviado" value="${concep.nombre}">Submit</button>
					</form>
				</c:when>
			</c:choose>		

 </div>
 
 

	<form>
    <div class="col-md-3">
      <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="#">Conceptos Relacionados</a></li>
        <c:forEach items="${concepsr}" var="c">
        <td class="success"> <button type="submit" value="${c.nombre}" name="conce">${e:forHtml(c.nombre)}</button>
        </c:forEach>
      </ul>
      </div>
   </form>
   
   
	<c:choose>
	<c:when test="${concep.tipo eq 'examen'}">
		<form>   
   		<div class="col-md-3">
      	<ul class="nav nav-pills nav-stacked">
      			<li class="active"><a href="#">Soluciones</a></li>
        		<c:forEach items="${sols}" var="s">
        		<td class="success"> <button type="submit" value="${s.nombre}" name="sol">solución de ${e:forHtml(s.user.nombre)}   puntuacion:${e:forHtml(s.puntuacion)}</button>
        		</c:forEach>
      		</ul>
    	</div>
    	</form>
    </c:when>
    </c:choose>
    <div class="clearfix visible-lg"></div>
  </div>



<%@ include file="../fragments/footer.jspf" %>