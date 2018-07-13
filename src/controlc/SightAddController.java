package controlc;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abc.da.Dac;
import abc.da.IDac;
import model3.City;
import model3.Sightseeing;
import model3.Trip;
import model3.TripSightseeing;

/**
 * Servlet implementation class SightAddController
 */
@WebServlet("/SightAddController")
public class SightAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	@EJB(beanName = "TripBe")
	IDac TBL;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public static final String tripinit = "/TripSightUpdate.jsp";

	public SightAddController() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getParameter("action");
		String forward = tripinit;
		String city = request.getParameter("city");
	//	HttpSession session1 = request.getSession();
		List<Trip> trl = TBL.Tripl();
		request.setAttribute("trl", trl);
		if (action.equals("trinit")) {
			//List<Trip> trl = TBL.Tripl();
			request.setAttribute("trl", trl);
		}
		if (action.equals("tslctd")) {
			String Tripid = request.getParameter("tripn");
			City c = TBL.getCitybyTrip(Tripid);
		//	String s=c.getIdcities()+"";
			//request.setAttribute("city", s);
			request.setAttribute("tna", Tripid);//<-----
			request.setAttribute("tripn", Tripid);	
			List<Sightseeing> ls = TBL.SightsforTrip(Tripid);
			request.setAttribute("ls", ls);
			List<TripSightseeing> tsig = TBL.SightseeingforTrip(Tripid);
			request.setAttribute("tsig", tsig);
			List<Sightseeing> news = TBL.newTripCitySights(c.getIdcities() + "", Tripid);
			request.setAttribute("news", news);
		}
		if (action.equals("deletes")) {
			String todel = request.getParameter("sight");

			String Tripid = request.getParameter("tripn");
		    city = TBL.getCitybyTrip(Tripid).getIdcities()+"";	
			TBL.removeSightFromTrip(todel, Tripid);
			//request.setAttribute("city", c.getIdcities());
			request.setAttribute("tripn", Tripid);
			List<Sightseeing> ls = TBL.SightsforTrip(Tripid);
			request.setAttribute("ls", ls);
			List<TripSightseeing> tsig = TBL.SightseeingforTrip(Tripid);
			request.setAttribute("tsig", tsig);
			List<Sightseeing> news = TBL.newTripCitySights(city, Tripid);
			request.setAttribute("news", news);
		}
		if ("addsightfn".equals(action)) {
			String sfn = request.getParameter("sight");
			String Tripid = request.getParameter("tripn");
			TBL.addsighttotrip(sfn, Tripid);
			city = TBL.getCitybyTrip(Tripid).getIdcities()+"";
			List<TripSightseeing> tsig = TBL.SightseeingforTrip(Tripid);
			request.setAttribute("tsig", tsig);
			List<Sightseeing> news = TBL.newTripCitySights(city, Tripid);
			request.setAttribute("news", news);
			request.setAttribute("tripn", Tripid);
		}
		if ("addsight".equals(action)) {
			String whereto = request.getParameter("whereto");
			String sfn = request.getParameter("sign");
			String Tripid = request.getParameter("tripn");  //<-----
			city =TBL.getCitybyTrip(Tripid).getIdcities()+"";
			int sigt =TBL.addSighti(sfn, city);
			if ("totrip".equals(whereto))
				TBL.addsighttotrip(sigt+"", Tripid);
			List<TripSightseeing> tsig = TBL.SightseeingforTrip(Tripid);
			request.setAttribute("tsig", tsig);
			request.setAttribute("tripn", Tripid);
			List<Sightseeing> news = TBL.newTripCitySights(city, Tripid);
			request.setAttribute("news", news);

		}
		//request.setAttribute("city", city);
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
		doGet(request, response);
	}

}
