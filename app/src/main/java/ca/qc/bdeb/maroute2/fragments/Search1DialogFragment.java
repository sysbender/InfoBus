package ca.qc.bdeb.maroute2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import ca.qc.bdeb.maroute2.MainActivity;
import ca.qc.bdeb.maroute2.R;
import ca.qc.bdeb.maroute2.utils.Search1DialogListener;

/**
 * Created by jason on 6/5/2016.
 */
public class Search1DialogFragment extends DialogFragment {
    SearchView ss_searchview;
    ListView ss_listview;
    ArrayAdapter<String> adapter;
    // to get the result from dialog

    String[] test = {"hello", "salut"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_select, null);
        //set title for dialog
        getDialog().setTitle("Select Route");

        //get view by id
        ss_searchview = (SearchView) view.findViewById(R.id.ss_searchview);
        ss_listview = (ListView) view.findViewById(R.id.ss_listview);


        //adapter for listview
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        ss_listview.setAdapter(adapter);
        adapter.addAll(test);
        //ss_listview.setOnClickListener(new HandleClickListView());
        ss_listview.setOnItemClickListener(new HandleClickListView());

        //search hint
        ss_searchview.setQueryHint("type to search");
        ss_searchview.setOnQueryTextListener(new HandleQueryText());


        return view;

        // return super.onCreateView(inflater, container, savedInstanceState);


    }

    // inner class for ListView click listener
    private class HandleClickListView implements AdapterView.OnItemClickListener {



        @Override
        //public void onItemClick(AdapterView<?> parent, View view, int position, long id) { }
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //String value = (String)adapter.getItemAtPosition(position);
            String value = adapter.getItem(position);

        }
    }

    // inner class for Searchview listner
    private class HandleQueryText implements SearchView.OnQueryTextListener {

        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            adapter.getFilter().filter(newText);
            return false;
        }
    }


}
