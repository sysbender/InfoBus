package ca.qc.bdeb.maroute2.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ca.qc.bdeb.maroute2.MainActivity;
import ca.qc.bdeb.maroute2.R;
import ca.qc.bdeb.maroute2.models.*;
import ca.qc.bdeb.maroute2.utils.DbGetter;

/**
 * Created by jason on 6/3/2016.
 */
public class Tab2Fragment extends Fragment implements View.OnClickListener {
    //request code to identify different destination
    private static final int REQUEST_CODE_1 = 1, REQUEST_CODE_2 = 2, REQUEST_CODE_3 = 3;
    //private LinearLayout search_1_layout;
    private ImageButton search_5_button;
    private LinearLayout search_1_layout, search_2_layout, search_3_layout;
    private TextView search_1_tv, search_2_tv, search_3_tv, search_4_tv;
    // attributes
    private Route route;
    private Direction direction;
    private Stop stop;
    private FavoriteList favoriteList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_layout, container, false);
        //get favoriteList
        favoriteList = ((MainActivity) getActivity()).getFavoriteList();
        // in fragment get view first before findViewbyId
        search_5_button = (ImageButton) view.findViewById(R.id.search_5_button);
        search_5_button.setOnClickListener(this);
        //get linearLayout
        search_1_layout = (LinearLayout) view.findViewById(R.id.search_1_layout);
        search_1_layout.setOnClickListener(this);

        search_2_layout = (LinearLayout) view.findViewById(R.id.search_2_layout);
        search_2_layout.setOnClickListener(this);

        search_3_layout = (LinearLayout) view.findViewById(R.id.search_3_layout);
        search_3_layout.setOnClickListener(this);

        //get textViews
        search_1_tv = (TextView) view.findViewById(R.id.search_1_tv);
        search_2_tv = (TextView) view.findViewById(R.id.search_2_tv);
        search_3_tv = (TextView) view.findViewById(R.id.search_3_tv);
        search_4_tv = (TextView) view.findViewById(R.id.search_4_tv);
        syncTextViews();
        showArrivalTime();

        return view;
    }
    // check bundled favorite from arguments




    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
       // Log.e("test-onHiddenChanged", "on onHiddenChanged");
        syncTextViews();
        showArrivalTime();

    }



    public void setFavorite(Favorite favorite) {
                route = favorite.getRoute();
                direction = favorite.getDirection();
                stop = favorite.getStop();



    }

    @Override
    public void onClick(View v) {
        //Log.d("========logcat====", "################### search button 5 clicked ##############################");
        final FragmentManager fm = getFragmentManager();

        switch (v.getId()) {
            case R.id.search_5_button:
                if (route != null && direction != null && stop != null) {
                    Favorite f = new Favorite(route, direction, stop);
                    if (!favoriteList.contains(f)) {
                        favoriteList.add(f);
                        Toast.makeText(this.getActivity(), getResources().getString(R.string.toast_favorite_added) + f.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        favoriteList.remove(f);
                        Toast.makeText(this.getActivity(), getResources().getString(R.string.toast_favorite_removed) + f.toString(), Toast.LENGTH_SHORT).show();
                    }
                    syncFavoriteButton();
                    try {
                        favoriteList.save();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this.getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                    //update main activity
                }

                break;
            case R.id.search_1_layout:
                // Toast.makeText(this.getActivity(), "search 1 layout is clicked!", Toast.LENGTH_LONG).show();
                final Search1DialogFragment s1 = new Search1DialogFragment();
                //for result
                s1.setTargetFragment(this, REQUEST_CODE_1);
                s1.show(fm, "search and select");
                break;
            case R.id.search_2_layout:
                final Search2DialogFragment s2 = Search2DialogFragment.newInstance(route);
                s2.setTargetFragment(this, REQUEST_CODE_2);
                s2.show(fm, "search and select");
                break;
            case R.id.search_3_layout:

                final Search3DialogFragment s3 = Search3DialogFragment.newInstance(route, direction);
                s3.setTargetFragment(this, REQUEST_CODE_3);
                s3.show(fm, "search and select");
                break;
        }
    }

    // get result from search and select DialogFragment
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle;
        switch (requestCode) {

            case REQUEST_CODE_1:
                if (resultCode == Activity.RESULT_OK) {
                    bundle = intent.getExtras();
                    route = (Route) bundle.getSerializable("route");
                        direction = null;
                        stop = null;

                    //Toast.makeText(this.getActivity(),  r.toString(), Toast.LENGTH_LONG).show();
                    syncTextViews();
                }
                break;
            case REQUEST_CODE_2:
                if (resultCode == Activity.RESULT_OK) {
                    bundle = intent.getExtras();
                    direction = (Direction) bundle.getSerializable("direction");
                        stop = null;
                    syncTextViews();
                }
                break;
            case REQUEST_CODE_3:
                if (resultCode == Activity.RESULT_OK) {
                    bundle = intent.getExtras();
                    stop = (Stop) bundle.getSerializable("stop");
                    syncTextViews();
                    showArrivalTime();



                }
                break;
        }


    }

    private void showArrivalTime() {
        //show stop times
        // query direction from db
        if(route != null && direction !=null && stop !=null) {
            DbGetter db = DbGetter.getInstance(getActivity());
            db.open();
            List<String> timeList = db.getTimeList(route.getId(), direction.getId(), stop.getCode(), 1);
            db.close();

            if (timeList.size() > 0) {
                search_4_tv.setText(timeList.get(0));
            }
        }
    }



    // change TextViews according to attributes
    public void syncTextViews() {
        //verify state of route,direction,stop
        if (route == null) {    // all are null
            direction = null;
            stop = null;

            search_1_layout.setClickable(true);
            search_2_layout.setClickable(false);
            search_3_layout.setClickable(false);


        } else if (direction == null) { // only route is not null
            stop = null;
            search_1_layout.setClickable(true);
            search_2_layout.setClickable(true);
            search_3_layout.setClickable(false);

        } else if (stop == null) {   // only route and direction are not null
            search_1_layout.setClickable(true);
            search_2_layout.setClickable(true);
            search_3_layout.setClickable(true);

        } else {  // all are not null
            search_1_layout.setClickable(true);
            search_2_layout.setClickable(true);
            search_3_layout.setClickable(true);

        }

        //set textviews

        if (route == null) {
            search_1_tv.setText(getResources().getString(R.string.search_1_tv_hint));
        } else {
            search_1_tv.setText(route.toString());
        }

        if (direction == null) {
            search_2_tv.setText(getResources().getString(R.string.search_2_tv_hint));
        } else {
            search_2_tv.setText(direction.toString());
        }

        if (stop == null) {
            search_3_tv.setText(getResources().getString(R.string.search_3_tv_hint));
        } else {
            search_3_tv.setText(stop.toString());
        }

        if (route == null || direction == null || stop == null) {
            search_4_tv.setText(getResources().getString(R.string.search_4_tv_hint));
        }

        syncFavoriteButton();
    }

    // change Favorite button according attributes
    public void syncFavoriteButton() {
        if (route != null && direction != null && stop != null) {
            //set favorite button clickable
            search_5_button.setClickable(true);

            if (favoriteList.contains(new Favorite(route, direction, stop))) {
                //select image = search_5_favorite_c
                search_5_button.setImageResource(R.mipmap.search_5_favorite_c);

            } else {
                // set image = search_5_favorite_y
                search_5_button.setImageResource(R.mipmap.search_5_favorite_y);
            }

        } else {
            //set favorite button unclickable
            search_5_button.setClickable(false);
            //set image search_5_favorite_g
            search_5_button.setImageResource(R.mipmap.search_5_favorite_g);

        }

    }
}