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
@Table(name = "GameScore")
public class GameScore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idScore;
	private int idUser;
	private int idGame;
	private int points;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idScore")
	public int getIdScore() {
		return idScore;
	}

	public void setIdScore(int idScore) {
		this.idScore = idScore;
	}

	@Column(name = "idUser")
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@Column(name = "idGame")
	public int getIdGame() {
		return idGame;
	}

	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}

	@Column(name = "points")
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}