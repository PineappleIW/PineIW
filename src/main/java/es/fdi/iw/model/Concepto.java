package es.fdi.iw.model;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NamedQueries({
    @NamedQuery(name="concepts",
        query="select u from Concepto u"),
    @NamedQuery(name="conceptsByUser",
    	query="select u from Concepto u where u.user = :userParam"),
    @NamedQuery(name="conceptByName",
    	query="select u from Concepto u where u.nombre = :concepParam"),
    @NamedQuery(name="conceptsDenun",
    	query="select u from Concepto u where u.denuncia = true"),
    @NamedQuery(name="getTipo",
   		query="select u from Concepto u where u.tipo = :tipo"),
})


@Entity
public class Concepto {
	private long id;
	private Usuario user;
	private String descripcion;
	private String nombre;
	private String tipo;
	private int puntuacion;
	private boolean denuncia;
	private List<Solucion> soluciones;
	private List<Tageo> tageo;
	
	
	public static Concepto createConcept(Usuario user, String nombre,String descripcion, String tipo) {
		Concepto u = new Concepto();
		u.user = user;
		u.nombre=nombre;
		u.descripcion = descripcion;
		u.tipo=tipo;
		u.denuncia=false;
		u.soluciones=new ArrayList<Solucion>();
		u.tageo=new ArrayList<Tageo>();
		u.puntuacion=0;
		return u;
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return this.id;
	}
	
	@ManyToOne(cascade=CascadeType.REMOVE,targetEntity=Usuario.class)
	public Usuario getUser() {
		return this.user;
	}

	public boolean getDenuncia() {
		return denuncia;
	}
	
	public void borrauser (){
		this.user=null;
	}
	public void setDenuncia(boolean denuncia) {
		this.denuncia = denuncia;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	@OneToMany(targetEntity=Solucion.class)
	public List<Solucion> getSoluciones() {
		return soluciones;
	}
	
	public void setSoluciones(List<Solucion> soluciones) {
		this.soluciones = soluciones;
	}

	@OneToMany(targetEntity=Tageo.class)
	public List<Tageo> getTags() {
		return tageo;
	}

	public void setTags(List<Tageo> tags) {
		this.tageo = tags;
	}
	
	public void addTageo(Tageo tageo){
		this.tageo.add(tageo);
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
}
