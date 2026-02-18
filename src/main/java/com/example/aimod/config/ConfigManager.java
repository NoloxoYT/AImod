package com.example.aimod.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "aimod/config.json");

    public static JsonObject loadConfig() {
        if (!CONFIG_FILE.exists()) {
            CONFIG_FILE.getParentFile().mkdirs();
            JsonObject defaultConfig = new JsonObject();
            defaultConfig.addProperty("api_key", "your_api_key_here");
            saveConfig(defaultConfig);
        }

        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            return JsonParser.parseReader(reader).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public static void saveConfig(JsonObject config) {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(config, writer);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save config file", e);
        }
    }
}