package io.github.faiz36.killchance.handler

import io.github.faiz36.killchance.data.MainData
import io.github.monun.invfx.InvFX
import io.github.monun.invfx.frame.InvFrame
import io.github.monun.invfx.openFrame
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class MainHander(player: Player, private val data:MainData) {

    init {
        val fr = InvFX.frame(3, Component.text("킬 확률 GUI").decoration(TextDecoration.BOLD,true)) {
            val green = ItemStack(Material.GREEN_CONCRETE)
            val greenmeta = green.itemMeta
            greenmeta.displayName(Component.text("활성화됨").decorate(TextDecoration.BOLD).decoration(TextDecoration.ITALIC,false).color(TextColor.color(0,255,0)))
            green.itemMeta = greenmeta

            val red = ItemStack(Material.RED_CONCRETE)
            val redmeta = red.itemMeta
            redmeta.displayName(Component.text("비활성화됨").decoration(TextDecoration.BOLD,true).decoration(TextDecoration.ITALIC,false).color(TextColor.color(255,0,0)))
            red.itemMeta = redmeta
            initframe()
            slot(4,1){
                item = if(data.instance.getToggle()){
                    green
                }else{
                    red
                }
                onClick { e ->
                    if(data.instance.getToggle()){
                        data.instance.setToggle(!data.instance.getToggle())
                        item = if(data.instance.getToggle()){
                            green
                        }else{
                            red
                        }
                    }
                }
            }
        }
        player.openFrame(fr)
    }

    private fun InvFrame.initframe() {
        val item1 = ItemStack(Material.BLACK_STAINED_GLASS_PANE)
        val item1meta = item1.itemMeta
        item1meta.displayName(Component.text(""))
        item1.itemMeta = item1meta
        for (i: Int in 0..8) {
            slot(i, 0) {
                item = item1
                onClick { e ->
                    e.isCancelled = true
                }
            }
            slot(i,2){
                item = item1
                onClick { e ->
                    e.isCancelled = true
                }
            }
        }
        for (i: Int in 0..8) {
            if (i == 1 || i == 4 || i == 7) {
                continue
            }
            slot(i, 1) {
                item = item1
                onClick { e ->
                    e.isCancelled = true
                }
            }
        }
    }

}


