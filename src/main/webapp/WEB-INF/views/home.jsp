<%@ include file="../fragments/header.jspf" %>
<%@ taglib 
   uri="https://www.owasp.org/index.php/OWASP_Java_Encoder_Project" 
   prefix="e" %>
<style>
.list-group-item {
	width: 274px;
	
	}  

</style>

<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img class="imagenes" src="http://www.abc.es/Media/201402/25/einstein-documento1--644x460.jpg" alt="...">
      
      <div class="carousel-caption">
        Teoria
      </div>
    </div>
    <div class="item">
      <img class="imagenes" src="http://www.aquenosabias.net/wp-content/uploads/2012/12/18093_470911522947378_813889427_n.jpg" alt="...">
      <div class="carousel-caption">
        esto es una pruesdasdsa...
      </div>
    </div>
 
    </style>
    <div class="item">
      <img class="imagenes" src="https://jrbenjamin.files.wordpress.com/2014/01/einstein.jpg" alt="...">
      <div class="carousel-caption">
        .assasas.
      </div>
    </div>
    ...
  </div>
  
</div>

<div class="articulo">
	<h2>TOP TAGS</h2>
	<c:forEach items="${t}" var="a">
		<div class="tags">${e:forHtml(a)}</div>
	</c:forEach>
</div>
<div class="articulo">
	<h2>TOP USUARIOS</h2>
	
	<ul class=list-group>
	<c:forEach items="${u}" var="a">
		<div class=list-group-item>${e:forHtml(a)}</div><br />
	</c:forEach>
	</ul><br />

</div>
<div class="articulo">
	<h2>TOP CONCEPTOS</h2>
	<ul class=list-group>
	<c:forEach items="${c}" var="a">
		<form>
		<button type="submit" name="conce" value="${a}">${e:forHtml(a)}</button>
		</form>
		<br />
	</c:forEach>
	</ul><br />
</div>



<%@ include file="../fragments/footer.jspf" %>