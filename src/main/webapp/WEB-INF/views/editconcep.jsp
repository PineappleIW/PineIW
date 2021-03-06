<%@ include file="../fragments/header.jspf" %>



<div class="formulariodos">
<h1>Editado de concepto</h1>
	<form method="post">
	  <div class="form-group">
	    <label for="exampleInputEmail1">Nombre</label>
	    <input required type="text" class="form-control" value="${c.nombre}" name="nombre" id="nameElement" placeholder="Nombre del elemento">
	  </div>
		<div class="texto_solucion">
		    
		    <div style="float:right">
				  <a href="https://en.wikibooks.org/wiki/LaTeX/Mathematics">Ejemplos de usos del lenguaje matem�tico</a>
				</div>
				
				<h4>Descripci�n</h4>
				
				  <textarea class="textarea1" id="marked-mathjax-input"
				          onkeyup="Preview.Update()"
				          name="comment"
				          "autofocus">${e:forHtml(c.descripcion)}
				   </textarea>
				
				  <div class="hint">Use 
				    <a href="http://en.wikibooks.org/wiki/LaTeX/Mathematics">$\LaTeX$</a>
				    to type formul� 
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
		
	  
	  <button type="submit" class="btn btn-default" value="${c.nombre}" name="enviado">Submit</button>
	</form>
</div>



<%@ include file="../fragments/footer.jspf" %>