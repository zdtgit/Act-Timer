package com.act.timer

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable

class TimerTask: BukkitRunnable() {
    override fun run() {
        if (TimerManager.state == TimerState.STARTED) {
            TimerManager.seconds++
        }

        for (player in Bukkit.getOnlinePlayers()) {
            val seconds = TimerManager.seconds

            val m = seconds / 60
            val s = seconds % 60

            player.sendActionBar("<green>${m}분 ${s}초".mini)
        }
    }

    private val String.mini: Component
        get() = MiniMessage.miniMessage().deserialize(this)
}