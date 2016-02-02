<%@ include file="../fragments/header.jspf" %>


<div class="formulariodos">
<h1>Formulario para subir un nuevo elemento a la página</h1>
	<form method="post">
	  <div class="form-group">
	    <label for="exampleInputEmail1">Nombre</label>
	    <input required type="text" class="form-control" name="nombre" id="nameElement" placeholder="Nombre del elemento">
	  </div>
	  <div class="typeElement">
	   <label for="exampleInputEmail1">Tipo de Elemento <br></label><br>
			<input required type="radio" name="tipo" value="examen"  onchange="titulosolucion()"/>
			Examen <br>
			<input required type="radio" name="tipo" value="teoria"  onchange="titulosolucion()()"/>
			Teoría <br>
		</ul>
		</div>
		<div class="texto_solucion">
		    
		    <div style="float:right">
				  <a href="https://en.wikibooks.org/wiki/LaTeX/Mathematics">Ejemplos de usos del lenguaje matemático</a>
				</div>
				
				<h4>Type something:</h4>
				
				  <textarea class="textarea1" id="marked-mathjax-input"
				          onkeyup="Preview.Update()"
				          name="comment"
				          "autofocus">**Escribe aqui**
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
		
	  <div class="form-group">
	    <label for="exampleInputPassword1">Tags</label>
	    <input required type="text" class="form-control" id="tag" placeholder="Tags a añadir(separados por ;)" name="tags">
	    

	  
	  <button type="submit" class="btn btn-default" name="enviado">Submit</button>
		<c:if test="${e:forHtml(h!=null)}"> 
	  	
	  			<script >
	  			alert("Subida con exito");

				</script>

		
		</c:if>
	
	</form>
</div>



