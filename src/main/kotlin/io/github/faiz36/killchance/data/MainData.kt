package io.github.faiz36.killchance.data

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File

class MainData(val plugin:Plugin) {

    var instance:MainData = this

    private val file get() = File(plugin.dataFolder, "data.yml")
    private val yaml = YamlConfiguration.loadConfiguration(file)

    fun getToggle():Boolean{
        return yaml.getBoolean("killchance.toggle")
    }

    fun setToggle(boolean: Boolean){
        yaml.set("killchance.toogle",boolean)
    }

    fun save(){
        yaml.save(file)
    }

}
