package com.smartcommunities.xdengue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class XdengueActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final Context cont = this;
		Button oldUserButton = (Button) findViewById(R.id.oldUserButton);
		oldUserButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(cont, loginActivity.class));
			}
		});
		Button newUserButton = (Button) findViewById(R.id.newUserButton);
		newUserButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(cont, registerActivity.class));
			}
		});
    }
}