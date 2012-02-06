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
@Table(name = "GameInstance")
public class GameInstance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idGameInstance;
	private int idGame;
	private String state;

	public GameInstance(int idGame, String state) {
		super();
		this.idGame = idGame;
		this.state = state;
	}

	public GameInstance() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idGameInstance")
	public int getIdGameInstance() {
		return idGameInstance;
	}

	public void setIdGameInstance(int idGameInstance) {
		this.idGameInstance = idGameInstance;
	}

	@Column(name = "idGame")
	public int getIdGame() {
		return idGame;
	}

	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}

	@Column(name = "state")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}