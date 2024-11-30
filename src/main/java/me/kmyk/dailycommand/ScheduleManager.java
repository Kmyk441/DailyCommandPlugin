package me.kmyk.dailycommand;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleManager {

    private final ScheduledExecutorService scheduler;
    private final Main plugin;

    public ScheduleManager(Main plugin) {
        this.plugin = plugin;
        scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public void scheduleCommand(String command, String timeZone, int hour, int minute, int second) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(timeZone));
        ZonedDateTime nextRun = now.withHour(hour).withMinute(minute).withSecond(second);
        if (nextRun.isBefore(now)) {
            nextRun = nextRun.plusDays(1);
        }

        long initialDelay = Duration.between(now, nextRun).toMillis();

        scheduler.scheduleAtFixedRate(
                () -> Bukkit.getScheduler().runTask(plugin, () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command)),
                initialDelay,
                TimeUnit.DAYS.toMillis(1),
                TimeUnit.MILLISECONDS
        );
    }
}
