<%@ include file="../fragments/header.jspf" %>
<style>
	.container{
		width:100%;
		float:left;
	}
	
	.busquedasteoria{
	    position: relative;
    	width: 75%;
    	float: left;
    }
	.list-group-item1{    
        position: relative;
	    display: block;
        padding: 0px 13px;
	}
</style>
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
			   <form class="filter-sec" id="facets">
			   
                                <input  id="filtrar1" type="text" name="textobusqueda" class="form-control"  type="text" />
                <h5>Tipo de elemento:</h5>
				  <div class="input-control">
                                    <input name="tipo" onChange ="form.action=teoria?conce=as" class="checkbox2" id="red2" type="radio" value="examen" />
                                    <label for="red2">Examen</label>
                                    <input name="tipo" id="yellow2" type="radio" value="teoria" />
                                    <label for="yellow2">Teoría</label>
                                    <input name="tipo" class="checkbox2" id="blue2" type="radio" value="ambos" />
                                    <label for="blue2">Ambos</label>
                                </div>
                           
				<h5>Puntuación:</h5>
				  <div class="input-control">
                       <input name="color1[]" class="checkbox1" id="red1" type="radio" value="red1" />
                       <label for="red1">4 ó más</label>
                       <input name="color1[]" class="checkbox1" id="blue1" type="radio" value="blue1" />
                       <label for="blue1">3 ó más</label>
                       <input name="color1[]" class="checkbox1" id="green1" type="radio" value="green1" />
                       <label for="green1">2 ó más</label>
                       <input name="color1[]" class="checkbox1" id="yellow1" type="radio" value="yellow1" />
                       <label for="yellow1">1 ó más</label>
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
										<h3><a href="ver_concepto?conce=${a.nombre}">${a.descripcion}</a></h3>
										<p>${a.nombre}</p>
									</div>
								</c:forEach>
								<br />
                </div>
            </div>
        </div>


<%@ include file="../fragments/footer.jspf" %>