package uoc.edu.svrKpax.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Score implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nameUser;
	private int points;
	
	
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getPoints() {
		return points;
	}

	

}