package es.fdi.iw.model;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NamedQueries({
    @NamedQuery(name="userByLogin",
        query="select u from Usuario u where u.login = :loginParam"),
    @NamedQuery(name="users",
        query="select u from Usuario u where u.admin = false"),
    @NamedQuery(name="borrausers",
    	query="delete from Usuario where login =:loginParam"),
})
@Entity
public class Usuario {
	private long id;
	private String nombre;
	private String apellidos;
	private String login;
	private String email;
	private String hashedAndSalted;
	private boolean admin;

    private static BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

    public static Usuario createUser(String nombre, String pass, boolean admin,String login,String apellidos, String email) {
		Usuario u = new Usuario();
		u.nombre = nombre;
		u.hashedAndSalted = generateHashedAndSalted(pass);
		u.admin = admin;
		u.apellidos=apellidos;
		u.login=login;
		u.email=email;
		return u;
	}
	

	public boolean isPassValid(String pass) {
		return bcryptEncoder.matches(pass, hashedAndSalted);		
	}	
	
	// para generar contraseñas 
	/*
	public static void main(String[] args) {	
		System.err.println(generateHashedAndSalted("hola1"));
		System.err.println(generateHashedAndSalted("hola2"));
		System.err.println(generateHashedAndSalted("hola3"));
		System.err.println(generateHashedAndSalted("hola4"));
	}
	*/
	
	
	/**
	 * Generate a hashed&salted hex-string from a user's pass and salt
	 * @param pass to use; no length-limit!
	 * @param salt to use
	 * @return a string to store in the BD that does not reveal the password even
	 * if the DB is compromised. Note that brute-force is possible, but it will
	 * have to be targeted (ie.: use the same salt)
	 */
	public static String generateHashedAndSalted(String pass) {
		return bcryptEncoder.encode(pass);
	}	
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	
	@Column(unique=true)
	public String getLogin() {
		return login;
	}
	public void setLogin(String user) {
		this.login = user;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
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
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getHashedAndSalted() {
		return hashedAndSalted;
	}

	public void setHashedAndSalted(String hashedAndSalted) {
		this.hashedAndSalted = hashedAndSalted;
	}
	
	/**
	 * Converts a hex string to a byte array
	 * @param hex string to convert
	 * @return equivalent byte array
	 */
	public static byte[] hexStringToByteArray(String hex) {
		byte[] r = new byte[hex.length()/2];
		for (int i=0; i<r.length; i++) {
			String h = hex.substring(i*2, (i+1)*2);
			r[i] = (byte)Integer.parseInt(h, 16);
		}
		return r;
	}
	
	/**
	 * Converts a byte array to a hex string
	 * @param b converts a byte array to a hex string; nice for storing
	 * @return the corresponding hex string
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<b.length; i++) {
			sb.append(Integer.toString((b[i]&0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
	
}
