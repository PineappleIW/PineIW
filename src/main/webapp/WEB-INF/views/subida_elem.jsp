<%@ include file="../fragments/header.jspf" %>

<!--                          -->


<style>
	
.hint {
    text-align: right;
    color: #555555;
    font-size: small;
}
.hint a {
    color: #555555;
}
.textarea1 {
    margin-top: 1ex;
    width: 100%;
    height: 20em;
}
.preview {
    border:1px dotted; 
    padding: 3px; 
    width: 99%;
    margin-top:1ex;
}
</style>

<script src="${prefix}resources/js/cuadro_texto.js"></script>

<script type="text/x-mathjax-config">
  MathJax.Hub.Config({
    showProcessingMessages: false,
    tex2jax: { inlineMath: [['$','$'],['\\(','\\)']] },
    TeX: { equationNumbers: {autoNumber: "AMS"} }
  });
</script>
<script type="text/javascript" src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>

<script>
marked.setOptions({
  renderer: new marked.Renderer(),
  gfm: true,
  tables: true,
  breaks: false,
  pedantic: false,
  sanitize: false, // IMPORTANT, because we do MathJax before markdown,
                   //            however we do escaping in 'CreatePreview'.
  smartLists: true,
  smartypants: false
});
</script>

<script>
var Preview = {
  delay: 50,        // delay after keystroke before updating

  preview: null,     // filled in by Init below
  buffer: null,      // filled in by Init below

  timeout: null,     // store setTimout id
  mjRunning: false,  // true when MathJax is processing
  oldText: null,     // used to check if an update is needed

  //
  //  Get the preview and buffer DIV's
  //
  Init: function () {
    this.preview = document.getElementById("marked-mathjax-preview");
    this.buffer = document.getElementById("marked-mathjax-preview-buffer");
    this.textarea = document.getElementById("marked-mathjax-input");
  },

  //
  //  Switch the buffer and preview, and display the right one.
  //  (We use visibility:hidden rather than display:none since
  //  the results of running MathJax are more accurate that way.)
  //
  SwapBuffers: function () {
    var buffer = this.preview;
    var preview = this.buffer;
    this.buffer = buffer;
    this.preview = preview;
    buffer.style.display = "none";
    buffer.style.position = "absolute";
    preview.style.position = ""; 
    preview.style.display = "";
  },

  //
  //  This gets called when a key is pressed in the textarea.
  //  We check if there is already a pending update and clear it if so.
  //  Then set up an update to occur after a small delay (so if more keys
  //    are pressed, the update won't occur until after there has been 
  //    a pause in the typing).
  //  The callback function is set up below, after the Preview object is set up.
  //
  Update: function () {
    if (this.timeout) {clearTimeout(this.timeout)}
    this.timeout = setTimeout(this.callback,this.delay);
  },

  //
  //  Creates the preview and runs MathJax on it.
  //  If MathJax is already trying to render the code, return
  //  If the text hasn't changed, return
  //  Otherwise, indicate that MathJax is running, and start the
  //    typesetting.  After it is done, call PreviewDone.
  //  
  CreatePreview: function () {
    Preview.timeout = null;
    if (this.mjRunning) return;
    var text = this.textarea.value;
    if (text === this.oldtext) return;
    text = this.Escape(text);                       //Escape tags before doing stuff
    this.buffer.innerHTML = this.oldtext = text;
    this.mjRunning = true;
    MathJax.Hub.Queue(
      ["Typeset",MathJax.Hub,this.buffer],
      ["PreviewDone",this],
      ["resetEquationNumbers", MathJax.InputJax.TeX]
    );
  },

  //
  //  Indicate that MathJax is no longer running,
  //  do markdown over MathJax's result, 
  //  and swap the buffers to show the results.
  //
  PreviewDone: function () {
    this.mjRunning = false;
    text = this.buffer.innerHTML;
    // replace occurrences of &gt; at the beginning of a new line
    // with > again, so Markdown blockquotes are handled correctly
    text = text.replace(/^&gt;/mg, '>');
    this.buffer.innerHTML = marked (text);
    this.SwapBuffers();
  },

  Escape: function (html, encode) {
    return html
      .replace(!encode ? /&(?!#?\w+;)/g : /&/g, '&amp;')
      .replace(/</g, '&lt;')
      .replace(/>/g, '&gt;')
      .replace(/"/g, '&quot;')
     .replace(/'/g, '&#39;');
  },

  // The idea here is to perform fast updates. See http://stackoverflow.com/questions/11228558/let-pagedown-and-mathjax-work-together/21563171?noredirect=1#comment46869312_21563171
  // But our implementation is a bit buggy: flickering, bad rendering when someone types very fast.
  //
  // If you want to enable such buggy fast updates, you should 
  // add something like  onkeypress="Preview.UpdateKeyPress(event)" to textarea's attributes.
  UpdateKeyPress: function (event) {
    if (event.keyCode < 16 || event.keyCode > 47) {
      this.preview.innerHTML = '<p>' + marked(this.textarea.value) + '</p>';
      this.buffer.innerHTML = '<p>' + marked(this.textarea.value) + '</p>';
    }
    this.Update();
  }
  
};

//
//  Cache a callback to the CreatePreview action
//
Preview.callback = MathJax.Callback(["CreatePreview",Preview]);
Preview.callback.autoReset = true;  // make sure it can run more than once</script>



 <!-- -->

<div class="formulariodos">
<h1>Formulario para subir un nuevo elemento a la página</h1>
	<form>
	  <div class="form-group">
	    <label for="exampleInputEmail1">Nombre</label>
	    <input required type="text" class="form-control" id="nameElement" placeholder="Nombre del elemento">
	  </div>
	  <div class="typeElement">
	   <label for="exampleInputEmail1">Tipo de Elemento <br></label><br>
			<input required type="radio" name="tipo" value="examen"  onchange="titulosolucion()"/>
			Examen <br>
			<input required type="radio" name="tipo" value="teoria"  onchange="titulosolucion()()"/>
			Teoría <br>
		</ul>
		</div>
		<div class="texto_solucion">
			<label for="exampleInputEmail1" id="titulosolucion">Dime la pregunta</label>
		    
		    <div style="float:right">
				  <a href="https://en.wikibooks.org/wiki/LaTeX/Mathematics">Ejemplos de usos del lenguaje matemático</a>
				</div>
				
				<h4>Type something:</h4>
				
				  <textarea class="textarea1" id="marked-mathjax-input"
				          onkeyup="Preview.Update()"
				          name="comment"
				          "autofocus">**Escribe aqui**
				   </textarea>
				
				  <div class="hint">Use 
				    <a href="http://en.wikibooks.org/wiki/LaTeX/Mathematics">$\LaTeX$</a>
				    to type formulæ 
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



<%@ include file="../fragments/footer.jspf" %>
