package me.kmyk.dailycommand;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigManager {

    private final Main plugin;

    public ConfigManager(Main plugin) {
        this.plugin = plugin;
    }

    private FileConfiguration getConfig() {
        return plugin.getConfig();
    }

    private void reload() {
        plugin.reloadConfig();
    }

    private void save() {
        plugin.saveConfig();
    }

    public String getTimeZone() {
        reload();
        return getConfig().getString("time-zone");
    }

    public void setTimeZone(String timeZone) {
        reload();
        getConfig().set("time-zone", timeZone);
        save();
    }

    public List<Map<?, ?>> getCommands() {
        reload();
        return getConfig().getMapList("commands");
    }

    public void addCommand(String command, String time) {
        reload();
        List<Map<?, ?>> mapList = getConfig().getMapList("commands");
        Map<String, String> map = new HashMap<>();
        map.put("command", command);
        map.put("time", time);
        mapList.add(map);
        getConfig().set("commands", mapList);
        save();
    }

}
