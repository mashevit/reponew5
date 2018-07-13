package model3;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sightseeings database table.
 * 
 */
@Entity
@Table(name="sightseeings")
@NamedQuery(name="Sightseeing.findAll", query="SELECT s FROM Sightseeing s")
public class Sightseeing implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSightSeeings;

	private String sightSeeingsName;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="SightSeeingsCityId")
	private City city;

	//bi-directional many-to-one association to TripSightseeing
	@OneToMany(mappedBy="sightseeing")
	private List<TripSightseeing> tripSightseeings;

	public Sightseeing() {
	}

	public int getIdSightSeeings() {
		return this.idSightSeeings;
	}

	public void setIdSightSeeings(int idSightSeeings) {
		this.idSightSeeings = idSightSeeings;
	}

	public String getSightSeeingsName() {
		return this.sightSeeingsName;
	}

	public void setSightSeeingsName(String sightSeeingsName) {
		this.sightSeeingsName = sightSeeingsName;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<TripSightseeing> getTripSightseeings() {
		return this.tripSightseeings;
	}

	public void setTripSightseeings(List<TripSightseeing> tripSightseeings) {
		this.tripSightseeings = tripSightseeings;
	}

	public TripSightseeing addTripSightseeing(TripSightseeing tripSightseeing) {
		getTripSightseeings().add(tripSightseeing);
		tripSightseeing.setSightseeing(this);

		return tripSightseeing;
	}

	public TripSightseeing removeTripSightseeing(TripSightseeing tripSightseeing) {
		getTripSightseeings().remove(tripSightseeing);
		tripSightseeing.setSightseeing(null);

		return tripSightseeing;
	}
	public String toString() {
		return this.sightSeeingsName;
	}
	public String toStringDetail() {
		return this.sightSeeingsName +" in city "+ this.getCity().getCityName();
	}
}