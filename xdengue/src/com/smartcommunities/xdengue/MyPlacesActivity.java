package com.smartcommunities.xdengue;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.smartcommunities.xdengue.dataModel.CustomerData;
import com.smartcommunities.xdengue.dataModel.Place;

public class MyPlacesActivity extends customLogoActivity {
	/** Called when the activity is first created. */
	private CustomerData cd = null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		setContentView(R.layout.myplaces);
		final Context cont = this;
		try{
		cd = ((XdengueGlobalState)getApplication()).getCustomerData();
		}
		catch(Exception e){
			e.printStackTrace();
			Log.v("cutomer data: ", e.getMessage());
		}
		if(cd!=null){
			final ListView myPlaceList = (ListView)findViewById(R.id.myplaceslist);
			MyPlacesBaseAdapter myPlacesBaseAdapter = new MyPlacesBaseAdapter(cont, cd.getCustomer().getPlaces());
			myPlaceList.setAdapter(myPlacesBaseAdapter);
			
			myPlaceList.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Object o = myPlaceList.getItemAtPosition(position);
					Place selectedPlace = (Place) o;
					
					Toast.makeText(
							cont,
							"You have chosen: "
									+ " "
									+ selectedPlace.getSynonym()
									+ " "
									+ selectedPlace.getPlaceType()
									+ " "
									+ position
									+ " "
									+ cd.getCustomer().getPlaces().get(position).getSynonym(), Toast.LENGTH_LONG)
							.show();
					 
				}
			});
		}
		
	}

}
