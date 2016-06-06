package ca.qc.bdeb.maroute2.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ca.qc.bdeb.maroute2.models.Direction;
import ca.qc.bdeb.maroute2.models.Route;
import ca.qc.bdeb.maroute2.models.Stop;

/**
 * Created by jason on 6/5/2016.
 */
public class DbGetter {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DbGetter instance;
    //private constructor
    private DbGetter(Context context){
        this.openHelper = new DbHelper(context);

    }

    // singleton instance of DbGetter
    public static DbGetter getInstance(Context context){
        if(instance == null){
            instance = new DbGetter(context);
        }
        return instance;
    }

    // open db
    public void open(){
        this.database = openHelper.getReadableDatabase();
    }
    // close db
    public void close(){
        if(database != null){
            this.database.close();
        }
    }

    // get routes

    public List<Route> getRouteList(){
        List<Route> routeList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select route_id, route_name from routes" , null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            routeList.add(new Route(cursor.getString(0), cursor.getString(1)));
            cursor.moveToNext();
        }
        cursor.close();
        return routeList;

    }
    // return direction list for route = route_id
    public List<Direction> getDirectionList(String route_id){
        List<Direction> directionList = new ArrayList<>();
        Cursor cursor = database.query("route_directions",new String[]{"direction_id","trip_headsign"},"route_id=?",new String[]{route_id}, null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            directionList.add(new Direction(cursor.getString(0), cursor.getString(1)));
            cursor.moveToNext();
        }
        cursor.close();
        return directionList;


    }

    // return stop list for route=route_id and direction = direction_id
    public List<Stop> getStopList(String route_id, String direction_id){
        List<Stop> stopList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select route_stops.stop_code, stops.stop_name " +
                "from route_stops, stops " +
                "where route_id= ? and direction_id= ? and route_stops.stop_code=stops.stop_code " +
                "order by stop_sequence",
                 new String[] {route_id, direction_id});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            stopList.add(new Stop(cursor.getString(0), cursor.getString(1)));
            cursor.moveToNext();
        }
        cursor.close();
        return  stopList;

    }

    // get next three bus time
    public List<String> getTimeList(String route_id, String direction_id, String stop_code,  int limit){
        String now=getCurrentTime();
        List<String> timeList = new ArrayList<>();
        Cursor cursor = database.rawQuery("select arrival_time " +
                "from stop_times " +
                "where route_id = ? " +
                "and direction_id= ? " +
                "and stop_code= ? " +
                "and arrival_time > ? " +
                "order by arrival_time",
                new String[]{route_id, direction_id, stop_code, now});
        cursor.moveToFirst();
        int l=0;
        while(!cursor.isAfterLast() && l<limit ){
            timeList.add(cursor.getString(0));
            cursor.moveToNext();
            l++;
        }
        cursor.close();
        return timeList;
    }
    // return current time "hh:mm:ss"
    public String getCurrentTime(){
        Date date = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("hh:mm:ss");
        return sdf.format(date);

    }


}
