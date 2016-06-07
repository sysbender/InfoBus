package ca.qc.bdeb.maroute2;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.ArrayList;

import ca.qc.bdeb.maroute2.fragments.Tab1Fragment;
import ca.qc.bdeb.maroute2.fragments.Tab2Fragment;
import ca.qc.bdeb.maroute2.fragments.Tab3Fragment;
import ca.qc.bdeb.maroute2.models.Favorite;
import ca.qc.bdeb.maroute2.models.FavoriteList;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    //tab number
    private static final int TABNUM = 3;

    //layout for tab
    private LinearLayout[] mTabLayout = new LinearLayout[TABNUM];
    private int[] mTabLayoutID = {R.id.id_tab_1, R.id.id_tab_2, R.id.id_tab_3};

    //image for tab
    private ImageButton[] mTabImageButton = new ImageButton[TABNUM];
    private int[] mTabImageButtonID = {R.id.id_tab_1_img, R.id.id_tab_2_img, R.id.id_tab_3_img};

    //color, gray picture for table images
    private int[] mTabImageColorID = {R.mipmap.tab_1_c, R.mipmap.tab_2_c, R.mipmap.tab_3_c};
    private int[] mTabImageGrayID = {R.mipmap.tab_1_g, R.mipmap.tab_2_g, R.mipmap.tab_3_g};


    private Fragment[] mTabFragment = {new Tab1Fragment(), new Tab2Fragment(), new Tab3Fragment()};
    //private Fragment[] mTabFragment = {null,null, null} ;

    private FavoriteList favoriteList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_main);
        favoriteList = new FavoriteList();
        loadFavoriteList();
        initViews(); //
        initFragments();
        initEvents(); //
        showTab(0); //
    }

    // load favorite from file
    private void loadFavoriteList(){
        try {
            favoriteList.load();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public FavoriteList getFavoriteList(){
        return this.favoriteList;
    }

    public Fragment[] getTabFragmentList(){
        return mTabFragment;

    }


    private void initFragments() {
        // mTabFragment = new

    }


    //setOnclickListener for tab
    private void initEvents() {
        for (int i = 0; i < TABNUM; i++) {
            mTabLayout[i].setOnClickListener(this);
        }

    }
    // get all the layout for tabs
    private void initViews() {
        for (int i = 0; i < TABNUM; i++) {
            mTabLayout[i] = (LinearLayout) findViewById(mTabLayoutID[i]);
            mTabImageButton[i] = (ImageButton) findViewById(mTabImageButtonID[i]);
        }


    }

    @Override
    public void onClick(View v) {
        setGrayAll();
        switch (v.getId()) {
            case R.id.id_tab_1:
                showTab(0);
                break;
            case R.id.id_tab_2:
                showTab(1);
                break;
            case R.id.id_tab_3:
                showTab(2);
                break;
        }
    }


    private void selectTab(int t) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragments(transaction);

        switch (t) {
            case 0:
                mTabImageButton[t].setImageResource(mTabImageColorID[t]);

                if (mTabFragment[t] == null) {
                    mTabFragment[t] = new Tab1Fragment();
                    transaction.add(R.id.id_content, mTabFragment[t]);
                    transaction.show(mTabFragment[t]);
                } else {
                    transaction.show(mTabFragment[t]);
                }
                //Log.e("ma-route", "select " + t);
                break;
            case 1:
                mTabImageButton[t].setImageResource(mTabImageColorID[t]);
                if (mTabFragment[t] == null) {
                    mTabFragment[t] = new Tab2Fragment();
                    transaction.add(R.id.id_content, mTabFragment[t]);
                    transaction.show(mTabFragment[t]);
                } else {
                    transaction.show(mTabFragment[t]);
                }
                //Log.e("ma-route", "select " + t);
                break;
            case 2:
                mTabImageButton[t].setImageResource(mTabImageColorID[t]);
                if (mTabFragment[t] == null) {
                    mTabFragment[t] = new Tab3Fragment();
                    transaction.add(R.id.id_content, mTabFragment[t]);
                    transaction.show(mTabFragment[t]);
                } else {
                    transaction.show(mTabFragment[t]);
                }
                //Log.e("ma-route", "select " + t);
                break;
        }
        transaction.commit();
    }


    public void showTab(int t) {
        //change img
        mTabImageButton[t].setImageResource(mTabImageColorID[t]);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        //hideFragments(ft);


        for (int i = 0; i < TABNUM; i++) {
            if (i == t) { //show
                if (!mTabFragment[t].isAdded()) {
                    ft.add(R.id.id_content, mTabFragment[t]);
                }
                ft.show(mTabFragment[t]);

            } else { //hide
                if (mTabFragment[i].isAdded()) {
                    ft.hide(mTabFragment[i]);
                }
            }
        }
        ft.commit();
    }


    private void hideFragments(FragmentTransaction transaction) {
        for (int i = 0; i < TABNUM; i++) {
            if (mTabFragment[i] != null) {
                transaction.hide(mTabFragment[i]);
                //Log.e("ma-route", "hide " + i);
            }
        }

    }

    private void setGrayAll() {
        for (int i = 0; i < TABNUM; i++) {
            mTabImageButton[i].setImageResource(mTabImageGrayID[i]);
        }
    }

}
