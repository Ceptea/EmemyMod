package xyz.ceptea.ememymod.ememy;

import com.google.gson.annotations.Expose;
import xyz.ceptea.ememymod.Ememymod;

public class Ememy {
    @Expose
    String name;

    public Ememy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static boolean with(String name) {
        return Ememymod.ememies.with(name);
    }
}
