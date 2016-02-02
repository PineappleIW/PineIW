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

import org.hibernate.exception.GenericJDBCException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
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
			@RequestParam ("textobusqueda") String texto,
			@RequestParam ("valoracion") String valoracion
			) {
			ModelAndView model = new ModelAndView("teoria");
			List<Concepto> conceps;
			if(tipo.equals("ambos") || tipo.equals("")){
				if(valoracion.equals("ninguna")){
					conceps = (List<Concepto>)entityManager
						.createNamedQuery("concepts")
						.getResultList();
				}
				if(valoracion.equals("asc") || valoracion.equals("") ){
					conceps = (List<Concepto>)entityManager
						.createNamedQuery("conceptsAsc")
						.getResultList();
				}else{
					conceps = (List<Concepto>)entityManager
							.createNamedQuery("conceptsDesc")
							.getResultList();
				}				
			}
	    	else{
	    		if(valoracion.equals("ninguna")){
					conceps = (List<Concepto>)entityManager
						.createNamedQuery("concepts")
						.getResultList();
				}
	    		if(valoracion.equals("asc") || valoracion.equals("")){
	    			conceps = (List<Concepto>)entityManager
						.createNamedQuery("getTipoAsc")
						.setParameter("tipo", tipo)
						.getResultList();
	    		}else{
	    			conceps = (List<Concepto>)entityManager
							.createNamedQuery("getTipoDesc")
							.setParameter("tipo", tipo)
							.getResultList();
	    		}
	    	}
			List<String> conceptos= new ArrayList<String>();
	    	for (Concepto con: conceps) {  
	    		 conceptos.add(con.getNombre()+" - "+con.getUser().getLogin());
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
    		model.addObject("h", "h");
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
		Usuario u = (Usuario)session.getAttribute("user");
		ModelAndView ret;
		if (u.isAdmin()){
			ret=new ModelAndView("redirect:admin");
		}
		else{
		ret= new ModelAndView("usuario");
		ret.addObject("usuario",u);
		List<Concepto> concepsuser = (List<Concepto>)entityManager
				.createNamedQuery("conceptsByUser")
				.setParameter("userParam", u)
				.getResultList();
		ret.addObject("lista",concepsuser);
		
		List<Integer> p=new ArrayList<Integer>();
		List<Integer> l=new ArrayList<Integer>();
		for(Concepto e:concepsuser){
		List<Solucion> numsols=(List<Solucion>)entityManager
				.createNamedQuery("solucionesByConcep")
				.setParameter("concep", e)
				.getResultList();
				int i=numsols.size();
				l.add(i);
				p.add(e.getPuntuacion());
		}
		ret.addObject("votos",p);
		ret.addObject("numsols",l);
		}
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
		if (u!=null){
		Concepto con = (Concepto)entityManager
				.createNamedQuery("conceptByName")
				.setParameter("concepParam", nombre)
				.getSingleResult();
		
		Solucion sol=Solucion.CreateSolucion(con, u, contenido);
		entityManager.persist(sol);
		}
		else {
			ret=new ModelAndView("redirect:login");
		}
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
	
	@Transactional
	@RequestMapping(value = "/home",params={"conceptovoto"},method = RequestMethod.GET)
	public ModelAndView upconcepto(@RequestParam ("conceptovoto") String nombre,
								   @RequestParam ("votos") String voto) {
		ModelAndView ret=ver_concepto(nombre);
		Concepto concep=(Concepto)entityManager
				.createNamedQuery("conceptByName")
				.setParameter("concepParam", nombre)
				.getSingleResult();
		if(voto.equalsIgnoreCase("true")){
			concep.puntuacionmas();
		}
		else {
			concep.puntuacionmenos();
			if (concep.getPuntuacion()<-10){
				concep.setDenuncia(true);	
			} 
		}
		entityManager.persist(concep);
		return ret;
	}
	
	@Transactional
	@RequestMapping(value = "/home",params={"solucionvoto"},method = RequestMethod.GET)
	public ModelAndView upsolucion(@RequestParam ("solucionvoto") String nombre,
								   @RequestParam ("votos") String voto) {
		ModelAndView ret=ver_concepto3(nombre);
		Solucion sol=(Solucion)entityManager
				.createNamedQuery("solucionByName")
				.setParameter("nombre", nombre)
				.getSingleResult();
		if(voto.equalsIgnoreCase("true")){
			sol.puntuacionmas();
		}
		else {
			sol.puntuacionmenos();
			if (sol.getPuntuacion()<-10){
				sol.setDenuncia(true);	
			}
		}
		entityManager.persist(sol);
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
	    	
	    	List<Solucion> solucionden = (List<Solucion>)entityManager
					.createNamedQuery("solucionesDenun")
					.getResultList();
	    	 model.addObject("sd",solucionden);
	    	
	    	List<Solucion> solucion = (List<Solucion>)entityManager
					.createNamedQuery("soluciones")
					.getResultList();
	    	 model.addObject("s",solucion);
	    	
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
	
	@Transactional
	@RequestMapping(value="/admin", params={"borradosol"})
	public ModelAndView borrarsol(@RequestParam ("borradosol") String solu){
		ModelAndView model2 = new ModelAndView("redirect:admin");
		Solucion sol = (Solucion)entityManager
				.createNamedQuery("solucionByName")
				.setParameter("nombre", solu)
				.getSingleResult();
		sol.borradoTotal();
		entityManager.remove(sol);
		return model2;
	}
	
	@Transactional
	@RequestMapping(value="/admin", params={"dessol"})
	public ModelAndView dessol(@RequestParam ("dessol") String solu){
		ModelAndView model2 = new ModelAndView("redirect:admin");
		Solucion sol = (Solucion)entityManager
				.createNamedQuery("solucionByName")
				.setParameter("nombre", solu)
				.getSingleResult();
		sol.setDenuncia(false);
		sol.setPuntuacion(0);
		entityManager.persist(sol);
		return model2;
	}
	
	@Transactional
	@RequestMapping(value="/admin", params={"desconcep"})
	public ModelAndView desconcep(@RequestParam ("desconcep") String concep){
		ModelAndView model2 = new ModelAndView("redirect:admin");
		Concepto concepto = (Concepto)entityManager
				.createNamedQuery("conceptByName")
				.setParameter("concepParam", concep)
				.getSingleResult();
		concepto.desDen();
		concepto.setPuntuacion(0);
		entityManager.persist(concepto);
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
	
	@RequestMapping(value="/admin", params={"editasol"})
	public ModelAndView editasol(@RequestParam ("editasol") String solucion){
		ModelAndView model2 = new ModelAndView("editsolucion");
		Solucion sol=(Solucion)entityManager
				.createNamedQuery("solucionByName")
				.setParameter("nombre", solucion)
				.getSingleResult();
		model2.addObject("s",sol);
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
	@RequestMapping(value="/admin", params={"enviadosol"})
	public ModelAndView editadosolucion (@RequestParam ("comment") String descrip,
			@RequestParam ("enviadosol") String envi){
		
		ModelAndView model2 = new ModelAndView("redirect:admin");
		Solucion sol = (Solucion)entityManager
				.createNamedQuery("solucionByName")
				.setParameter("nombre", envi)
				.getSingleResult();
		sol.setContenido(descrip);
		entityManager.persist(sol);
		return model2;
	}
	
	@Transactional
	@RequestMapping(value="/admin", params={"enviado"})
	public ModelAndView editadoconcepto (@RequestParam ("nombre") String nombre,
			@RequestParam ("comment") String descrip,
			@RequestParam ("enviado") String envi){
		ModelAndView model2 = new ModelAndView("redirect:admin");
		Concepto concepto = (Concepto)entityManager
				.createNamedQuery("conceptByName")
				.setParameter("concepParam", envi)
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
    	try{
		Usuario u = (Usuario)entityManager.createNamedQuery("userByLogin")
				.setParameter("loginParam", us).getSingleResult();
    	}
    	catch (Exception e){
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
   		 	model= new ModelAndView("redirect:home");
    	}
    	}
    	return model;
    	}
    	ModelAndView model = new ModelAndView("registro");
    	model.addObject("fallo","fallo");
    	return model;
    	

    	
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
		if (!(formLogin == null || formPass == null)) {
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
