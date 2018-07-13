package abc.da;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model3.City;
import model3.Pic;
import model3.Sightseeing;
import model3.Traveler;
import model3.Trip;
import model3.TripSightseeing;

public class Dac implements IDac, ICSC {

	// EntityManager em;
	EntityManagerFactory emf;

	public Dac() {
		// TODO Auto-generated constructor stub
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		// em = emf.createEntityManager();
	}

	public int createTrip(String cityi, Date date, String traveleri, int numdays, int moneyspent, String hotel,
			String Sightseeingi, int transit) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		Trip trip = new Trip();
		int i = Integer.parseInt(cityi);
		City city = em.find(City.class, i);
		int i1 = Integer.parseInt(traveleri);
		Traveler traveler2 = em.find(Traveler.class, i1);

		trip.setCity(city);
		trip.setTraveler(traveler2);
		trip.setTripDate(date);
		trip.setTripHotel(hotel);
		trip.setTripNumdays(numdays);
		trip.setTripMoneyspent(moneyspent);
		trip.setTrip_numMinTransit(transit);
		
		
		  em.getTransaction().begin();
	        em.persist(trip);
	        em.getTransaction().commit();
		//em.persist();
		if (Sightseeingi != null) {
			int i3 = Integer.parseInt(Sightseeingi);
			Sightseeing sight = em.find(Sightseeing.class, i3);
			TripSightseeing tsi = new TripSightseeing();
			tsi.setSightseeing(sight);
			tsi.setTrip(trip);
			
			
			
			  em.getTransaction().begin();
		        em.persist(tsi);
		        em.getTransaction().commit();
		//	em.persist();
		}

		em.close();
		emf.close();
		return trip.getIdtrip();
	}

	public int createTripStrings(String cityi, Date date, String traveleri, String numdays, String moneyspent,
			String hotel, String transit) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		Trip trip = new Trip();
		int i = Integer.parseInt(cityi);
		City city = em.find(City.class, i);
		int i1 = Integer.parseInt(traveleri);
		Traveler traveler2 = em.find(Traveler.class, i1);

		trip.setCity(city);
		trip.setTraveler(traveler2);
		trip.setTripDate(date);
		trip.setTripHotel(hotel);
		trip.setTripNumdays(Integer.parseInt(numdays));
		trip.setTripMoneyspent(Integer.parseInt(moneyspent));
		trip.setTrip_numMinTransit(Integer.parseInt(transit));
		
		
		
		
		  em.getTransaction().begin();
	        em.persist(trip);
	        em.getTransaction().commit();
	//	em.persist();

		em.close();
		emf.close();
		return trip.getIdtrip();
	}

	public int createTraveler(String name, Date birthDate) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		Traveler travelr = new Traveler();
		travelr.setTravelerName(name);
		travelr.setTraveler_BirthDate(birthDate);
		
		
		
		  em.getTransaction().begin();
	        em.persist(travelr);
	        em.getTransaction().commit();
	//	em.persist();

		em.close();
		emf.close();
		return travelr.getIdtraveler();
	}

	public int addCity(String cityName) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		City city = new City();
		String statement = "SELECT c FROM City c WHERE c.cityName= :cn";
		try {
			Query q = em.createQuery(statement).setParameter("cn", cityName);
			@SuppressWarnings("unchecked")
			List<City> lct = q.getResultList();
			if (!lct.isEmpty())
				return lct.get(0).getIdcities();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		city.setCityName(cityName);
		
		
		  em.getTransaction().begin();
	        em.persist(city);
	        em.getTransaction().commit();
		//em.persist(city);

		em.close();
		emf.close();
		return city.getIdcities();
	}

	public int createSight(String name, City city) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		Sightseeing sight = new Sightseeing();
		sight.setSightSeeingsName(name);
		sight.setCity(city);
		
		
		
		  em.getTransaction().begin();
	        em.persist(sight);
	        em.getTransaction().commit();
		//em.persist(sight);

		em.close();
		emf.close();
		return sight.getIdSightSeeings();
	}

	// ?
	public int addSightTrip(Sightseeing sightseeing) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		TripSightseeing tS = new TripSightseeing();
		tS.setSightseeing(sightseeing);
		
		  em.getTransaction().begin();
	        em.persist(tS);
	        em.getTransaction().commit();
		
		
		//em.persist(tS);

		em.close();
		emf.close();
		return tS.getIdtripSightseeing();
	}

	@SuppressWarnings("unchecked")
	public List<City> getAllCities() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("City.findAll");
		List<City> ans = query.getResultList();
		em.close();
		emf.close();
		return ans;
	}

	public String removeCity(String ist) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int i = Integer.parseInt(ist);
		City city = em.find(City.class, i);
		em.remove(city);

		em.close();
		emf.close();
		return i + "  city removed ";
	}

	@SuppressWarnings("unchecked")
	public List<Traveler> getTravellers() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Traveler.findAll");
		List<Traveler> ans = query.getResultList();

		em.close();
		emf.close();

		return ans;
	}

	public int addTraveller(String name, Date BirthD) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		Traveler traveler = new Traveler();
		traveler.setTravelerName(name);
		traveler.setTraveler_BirthDate(BirthD);
		
		
		  em.getTransaction().begin();
	        em.persist(traveler);
	        em.getTransaction().commit();
		//em.persist(traveler);
		em.close();
		emf.close();
		return traveler.getIdtraveler();

	}

	public int addSight(String name, City city) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		Sightseeing sight = new Sightseeing();
		sight.setCity(city);
		sight.setSightSeeingsName(name);
		
		
		
		  em.getTransaction().begin();
	        em.persist(sight);
	        em.getTransaction().commit();
		//em.persist(sight);
		em.close();
		emf.close();
		return sight.getIdSightSeeings();

	}

	public int addSighti(String name, String ist) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int i = Integer.parseInt(ist);
		Sightseeing sight = new Sightseeing();
		City city = em.find(City.class, i);
		sight.setCity(city);
		sight.setSightSeeingsName(name);
		
		
		  em.getTransaction().begin();
	        em.persist(sight);
	        em.getTransaction().commit();
		//em.persist(sight);

		em.close();
		emf.close();

		return sight.getIdSightSeeings();
	}

	@SuppressWarnings("unchecked")
	public List<Sightseeing> sightsbycity(String cityidstr) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int i = Integer.parseInt(cityidstr);
		// City city = em.find(City.class, i);
		String statement = "SELECT st FROM Sightseeing st JOIN st.city c WHERE c.idcities = :id ";
		Query query = em.createQuery(statement).setParameter("id", i);
		List<Sightseeing> ans = query.getResultList();
		em.close();
		emf.close();

		return ans;
	}

	@SuppressWarnings("unchecked")
	public List<Trip> Tripl() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("Trip.findAll");
		List<Trip> ans = query.getResultList();
		;
		em.close();
		emf.close();

		return ans;
	}

	public String getTripCityName(String idt) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int id = Integer.parseInt(idt);
		String statement = "SELECT c FROM Trip t JOIN t.City c WHERE t.idtrip= :i";
		Query q = em.createQuery(statement).setParameter("i", id);
		@SuppressWarnings("unchecked")
		List<City> c = q.getResultList();

		em.close();
		emf.close();

		return c.get(0).getCityName();
	}

	@Override
	public City getCitybyTrip(String idt) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int id = Integer.parseInt(idt);
		String statement = "SELECT c FROM Trip t JOIN t.city c WHERE t.idtrip= :i";
		Query q = em.createQuery(statement).setParameter("i", id);
		@SuppressWarnings("unchecked")
		List<City> c = q.getResultList();

		em.close();
		emf.close();

		return c.get(0);
	}

	public List<Sightseeing> SightsforTrip(String tri) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int id = Integer.parseInt(tri);
		String statement = "Select s FROM Trip t JOIN t.tripSightseeings ts JOIN ts.sightseeing s WHERE t.idtrip= :i";
		Query q = em.createQuery(statement).setParameter("i", id);
		@SuppressWarnings("unchecked")
		List<Sightseeing> l = q.getResultList();

		em.close();
		emf.close();

		return l;
	}

	@SuppressWarnings("unchecked")
	public List<Sightseeing> newTripCitySights(String ci, String ti) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int id = Integer.parseInt(ti);
		int ic = Integer.parseInt(ci);
		String statement = "SELECT s FROM Sightseeing s join s.city cq WHERE (cq.idcities=:ic) AND s NOT IN (SELECT q FROM Trip a JOIN a.tripSightseeings b JOIN b.sightseeing q JOIN q.city c WHERE ((a.idtrip = :id)))";
		Query q = em.createQuery(statement).setParameter("ic", ic).setParameter("id", id);
		List<Sightseeing> ans = q.getResultList();
		em.close();
		emf.close();

		return ans;
	}

	public String removeSightFromTrip(String si, String ti) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int id = Integer.parseInt(ti);
		int is = Integer.parseInt(si);
		Trip trip = em.find(Trip.class, id);
		TripSightseeing trs = em.find(TripSightseeing.class, is);
		trs = trip.removeTripSightseeing(trs);
		em.remove(trs);
		em.close();
		emf.close();

		return "  Sightseeing " + si + " was renoved from trip" + ti + "and deleted";

	}

	public int removeSightFromTripint(String si, String ti) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int id = Integer.parseInt(ti);
		int is = Integer.parseInt(si);
		Trip trip = em.find(Trip.class, id);
		TripSightseeing trs = em.find(TripSightseeing.class, is);
		int i = trs.getSightseeing().getIdSightSeeings();
		trs = trip.removeTripSightseeing(trs);
		em.remove(trs);

		em.close();
		emf.close();

		return i;
	}

	public List<TripSightseeing> SightseeingforTrip(String tri) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int id = Integer.parseInt(tri);
		String statement = "Select s FROM Trip t JOIN t.tripSightseeings s WHERE t.idtrip= :i";
		Query q = em.createQuery(statement).setParameter("i", id);
		@SuppressWarnings("unchecked")
		List<TripSightseeing> l = q.getResultList();

		em.close();
		emf.close();

		return l;
	}

	public int addsighttotrip(String sigind, String tripind) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int id = Integer.parseInt(tripind);
		int is = Integer.parseInt(sigind);
		Trip trip = em.find(Trip.class, id);
		Sightseeing sight = em.find(Sightseeing.class, is);
		TripSightseeing tsi = new TripSightseeing();
		tsi.setSightseeing(sight);
		tsi.setTrip(trip);
		
		
		
		  em.getTransaction().begin();
	        em.persist(tsi);
	        em.getTransaction().commit();
		//em.persist(tsi);
		int ans = tsi.getIdtripSightseeing();
		em.close();
		emf.close();
		return ans;
	}

	@SuppressWarnings("unchecked")
	public List<Pic> picsForSightTrip(String idSight) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int id = Integer.parseInt(idSight);
		String statement = "Select p FROM Pic p Join p.tripSightseeing a where a.idtripSightseeing =:i";
		Query q = em.createQuery(statement).setParameter("i", id);
		List<Pic> ans = q.getResultList();
		em.close();
		emf.close();
		return ans;
	}

	public int setpicsForSighttrip(String addr, String idtrsig) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");	
		EntityManager em = emf.createEntityManager();
		int id = Integer.parseInt(idtrsig);
		TripSightseeing trs = em.find(TripSightseeing.class, id);
		Pic p = new Pic();
		p.setPicsAddr(addr);
		p.setTripSightseeing(trs);
		   em.getTransaction().begin();
		   
		   em.persist(p);
	        em.getTransaction().commit();
		  em.close();
	        emf.close();
		int ans =  p.getIdpics();
		return ans;
	}

	public Pic picbyid(String ids) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int i = Integer.parseInt(ids);
		Pic p = em.find(Pic.class, i);
		em.close();
		emf.close();
		return p;

	}

	public void deletPic(String idpic) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int i = Integer.parseInt(idpic);
		Pic p = em.find(Pic.class, i);
		p.setTripSightseeing(null);
		em.remove(p);
		em.close();
		emf.close();
	}

	@SuppressWarnings("unchecked")
	public List<Trip> GetTripsByCity(String cityidstr) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int i = Integer.parseInt(cityidstr);
		// City city = em.find(City.class, i);
		String statement = "SELECT tr FROM Trip tr JOIN tr.city c WHERE c.idcities = :id ";
		Query query = em.createQuery(statement).setParameter("id", i);
		List<Trip> ans = query.getResultList();

		em.close();
		emf.close();

		return ans;
	}

	public List<Pic> PicbyTrip(String tripid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int id = Integer.parseInt(tripid);
		String statement = "Select p FROM Pic p Join p.tripSightseeing s Join s.trip t WHERE t.idtrip =:i";
		Query q = em.createQuery(statement).setParameter("i", id);
		@SuppressWarnings("unchecked")
		List<Pic> l = q.getResultList();
		em.close();
		emf.close();
		return l;

	}

	public List<Trip> get3uniq() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		String q1 = "SELECT t FROM Trip t GROUP BY t.city ORDER BY COUNT(t.city) Asc";
		Query q = em.createQuery(q1).setMaxResults(3);
		@SuppressWarnings("unchecked")
		List<Trip> l = q.getResultList();
		em.close();
		emf.close();

		return l;

	}

	public List<City> get3common() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		String q1 = "SELECT a FROM Trip t Join t.city a GROUP BY a ORDER BY COUNT(a) Desc";
		Query q = em.createQuery(q1).setMaxResults(3);
		@SuppressWarnings("unchecked")
		List<City> l = q.getResultList();
		em.close();
		emf.close();

		return l;

	}

	public List<Pic> get3pics() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		String q1 = "SELECT a FROM Pic a GROUP BY a.tripSightseeing ORDER BY COUNT(a.tripSightseeing) Desc";
		Query q = em.createQuery(q1).setMaxResults(3);
		@SuppressWarnings("unchecked")
		List<Pic> l = q.getResultList();
		em.close();
		emf.close();

		return l;

	}

	public Traveler getTravelerbyId(String id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int i = Integer.parseInt(id);
		Traveler t = em.find(Traveler.class, i);
		em.close();
		emf.close();
		return t;

	}

	public void removetraveller(String travid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int i = Integer.parseInt(travid);
		Traveler t = em.find(Traveler.class, i);
		em.remove(t);
		em.close();
		emf.close();
		// return t + " removed ";
	}

	public int addSightStr(String tripid, String sightname) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int i = Integer.parseInt(tripid);
		Trip t = em.find(Trip.class, i);
		Sightseeing sight = new Sightseeing();
		sight.setCity(t.getCity());
		sight.setSightSeeingsName(sightname);
		
		
   em.getTransaction().begin();
		   
		   em.persist(sight);
	        em.getTransaction().commit();
	
		TripSightseeing tss = new TripSightseeing();
		tss.setSightseeing(sight);
		tss.setTrip(t);
		
		
		 em.getTransaction().begin();
		   
		   em.persist(tss);
	        em.getTransaction().commit();
	
	
	
		em.close();
		emf.close();
		return tss.getIdtripSightseeing();
	}

	// public int addSightTripIndStr(String tripid,String sightid) {
	// int i = Integer.parseInt(tripid);
	// //Sightseeing sight = new Sightseeing();
	// City city = em.find(City.class, i);
	// sight.setCity(city);
	// sight.setSightSeeingsName(name);
	// em.persist(sight);
	// return sight.getIdSightSeeings();
	//
	// }
	// public Trip tripbyid{
	//
	// }
	public Sightseeing getsightbyid(String ids) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int i = Integer.parseInt(ids);
		Sightseeing s = em.find(Sightseeing.class, i);
		em.close();
		emf.close();
		return s;
	}

	public TripSightseeing getTrsightbyid(String ids) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int i = Integer.parseInt(ids);
		TripSightseeing s = em.find(TripSightseeing.class, i);
		em.close();
		emf.close();
		return s;
	}

	public String removeTSightseeingFromCity(String trsid) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();

		int i2 = Integer.parseInt(trsid);
		TripSightseeing tmp = em.find(TripSightseeing.class, i2);
		Sightseeing todel = tmp.getSightseeing();
		tmp.setSightseeing(null);

		tmp.setTrip(null);
		em.remove(tmp);
		todel.setCity(null);
		todel.setSightSeeingsName(null);
		if (todel.getTripSightseeings().size() <= 1)
			todel.setTripSightseeings(null);
		em.remove(todel);

		em.close();
		emf.close();
		return "deleted sucssefully/unsucssefuly";
		// em.find(Sightseeing.class, tmp.);
		// Sightseeing todel=
	}

	public void removeSightUndefined(String ids) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("mywb2");
		EntityManager em = emf.createEntityManager();
		int i2 = Integer.parseInt(ids);
		Sightseeing todel = em.find(Sightseeing.class, i2);
		todel.setCity(null);
		todel.setSightSeeingsName(null);
		if (todel.getTripSightseeings().size() <= 1)
			todel.setTripSightseeings(null);
		em.remove(todel);
		em.close();
		emf.close();
	}
}
