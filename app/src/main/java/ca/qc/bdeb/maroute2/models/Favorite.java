package ca.qc.bdeb.maroute2.models;

import java.io.Serializable;

/**
 * Created by jason on 6/5/2016.
 */
public class Favorite implements Serializable{
    private Route route;
    private Direction direction;
    private Stop stop;

    public Favorite(Direction direction, Route route, Stop stop) {
        this.direction = direction;
        this.route = route;
        this.stop = stop;
    }

    @Override
    public String toString() {
        return  direction.getHeadsign()
                +" "
                +route.getName()
                +"\n"
                +stop.getName();
    }
}
