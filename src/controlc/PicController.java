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
import model3.Pic;
import model3.Sightseeing;
import model3.Trip;
import model3.TripSightseeing;

/**
 * Servlet implementation class PicController
 */
@WebServlet("/PicController")
public class PicController extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	@EJB(beanName = "TripBe")
	IDac TBL;
	public static final String picsupdate = "/add pics.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PicController() {
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
		String forward = picsupdate;
		List<Trip> trl = TBL.Tripl();
		request.setAttribute("trl", trl);
		if (action.equals("trinit")) {
			// List<Trip> trl = TBL.Tripl();
			request.setAttribute("trl", trl);
		}
		if (action.equals("tslctd")) {
			String Tripid = request.getParameter("tripn");
			// City c = TBL.getCitybyTrip(Tripid);
			// String s=c.getIdcities()+"";
			// request.setAttribute("city", s);
			request.setAttribute("tna", Tripid);// <-----
			request.setAttribute("tripn", Tripid);
			List<Sightseeing> ls = TBL.SightsforTrip(Tripid);
			request.setAttribute("ls", ls);
			List<TripSightseeing> tsig = TBL.SightseeingforTrip(Tripid);
			request.setAttribute("tsig", tsig);
		}
		if (action.equals("sighslctd")) {
			String Tripid = request.getParameter("tripn");
			// City c = TBL.getCitybyTrip(Tripid);
			// String s=c.getIdcities()+"";
			// request.setAttribute("city", s);
			request.setAttribute("tna", Tripid);// <-----
			request.setAttribute("tripn", Tripid);
			List<Sightseeing> ls = TBL.SightsforTrip(Tripid);
			request.setAttribute("ls", ls);
			List<TripSightseeing> tsig = TBL.SightseeingforTrip(Tripid);
			request.setAttribute("tsig", tsig);
			String sight = request.getParameter("sights");
			request.setAttribute("slctsi", sight);
			try {
			List<Pic> pics = TBL.picsForSightTrip(sight);
			request.setAttribute("pics", pics);

			}
			catch(Exception e) {
				String pics=null;
				request.setAttribute("pics", pics);

			}
		}
		if (action.equals("picadd")) {
			String Tripid = request.getParameter("tripn");
			// City c = TBL.getCitybyTrip(Tripid);
			// String s=c.getIdcities()+"";
			// request.setAttribute("city", s);
		//	request.setAttribute("tna", Tripid);// <-----
			// request.setAttribute("tripn", Tripid);
			List<Sightseeing> ls = TBL.SightsforTrip(Tripid);
			request.setAttribute("ls", ls);
			List<TripSightseeing> tsig = TBL.SightseeingforTrip(Tripid);
			request.setAttribute("tsig", tsig);
			String sight = request.getParameter("slctsi");
			request.setAttribute("slctsi", sight);
			String picaddr = request.getParameter("pic_addr");
			TBL.setpicsForSighttrip(picaddr, sight);
			List<Pic> pics = TBL.picsForSightTrip(sight);
			request.setAttribute("pics", pics);
		//	request.setAttribute("tna", Tripid);// <-----
			request.setAttribute("tripn", Tripid);
		}
		if("delpic".equals(action)) {
			String i=request.getParameter("todel");
			TBL.deletPic(i);
			
			String Tripid = request.getParameter("tripn");
			List<Sightseeing> ls = TBL.SightsforTrip(Tripid);
			request.setAttribute("ls", ls);
			List<TripSightseeing> tsig = TBL.SightseeingforTrip(Tripid);
			request.setAttribute("tsig", tsig);
			String sight = request.getParameter("slctsi");
			request.setAttribute("slctsi", sight);
		//	String picaddr = request.getParameter("pic_addr");
		//	TBL.setpicsForSighttrip(picaddr, sight);
			List<Pic> pics = TBL.picsForSightTrip(sight);
			request.setAttribute("pics", pics);
			request.setAttribute("tripn", Tripid);
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
		doGet(request, response);
	}

}
