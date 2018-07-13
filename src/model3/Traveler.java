package model3;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the traveler database table.
 * 
 */
@Entity
@NamedQuery(name="Traveler.findAll", query="SELECT t FROM Traveler t")
public class Traveler implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtraveler;

	@Temporal(TemporalType.DATE)
	private Date traveler_BirthDate;

	@Column(name="traveler_name")
	private String travelerName;

	//bi-directional many-to-one association to Trip
	@OneToMany(mappedBy="traveler")
	private List<Trip> trips;

	public Traveler() {
	}

	public int getIdtraveler() {
		return this.idtraveler;
	}

	public void setIdtraveler(int idtraveler) {
		this.idtraveler = idtraveler;
	}

	public Date getTraveler_BirthDate() {
		return this.traveler_BirthDate;
	}

	public void setTraveler_BirthDate(Date traveler_BirthDate) {
		this.traveler_BirthDate = traveler_BirthDate;
	}

	public String getTravelerName() {
		return this.travelerName;
	}

	public void setTravelerName(String travelerName) {
		this.travelerName = travelerName;
	}

	public List<Trip> getTrips() {
		return this.trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public Trip addTrip(Trip trip) {
		getTrips().add(trip);
		trip.setTraveler(this);

		return trip;
	}

	public Trip removeTrip(Trip trip) {
		getTrips().remove(trip);
		trip.setTraveler(null);

		return trip;
	}
	public String toString() {
//		SimpleDateFormat sdf = new SimpleDateFormat(
//			    "MM-dd-yyyy");
		 long millisecondsSince = new Date().getTime() - this.getTraveler_BirthDate().getTime();
		    long days= TimeUnit.DAYS.convert(millisecondsSince, TimeUnit.MILLISECONDS);
		    return this.getTravelerName() +" age "+ (int) (days/365.25);
	}

}