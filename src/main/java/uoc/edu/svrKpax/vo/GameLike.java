package uoc.edu.svrKpax.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "GameLike")
public class GameLike implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int likeId;
	private int userId;
	private int gameId;
	private int containerId;

	@Id
	@Column(name = "likeId")
	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	@Column(name = "userId")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "gameId")
	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	@Column(name = "containerId")
	public void setContainerId(int containerId) {
		this.containerId = containerId;
	}

	public int getContainerId() {
		return containerId;
	}
}