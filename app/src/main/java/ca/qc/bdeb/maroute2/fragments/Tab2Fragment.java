package ca.qc.bdeb.maroute2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ca.qc.bdeb.maroute2.R;

/**
 * Created by jason on 6/3/2016.
 */
public class Tab2Fragment extends Fragment implements View.OnClickListener {
    //private LinearLayout search_1_layout;
    private ImageButton search_5_button;
    private LinearLayout search_1_layout, search_2_layout, search_3_layout;
    private TextView search_1_tv,  search_2_tv,  search_3_tv,  search_4_tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_layout, container, false);
        // in fragment get view first before findViewbyId
        search_5_button = (ImageButton)view.findViewById(R.id.search_5_button);
        search_5_button.setOnClickListener(this);
        //get linearLayout
        search_1_layout = (LinearLayout) view.findViewById(R.id.search_1_layout);
        search_1_layout.setOnClickListener(this);
        //get textViews



        return view;
    }

    @Override
    public void onClick(View v) {
        Log.d("========logcat====","################### search button 5 clicked ##############################");

        switch (v.getId()){
            case R.id.search_5_button:

                Toast.makeText(this.getActivity(), "favorite Button is clicked!", Toast.LENGTH_LONG).show();
                break;
            case R.id.search_1_layout:
                Toast.makeText(this.getActivity(), "search 1 layout is clicked!", Toast.LENGTH_LONG).show();
                final FragmentManager fm = getFragmentManager();
                final Search1DialogFragment s = new Search1DialogFragment();
                s.show(fm, "search and select");
                break;
        }
    }
}