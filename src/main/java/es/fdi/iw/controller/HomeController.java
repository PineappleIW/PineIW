package es.fdi.iw.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.fdi.iw.model.Concepto;
import es.fdi.iw.model.Solucion;
import es.fdi.iw.model.Tag;
import es.fdi.iw.model.Tageo;
import es.fdi.iw.model.Usuario;

/**
 * Una aplicación de ejemplo para IW.
 */
@Controller
public class HomeController {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public String getDate(Locale locale) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(
				DateFormat.LONG, DateFormat.LONG, locale);		
		return dateFormat.format(date);				
	}
	
	@RequestMapping(value = "/teoria", method = RequestMethod.GET)
	public ModelAndView teoria() {
    	ModelAndView model = new ModelAndView("teoria");
    	List<Concepto> conceps = (List<Concepto>)entityManager
					.createNamedQuery("concepts")
					.getResultList();
	    	List<String> conceptos= new ArrayList<String>();
	    	for (Concepto con: conceps) {  
	    		 conceptos.add(con.getNombre()+" - "+con.getUser().getLogin());
	    	}
	    	model.addObject("t",conceps);
    	return model;
	}
	
		@Transactional
		@RequestMapping(value = "/teoria", params={"actualizar"})
	public ModelAndView teoria(@RequestParam ("tipo") String tipo,
			@RequestParam ("textobusqueda") String texto
			) {
			ModelAndView model = new ModelAndView("teoria");
			List<Concepto> conceps;
			if(tipo.equals("ambos") || tipo.equals("")){
				conceps = (List<Concepto>)entityManager
						.createNamedQuery("concepts")
						.getResultList();
		    	List<String> conceptos= new ArrayList<String>();
		    	for (Concepto con: conceps) {  
		    		 conceptos.add(con.getNombre()+" - "+con.getUser().getLogin());
		    	}
				
			}
	    	else{
	    		conceps = (List<Concepto>)entityManager
						.createNamedQuery("getTipo")
						.setParameter("tipo", tipo)
						.getResultList();
		    	List<String> conceptos= new ArrayList<String>();
		    	for (Concepto con: conceps) {  
		    		 conceptos.add(con.getNombre()+" - "+con.getUser().getLogin());
		    	}
	    	}
	    	
	  
    	model.addObject("t",conceps);
   
		return model;
	}
	
	@RequestMapping(value = "/examenes", method = RequestMethod.GET)
	public String examenes() {
		return "examenes";
	}
	
	@RequestMapping(value = "/ver_concepto", method = RequestMethod.GET)
	public String ver_concepto() {
		return "ver_concepto";
	}
	
	@RequestMapping(value = "/solucion", method = RequestMethod.GET)
	public String solucion() {
		return "solucion";
	}
	
	
	
	
	@RequestMapping(value = "/subida_elem", method = RequestMethod.GET)
	public ModelAndView subir_ele() {
    	ModelAndView model = new ModelAndView("subida_elem");
    	return model;
	}
	
	@Transactional
	@RequestMapping(value = "/subida_elem", params={"enviado"})
	public ModelAndView subir_elem(@RequestParam ("tags") String tag,
			@RequestParam ("tipo") String tipo,HttpSession session,
			@RequestParam ("comment") String descrip,
			@RequestParam ("nombre") String nombre) {
    	ModelAndView model = new ModelAndView("subida_elem");
    	
    	Usuario u = (Usuario)session.getAttribute("user");
    	Concepto c=Concepto.createConcept(u, nombre, descrip, tipo);
    	
    	tag.replace(" ", "");
    	String[] cadena= tag.split(";");
    	List<Tag> tags = (List<Tag>)entityManager
				.createNamedQuery("tags")
				.getResultList();
    	Tag t=null;
    	for (int i=0; i<cadena.length; i++){
    		boolean encon=false;
    		for (Tag tagpru: tags) {  
       		    if (tagpru.getNombre().equalsIgnoreCase(cadena[i])){
       		    	encon=true;
       		    	t=tagpru;
       		    }
    		}
    		if (!encon){
    			t=Tag.createTag(cadena[i]);
    			Tageo ta=Tageo.createTageo(c, t);
    			t.addTageo(ta);
    			c.addTageo(ta);
    			entityManager.persist(ta);
    			entityManager.persist(t);
    		    }
    		else{
    			Tageo ta=Tageo.createTageo(c, t);
    			t.addTageo(ta);
    			c.addTageo(ta);
    			entityManager.persist(ta);
    			entityManager.persist(t);
    	}
    	}
    	entityManager.persist(c);
    	return model;
	}
	
	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
	public ModelAndView usuario(HttpSession session) {
		ModelAndView ret= new ModelAndView("usuario");
		Usuario u = (Usuario)session.getAttribute("user");
		ret.addObject("usuario",u);
		List<Concepto> concepsuser = (List<Concepto>)entityManager
				.createNamedQuery("conceptsByUser")
				.setParameter("userParam", u)
				.getResultList();
		ret.addObject("lista",concepsuser);
		return ret;
		
	}
	
	@RequestMapping(value = "/examenes_sol", method = RequestMethod.GET)
	public String examenes_sol() {
		return "examenes_sol";
	}
	
	// para ver cualquier concepto
	
	@RequestMapping(value = "/home",params={"conce"},method = RequestMethod.GET)
	public ModelAndView ver_concepto(@RequestParam ("conce") String nombre) {
		ModelAndView ret=new ModelAndView("ver_concepto");
		Concepto concep = (Concepto)entityManager
				.createNamedQuery("conceptByName")
				.setParameter("concepParam", nombre)
				.getSingleResult();
	
		List<Tag> tags = (List<Tag>)entityManager
						.createNamedQuery("tagsByConcep")
						.setParameter("concep", concep)
						.getResultList();

	
		List<Concepto> concepsrel= new ArrayList<Concepto>();
		for (Tag ta: tags) {  
		    List<Concepto> con= (List<Concepto>)entityManager
		    			.createNamedQuery("concepsByTag")
		    			.setParameter("tag",ta)
		    			.getResultList();
		    for (Concepto c: con){
		    	if ((c!=concep)&&(!concepsrel.contains(c))){
		    		concepsrel.add(c);
		    	}
		    }
		}
		List<Solucion> soluciones=(List<Solucion>)entityManager
    			.createNamedQuery("solucionesByConcep")
    			.setParameter("concep",concep)
    			.getResultList();
		ret.addObject("sols",soluciones);
		ret.addObject("concepsr",concepsrel);
		ret.addObject("concep",concep);
		
		return ret;
	}
	
	@Transactional
	@RequestMapping(value = "/home",params={"enviado"},method = RequestMethod.GET)
	public ModelAndView ver_conceptoconsol(@RequestParam ("enviado") String nombre,
										   @RequestParam ("comment") String contenido,
										   HttpSession session) {
		
		
			
			
		Usuario u = (Usuario)session.getAttribute("user");
		ModelAndView ret=new ModelAndView("redirect:home");
		Concepto con = (Concepto)entityManager
				.createNamedQuery("conceptByName")
				.setParameter("concepParam", nombre)
				.getSingleResult();
		
		Solucion sol=Solucion.CreateSolucion(con, u, contenido);
		entityManager.persist(sol);
		
		return ret;
	}
	
	
	
	@RequestMapping(value = "/home",params={"sol"},method = RequestMethod.GET)
	public ModelAndView ver_concepto3(@RequestParam ("sol") String nombre) {
		ModelAndView ret=new ModelAndView("solucion");
		Solucion solucion=(Solucion)entityManager
				.createNamedQuery("solucionByName")
				.setParameter("nombre", nombre)
				.getSingleResult();
		
		List<Solucion> sols = (List<Solucion>)entityManager
				.createNamedQuery("solucionesByConcep")
				.setParameter("concep", solucion.getExamen())
				.getResultList();
		
		sols.remove(solucion);
		
		ret.addObject("solucion",solucion);
		ret.addObject("sols",sols);
		
		return ret;
	}
	
	@RequestMapping(value ="/solucion",params={"sol"},method = RequestMethod.GET)
	public ModelAndView solucion(@RequestParam ("sol") String nombre) {
		ModelAndView ret=new ModelAndView("ver_concepto");
		Solucion solucion=(Solucion)entityManager
				.createNamedQuery("solucionByName")
				.setParameter("sol", nombre)
				.getSingleResult();
		
		List<Solucion> sols = (List<Solucion>)entityManager
				.createNamedQuery("solucionesByConcep")
				.setParameter("examen", solucion.getExamen())
				.getSingleResult();
		
		
		
		ret.addObject("solucion",solucion);
		ret.addObject("sols",sols);
		
		return ret;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	


	// metodo inicial de admin, sera el encargado de buscar todos los articulos de teoria y examenes y sacarlos por pantalla
	@RequestMapping(value ="/admin",method = RequestMethod.GET)
	public ModelAndView administrador() {
	
		
    	ModelAndView model = new ModelAndView("admin");
    	List<Usuario> usuarios = (List<Usuario>)entityManager
				.createNamedQuery("users")
				.getResultList();
    	model.addObject("u",usuarios);
    	
    	
    	List<Concepto> concepsden = (List<Concepto>)entityManager
				.createNamedQuery("conceptsDenun")
				.getResultList();
    	 model.addObject("o",concepsden);
    	
    	List<Concepto> conceps = (List<Concepto>)entityManager
				.createNamedQuery("concepts")
				.getResultList();
    	 model.addObject("i",conceps);
    	return model;
	}
	

	@Transactional
	@RequestMapping(value="/admin", params={"borrado"})
	public ModelAndView borraruno(@RequestParam ("borrado") String concep){
		ModelAndView model2 = new ModelAndView("redirect:admin");
		Concepto concepto = (Concepto)entityManager
				.createNamedQuery("conceptByName")
				.setParameter("concepParam", concep)
				.getSingleResult();
		concepto.borrauser();
		entityManager.remove(concepto);
		return model2;
	}
	

	@RequestMapping(value="/admin", params={"editaconcep"})
	public ModelAndView editado(@RequestParam ("editaconcep") String concep){
		ModelAndView model2 = new ModelAndView("editconcep");
		Concepto concepto = (Concepto)entityManager
				.createNamedQuery("conceptByName")
				.setParameter("concepParam", concep)
				.getSingleResult();
		model2.addObject("c",concepto);
		return model2;
	}
	
	
	
	@Transactional
	@RequestMapping(value="/admin", params={"borradous"})
	public ModelAndView borrarus(@RequestParam ("borradous") String log){
		ModelAndView model2 = new ModelAndView("redirect:admin");
		Usuario usuario = (Usuario)entityManager
				.createNamedQuery("userByLogin")
				.setParameter("loginParam", log)
				.getSingleResult();
		
		/*borro todos los conceptos asociados al usuario*/
		List<Concepto> conceptos = (List<Concepto>)entityManager
				.createNamedQuery("conceptsByUser")
				.setParameter("userParam", usuario)
				.getResultList();
		for (Concepto con: conceptos) {  
		    entityManager.remove(con);
		}
		entityManager.remove(usuario);
		return model2;
	}
	
	@Transactional
	@RequestMapping(value="/admin", params={"enviado"})
	public ModelAndView editadoconcepto (@RequestParam ("nombre") String nombre,
			@RequestParam ("comment") String descrip){
		ModelAndView model2 = new ModelAndView("redirect:admin");
		Concepto concepto = (Concepto)entityManager
				.createNamedQuery("conceptByName")
				.setParameter("concepParam", nombre)
				.getSingleResult();
		concepto.setDescripcion(descrip);
		concepto.setNombre(nombre);
		entityManager.persist(concepto);
		return model2;
	}
	
	@RequestMapping(value ="/registro", method = RequestMethod.GET)
	public ModelAndView registro() {
    	ModelAndView model = new ModelAndView("registro");
    	model.addObject("fracaso", "Usuario no tuvo éxito");
    	return model;
	}
	
	
	@Transactional
	@RequestMapping(value="/registro", params={"registrar"})
    public ModelAndView registrar(@RequestParam ("nombre") String nom,@RequestParam ("apellido") String apell,
    		@RequestParam ("email") String emai,@RequestParam ("contra") String con,
    		@RequestParam ("contra2") String con2, @RequestParam ("user") String us){
    	
		
    	if(nom.equalsIgnoreCase("admin")){
    		return new ModelAndView("redirect:admin");
    	}
    	else {
    	boolean correcto=true;
    	ModelAndView model = new ModelAndView("registro");
       	if(us.equalsIgnoreCase("")){
    		model.addObject("Nouser", "ERROR");
    		correcto=false;
    	}
    	else {
    		model.addObject("Siuser", us);
    	}
    	if(nom.equalsIgnoreCase("")){
    		model.addObject("Nonomb", "ERROR");
    		correcto=false;
    	}
    	else {
    		model.addObject("Sinomb", nom);
    	}
    	if(apell.equalsIgnoreCase("")){
        	model.addObject("Noapel", "apell no tuvo éxito");
        	correcto=false;
        }
    	else {
    		model.addObject("Siapel", apell);
    	}
    	if(emai.equalsIgnoreCase("")){
        	model.addObject("Noemail", "emai no tuvo éxito");
        	correcto=false;
        }
    	else {
    		model.addObject("Siemail", emai);
    	}
    	if(con.equalsIgnoreCase("")){
        	model.addObject("Nocontra", "con no tuvo éxito");
        	correcto=false;
        }else {
    	if(con2.equalsIgnoreCase("")){
        	model.addObject("Nocontra2", "con2 no tuvo éxito");
        	correcto=false;
        }
    	else if(!(con.equalsIgnoreCase(con2))){
    		model.addObject("Contrasdif", "con2 no tuvo éxito");
    		correcto=false;
        }
    	if(correcto){
    		Usuario user = Usuario.createUser(nom,con,false,us,apell,emai);
    		entityManager.persist(user);
    		Concepto concep = Concepto.createConcept(user,"concepto1","concepto1 de tipo prueba","teoría");
    		Tag ta = Tag.createTag("SO");
    		Tageo tageo= Tageo.createTageo(concep, ta);
    		ta.addTageo(tageo);
    		concep.addTageo(tageo);
    		Solucion so= Solucion.CreateSolucion(concep, user, "hola que tal");
    		entityManager.persist(so);
   		 	entityManager.persist(concep);
   		 	entityManager.persist(ta);
   		 	entityManager.persist(tageo);
    	}
        }
    	return model;
    	}
    	
    }
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("home");
		
		List<Tag> tag = (List<Tag>)entityManager
				.createNamedQuery("tags")
				.getResultList();
    	List<String> tags= new ArrayList<String>();
    	for (Tag t: tag) {  
    		 tags.add(t.getNombre());
    	}
    	model.addObject("t",tags);
    	
    	/*cambiar esta query para seleccionar los usuarios más top*/
    	List<Usuario> usuarios = (List<Usuario>)entityManager
				.createNamedQuery("users")
				.getResultList();
    	List<String> users= new ArrayList<String>();
    	for (Usuario us: usuarios) {  
    		 users.add(us.getLogin());
    	}
    	model.addObject("u",users);
    	
    	/*cambiar esta query para seleccionar los conceptos más top*/
    	List<Concepto> conceps = (List<Concepto>)entityManager
				.createNamedQuery("concepts")
				.getResultList();
    	List<String> conceptos= new ArrayList<String>();
    	for (Concepto con: conceps) {  
    		 conceptos.add(con.getNombre());
    	}
    	 model.addObject("c",conceptos);
    	
		return model;
	}
	

	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		return new ModelAndView("redirect:home");
	}

	
	
	
	// Esto es solo para propositos de demostración; veremos como hacerlo bien
	public static class User {
		private String login; 
		private int id;
		private String role;
		public User(String login, int id, String role) {
			this.login = login; this.id = id; this.role = role;
		}
		public String getLogin() { return login; }
		public int getId() { return id; }
		public String getRole() { return role; }		
	}
	
	@RequestMapping(value = "/reg", method = RequestMethod.GET) 
	public String reg(Locale locale, Model model) {
		logger.info("Ok, eres un usuario registrado.");
		model.addAttribute("serverTime", getDate(locale));				
		model.addAttribute("user", new User("paco", 12312, "admin"));
		model.addAttribute("pageTitle", "Paco");
		
		int[] enteros = new int[10];
		for (int i=0; i<enteros.length; i++) enteros[i] = i;
		model.addAttribute("elementos", enteros);
		return "home";
	}
	

	@RequestMapping(value = "/login",  params={"logear"})
	@Transactional
	public ModelAndView login(
			@RequestParam("login") String formLogin,
			@RequestParam("pass") String formPass, HttpSession session) {
		
		ModelAndView ret = new ModelAndView ("redirect:home");
		logger.info("Login attempt from '{}' while visiting '{}'", formLogin);
		
		// validate request
		if (formLogin == null || formLogin.length() < 4 || formPass == null || formPass.length() < 4) {
			ret.addObject("loginError", "usuarios y contraseñas: 4 caracteres mínimo");

		} else {
			Usuario u = null;
			try {
				u = (Usuario)entityManager.createNamedQuery("userByLogin")
					.setParameter("loginParam", formLogin).getSingleResult();
				if (u.isPassValid(formPass)) {
					logger.info("pass was valid");				
					session.setAttribute("user", u);
					// sets the anti-csrf token
					getTokenForSession(session);
				} else {
					logger.info("pass was NOT valid");
				}
			} catch (NoResultException nre) {
				ret.addObject("loginError", "mal consulta");
			}
		}
		
		// redirects to view from which login was requested
		return ret;
	}
	
	@RequestMapping(value = "/deslogeo")
	@Transactional
	public ModelAndView homedes(HttpSession session) {
		
		ModelAndView ret = new ModelAndView ("redirect:home");
		logger.info("User '{}' logged out", session.getAttribute("user"));
		session.invalidate();
		// redirects to view from which login was requested
		return ret;
	}
	
	static String getTokenForSession (HttpSession session) {
	    String token=UUID.randomUUID().toString();
	    session.setAttribute("csrf_token", token);
	    return token;
	}
}

/*
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@Transactional
	public String login(
			@RequestParam("login") String formLogin,
			@RequestParam("pass") String formPass,
			@RequestParam("source") String formSource,
			HttpServletRequest request, HttpServletResponse response, 
			Model model, HttpSession session) {
		
		logger.info("Login attempt from '{}' while visiting '{}'", formLogin, formSource);
		
		// validate request
		if (formLogin == null || formLogin.length() < 4 || formPass == null || formPass.length() < 4) {
			model.addAttribute("loginError", "usuarios y contraseñas: 4 caracteres mínimo");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			User u = null;
			try {
				u = (User)entityManager.createNamedQuery("userByLogin")
					.setParameter("loginParam", formLogin).getSingleResult();
				if (u.isPassValid(formPass)) {
					logger.info("pass was valid");				
					session.setAttribute("user", u);
					// sets the anti-csrf token
					getTokenForSession(session);
				} else {
					logger.info("pass was NOT valid");
					model.addAttribute("loginError", "error en usuario o contraseña");
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				}
			} catch (NoResultException nre) {
				if (formPass.length() == 4) {
					// UGLY: register new users if they do not exist and pass is 4 chars long
					logger.info("no-such-user; creating user {}", formLogin);				
					User user = User.createUser(formLogin, formPass, "user");
					entityManager.persist(user);				
					session.setAttribute("user", user);
					// sets the anti-csrf token
					getTokenForSession(session);					
				} else {
					logger.info("no such login: {}", formLogin);
				}
				model.addAttribute("loginError", "error en usuario o contraseña");
			}
		}
		
		// redirects to view from which login was requested
		return "redirect:" + formSource;
	}
	
	
	@RequestMapping(value = "/delUser", method = RequestMethod.POST)
	@ResponseBody
	@Transactional // needed to allow DB change
	public ResponseEntity<String> bookAuthors(@RequestParam("id") long id,
			@RequestParam("csrf") String token, HttpSession session) {
		if ( ! isAdmin(session) || ! isTokenValid(session, token)) {
			return new ResponseEntity<String>("Error: no such user or bad auth", 
					HttpStatus.FORBIDDEN);
		} else if (entityManager.createNamedQuery("delUser")
				.setParameter("idParam", id).executeUpdate() == 1) {
			return new ResponseEntity<String>("Ok: user " + id + " removed", 
					HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Error: no such user", 
					HttpStatus.BAD_REQUEST);
		}
	}			
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		logger.info("User '{}' logged out", session.getAttribute("user"));
		session.invalidate();
		return "redirect:home";
	}

	
	@RequestMapping(value="/user", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("photo") MultipartFile photo,
    		@RequestParam("id") String id){
        if (!photo.isEmpty()) {
            try {
                byte[] bytes = photo.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(
                        		new FileOutputStream(ContextInitializer.getFile("user", id)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + id + 
                		" into " + ContextInitializer.getFile("user", id).getAbsolutePath() + "!";
            } catch (Exception e) {
                return "You failed to upload " + id + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload a photo for " + id + " because the file was empty.";
        }
    }

	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(HttpSession session, HttpServletRequest request) {		
		return "user";
	}	

	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public String book(@PathVariable("id") long id, HttpServletResponse response, Model model) {
		Book b = entityManager.find(Book.class, id);
		if (b == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			logger.error("No such book: {}", id);
		} else {
			model.addAttribute("book", b);
		}
		model.addAttribute("prefix", "../");
		return "book";
	}	
	
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
	@Transactional
	@ResponseBody
	public String rmbook(@PathVariable("id") long id, HttpServletResponse response, Model model) {
		try {
			Book b = entityManager.find(Book.class, id);
			for (Author a : b.getAuthors()) {
				a.getWritings().remove(b);
				entityManager.persist(a);
			}
			entityManager.remove(b);
			response.setStatus(HttpServletResponse.SC_OK);
			return "OK";
		} catch (NoResultException nre) {
			logger.error("No such book: {}", id, nre);
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return "ERR";
		}
	}		
	
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	@Transactional
	public String books(Model model) {
		model.addAttribute("books", entityManager.createNamedQuery("allBooks").getResultList());
		model.addAttribute("owners", entityManager.createNamedQuery("allUsers").getResultList());
		model.addAttribute("authors", entityManager.createNamedQuery("allAuthors").getResultList());
		return "books";
	}	
	

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	@Transactional
	public String book(@RequestParam("owner") long ownerId,
			@RequestParam("authors") long[] authorIds,
			@RequestParam("title") String title,
			@RequestParam("description") String description, Model model) {
		Book b = new Book();
		b.setTitle(title);
		b.setDescription(description);
		for (long aid : authorIds) {
			// adding authors to book is useless, since author is the owning side (= has no mappedBy)
			Author a = entityManager.find(Author.class, aid);
			a.getWritings().add(b);
			entityManager.persist(a);
		}
		b.setOwner(entityManager.getReference(User.class, ownerId));
		entityManager.persist(b);
		entityManager.flush();
		logger.info("Book " + b.getId() + " written ok - owned by " + b.getOwner().getLogin() 
				+ " written by " + b.getAuthors());
		
		return "redirect:book/" + b.getId();
	}	
	
	@RequestMapping(value = "/bookAuthors")
	@ResponseBody
	@Transactional // needed to allow lazy init to work
	public ResponseEntity<String> bookAuthors(@RequestParam("id") long id, HttpServletRequest request) {
		try {
			Book book = (Book)entityManager.find(Book.class, id);
			List<Author> authors = book.getAuthors();
			StringBuilder sb = new StringBuilder("[");
			for (Author a : authors) {
				if (sb.length()>1) sb.append(",");
				sb.append("{ "
						+ "\"id\": \"" + a.getId() + "\", "
						+ "\"familyName\": \"" + a.getFamilyName() + "\", "
						+ "\"lastName\": \"" + a.getLastName() + "\"}");
			}
			return new ResponseEntity<String>(sb + "]", HttpStatus.OK);
		} catch (NoResultException nre) {
			logger.error("No such book: {}", id, nre);
		}
		return new ResponseEntity<String>("Error: libro no existe", HttpStatus.BAD_REQUEST);		
	}			
	
	
	@RequestMapping(value = "/author/{id}", method = RequestMethod.GET)
	public String author(@PathVariable("id") long id, Model model) {		
		try {
			model.addAttribute("author", entityManager.find(Author.class, id));
		} catch (NoResultException nre) {
			logger.error("No such author: {}", id, nre);
		}
		model.addAttribute("prefix", "../");
		return "author";
	}	
	
	
	@ResponseBody
	@RequestMapping(value="/user/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] userPhoto(@RequestParam("id") String id) throws IOException {
	    File f = ContextInitializer.getFile("user", id);
	    InputStream in = null;
	    if (f.exists()) {
	    	in = new BufferedInputStream(new FileInputStream(f));
	    } else {
	    	in = new BufferedInputStream(
	    			this.getClass().getClassLoader().getResourceAsStream("unknown-user.jpg"));
	    }
	    
	    return IOUtils.toByteArray(in);
	}
	
	
	@RequestMapping(value = "/debug", method = RequestMethod.GET)
	public String debug(HttpSession session, HttpServletRequest request) {
		String formDebug = request.getParameter("debug");
		logger.info("Setting debug to {}", formDebug);
		session.setAttribute("debug", 
				"true".equals(formDebug) ? "true" : "false");
		return "redirect:/";
	}

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String empty(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("pageTitle", "Bienvenido a IW");
		
		return "home";
	}	

	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return empty(locale, model);
	}	

	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	@Transactional
	public String about(Locale locale, Model model) {
		logger.info("User is looking up 'about us'");
		@SuppressWarnings("unchecked")
		List<User> us = (List<User>)entityManager.createQuery("select u from User u").getResultList();
		System.err.println(us.size());
		model.addAttribute("users", us);
		model.addAttribute("pageTitle", "IW: Quienes somos");
		return "about";
	}	
	
	
	static boolean isTokenValid(HttpSession session, String token) {
	    Object t=session.getAttribute("csrf_token");
	    return (t != null) && t.equals(token);
	}
	
	
	static String getTokenForSession (HttpSession session) {
	    String token=UUID.randomUUID().toString();
	    session.setAttribute("csrf_token", token);
	    return token;
	}
	
	
	static boolean isAdmin(HttpSession session) {
		User u = (User)session.getAttribute("user");
		if (u != null) {
			return u.getRole().equals("admin");
		} else {
			return false;
		}
	}
}*/
