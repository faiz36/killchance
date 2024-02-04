package io.github.faiz36.killchance.data

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import java.io.File

class MainData(private val plugin:Plugin) {
    private val file get() = File(plugin.dataFolder, "data.yml")
    private val yaml = YamlConfiguration.loadConfiguration(file)

    fun getToggle(): Boolean {
        return yaml.getBoolean("killchance.toggle")
    }

    fun setToggle(boolean: Boolean) {
        yaml.set("killchance.toggle", boolean)
    }

    fun getItem(): ItemStack? {
        return yaml.getItemStack("killchance.item")
    }

    fun setItem(item: ItemStack?) {
        yaml.set("killchance.item", item)
    }

    fun getChance(): Int {
        return yaml.getInt("killchance.chance")
    }

    fun setChance(int: Int) {
        yaml.set("killchance.chance", int)
    }

    fun save() {
        yaml.save(file)
    }

}
