package uoc.edu.svrKpax.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "User")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idUser;
	private String login;
	private String password;
	private String secret;

	private Set<Realm> realm = new HashSet<Realm>(0);
	private Set<GameInstance> gamesInstance = new HashSet<GameInstance>(0);

	public User() {
		super();
	}

	public User(int userId, Set<Realm> realms) {
		this.idUser = userId;
		this.realm = realms;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idUser")
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@Column(name = "login")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "secret")
	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "UserRealm", joinColumns = { @JoinColumn(name = "idUser") }, inverseJoinColumns = { @JoinColumn(name = "idRealm") })
	public Set<Realm> getRealm() {
		return this.realm;
	}

	public void setRealm(Set<Realm> realm) {
		this.realm = realm;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "UserGameInstance", joinColumns = { @JoinColumn(name = "idUser") }, inverseJoinColumns = { @JoinColumn(name = "idGameInstance") })
	public Set<GameInstance> getGamesInstance() {
		return gamesInstance;
	}

	public void setGamesInstance(Set<GameInstance> gamesInstance) {
		this.gamesInstance = gamesInstance;
	}

}