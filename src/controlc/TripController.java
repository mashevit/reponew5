package controlc;


import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import abc.da.Dac;
import abc.da.IDac;
import model3.City;
import model3.Sightseeing;
import model3.Traveler;

/**
 * Servlet implementation class TripController
 */
@WebServlet("/TripController")
public class TripController extends HttpServlet {
	//@EJB(beanName = "TripBe")
	IDac TBL;
	private static final long serialVersionUID = 1L;
	public static final String tripcreate = "/create trip.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TripController() {
		super();
		// TODO Auto-generated constructor stub
		TBL=new Dac();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		String forward = tripcreate;
		// request.setAttribute("ci", -1);
		// request.setAttribute("ti", -1);
		HttpSession session1 = request.getSession();
		int cityn = (request.getParameter("ci") != null) ? Integer.parseInt(request.getParameter("ci")) : -1;
		int tri = (request.getParameter("ti") != null) ? Integer.parseInt(request.getParameter("ti")) : -1;

		if (action.equalsIgnoreCase("createtrip")) {
			forward = tripcreate;
			 List<City> cities = TBL.getAllCities();
			 request.setAttribute("cities", cities);
			 List<Traveler> tr = TBL.getTravellers();
			 request.setAttribute("travellers", tr);
		}
		if(action.equalsIgnoreCase("clean")) {
			forward = tripcreate;
			 List<City> cities = TBL.getAllCities();
			 request.setAttribute("cities", cities);
			 List<Traveler> tr = TBL.getTravellers();
			 request.setAttribute("travellers", tr);
			 session1.removeAttribute("ti");
			 session1.removeAttribute("ti");
			 session1.removeAttribute("ci");
			 session1.removeAttribute("triho");
			 session1.removeAttribute("trt");
			 session1.removeAttribute("trdy");
			 session1.removeAttribute("trm");
			 session1.removeAttribute("trid");


			
		}
		if (action.equals("addcity")) {
			String fromto = request.getParameter("fromto");
			if ("create".equals(fromto))
				forward = tripcreate;

			String name = request.getParameter("cn");
			int i = TBL.addCity(name);
			// List<City> cities = TBL.getAllCities();
			// request.setAttribute("cities", cities);
			cityn = i;
			request.setAttribute("ci", i);
		}

		if (action.equals("addtraveler")) {
			String fromto = request.getParameter("fromto");
			if ("create".equals(fromto))
				forward = tripcreate;

			String name = request.getParameter("trn");
			String bd = request.getParameter("bd");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			// surround below line with try catch block as below code throws checked
			// exception
			Traveler trv = new Traveler();
			trv.setTravelerName(name);
			try {
				java.util.Date date = sdf.parse(bd);
				Date sqld = new java.sql.Date(date.getTime());
				trv.setTraveler_BirthDate(sqld);
				int di = TBL.addTraveller(name, sqld);
				request.setAttribute("ti", di);
				tri = di;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Traveler> travellers = TBL.getTravellers();
			request.setAttribute("travellers", travellers);
		}
		if (action.equals("selectcity")) {
			forward = "/removemul.jsp";

			// List<City> cities = TBL.getAllCities();
			// request.setAttribute("cities", cities);
		}
		if ("addatrr".equals(action)) {
			String fromto = request.getParameter("fromto");
			if (fromto.equalsIgnoreCase("createtrip"))
				forward = tripcreate;
			// HttpSession session = request.getSession();
			String transit = request.getParameter("transit");
			String tripdate = request.getParameter("tripdate");
			String hotel = request.getParameter("hotel");
			String money = request.getParameter("money");
			String days = request.getParameter("days");
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			// surround below line with try catch block as below code throws checked
			// exception

			try {
				java.util.Date date = sdf.parse(tripdate);
				Date sqld = new java.sql.Date(date.getTime());
				session1.setAttribute("trid", sqld);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				session1.setAttribute("trid", "");
			}



			try {
				int mon = Integer.parseInt(money);
				session1.setAttribute("trm", mon+"");
			} catch (Exception e) {
				session1.setAttribute("trm", "");
			}
			try {
				int day = Integer.parseInt(days);
				session1.setAttribute("trdy", day+"");
			} catch (Exception e) {
				session1.setAttribute("trdy", "");
			}
			try {
				int trsi = Integer.parseInt(transit);
				session1.setAttribute("trt", trsi+"");
			} catch (Exception e) {
				session1.setAttribute("trt", "");
			}
			session1.setAttribute("triho", hotel);



		}
		if ("cityset".equals(action)) {
			String fromto = request.getParameter("fromto");
			if (fromto.equalsIgnoreCase("createtrip"))
				forward = tripcreate;
			String fp = request.getParameter("city");
			List<Sightseeing> lstg = TBL.sightsbycity(fp);
			request.setAttribute("sights", lstg);
			session1.setAttribute("city", fp);
			if (!fp.equals(cityn + ""))
				request.setAttribute("sig", "");
			request.setAttribute("ci", fp);
		}
		if ("settra".equals(action)) {
			String fromto = request.getParameter("fromto");
			if (fromto.equalsIgnoreCase("createtrip"))
				forward = tripcreate;
			tri = Integer.parseInt(request.getParameter("traveller"));
		}

		if ("addsightf".equals(action)) {
			String sight = request.getParameter("sight");
			request.setAttribute("sig", sight);
		}
		if("addsight".equals(action)) {
			String sight = request.getParameter("sign");
			String ci=request.getParameter("ci");
			sight = ""+TBL.addSighti(sight, ci);
			request.setAttribute("sig", sight);
		}
		if ("docreate".equals(action)) {
			String ci=request.getParameter("ci");//city
			String trt=request.getParameter("trt");//transit
			String trid=request.getParameter("trid");//date
			String triho=request.getParameter("triho");//hotel
			String trm=request.getParameter("trm");//money
			String trdy=request.getParameter("trdy");//days
			String ti=request.getParameter("ti");//traveller
			String sig=request.getParameter("sig");//sight
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				java.util.Date date = sdf.parse(trid);
				Date sqld = new java.sql.Date(date.getTime());
				int mon = Integer.parseInt(trm);
				int day = Integer.parseInt(trdy);
				int trsi = Integer.parseInt(trt);
				TBL.createTrip(ci, sqld, ti, day, mon, triho, sig, trsi);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			 session1.removeAttribute("ti");
			 session1.removeAttribute("ti");
			 session1.removeAttribute("ci");
			 session1.removeAttribute("triho");
			 session1.removeAttribute("trt");
			 session1.removeAttribute("trdy");
			 session1.removeAttribute("trm");
			 session1.removeAttribute("trid");
				
				
		}
		cityn = (request.getParameter("city") != null) ? Integer.parseInt(request.getParameter("city")) : cityn;

		session1.setAttribute("ci", cityn);
		tri = (request.getParameter("traveller") != null) ? Integer.parseInt(request.getParameter("traveller")) : tri;

		session1.setAttribute("ti", tri);
		List<City> cities = TBL.getAllCities();
		request.setAttribute("cities", cities);
		List<Traveler> tr = TBL.getTravellers();
		request.setAttribute("travellers", tr);
		if (cityn != -1) {
			List<Sightseeing> lstg = TBL.sightsbycity("" + cityn);
			request.setAttribute("sights", lstg);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);

		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
