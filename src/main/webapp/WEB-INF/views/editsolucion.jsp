<%@ include file="../fragments/header.jspf" %>



<div class="formulariodos">
<h1>Editado de soluci�n</h1>
	<form method="post">
	  <div class="form-group">
	  </div>
		<div class="texto_solucion">
		    
		    <div style="float:right">
				  <a href="https://en.wikibooks.org/wiki/LaTeX/Mathematics">Ejemplos de usos del lenguaje matem�tico</a>
				</div>
				
				<h4>soluci�n de ${e:forHtml(s.user.login)}   puntuacion:${e:forHtml(s.puntuacion)}</h4>
				
				  <textarea class="textarea1" id="marked-mathjax-input"
				          onkeyup="Preview.Update()"
				          name="comment"
				          "autofocus">${e:forHtml(s.contenido)}
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
		
	  
	  <button type="submit" class="btn btn-default" value="${s.nombre}" name="enviadosol">Submit</button>
	</form>
</div>



<%@ include file="../fragments/footer.jspf" %>