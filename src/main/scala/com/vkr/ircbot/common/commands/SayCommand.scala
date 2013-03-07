package com.vkr.ircbot.common.commands
import com.vkr.ircbot.io.SimpleCommand
import com.vkr.ircbot.io.CommandManager

class SayCommand(prefix: String) extends SimpleCommand(prefix) {
  
  override def getValue(command: String) : Array[String] = {
    val elems = command.split(" ")
    val channel = elems(2)
    val msg = CommandManager.join(elems.slice(4, elems.length), ' ')
    val c: String = "PRIVMSG " + channel + " : " + msg
    Array(c)
  }

}