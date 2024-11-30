package me.kmyk.dailycommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ScheduleCommandCommand implements CommandExecutor {

    private final ConfigManager configManager;
    private final ScheduleManager scheduleManager;

    public ScheduleCommandCommand(ConfigManager configManager, ScheduleManager scheduleManager) {
        this.configManager = configManager;
        this.scheduleManager = scheduleManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {

        String usage = ChatColor.RED + "Niepoprawne użycie komendy, użyj /ustaw-komende [godzina np. 12:30] [komenda bez znaku \"/\"]";

        if (args.length < 2) {
            sender.sendMessage(usage);
            return true;
        }

        String time = args[0];

        if (!TimeUtils.isValidTime(time)) {
            sender.sendMessage(usage);
            return true;
        }

        StringBuilder stringBuilder = new StringBuilder(args[1]);
        for (int i = 2; i < args.length; i++) {
            stringBuilder.append(" ").append(args[i]);
        }
        String commandToSchedule = stringBuilder.toString();

        configManager.addCommand(commandToSchedule, time);
        scheduleManager.scheduleCommand(commandToSchedule, configManager.getTimeZone(), TimeUtils.getHours(time), TimeUtils.getMinutes(time), 0);
        sender.sendMessage(ChatColor.GREEN + "Ustawiłeś komendę \"" + commandToSchedule + "\" na godzinę " + time);
        return true;
    }
}
