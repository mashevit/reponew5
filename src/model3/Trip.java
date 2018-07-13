package model3;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the trip database table.
 * 
 */
@Entity
@NamedQuery(name="Trip.findAll", query="SELECT t FROM Trip t")
public class Trip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtrip;

	@Temporal(TemporalType.DATE)
	@Column(name="trip_date")
	private Date tripDate;

	@Column(name="trip_hotel")
	private String tripHotel;

	@Column(name="trip_moneyspent")
	private int tripMoneyspent;

	@Column(name="trip_numdays")
	private int tripNumdays;

	private int trip_numMinTransit;

	//bi-directional many-to-one association to Traveler
	@ManyToOne
	@JoinColumn(name="Trip_id_traveller")
	private Traveler traveler;

	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="trip_city")
	private City city;

	//bi-directional many-to-one association to TripSightseeing
	@OneToMany(mappedBy="trip")
	private List<TripSightseeing> tripSightseeings;

	public Trip() {
	}

	public int getIdtrip() {
		return this.idtrip;
	}

	public void setIdtrip(int idtrip) {
		this.idtrip = idtrip;
	}

	public Date getTripDate() {
		return this.tripDate;
	}

	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}

	public String getTripHotel() {
		return this.tripHotel;
	}

	public void setTripHotel(String tripHotel) {
		this.tripHotel = tripHotel;
	}

	public int getTripMoneyspent() {
		return this.tripMoneyspent;
	}

	public void setTripMoneyspent(int tripMoneyspent) {
		this.tripMoneyspent = tripMoneyspent;
	}

	public int getTripNumdays() {
		return this.tripNumdays;
	}

	public void setTripNumdays(int tripNumdays) {
		this.tripNumdays = tripNumdays;
	}

	public int getTrip_numMinTransit() {
		return this.trip_numMinTransit;
	}

	public void setTrip_numMinTransit(int trip_numMinTransit) {
		this.trip_numMinTransit = trip_numMinTransit;
	}

	public Traveler getTraveler() {
		return this.traveler;
	}

	public void setTraveler(Traveler traveler) {
		this.traveler = traveler;
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
		tripSightseeing.setTrip(this);

		return tripSightseeing;
	}

	public TripSightseeing removeTripSightseeing(TripSightseeing tripSightseeing) {
		getTripSightseeings().remove(tripSightseeing);
		tripSightseeing.setTrip(null);

		return tripSightseeing;
	}
	public String toString() {
		return "trip on the "+ this.getTripDate()+" stayed in "+this.getTripHotel()+" hotel, budget was: " +this.getTripMoneyspent()+" transit time was "+ this.getTrip_numMinTransit()+" minutes";
	}
	public String nameCompat() {
		return "trip on the "+this.tripDate+" to "+this.getCity().getCityName();
	}

}