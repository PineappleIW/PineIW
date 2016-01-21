<%@ include file="../fragments/header.jspf" %>
	
	<div class="titulo">
		<h2 >Nombre Examen</h2>
	</div>
	<style>
		.ec-stars-wrapper {
			/* Espacio entre los inline-block (los hijos, los `a`) 
			   http://ksesocss.blogspot.com/2012/03/display-inline-block-y-sus-empeno-en.html */
			font-size: 0;
			/* Podríamos quitarlo, 
				pero de esta manera (siempre que no le demos padding), 
				sólo aplicará la regla .ec-stars-wrapper:hover a cuando
				también se esté haciendo hover a alguna estrella */
			display: inline-block;
		}
		.ec-stars-wrapper a {
			text-decoration: none;
			display: inline-block;
			/* Volver a dar tamaño al texto */
			font-size: 32px;
			font-size: 2rem;
			
			color: #888;
		}
		
		.ec-stars-wrapper:hover a {
			color: rgb(39, 130, 228);
		}
		/*
		 * El selector de hijo, es necesario para aumentar la especifidad
		 */
		.ec-stars-wrapper > a:hover ~ a {
			color: #888;
		}
	</style>
	
  <div class="row">
    <div class="divbusqueda">
     	Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed pulvinar magna in nulla tincidunt, eget dictum leo fringilla. Etiam sed enim nisi. Sed luctus at arcu non tempor. Phasellus vitae porta turpis. Mauris ut convallis urna. In cursus odio ac leo mollis, eu gravida ligula dictum. Mauris a libero cursus tortor auctor feugiat. Duis pretium malesuada quam, eu commodo nunc. Etiam facilisis vel magna sit amet pretium. Integer gravida tortor eros, et imperdiet felis pharetra eget. Mauris porttitor sollicitudin mauris sit amet ullamcorper. Donec porta neque nec mi viverra consectetur.

Nullam posuere, lectus vitae facilisis iaculis, neque ipsum tempor mi, vel aliquam ex purus ac mi. Aliquam hendrerit purus sit amet elit ornare gravida. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris leo orci, pretium vel gravida sit amet, tincidunt tempus ex. Vestibulum varius, diam vitae auctor faucibus, dui ante malesuada metus, lacinia porttitor mauris velit in erat. Maecenas consectetur dolor sed nunc dictum, non accumsan ex hendrerit. Vestibulum tincidunt pretium augue, at dictum est fringilla eu. Morbi in nisi accumsan, commodo nunc nec, convallis arcu. Quisque at nisi sapien.
<br> 
	<div class="ec-stars-wrapper">
		<a href="#" data-value="1" title="Votar con 1 estrellas">&#9733;</a>
		<a href="#" data-value="2" title="Votar con 2 estrellas">&#9733;</a>
		<a href="#" data-value="3" title="Votar con 3 estrellas">&#9733;</a>
		<a href="#" data-value="4" title="Votar con 4 estrellas">&#9733;</a>
		<a href="#" data-value="5" title="Votar con 5 estrellas">&#9733;</a>
	</div>
<br>
	<div class="ec-stars-wrapper">
		<a href="#" data-value="1" title="Votar con 1 estrellas">&#9733;</a>
		<a href="#" data-value="2" title="Votar con 2 estrellas">&#9733;</a>
		<a href="#" data-value="3" title="Votar con 3 estrellas">&#9733;</a>
		<a href="#" data-value="4" title="Votar con 4 estrellas">&#9733;</a>
		<a href="#" data-value="5" title="Votar con 5 estrellas">&#9733;</a>
	</div>
	<noscript>Necesitas tener habilitado javascript para poder votar</noscript>
	

<br>
<textarea class="form-control" rows="3"></textarea>

<div class="botonmarg">
<button type="button" class="btn btn-primary btn-lg">RESOLVER</button>
</div>

    </div>
    <div class="col-md-3">
      <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="#">Apuntes</a></li>
        <li><a href="#">MDL_Completo</a></li>
        <li><a href="#">MDL_Ejercicios_resueltos</a></li>
        <li><a href="#">MDL_Apuntes_clase</a></li>
      </ul>
      <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="#">Soluciones</a></li>
        <li><a href="#">Archer: 1945 votos</a></li>
        <li><a href="#">Juan: 223 votos</a></li>
        <li><a href="#">Pepe: 27 votos</a></li>
      </ul>
    </div>
    <div class="clearfix visible-lg"></div>
  </div>
</div>


<%@ include file="../fragments/footer.jspf" %>