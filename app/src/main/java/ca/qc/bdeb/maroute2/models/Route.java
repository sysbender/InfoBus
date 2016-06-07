package ca.qc.bdeb.maroute2.models;

import java.io.Serializable;

/**
 * Created by jason on 6/5/2016.
 */
public class Route implements Serializable {
    private String id;
    private String name;

    public Route(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;
        return id.equals(route.getId()) ;


       // if (!id.equals(route.id)) return false;
       // return name.equals(route.name);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return id + '-'+ name ;
    }
}

