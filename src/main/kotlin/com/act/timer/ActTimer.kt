package com.act.timer

import org.bukkit.plugin.java.JavaPlugin

class ActTimer: JavaPlugin() {
    override fun onEnable() {
        val timer = getCommand("timer")!!
        val cmd = TimerCommand()

        timer.setExecutor(cmd)
        timer.tabCompleter = cmd
    }

    companion object {
        val instance
            get() = getPlugin(ActTimer::class.java)
    }
}