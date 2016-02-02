<%@ include file="../fragments/header.jspf" %>
<div class="formulario">
	<form method="post">
	 	
	 	<c:if test="${e:forHtml(Contrasdif!=null)}"> 
	  	<script type="text/javascript">
	  	alerta();

		</script>
		</c:if>
	  
		<c:choose>
    		<c:when test="${e:forHtml(Nouser!=null)}">
        		<div class="form-group has-warning has-feedback">
  			 	<label class="control-label" for="inputWarning2">Nombre de usuario </label>
  			 	<input type="text" class="form-control" id="inputWarning2" aria-describedby="inputWarning2Status" placeholder="Usuario necesario" name="user" >
  			 	<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>
  			 	<span id="inputWarning2Status" class="sr-only">(warning)</span>
 			 	</div> 
        	<br />
    		</c:when> 
    		<c:when test="${e:forHtml(Siuser!=null)}">
				<div class="form-group">
	    		<label for="exampleInputEmail1">Nombre de usuario </label>
	    		<input type="text" class="form-control" id="exampleInputEmail1" value="${e:forHtml(Siuser)}" name="user">
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
    		<c:when test="${e:forHtml(Nonomb!=null)}">
        		<div class="form-group has-warning has-feedback">
  			 	<label class="control-label" for="inputWarning2">Nombre </label>
  			 	<input type="text" class="form-control" id="inputWarning2" aria-describedby="inputWarning2Status" placeholder="Name" name="nombre">
  			 	<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>
  			 	<span id="inputWarning2Status" class="sr-only">(warning)</span>
 			 	</div> 
        	<br />
    		</c:when>
    		<c:when test="${e:forHtml(Sinomb!=null)}">
				<div class="form-group">
	    		<label for="exampleInputEmail1">Nombre </label>
	    		<input type="text" class="form-control" id="exampleInputEmail1" value="${e:forHtml(Sinomb)}" name="nombre">
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
    		<c:when test="${e:forHtml(Noapel!=null)}">
        		<div class="form-group has-warning has-feedback">
  			 	<label class="control-label" for="inputWarning2">Apellidos </label>
  			 	<input type="text" class="form-control" id="inputWarning2" aria-describedby="inputWarning2Status" placeholder="Apellido necesario" name="apellido" >
  			 	<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>
  			 	<span id="inputWarning2Status" class="sr-only">(warning)</span>
 			 	</div> 
        	<br />
    		</c:when> 
    		<c:when test="${e:forHtml(Siapel!=null)}">
				<div class="form-group">
	    		<label for="exampleInputEmail1">Apellidos </label>
	    		<input type="text" class="form-control" id="exampleInputEmail1" value="${e:forHtml(Siapel)}" name="apellido">
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
    		<c:when test="${e:forHtml(Noemail!=null)}">
        		<div class="form-group has-warning has-feedback">
  			 	<label class="control-label" for="inputWarning2">Email </label>
  			 	<input type="email" class="form-control" id="inputWarning2" aria-describedby="inputWarning2Status" placeholder="Email necesario" name="email" >
  			 	<span class="glyphicon glyphicon-warning-sign form-control-feedback" aria-hidden="true"></span>
  			 	<span id="inputWarning2Status" class="sr-only">(warning)</span>
 			 	</div> 
        	<br />
    		</c:when>   
    		<c:when test="${e:forHtml(Siemail!=null)}">
				<div class="form-group">
	    		<label for="exampleInputEmail1">Email </label>
	    		<input type="text" class="form-control" id="exampleInputEmail1" value="${e:forHtml(Siemail)}" name="email">
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
    		<c:when test="${e:forHtml(Nocontra!=null)}">
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
    		<c:when test="${e:forHtml(Nocontra2!=null)}">
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
		
		
	 
	  <button type="submit" class="btn btn-primary btn-lg btn-block" value="id" name="registrar">Registrar</button>
				<c:if test="${e:forHtml(fallo!=null)}"> 
	  	
	  			<script >
	  			alert("nombre de usuario no disponible");

				</script>

		
				</c:if>
	</form>
</div>
<%@ include file="../fragments/footer.jspf" %>