<%@ include file="../fragments/header.jspf" %>

    <script>
        $(function() {
            $(".selecter_label_1").selecter({
                defaultLabel: "Select a Make"
            });
            $(".selecter_label_2").selecter({
                defaultLabel: "Select A Model"
            });
            $(".selecter_label_3").selecter({
                defaultLabel: "Condition"
            });
            $(".selecter_label_4").selecter({
                defaultLabel: "Transmission"
            });
            $("input[type=checkbox], input[type=radio]").picker();      

            $('#filtrar1').keyup(function () {
                var rex = new RegExp($(this).val(), 'i');
                $('.list-group-item1').hide();
                $('.list-group-item1').filter(function () {
                    return rex.test($(this).text());
                }).show();
            })
        });

    </script>


        <div class="container">
            <div class="row">

                <div class="col-md-3 col-sm-4"> 
                    <!-- left sec -->
                    <div class="left-sec">
                        <div class="left-cont">
                             <h6><span class="glyphicon glyphicon-search"></span> Refine Search</h6>
			   <form class="filter-sec" method="GET" id="facets">
				   
	               <input  id="filtrar1" type="text" name="textobusqueda" class="form-control"  type="text" />
	                <h5>Tipo de elemento:</h5>
					<div class="input-control">
	                	<input name="tipo" class="checkbox2" id="red2" type="radio" value="examen" />
	                    <label for="red2">Examen</label>
	                	<input name="tipo" id="yellow2" type="radio" value="teoria" />
	                	<label for="yellow2">Teoría</label>
	                    <input name="tipo" class="checkbox2" id="blue2" type="radio" value="ambos" checked/>
	                    <label for="blue2">Ambos</label>
	                </div>
	                
	                <h5>Ordenar por:</h5>
					  <div class="input-control">
					  	   <input name="valoracion" class="checkbox1" id="red22" type="radio" value="ninguna" checked/>
	                       <label for="red22">Ninguna</label>
	                       <input name="valoracion" class="checkbox1" id="red1" type="radio" value="desc"/>
	                       <label for="red1">Mayor Valoración</label>
	                       <input name="valoracion" class="checkbox1" id="blue1" type="radio" value="asc" />
	                       <label for="blue1">Menos Valoraciones</label>
	                   </div>                                
	                         <input type="submit" class="btn btn-default" value="Actualizar" name="actualizar">
                </form>
                        </div>
                    </div>
                </div>
                <div class="busquedasteoria">
								<h2>Contenido en la web</h2>
									<c:forEach items="${t}" var="a">
									<div class=list-group-item1>
										<h3><a href="home?conce=${e:forHtml(a.nombre)}">${e:forHtml(a.descripcion)}</a></h3>
										<p>${e:forHtml(a.nombre)}<span style="float: right;">Valoración: ${a.puntuacion}</span></p>
									</div>
								</c:forEach>
								<br />
                </div>
            </div>
        </div>


<%@ include file="../fragments/footer.jspf" %>