package io.github.faiz36.killchance.handler

import io.github.faiz36.killchance.Main
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

class MainHandler(player: Player, private val data:MainData, main:Main) {

    init {
        val fr = InvFX.frame(3, Component.text("킬 확률 GUI").decoration(TextDecoration.BOLD,true)) {
            // 기초 변수 설정
            val killItem = data.getItem()
            val chance = data.getChance()

            // 기초 아이템 설정
            val green = ItemStack(Material.GREEN_CONCRETE)
            val greenmeta = green.itemMeta
            greenmeta.displayName(Component.text("활성화됨").decorate(TextDecoration.BOLD).decoration(TextDecoration.ITALIC,false).color(TextColor.color(0,255,0)))
            green.itemMeta = greenmeta

            val red = ItemStack(Material.RED_CONCRETE)
            val redmeta = red.itemMeta
            redmeta.displayName(Component.text("비활성화됨").decoration(TextDecoration.BOLD,true).decoration(TextDecoration.ITALIC,false).color(TextColor.color(255,0,0)))
            red.itemMeta = redmeta

            val sign = ItemStack(Material.OAK_SIGN)
            val signmeta = sign.itemMeta
            signmeta.displayName(Component.text("확률 변경").decorate(TextDecoration.BOLD).decoration(TextDecoration.ITALIC,false))
            val lore:MutableList<Component> = ArrayList()
            lore.add(Component.text("$chance%").color(TextColor.color(255,255,0)).decorate(TextDecoration.BOLD).decoration(TextDecoration.ITALIC,false).append(Component.text("의 확률로 아이템이 지급됩니다.").color(TextColor.color(255,255,255)).decoration(TextDecoration.BOLD,false)))
            signmeta.lore(lore)
            sign.itemMeta = signmeta

            // 기초 프레임 설정
            initframe()

            onClickBottom { e ->
                if (e.currentItem == null) return@onClickBottom
                data.setItem(e.currentItem!!)
                item(1,1,e.currentItem!!)
            }

            slot(1,1){
                if(killItem != null){
                    item = killItem
                }
                onClick { _ ->
                    item = null
                    data.setItem(null)
                }
            }

            slot(4,1){
                item = if(data.getToggle()){
                    green
                }else{
                    red
                }
                onClick { e ->
                    data.setToggle(!data.getToggle())
                    item = if(data.getToggle()){
                        green
                    }else{
                        red
                    }
                }
            }

            slot(7,1){
                item = sign
                onClick { e ->
                    e.whoClicked.sendMessage("채팅에 0~100 사이에 숫자를 넣어주세요! (다른걸 넣을시 취소됩니다.)")
                    e.whoClicked.closeInventory()
                    main.list.add(e.whoClicked.uniqueId.toString())
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


