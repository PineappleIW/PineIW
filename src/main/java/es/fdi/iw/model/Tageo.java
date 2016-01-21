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
    @NamedQuery(name="tagsByConcep",
        query="select u.tag from Tageo u where u.concep = :concep"),
    @NamedQuery(name="concepsByTag",
    	query="select u.concep from Tageo u where u.tag = :tag"),
})
@Entity
public class Tageo {
	private Concepto concep;
	private Tag tag;
	private long id;

	public static Tageo createTageo(Concepto con, Tag tag) {
		Tageo t = new Tageo();
		t.tag=tag;
		t.concep=con;
		return t;
	}
	
	@ManyToOne(targetEntity=Concepto.class)
	@OnDelete(action = OnDeleteAction.CASCADE)
	public Concepto getConcep() {
		return concep;
	}
	public void setConcep(Concepto concep) {
		this.concep = concep;
	}
	
	@ManyToOne(targetEntity=Tag.class)
	@OnDelete(action = OnDeleteAction.CASCADE)
	public Tag getTag() {
		return tag;
	}
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
