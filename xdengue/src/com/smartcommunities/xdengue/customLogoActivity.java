package com.smartcommunities.xdengue;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class customLogoActivity extends Activity {
 
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
 
        setContentView(R.layout.main);
 
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.logo);
 
    }

}
