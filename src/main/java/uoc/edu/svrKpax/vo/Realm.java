package uoc.edu.svrKpax.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "Realm")
public class Realm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idRealm;
	private String alias;
	
	

	public Realm() {
		super();
	}

	public Realm(String alias) {
		super();
		this.alias = alias;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idRealm")
	public int getIdRealm() {
		return idRealm;
	}

	public void setIdRealm(int idRealm) {
		this.idRealm = idRealm;
	}

	@Column(name = "alias", unique = true)
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}



	


}