package com.smartcommunities.xdengue.dataModel;

import java.util.HashMap;
import java.util.Map;

import com.smartcommunities.xdengue.R;

public class PlaceImageUtil {
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
	}
	
	public static int getIconId(String iconName){
		if(!placeImageIcons.containsKey(iconName)){
			return R.drawable.cluster;
		}
		return placeImageIcons.get(iconName);
	}

	public static Map<String, Integer> getPlaceImageIcons() {
		return placeImageIcons;
	}

	public static void setPlaceImageIcons(Map<String, Integer> placeImageIcons) {
		PlaceImageUtil.placeImageIcons = placeImageIcons;
	}
	
}
