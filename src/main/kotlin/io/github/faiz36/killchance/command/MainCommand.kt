package io.github.faiz36.killchance.command

import io.github.faiz36.killchance.Main
import io.github.faiz36.killchance.data.MainData
import io.github.faiz36.killchance.handler.MainHandler
import io.github.monun.kommand.kommand

class MainCommand( private val main:Main,private val data: MainData) {

    init {
        main.apply {
            kommand {
                register("killchance"){
                    requires { isOp && isPlayer }
                    executes {
                        MainHandler(player,data,main)
                    }
                }
            }
        }
    }

}
