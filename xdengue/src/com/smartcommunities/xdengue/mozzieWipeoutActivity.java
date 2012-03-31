package com.smartcommunities.xdengue;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

public class mozzieWipeoutActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mozziewipeout);

        // Reference the Gallery view
        Gallery g = (Gallery) findViewById(R.id.moozziewipeout);
        // Set the adapter to our custom adapter (below)
        g.setAdapter(new ImageAdapter(this));
        
        // Set a item click listener, and just Toast the clicked position
    /*.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Toast.makeText(Gallery1.this, "" + position, Toast.LENGTH_SHORT).show();
            }
            
        });*/
        
        // We also want to show context menu for longpressed items in the gallery
        registerForContextMenu(g);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        menu.add(R.string.mozziewipeouttext);
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        Toast.makeText(this, "Longpress: " + info.position, Toast.LENGTH_SHORT).show();
        return true;
    }

    public class ImageAdapter extends BaseAdapter {
        int mGalleryItemBackground;
        
        public ImageAdapter(Context c) {
            mContext = c;
            // See res/values/attrs.xml for the <declare-styleable> that defines
            // Gallery1.
            TypedArray a = obtainStyledAttributes(R.styleable.xdengue);
            mGalleryItemBackground = a.getResourceId(
                    R.styleable.xdengue_android_galleryItemBackground, 0);
            a.recycle();
        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView i = new ImageView(mContext);

            i.setImageResource(mImageIds[position]);
            i.setScaleType(ImageView.ScaleType.FIT_XY);
            //i.setLayoutParams(new Gallery.LayoutParams(136, 88));
            
            // The preferred Gallery item background
            i.setBackgroundResource(mGalleryItemBackground);
            
            return i;
        }

        private Context mContext;

        private Integer[] mImageIds = {
                R.drawable.prevention1,
                R.drawable.prevention2,
                R.drawable.prevention3,
                R.drawable.prevention4,
                R.drawable.prevention5
        };
    }

}

