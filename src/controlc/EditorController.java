package controlc;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Imgg.Stac;
import abc.da.Dac;
import abc.da.IDac;
import model3.City;
import model3.Sightseeing;
import model3.Trip;
import model3.TripSightseeing;

/**
 * Servlet implementation class EditorController
 */
@WebServlet("/EditorController")
public class EditorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// @EJB(beanName = "TripBe")
	IDac TBL;
	// @EJB(beanName = "bla")
	Stac stc;
	private static final String EdtPage = "/tripEditor.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditorController() {
		super();
		// TODO Auto-generated constructor stub
		TBL = new Dac();
		stc=new Stac();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("myaction");
		String forward = "";
		String init = request.getParameter("init");
		HttpSession session1 = request.getSession();
		int tri = 0;
		if ("listOftrips".equalsIgnoreCase(action)) {
			List<Trip> tr = TBL.Tripl();
			session1.setAttribute("trips", tr);
			if (!(tr == null)) {
				tri = tr.get(tr.size() - 1).getIdtrip();
			}
			session1.setAttribute("tri", tri);
			forward = EdtPage;
		}
		if ("true".equals(init)) {
			// Trip t = (Trip)tri.get("tr");
			String Tripid = tri + "";
			City c = TBL.getCitybyTrip(Tripid);
			session1.setAttribute("tripcity", c);
			List<Sightseeing> ls = TBL.SightsforTrip(Tripid);
			session1.setAttribute("ls", ls);
			List<TripSightseeing> tsig = TBL.SightseeingforTrip(Tripid);
			session1.setAttribute("tsig", tsig);
			List<Sightseeing> news = TBL.newTripCitySights(c.getIdcities() + "", Tripid);
			session1.setAttribute("news", news);
			session1.setAttribute("td",stc);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String act = request.getParameter("action");
		String offset = null;
		HttpSession session1 = request.getSession();
		String tri = "";
		String csi = "";
		if ("tripd".equals(act)) {
			String ind = request.getParameter("triptoedt");
			int tri1 = Integer.parseInt(ind);
			session1.removeAttribute("tri");
			session1.setAttribute("tri", tri1);
			tri = tri1 + "";
		}

		if ("trpsight".equals(act)) {
			
			offset = "about";

			String sight = request.getParameter("sightslct");
			int sightind;
			if (sight != null && sight.charAt(0) == 'n') {
				sight = sight.substring(1);
				session1.removeAttribute("chosensightind");
				tri = request.getParameter("trp");
				sightind = TBL.addsighttotrip(sight, tri);
				session1.setAttribute("chosensightind", sightind);
			} else {
				// sightind= Integer.parseInt(sight);
				session1.removeAttribute("chosensightind");
				tri = request.getParameter("trp");
				csi = sight;
				session1.setAttribute("chosensightind", sight);
			}
		
			session1.setAttribute("stp", 2);
		
		} else if ("dltsight".equals(act)) {
			offset = "about";
			String todel = request.getParameter("sightslct");
			if (todel.charAt(0) != 'n') {
				tri = request.getParameter("trp");
				TBL.removeSightFromTrip(todel, tri);
				csi = null;
			} else {
				todel = todel.substring(1);
				TBL.removeSightUndefined(todel);
				csi = null;
			}
		}
		if ("newsight".equals(act)) {
			offset = "about";

			String sight = request.getParameter("sight");
			tri = request.getParameter("trp");
			int tri2 = TBL.addSightStr(tri, sight);
			// request.setAttribute("tri",tri2);
			// tri=tri2+"";
			csi = tri2 + "";
			session1.removeAttribute("chosensightind");
			session1.setAttribute("chosensightind", tri2);

		}
		if ("dltsightcity".equals(act)) {
			offset = "about";

			String todel = request.getParameter("sightslct");
			int sightToDelInd;
			if (todel.charAt(0) != 'n') {
				tri = request.getParameter("trp");
				sightToDelInd = TBL.removeSightFromTripint(todel, tri);
				TBL.removeSightUndefined(sightToDelInd + "");
				csi = null;
			} else {
				todel = todel.substring(1);
				TBL.removeSightUndefined(todel);
				csi = null;
			}

		}
		if ("todb".equals((String) request.getAttribute("action"))) {

			// try {
			// final Properties jndiProps = new Properties();
			//
			// jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			// InitialContext ctx = new InitialContext(jndiProps);
			// Bla11 td = (Bla11) ctx.lookup("java:global/myDWP/Bla11!Imgg.Bla11");

			Stac tdmp = stc;
			Vector<Map> vec = tdmp.listItems();
			Iterator i = vec.iterator();
			while (i.hasNext()) {
				Map tmp = (Map) i.next();
				String imgaddr = (String) tmp.get("addr");
				TripSightseeing trs = (TripSightseeing) tmp.get("trs");
				TBL.setpicsForSighttrip(imgaddr, trs.getIdtripSightseeing() + "");
			}

			// tri=(String) request.getAttribute("trp");

			// tri = (String)request.getAttribute("trp");
			// tri=(String)session1.getAttribute(tri)+"";
			tdmp.clearItems();
			offset = null;

			// request.removeAttribute("td");
			// request.removeAttribute("action");
			// session1.removeAttribute("td");
			// session1.removeAttribute("action");
			session1.setAttribute("stp", 1);
			// } catch (Exception e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

			// set to session tr?

			/*
			 * List<Trip> tr = TBL.Tripl(); request.setAttribute("trips", tr); if (!(tr ==
			 * null)&&) { tri= tr.get(tr.size()-1).getIdtrip();
			 * 
			 * request.setAttribute("tri", tri);
			 */

			session1.removeAttribute("tripcity");
			session1.removeAttribute("ls");
			session1.removeAttribute("tsig");
			session1.removeAttribute("news");
			session1.removeAttribute("worksight");}
			// String Tripid= (String) request.getAttribute("tri");
			// Object a =request.getAttribute("tri");
			// String b="";}
			// if(a instanceof Integer) b = a.toString();
			// if(tri==null||"".equals(tri)) tri=b;
			/*
			 * if (tri == null) { List<Trip> tr = TBL.Tripl();
			 * session1.setAttribute("trips", tr); if (!(tr == null)) { tri =
			 * tr.get(tr.size() - 1).getIdtrip(); } session1.setAttribute("tri", tri);
			 * //forward = EdtPage; }
			 */
			if (tri != null && !"".equals(tri)) {
				int a = Integer.parseInt((String) (tri + ""));
				session1.removeAttribute(tri);
				session1.setAttribute(tri, a);
			} else {
				int trib = 0;
				List<Trip> tr = TBL.Tripl();
				session1.setAttribute("trips", tr);
				if (!(tr == null)) {
					trib = tr.get(tr.size() - 1).getIdtrip();
				}
				session1.removeAttribute(tri);
				session1.setAttribute("tri", trib);
				/// forward = EdtPage;
				tri = trib + "";
			}
			// tri =(String) request.getAttribute(tri);
			City c = TBL.getCitybyTrip(tri);
			session1.setAttribute("tripcity", c);
			List<Sightseeing> ls = TBL.SightsforTrip(tri);
			session1.setAttribute("ls", ls);
			List<TripSightseeing> tsig = TBL.SightseeingforTrip(tri);
			session1.setAttribute("tsig", tsig);
			List<Sightseeing> news = TBL.newTripCitySights(c.getIdcities() + "", tri);
			session1.setAttribute("news", news);
			TripSightseeing toput = (TripSightseeing) request.getAttribute("worksight");

			if (toput == null)
				toput = (csi != null && csi != "") ? TBL.getTrsightbyid(csi) : null;
			session1.setAttribute("worksight", toput);
			/*
			 * if(toput!=null) session1.setAttribute("wsts", TBL.getTrsightbyid(toput+""));
			 * else session1.setAttribute("wsts", null);
			 */
			request.setAttribute("scrollTo", offset);
			RequestDispatcher dispatcher = request.getRequestDispatcher(EdtPage);
			dispatcher.forward(request, response);
			// doGet(request, response);
		

	}
}
