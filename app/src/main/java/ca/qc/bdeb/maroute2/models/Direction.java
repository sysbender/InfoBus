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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction = (Direction) o;
        return id.equals(direction.getId()) ;

       // if (!id.equals(direction.id)) return false;
       // return headsign.equals(direction.headsign);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + headsign.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return headsign;
    }
}
