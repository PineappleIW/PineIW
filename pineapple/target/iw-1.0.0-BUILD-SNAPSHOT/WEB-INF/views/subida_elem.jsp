<%@ include file="../fragments/header.jspf" %>



<div class="formulariodos">
<h1>Formulario para subir un nuevo elemento a la página</h1>
	<form>
	  <div class="form-group">
	    <label for="exampleInputEmail1">Nombre</label>
	    <input required type="text" class="form-control" id="nameElement" placeholder="Nombre del elemento">
	  </div>
	  <div class="typeElement">
	   <label for="exampleInputEmail1">Tipo de Elemento <br></label>
	   <ul>
			<li><input required type="radio" name="tipo" value="examen" />
			Examen <br>
			<li><input required type="radio" name="tipo" value="teoria" />
			Teoría <br>
		</ul>
		</div>
		<div class="texto_solucion">
			<label for="exampleInputEmail1">Solución</label>
			<textarea class="form-control" rows="13" cols="25"></textarea>
		</div>
		
	  <div class="form-group">
	    <label for="exampleInputPassword1">Tags</label>
	    <input required type="text" class="form-control" id="tag" placeholder="Tags a añadir(separados por ;)" name="tags">
	    
	  </div>
	  <div class="form-group">
	    <label for="exampleInputFile">Subida de archivo</label>
	    <input type="file" id="exampleInputFile">
	    <p class="help-block">Example block-level help text here.</p>
	  </div>
	  
	  <button type="submit" class="btn btn-default" name="enviado">Submit</button>
	</form>
</div>

<!--para sacar los tags que se envian-->
<c:forEach items="${tag}" var="a">
			${a}
</c:forEach>


<%@ include file="../fragments/footer.jspf" %>