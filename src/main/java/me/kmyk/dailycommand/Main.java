package me.kmyk.dailycommand;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        ConfigManager configManager = new ConfigManager(this);
        ScheduleManager scheduleManager = new ScheduleManager(this);
        String timeZone = configManager.getTimeZone();

        getCommand("ustaw-komende").setExecutor(new ScheduleCommandCommand(configManager, scheduleManager));

        List<Map<?, ?>> commands = configManager.getCommands();

        for (Map<?, ?> commandTime : commands) {
            String command = (String) commandTime.get("command");
            String time = (String) commandTime.get("time");
            scheduleManager.scheduleCommand(command, timeZone, TimeUtils.getHours(time), TimeUtils.getMinutes(time), 0);
        }
    }
}
