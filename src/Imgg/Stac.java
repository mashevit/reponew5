package Imgg;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import model3.TripSightseeing;

public class Stac {
	public static boolean a =false;
	static Vector<Map> imgdetails;

	public Stac() {
		if(!a)
		imgdetails = new Vector<>();
		a=true;
		// TODO Auto-generated constructor stub
	}

	public void addItem(String imgaddr, TripSightseeing tripsight) {
		Map<String, Serializable> tmp = new HashMap<String, Serializable>();
		tmp.put("addr", imgaddr);
		tmp.put("trs", tripsight);
		imgdetails.add(tmp);
	}

	public void clearItems() {
		imgdetails.clear();
	}

	public void removeItem(String ind) {
		int indi = Integer.parseInt(ind);
		imgdetails.remove(indi);
	}

	public Vector<Map> listItems() {
		return imgdetails;
	}
}
