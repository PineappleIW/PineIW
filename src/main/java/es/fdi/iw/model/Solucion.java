package es.fdi.iw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class Solucion {
	
	private Concepto examen;
	private long id; 
	private Usuario user;
	private String contenido;
	private boolean denuncia;
	
	@ManyToOne(targetEntity=Concepto.class)
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
	
	

}
