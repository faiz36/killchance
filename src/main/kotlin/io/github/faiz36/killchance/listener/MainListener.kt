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

class MainListener(main:Main,data:MainData):Listener {

    private val main1: Main
    private val data1: MainData

    init {
        Bukkit.getPluginManager().registerEvents(this,main)
        main1 = main
        data1 = data
    }

    @EventHandler
    fun onChat(e: PlayerChatEvent){
        if(main1.instance.list.contains(e.player.uniqueId.toString())){
            if(e.message.toIntOrNull() != null){
                val chance = e.message.toInt()
                if(chance in 0..100){
                    e.isCancelled = true
                    data1.instance.setChance(chance)
                    main1.instance.list.remove(e.player.uniqueId.toString())
                    MainHandler(e.player,data1,main1)
                }else{
                    e.isCancelled = true
                    main1.instance.list.remove(e.player.uniqueId.toString())
                    MainHandler(e.player,data1,main1)
                }
            }else{
                e.isCancelled = true
                main1.instance.list.remove(e.player.uniqueId.toString())
                MainHandler(e.player,data1,main1)
            }
        }
    }

    @EventHandler
    fun onKill(e:PlayerDeathEvent){
        if(!data1.getToggle()) return
        if(e.player.killer !is Player) return
        val rand:Int = (Math.random()*100).toInt()
        if(rand in 0..data1.instance.getChance()){
            if(data1.instance.getItem() == null) return
            e.player.killer!!.inventory.addItem(data1.instance.getItem()!!)
        }
    }

}