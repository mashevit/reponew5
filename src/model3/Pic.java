package model3;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pics database table.
 * 
 */
@Entity
@Table(name="pics")
@NamedQuery(name="Pic.findAll", query="SELECT p FROM Pic p")
public class Pic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idpics;

	@Column(name="pics_addr")
	private String picsAddr;

	//bi-directional many-to-one association to TripSightseeing
	@ManyToOne
	@JoinColumn(name="picsTripOrSight")
	private TripSightseeing tripSightseeing;

	public Pic() {
	}

	public int getIdpics() {
		return this.idpics;
	}

	public void setIdpics(int idpics) {
		this.idpics = idpics;
	}

	public String getPicsAddr() {
		return this.picsAddr;
	}

	public void setPicsAddr(String picsAddr) {
		this.picsAddr = picsAddr;
	}

	public TripSightseeing getTripSightseeing() {
		return this.tripSightseeing;
	}

	public void setTripSightseeing(TripSightseeing tripSightseeing) {
		this.tripSightseeing = tripSightseeing;
	}

}