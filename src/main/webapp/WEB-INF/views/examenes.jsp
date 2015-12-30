<%@ include file="../fragments/header.jspf" %>



<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  
<script type="text/javascript">

</script>


<div class="container">
  <div class="jumbotron">
 
    <h1><div class=tit>Portal de examenes</div></h1>      
    <p>el 5 es el nuevo 10</p>      
   <input type="text" class="form-control" placeholder="Introduce materia/año/dificultad"> 
   <div class=botonmarg><a href="#" class="btn btn-info btn-lg"><span class="glyphicon glyphicon-search"></span> Search</a></div>
  
  </div>

  <div id="accordion">
  <h3>Materia</h3>
	  <div>
	    	<ul class="nav nav-pills nav-stacked" >
		        <li><a href="examenes_sol">-  EDA</a></li>
		        <li><a href="examenes_sol">- IW</a></li>
		        <li><a href="examenes_sol">- TP</a></li>
	      	</ul>
	  </div>
  <h3>Años</h3>
	  <div>
	    	<ul class="nav nav-pills nav-stacked" >
		        <li><a href="examenes_sol">-  EDA</a></li>
		        <li><a href="examenes_sol">- IW</a></li>
		        <li><a href="examenes_sol">- TP</a></li>
	      	</ul>
	  </div>
  <h3>Campos</h3>
	  <div>
	    	<ul class="nav nav-pills nav-stacked" >
		        <li><a href="examenes_sol">-  EDA</a></li>
		        <li><a href="examenes_sol">- IW</a></li>
		        <li><a href="examenes_sol">- TP</a></li>
	      	</ul>
	  </div>
  <h3>Temas</h3>
  <div>
   		 <ul class="nav nav-pills nav-stacked" >
	        <li><a href="examenes_sol">-  EDA</a></li>
	        <li><a href="examenes_sol">- IW</a></li>
	        <li><a href="examenes_sol">- TP</a></li>
      	</ul>
  </div>
</div>



<%@ include file="../fragments/footer.jspf" %>
