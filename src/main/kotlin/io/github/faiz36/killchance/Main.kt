package io.github.faiz36.killchance

import io.github.faiz36.killchance.command.MainCommand
import io.github.faiz36.killchance.data.MainData
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin(){

    private val maindata by lazy { MainData(this) }

    override fun onEnable() {
        MainCommand(this,maindata)
    }

    override fun onDisable() {
        maindata.save()
    }
}