package ca.qc.bdeb.maroute2.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by jason on 6/4/2016.
 */
public class MyDb extends SQLiteAssetHelper {
    // maroute database name and version
    public static final String DB_NAME = "maroute.db";
    public static final int DB_VERSION = 1;

    //
    private Context context;
    private SQLiteDatabase sqlitedb;

    //constructor
    public MyDb(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        //upgrade if ver <2
        //setForcedUpgrade(2);
    }
}
