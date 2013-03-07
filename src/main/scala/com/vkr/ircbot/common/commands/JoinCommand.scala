package com.vkr.ircbot.common.commands
import com.vkr.ircbot.io.SimpleCommand

class JoinCommand(prefix: String) extends SimpleCommand(prefix) {
	
  override def getValue(command: String) : Array[String] = {
    val elems = command.split(" ")
    val channel = elems(1)
    Array("JOIN " + channel)
  }
}