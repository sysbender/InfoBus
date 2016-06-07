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
 * Created by jason on 6/3/2016.
 */
public class Tab1Fragment extends Fragment {
    private ListView favoriteList_lv;
    FavoriteList favoriteList;
    ArrayAdapter<Favorite> adapter;
    MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_layout, container, false);
        // get listview
        favoriteList_lv= (ListView) view.findViewById(R.id.favoriteList_lv);
        //get favoriteList from mainActivity
        favoriteList =((MainActivity)getActivity()).getFavoriteList();
        setupListView();
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        adapter.notifyDataSetChanged();


    }
    private void setupListView() {
        adapter = new ArrayAdapter<Favorite>(getActivity(), R.layout.favorite_list_item, favoriteList);
        favoriteList_lv.setAdapter(adapter);
        favoriteList_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Favorite favorite=  adapter.getItem(position);


                //Toast.makeText(getActivity(), favorite.toString(), Toast.LENGTH_SHORT).show();
                ((Tab2Fragment )((((MainActivity)getActivity()).getTabFragmentList())[1])).setFavorite(favorite);
                ((MainActivity)getActivity()).showTab(1);



            }
        });

    }
}