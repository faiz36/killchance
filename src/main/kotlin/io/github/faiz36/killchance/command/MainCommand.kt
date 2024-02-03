package io.github.faiz36.killchance.command

import io.github.faiz36.killchance.data.MainData
import io.github.faiz36.killchance.handler.MainHander
import io.github.monun.kommand.kommand
import org.bukkit.plugin.Plugin

class MainCommand(val plugin:Plugin,val data: MainData) {

    init {
        plugin.apply {
            kommand {
                register("killchance"){
                    requires { isOp && isPlayer }
                    executes {
                        MainHander(player,data)
                    }
                }
            }
        }
    }

}
