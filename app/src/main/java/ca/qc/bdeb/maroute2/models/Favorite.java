package ca.qc.bdeb.maroute2.models;

import java.io.Serializable;

/**
 * Created by jason on 6/5/2016.
 */
public class Favorite implements Serializable{
    private Route route;
    private Direction direction;
    private Stop stop;

    public Favorite(Route route,Direction direction,  Stop stop) {
        this.direction = direction;
        this.route = route;
        this.stop = stop;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Favorite favorite = (Favorite) o;

        return route.equals(favorite.getRoute()) && direction.equals(favorite.getDirection()) && stop.equals(favorite.getStop());

      //  if (!route.equals(favorite.route)) return false;
      //  if (!direction.equals(favorite.direction)) return false;
      //  return stop.equals(favorite.stop);

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
