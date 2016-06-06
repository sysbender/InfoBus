package ca.qc.bdeb.maroute2.models;

import java.io.Serializable;

/**
 * Created by jason on 6/5/2016.
 */
public class Direction implements Serializable {
    private String id;
    private String headsign;

    public Direction(String id, String headsign) {
        this.headsign = headsign;
        this.id = id;
    }

    public String getHeadsign() {
        return headsign;
    }

    public void setHeadsign(String headsign) {
        this.headsign = headsign;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return headsign;
    }
}
