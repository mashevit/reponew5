package controlc;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import abc.da.Dac;
import abc.da.ICSC;
import abc.da.IDac;
import model3.City;
import model3.Pic;
import model3.Traveler;
import model3.Trip;

/**
 * Servlet implementation class CSC
 */
@WebServlet("/CSC")
public class CSC extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// @EJB(beanName = "TripBe")
	ICSC TBL;

	private static final String CP = "/fileOne.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public CSC() {
		super();
		// TODO Auto-generated constructor stub
		TBL = (ICSC)new Dac();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, Serializable> ti = new HashMap<String, Serializable>();
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getParameter("myaction");
		String forward = "";
		String init = request.getParameter("init");
		if ("listofusers".equalsIgnoreCase(action)) {
			List<Traveler> tr = TBL.getTravellers();
			request.setAttribute("user", tr);

			if (tr.size() > 0) {
				ti.put("ind", tr.get(0).getIdtraveler());
				ti.put("tr", tr.get(0));
			}
			request.setAttribute("ti", ti);
			forward = CP;
		}
		if ("true".equals(init)) {

			List<City> lc = TBL.get3common();
			if (lc.size() > 2) {
				Map<String, Object> m1 = new HashMap<String, Object>();
				m1.put("cname", lc.get(0).getCityName());
				m1.put("cind", lc.get(0).getIdcities());
				Map<String, Object> m2 = new HashMap<String, Object>();
				m2.put("cname", lc.get(1).getCityName());
				Map<String, Object> m3 = new HashMap<String, Object>();
				m3.put("cname", lc.get(2).getCityName());
				List<Trip> city1 = TBL.GetTripsByCity(lc.get(0).getIdcities() + "");
				m1.put("travels", city1);
				List<Trip> city2 = TBL.GetTripsByCity(lc.get(1).getIdcities() + "");
				List<Trip> city3 = TBL.GetTripsByCity(lc.get(2).getIdcities() + "");
				m2.put("travels", city2);
				m3.put("travels", city3);
				m2.put("cind", lc.get(1).getIdcities());
				m3.put("cind", lc.get(2).getIdcities());
				request.setAttribute("c1", m1);
				request.setAttribute("c2", m2);
				request.setAttribute("c3", m3);
				List<Trip> lt = TBL.get3uniq();
				request.setAttribute("tu", lt);
				List<Pic> lpc = TBL.get3pics();
				request.setAttribute("lpc", lpc);
				List<City> cityl = TBL.getAllCities();
				request.setAttribute("cities", cityl);
			}
			forward = CP;
		}
		if (ti.isEmpty()) {
			ti.put("ind", -1);
			ti.put("tr", null);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		String act = request.getParameter("action");
		String bd = request.getParameter("birth_d");
		String nm = request.getParameter("name");
		HttpSession session1 = request.getSession();

		List<Traveler> tr = null;
		Map<String, Serializable> ti = new HashMap<String, Serializable>();
		boolean setuser = request.getAttribute("ti") != null;

		String init = request.getParameter("init");
		// TO-DO: city? String comm = request.getParameter("comments");
		if ("newuser".equals(act)) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			// surround below line with try catch block as below code throws checked
			// exception
			try {
				java.util.Date date = sdf.parse(bd);
				Date sqld = new java.sql.Date(date.getTime());
				int di = TBL.addTraveller(nm, sqld);
				// request.setAttribute("ti", di);
				ti.put("ind", di);
				ti.put("tr", TBL.getTravelerbyId(di + ""));
				setuser = true;
				session1.removeAttribute("ti");
				session1.setAttribute("ti", ti);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * List<Traveler> travellers = TBL.getTravellers(); request.setAttribute("user",
			 * travellers);
			 */
		}
		if ("createt".equals(act)) {
			String ci = request.getParameter("city");// city
			String trt = request.getParameter("transit");// transit
			String trid = request.getParameter("date");// date
			String triho = request.getParameter("hotel");// hotel
			String trm = request.getParameter("budget");// money
			String trdy = request.getParameter("duration");// days
			String ti2 = request.getParameter("user");// traveller
			// String sig=request.getParameter("sig");//sight
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			try {
				java.util.Date date = sdf.parse(trid);
				Date sqld = new java.sql.Date(date.getTime());
				/*
				 * int mon = Integer.parseInt(trm); int day = Integer.parseInt(trdy); int trsi =
				 * Integer.parseInt(trt);
				 */
				TBL.createTripStrings(ci, sqld, ti2, trdy, trm, triho, trt);
				// TBL.createTrip(ci, sqld, ti2, day, mon, triho, null, trsi);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		if ("usr".equals(act)) {
			session1.removeAttribute("ti");

			Traveler t = TBL.getTravelerbyId(request.getParameter("traveller"));
			ti.put("ind", t.getIdtraveler());
			ti.put("tr", t);
			session1.setAttribute("ti", ti);
			setuser = true;
		} else if ("rmvusr".equals(act)) {
			TBL.removetraveller(request.getParameter("userid"));
			setuser = false;
		}

		tr = TBL.getTravellers();
		if (!(tr == null) & !setuser) {
			session1.removeAttribute("ti");
			ti.put("ind", tr.get(0).getIdtraveler());
			ti.put("tr", tr.get(0));
			session1.setAttribute("ti", ti);
		}
		request.setAttribute("user", tr);
		List<City> lc = TBL.get3common();
		Map<String, Object> m1 = new HashMap<String, Object>();
		m1.put("cname", lc.get(0).getCityName());
		m1.put("cind", lc.get(0).getIdcities());
		Map<String, Object> m2 = new HashMap<String, Object>();
		m2.put("cname", lc.get(1).getCityName());
		Map<String, Object> m3 = new HashMap<String, Object>();
		m3.put("cname", lc.get(2).getCityName());
		List<Trip> city1 = TBL.GetTripsByCity(lc.get(0).getIdcities() + "");
		m1.put("travels", city1);
		List<Trip> city2 = TBL.GetTripsByCity(lc.get(1).getIdcities() + "");
		List<Trip> city3 = TBL.GetTripsByCity(lc.get(2).getIdcities() + "");
		m2.put("travels", city2);
		m3.put("travels", city3);
		m2.put("cind", lc.get(1).getIdcities());
		m3.put("cind", lc.get(2).getIdcities());
		request.setAttribute("c1", m1);
		request.setAttribute("c2", m2);
		request.setAttribute("c3", m3);
		List<Trip> lt = TBL.get3uniq();
		request.setAttribute("tu", lt);
		List<Pic> lpc = TBL.get3pics();
		request.setAttribute("lpc", lpc);
		List<City> cityl = TBL.getAllCities();
		request.setAttribute("cities", cityl);

		RequestDispatcher dispatcher = request.getRequestDispatcher(CP);
		dispatcher.forward(request, response);

	}

}
