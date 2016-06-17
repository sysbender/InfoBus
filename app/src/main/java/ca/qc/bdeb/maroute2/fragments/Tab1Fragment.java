package ca.qc.bdeb.maroute2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import ca.qc.bdeb.maroute2.MainActivity;
import ca.qc.bdeb.maroute2.R;
import ca.qc.bdeb.maroute2.models.Direction;
import ca.qc.bdeb.maroute2.models.Favorite;
import ca.qc.bdeb.maroute2.models.FavoriteList;


/**
 * This tab shows the favorite list
 */
public class Tab1Fragment extends Fragment {
    private ListView favoriteList_lv;
    // ArrayList for favorite
    FavoriteList favoriteList;
    // adapter for favorite listview
    ArrayAdapter<Favorite> adapter;
    MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_layout, container, false);
        // get listview
        favoriteList_lv = (ListView) view.findViewById(R.id.favoriteList_lv);
        //get favoriteList from mainActivity
        favoriteList = ((MainActivity) getActivity()).getFavoriteList();
        // setup
        setupListView();
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        adapter.notifyDataSetChanged();


    }


    // use the favoriteList (ArrayList) from MainActivity as the adapter for ListView, setup OnItemClick
    private void setupListView() {
        adapter = new ArrayAdapter<Favorite>(getActivity(), R.layout.favorite_list_item, favoriteList);
        favoriteList_lv.setAdapter(adapter);
        favoriteList_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // get favorite object from clicked listview item
                Favorite favorite = adapter.getItem(position);
                // set the favorite for Tab2Fragment (search Fragment)
                ((Tab2Fragment) ((((MainActivity) getActivity()).getTabFragmentList())[1])).setFavorite(favorite);
                // let the MainActivity show the search Fragment with select Favorite
                ((MainActivity) getActivity()).showTab(1);
            }
        });

    }
}