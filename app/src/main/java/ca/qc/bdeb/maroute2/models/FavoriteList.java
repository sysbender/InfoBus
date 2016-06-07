package ca.qc.bdeb.maroute2.models;

import android.os.Environment;
import android.widget.ArrayAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by jason on 6/6/2016.
 */
public class FavoriteList extends ArrayList<Favorite> {


    public void save() throws IOException {
        final String FILENAME = "/sdcard/favorite_list.dat";

        String state = Environment.getExternalStorageState();

        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            //SDcard is available
            throw (new IOException("No SD card! "));

        }

        FileOutputStream fos = new FileOutputStream(FILENAME);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        // write size
        oos.writeInt(this.size());
        Iterator iterator = this.iterator();
        while (iterator.hasNext()) {
            oos.writeObject(iterator.next());
        }
        oos.close();
    }

    public void load() throws IOException, ClassNotFoundException {
        final String FILENAME = "/sdcard/favorite_list.dat";

        String state = Environment.getExternalStorageState();

        if (! Environment.MEDIA_MOUNTED.equals(state)) {
            //SDcard is available
           // File f = new File(FILENAME);
           // if (!f.exists()) {
                //File does not exists
           //     f.createNewFile();
           // }
            throw (new IOException("no SDcard"));

        }
        FileInputStream fis = new FileInputStream(FILENAME);
        ObjectInputStream ois = new ObjectInputStream(fis);
        // read size
        int count = ois.readInt();

        while (count > 0) {
            add((Favorite) ois.readObject());
            count--;
        }
        ois.close();

    }
}


