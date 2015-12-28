<%@ include file="../fragments/header.jspf" %>
<div class="formulario">
	<form>
	 	
	 	<c:if test="${Contrasdif!=null}"> 
	  	<script type="text/javascript">
	  	alerta();

		</script>
		</c:if>
	  
		<c:choose>
    		<c:when test="${Nouser!=null}">
        		<div class="form-group has-warning has-feedback">
  			 	<label class="control-label" for="inputWarning2">Nombre de usuario </label>
  			 	<input type="text" class="form-control" id="inputWarning2" aria-describedby="inputWarning2Status" placeholder="Usuario necesario" name="user" >
  			 	<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>
  			 	<span id="inputWarning2Status" class="sr-only">(warning)</span>
 			 	</div> 
        	<br />
    		</c:when> 
    		<c:when test="${Siuser!=null}">
				<div class="form-group">
	    		<label for="exampleInputEmail1">Nombre de usuario </label>
	    		<input type="text" class="form-control" id="exampleInputEmail1" value="${Siuser}" name="user">
	  			</div>
        	<br />
    		</c:when>    
    		<c:otherwise>
        		<div class="form-group">
	    		<label for="exampleInputPassword1">Nombre de usuario </label>
	    		<input type="text" class="form-control" id="exampleInputPassword1" placeholder="Username" name="user">
	  			</div>
        	<br />
    		</c:otherwise>
		</c:choose>	  
	  	<c:choose>
    		<c:when test="${Nonomb!=null}">
        		<div class="form-group has-warning has-feedback">
  			 	<label class="control-label" for="inputWarning2">Nombre </label>
  			 	<input type="text" class="form-control" id="inputWarning2" aria-describedby="inputWarning2Status" placeholder="Name" name="nombre">
  			 	<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>
  			 	<span id="inputWarning2Status" class="sr-only">(warning)</span>
 			 	</div> 
        	<br />
    		</c:when>
    		<c:when test="${Sinomb!=null}">
				<div class="form-group">
	    		<label for="exampleInputEmail1">Nombre </label>
	    		<input type="text" class="form-control" id="exampleInputEmail1" value="${Sinomb}" name="nombre">
	  			</div>
        	<br />
    		</c:when>      
    		<c:otherwise>
        		<div class="form-group">
	    		<label for="exampleInputEmail1">Nombre </label>
	    		<input type="text" class="form-control" id="exampleInputEmail1" placeholder="Name" name="nombre">
	  			</div>
        	<br />
    		</c:otherwise>
		</c:choose>
		<c:choose>
    		<c:when test="${Noapel!=null}">
        		<div class="form-group has-warning has-feedback">
  			 	<label class="control-label" for="inputWarning2">Apellidos </label>
  			 	<input type="text" class="form-control" id="inputWarning2" aria-describedby="inputWarning2Status" placeholder="Apellido necesario" name="apellido" >
  			 	<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>
  			 	<span id="inputWarning2Status" class="sr-only">(warning)</span>
 			 	</div> 
        	<br />
    		</c:when> 
    		<c:when test="${Siapel!=null}">
				<div class="form-group">
	    		<label for="exampleInputEmail1">Apellidos </label>
	    		<input type="text" class="form-control" id="exampleInputEmail1" value="${Siapel}" name="apellido">
	  			</div>
        	<br />
    		</c:when>    
    		<c:otherwise>
        		<div class="form-group">
	    		<label for="exampleInputPassword1">Apellidos </label>
	    		<input type="text" class="form-control" id="exampleInputPassword1" placeholder="Surname" name="apellido">
	  			</div>
        	<br />
    		</c:otherwise>
		</c:choose>
		<c:choose>
    		<c:when test="${Noemail!=null}">
        		<div class="form-group has-warning has-feedback">
  			 	<label class="control-label" for="inputWarning2">Email </label>
  			 	<input type="email" class="form-control" id="inputWarning2" aria-describedby="inputWarning2Status" placeholder="Email necesario" name="email" >
  			 	<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>
  			 	<span id="inputWarning2Status" class="sr-only">(warning)</span>
 			 	</div> 
        	<br />
    		</c:when>   
    		<c:when test="${Siemail!=null}">
				<div class="form-group">
	    		<label for="exampleInputEmail1">Email </label>
	    		<input type="text" class="form-control" id="exampleInputEmail1" value="${Siemail}" name="email">
	  			</div>
        	<br />
    		</c:when>  
    		<c:otherwise>
        		<div class="form-group">
	    		<label for="exampleInputPassword1">Email </label>
	    		<input type="email" class="form-control" id="exampleInputPassword1" placeholder="Email" name="email">
	  			</div>
        	<br />
    		</c:otherwise>
		</c:choose>
		<c:choose>
    		<c:when test="${Nocontra!=null}">
        		<div class="form-group has-warning has-feedback">
  			 	<label class="control-label" for="inputWarning2">Contraseña </label>
  			 	<input type="password" class="form-control" id="inputWarning2" aria-describedby="inputWarning2Status" placeholder="Contraseña necesaria" name="contra" >
  			 	<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>
  			 	<span id="inputWarning2Status" class="sr-only">(warning)</span>
 			 	</div> 
        	<br />
    		</c:when>    
    		<c:otherwise>
        		<div class="form-group">
	   			<label for="exampleInputPassword1">Contraseña </label>
	    		<input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="contra">
	 			</div>
        	<br />
    		</c:otherwise>
		</c:choose>
		<c:choose>
    		<c:when test="${Nocontra2!=null}">
        		<div class="form-group has-warning has-feedback">
  			 	<label class="control-label" for="inputWarning2">Repita contraseña </label>
  			 	<input type="password" class="form-control" id="inputWarning2" aria-describedby="inputWarning2Status" placeholder="Contraseña necesaria" name="contra2" >
  			 	<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>
  			 	<span id="inputWarning2Status" class="sr-only">(warning)</span>
 			 	</div> 
        	<br />
    		</c:when>    
    		<c:otherwise>
        		 <div class="form-group">
	    		 <label for="exampleInputPassword1">Repita contraseña </label>
	    		 <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="contra2">
	  			 </div>
        	<br />
    		</c:otherwise>
		</c:choose>
		
		
	  <div class="form-group">
	    <label for="exampleInputFile">Foto de perfil </label>
	    <input type="file" id="exampleInputFile">
	    <p class="help-block">Example block-level help text here.</p>
	  </div>
	  <div class="checkbox">
	    <label>
	      <input type="checkbox"> De acuerdo
	    </label>
	  </div>
	  <button type="submit" class="btn btn-primary btn-lg btn-block" value="id" name="registrar">Registrar</button>
	</form>
</div>
<%@ include file="../fragments/footer.jspf" %>