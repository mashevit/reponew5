package abc.da;

import java.sql.Date;
import java.util.List;

import model3.City;
import model3.Pic;
import model3.Traveler;
import model3.Trip;

public interface ICSC {
	List<Traveler> getTravellers();
	

	void removetraveller(String travid);

	Traveler getTravelerbyId(String id);

	List<Pic> get3pics();

	List<City> get3common();

	List<Trip> get3uniq();
	
	List<City> getAllCities();

	int addTraveller(String name, Date BirthD);
	
	int createTripStrings(String cityi, Date date, String traveleri, String numdays, String moneyspent, String hotel,
			String transit);
	
	List<Trip> GetTripsByCity(String cityidstr);
}
