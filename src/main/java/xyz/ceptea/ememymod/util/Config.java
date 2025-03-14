package xyz.ceptea.ememymod.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import xyz.ceptea.ememymod.Ememymod;
import xyz.ceptea.ememymod.ememy.Ememy;
import xyz.ceptea.ememymod.ememy.EmemyManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Config {
    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public static File getFile() {
        File file = new File("enemies.json");
        try {
            if (file.exists()) {
                return file;
            }
            if (file.createNewFile()) {
                return file;
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return null;
    }

    public static void save() {
        File file = getFile();
        Ememymod.LOGGER.info("Config is saving");
        try {
            FileWriter writer = new FileWriter(file);
            gson.toJson(Ememymod.ememies, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static EmemyManager load() {
        File file = getFile();
        Ememymod.LOGGER.info("Config is loading");
        try {
            Scanner scanner = new Scanner(file);
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNext()) {
                var l = scanner.nextLine();
                builder.append(l);
            }
            return gson.fromJson(builder.toString(), EmemyManager.class);
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }
}
