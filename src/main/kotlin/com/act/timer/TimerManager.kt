package com.act.timer

import org.bukkit.scheduler.BukkitTask

object TimerManager {
    var state = TimerState.STOPPED
    var seconds = 0
    var task: BukkitTask? = null

    fun start() {
        if (state == TimerState.STOPPED) {
            state = TimerState.STARTED
            seconds = 0
            task = TimerTask().runTaskTimer(ActTimer.instance, 0L, 20L)
        }
    }

    fun restart() {
        if (state == TimerState.PAUSED) {
            state = TimerState.STARTED
        }
    }

    fun pause() {
        if (state == TimerState.STARTED) {
            state = TimerState.PAUSED
        }
    }

    fun stop() {
        if (state != TimerState.STOPPED) {
            state = TimerState.STOPPED
            seconds = 0
            task?.cancel()
            task = null
        }
    }
}