package es.fdi.iw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NamedQueries({
    @NamedQuery(name="solucionByName",
        query="select u from Solucion u where u.nombre = :nombre"),
    @NamedQuery(name="solucionesByConcep",
    	query="select u from Solucion u where u.examen = :concep"),
    @NamedQuery(name="soluciones",
        query="select u from Solucion u"),
    @NamedQuery(name="solucionesDenun",
    	query="select u from Solucion u where u.denuncia = true"),	
})

@Entity
public class Solucion {
	
	private String nombre;
	private Concepto examen;
	private long id;
	private int puntuacion;
	private Usuario user;
	private String contenido;
	private boolean denuncia;
	
	
	public static Solucion CreateSolucion(Concepto con, Usuario us, String contenido){
		Solucion u=new Solucion();
		u.nombre=us.getLogin()+con.getNombre()+contenido;
		u.examen=con;
		u.user=us;
		u.contenido=contenido;
		return u;
	}
	
	
	
	public String getNombre() {
		return nombre;
	}

	public void borradoTotal(){
		user=null;
		examen=null;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	@ManyToOne(targetEntity=Concepto.class)
	@OnDelete(action = OnDeleteAction.CASCADE)
	public Concepto getExamen() {
		return examen;
	}
	
	public void setExamen(Concepto examen) {
		this.examen = examen;
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne(targetEntity=Usuario.class)
	@OnDelete(action = OnDeleteAction.CASCADE)
	public Usuario getUser() {
		return user;
	}
	
	public void setUser(Usuario user) {
		this.user = user;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public boolean isDenuncia() {
		return denuncia;
	}

	public void setDenuncia(boolean denuncia) {
		this.denuncia = denuncia;
	}
	
	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	public void puntuacionmas(){
		this.puntuacion++;
	}
	
	public void puntuacionmenos(){
		this.puntuacion--;
	}
	

}
