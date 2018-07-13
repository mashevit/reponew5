package model3;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the trip_sightseeing database table.
 * 
 */
@Entity
@Table(name="trip_sightseeing")
@NamedQuery(name="TripSightseeing.findAll", query="SELECT t FROM TripSightseeing t")
public class TripSightseeing implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idtrip_sightseeing")
	private int idtripSightseeing;

	//bi-directional many-to-one association to Pic
	@OneToMany(mappedBy="tripSightseeing")
	private List<Pic> pics;

	//bi-directional many-to-one association to Sightseeing
	@ManyToOne
	@JoinColumn(name="trip_sightseeing_nameid")
	private Sightseeing sightseeing;

	//bi-directional many-to-one association to Trip
	@ManyToOne
	@JoinColumn(name="trip_sightseeing_tripid")
	private Trip trip;

	public TripSightseeing() {
	}

	public int getIdtripSightseeing() {
		return this.idtripSightseeing;
	}

	public void setIdtripSightseeing(int idtripSightseeing) {
		this.idtripSightseeing = idtripSightseeing;
	}

	public List<Pic> getPics() {
		return this.pics;
	}

	public void setPics(List<Pic> pics) {
		this.pics = pics;
	}

	public Pic addPic(Pic pic) {
		getPics().add(pic);
		pic.setTripSightseeing(this);

		return pic;
	}

	public Pic removePic(Pic pic) {
		getPics().remove(pic);
		pic.setTripSightseeing(null);

		return pic;
	}

	public Sightseeing getSightseeing() {
		return this.sightseeing;
	}

	public void setSightseeing(Sightseeing sightseeing) {
		this.sightseeing = sightseeing;
	}

	public Trip getTrip() {
		return this.trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	public String toString() {
		return this.getSightseeing() + " in " + this.trip.nameCompat();
	}

}