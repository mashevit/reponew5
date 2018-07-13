package abc.da;

import java.sql.Date;
import java.util.List;

import model3.City;
import model3.Pic;
import model3.Sightseeing;
import model3.Traveler;
import model3.Trip;
import model3.TripSightseeing;

public interface IDac {

	void removeSightUndefined(String ids);

	String removeTSightseeingFromCity(String trsid);

	TripSightseeing getTrsightbyid(String ids);

	Sightseeing getsightbyid(String ids);

	int addSightStr(String tripid, String sightname);

	void removetraveller(String travid);

	Traveler getTravelerbyId(String id);

	List<Pic> get3pics();

	List<City> get3common();

	List<Trip> get3uniq();

	List<Pic> PicbyTrip(String tripid);

	List<Trip> GetTripsByCity(String cityidstr);

	void deletPic(String idpic);

	Pic picbyid(String ids);

	int setpicsForSighttrip(String addr, String idtrsig);

	List<Pic> picsForSightTrip(String idSight);

	int addsighttotrip(String sigind, String tripind);

	List<TripSightseeing> SightseeingforTrip(String tri);

	int removeSightFromTripint(String si, String ti);

	String removeSightFromTrip(String si, String ti);

	List<Sightseeing> newTripCitySights(String ci, String ti);

	List<Sightseeing> SightsforTrip(String tri);

	City getCitybyTrip(String idt);

	String getTripCityName(String idt);

	List<Trip> Tripl();

	List<Sightseeing> sightsbycity(String cityidstr);

	int addSighti(String name, String ist);

	int addSight(String name, City city);

	int addTraveller(String name, Date BirthD);

	List<Traveler> getTravellers();

	String removeCity(String ist);

	List<City> getAllCities();

	int addSightTrip(Sightseeing sightseeing);

	int createSight(String name, City city);

	int addCity(String cityName);

	int createTraveler(String name, Date birthDate);

	int createTripStrings(String cityi, Date date, String traveleri, String numdays, String moneyspent, String hotel,
			String transit);

	int createTrip(String cityi, Date date, String traveleri, int numdays, int moneyspent, String hotel, String Sightseeingi,
			int transit);

}
