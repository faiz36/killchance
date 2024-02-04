package io.github.faiz36.killchance

import io.github.faiz36.killchance.command.MainCommand
import io.github.faiz36.killchance.data.MainData
import io.github.faiz36.killchance.listener.MainListener
import org.bukkit.plugin.java.JavaPlugin

class Main: JavaPlugin(){

    var instance:Main = this

    val list:MutableList<String> = ArrayList()

    private val maindata by lazy { MainData(this) }

    override fun onEnable() {
        MainCommand(this,maindata)
        MainListener(this,maindata)
    }

    override fun onDisable() {
        maindata.save()
    }
}