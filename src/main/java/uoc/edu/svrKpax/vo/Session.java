package uoc.edu.svrKpax.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "Session")
public class Session implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idSession;
	private Timestamp startTime;
	private String campusSession;
	
	private User user;

	public Session() {
		super();
	}

	public Session(Timestamp startTime, String campusSession, User user) {
		super();
		this.startTime = startTime;
		this.campusSession = campusSession;
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idSession")
	public int getIdSession() {
		return idSession;
	}

	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}

	@Column(name = "startTime")
	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Column(name = "campusSession")
	public String getCampusSession() {
		return campusSession;
	}

	public void setCampusSession(String campusSession) {
		this.campusSession = campusSession;
	}

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "idUser", referencedColumnName = "idUser") })
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}