package xyz.ceptea.ememymod.ememy;

import com.google.gson.annotations.Expose;
import xyz.ceptea.ememymod.util.Config;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class EmemyManager {
    @Expose
    ArrayList<Ememy> ememies = new ArrayList<>();

    public ArrayList<Ememy> getEmemies() {
        return ememies;
    }

    public void add(String name) {
        ememies.add(new Ememy(name));
        Config.save();

    }

    public void remove(String name) {
        var copy = new CopyOnWriteArrayList<>(ememies);
        for (Ememy ememy: copy) {
            if (ememy.getName().equalsIgnoreCase(name)) {
                copy.remove(ememy);
            }
        }
        ememies = new ArrayList<>(copy);
        Config.save();
    }

    public boolean with(String name) {
        for (Ememy ememy: ememies) {
            if (ememy.name.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
