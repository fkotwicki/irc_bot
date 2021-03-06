package com.vkr.ircbot.common.commands
import com.vkr.ircbot.io.SimpleCommand

class PongCommand(prefix: String) extends SimpleCommand(prefix) {
  
  override def getValue(command: String) : Array[String] = {
    Array(command.replace("PING","PONG"))
  }

}