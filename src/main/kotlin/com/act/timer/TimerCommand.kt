package com.act.timer

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

class TimerCommand: TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (!sender.hasPermission("act.admin")) return true

        if (args.isEmpty()) {
            sender.sendUsage()
            return true
        }

        val state = TimerManager.state

        if (args[0] == "시작") {
            if (state != TimerState.STOPPED) {
                sender.sendMessage("<red>이미 타이머가 작동하고 있습니다".mini)
            } else {
                TimerManager.start()
                sender.sendMessage("<green>타이머가 시작되었습니다".mini)
            }
        } else if (args[0] == "재시작") {
            if (state != TimerState.PAUSED) {
                sender.sendMessage("<red>타이머가 중지되어있지 않습니다.".mini)
            } else {
                TimerManager.restart()
                sender.sendMessage("<green>타이머가 재시작되었습니다.".mini)
            }
        }
        else if (args[0] == "중지") {
            if (state == TimerState.PAUSED) {
                sender.sendMessage("<red>이미 타이머가 중지되었습니다.".mini)
                sender.sendMessage("<green>/타이머 켜기 <gray>을 사용하여 다시 시작하세요".mini)
            } else if (TimerManager.state == TimerState.STOPPED) {
                sender.sendMessage("<red>타이머가 켜져있지 않습니다".mini)
            } else {
                TimerManager.pause()
                sender.sendMessage("<green>타이머가 중단되었습니다".mini)
            }
        } else if (args[0] == "끄기") {
            if (state == TimerState.STOPPED) {
                sender.sendMessage("<red>타이머가 켜져있지 않습니다".mini)
            } else {
                TimerManager.stop()
                sender.sendMessage("<green>타이머가 꺼졌습니다".mini)
            }
        } else {
            sender.sendUsage()
        }

        return true
    }

    private fun CommandSender.sendUsage() {
        this.sendMessage("<red>사용법: <gray>/타이머 [시작/중지/끄기]".mini)
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>
    ): List<String> {
        if (args.size == 1) {
            return listOf("시작", "중단", "끄기", "재시작").filter { it.startsWith(args[0]) }
        }

        return emptyList()
    }

    private val String.mini: Component
        get() = MiniMessage.miniMessage().deserialize(this)
}