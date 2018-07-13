package model3;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cities database table.
 * 
 */
@Entity
@Table(name="cities")
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idcities;

	@Column(name="city_name")
	private String cityName;

	//bi-directional many-to-one association to Sightseeing
	@OneToMany(mappedBy="city")
	private List<Sightseeing> sightseeings;

	//bi-directional many-to-one association to Trip
	@OneToMany(mappedBy="city")
	private List<Trip> trips;

	public City() {
	}

	public int getIdcities() {
		return this.idcities;
	}

	public void setIdcities(int idcities) {
		this.idcities = idcities;
	}

	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public List<Sightseeing> getSightseeings() {
		return this.sightseeings;
	}

	public void setSightseeings(List<Sightseeing> sightseeings) {
		this.sightseeings = sightseeings;
	}

	public Sightseeing addSightseeing(Sightseeing sightseeing) {
		getSightseeings().add(sightseeing);
		sightseeing.setCity(this);

		return sightseeing;
	}

	public Sightseeing removeSightseeing(Sightseeing sightseeing) {
		getSightseeings().remove(sightseeing);
		sightseeing.setCity(null);

		return sightseeing;
	}

	public List<Trip> getTrips() {
		return this.trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public Trip addTrip(Trip trip) {
		getTrips().add(trip);
		trip.setCity(this);

		return trip;
	}

	public Trip removeTrip(Trip trip) {
		getTrips().remove(trip);
		trip.setCity(null);

		return trip;
	}
	public String toString() {
		return " "+this.getCityName()+" ";
		
	}

}