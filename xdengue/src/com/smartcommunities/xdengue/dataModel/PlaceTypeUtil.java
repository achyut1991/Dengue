package com.smartcommunities.xdengue.dataModel;

import java.util.HashMap;
import java.util.Map;

import com.smartcommunities.xdengue.R;

public class PlaceTypeUtil {
	private static Map<String, String> placeTypeName = new HashMap<String, String>();
	private static Map<String, Integer> placeImageIcons = new HashMap<String, Integer>();

	static {
		placeImageIcons.put("cluster.png", R.drawable.cluster);
		placeImageIcons.put("home.png", R.drawable.home);
		placeImageIcons.put("family.png", R.drawable.family);
		placeImageIcons.put("friends.png", R.drawable.friends);
		placeImageIcons.put("girlfriend.png", R.drawable.girlfriend);
		placeImageIcons.put("workoffice.png", R.drawable.workoffice);
		placeImageIcons.put("school.png", R.drawable.school);
		placeImageIcons.put("playground.png", R.drawable.playground);
		placeImageIcons.put("bus.png", R.drawable.bus);
		placeImageIcons.put("shoppingmall.png", R.drawable.shoppingmall);
		placeImageIcons.put("Park-urban.png", R.drawable.park);
		placeImageIcons.put("beach.png", R.drawable.beach);

		placeTypeName.put("cluster.png", "General");
		placeTypeName.put("home.png", "Home");
		placeTypeName.put("family.png", "Family");
		placeTypeName.put("friends.png", "Friends");
		placeTypeName.put("girlfriend.png", "Loved Ones");
		placeTypeName.put("workoffice.png", "Office");
		placeTypeName.put("school.png", "School");
		placeTypeName.put("playground.png", "Playground");
		placeTypeName.put("bus.png", "Bus");
		placeTypeName.put("shoppingmall.png", "Shopping Mall");
		placeTypeName.put("Park-urban.png", "Park");
		placeTypeName.put("beach.png", "Beach");
	}

	public static int getIconId(String iconName) {
		if (!placeImageIcons.containsKey(iconName)) {
			return R.drawable.cluster;
		}
		return placeImageIcons.get(iconName);
	}

	public static String getTypeNameId(String iconName) {
		if (!placeTypeName.containsKey(iconName)) {
			return "Other";
		}
		return placeTypeName.get(iconName);
	}

	public static Map<String, Integer> getPlaceImageIcons() {
		return placeImageIcons;
	}

	public static Map<String, String> getPlaceTypes() {
		return placeTypeName;
	}

	public static void setPlaceImageIcons(Map<String, Integer> placeImageIcons) {
		PlaceTypeUtil.placeImageIcons = placeImageIcons;
	}

	public static void setPlaceTypeNames(Map<String, String> placeTypeNames) {
		PlaceTypeUtil.placeTypeName = placeTypeNames;
	}

}
