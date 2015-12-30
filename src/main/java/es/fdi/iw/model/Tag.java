package es.fdi.iw.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQueries({
    @NamedQuery(name="tags",
        query="select u from Tag u"),
})
@Entity
public class Tag {
	private String nombre;
	private List<Tageo> tageo;
	private long id;

	@OneToMany(targetEntity=Tageo.class)
	public List<Tageo> getTageo() {
		return tageo;
	}
	
	public void setTageo(List<Tageo> tageo) {
		this.tageo = tageo;
	}
	
	public static Tag createTag(String nombre) {
		Tag t = new Tag();
		t.nombre=nombre;
		t.tageo=new ArrayList<Tageo>();
		return t;
	}
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	 
	public void addTageo(Tageo tageo){
		this.tageo.add(tageo);
	}
	
}
