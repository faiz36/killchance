@file:Suppress("DEPRECATION")

package io.github.faiz36.killchance.listener

import io.github.faiz36.killchance.Main
import io.github.faiz36.killchance.data.MainData
import io.github.faiz36.killchance.handler.MainHandler
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerChatEvent

class MainListener(
    private val main:Main,
    private val data:MainData
):Listener {
    init {
        Bukkit.getPluginManager().registerEvents(this, main)
    }

    @EventHandler
    fun onChat(e: PlayerChatEvent) {
        if (main.list.contains(e.player.uniqueId.toString())) {
            if (e.message.toIntOrNull() != null) {
                val chance = e.message.toInt()
                if (chance in 0..100) {
                    e.isCancelled = true
                    data.setChance(chance)
                    main.list.remove(e.player.uniqueId.toString())
                    MainHandler(e.player, data, main)
                } else {
                    e.isCancelled = true
                    main.list.remove(e.player.uniqueId.toString())
                    MainHandler(e.player, data, main)
                }
            } else {
                e.isCancelled = true
                main.list.remove(e.player.uniqueId.toString())
                MainHandler(e.player, data, main)
            }
        }
    }

    @EventHandler
    fun onKill(e: PlayerDeathEvent) {
        if (!data.getToggle()) return
        if (e.player.killer !is Player) return
        val rand: Int = (Math.random() * 100).toInt()
        if (rand in 0..data.getChance()) {
            if (data.getItem() == null) return
            e.player.killer!!.inventory.addItem(data.getItem()!!)
        }
    }

}