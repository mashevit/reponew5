package controlc;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import abc.da.Dac;
import abc.da.IDac;
import model3.Pic;
import model3.Trip;

/**
 * Servlet implementation class CExpCon
 */
@WebServlet("/CExpCon")
public class CExpCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String explorer = "/CityXplorer.jsp";
    /**
     * @see HttpServlet#HttpServlet()
     */
//	@EJB(beanName = "TripBe")
	IDac TBL;
    public CExpCon() {
        super();
        // TODO Auto-generated constructor stub
        TBL=new Dac();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String forward=explorer;
		String idcity=request.getParameter("city");
		ArrayList<Map<String, Object>> arl=new ArrayList<Map<String, Object>>();
		List<Trip> listtr=TBL.GetTripsByCity(idcity);
		if(listtr==null) request.setAttribute("err", 1);
		Iterator<Trip> myIterator = listtr.iterator();
		while (myIterator.hasNext()) {
			Trip t= myIterator.next();
			List<Pic> lpic = TBL.PicbyTrip(t.getIdtrip()+"");
			Map<String, Object> tmp=new HashMap<String, Object>();
			tmp.put("tripdate", t.getTripDate());
			tmp.put("piclist",lpic);
			tmp.put("sizel",lpic.size());
			arl.add(tmp);
		}
		request.setAttribute("tarrl", arl);
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
