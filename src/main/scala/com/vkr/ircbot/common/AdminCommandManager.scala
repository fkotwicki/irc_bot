package com.vkr.ircbot.common
import com.vkr.ircbot.io.CommandManager
import com.vkr.ircbot.io.CommandParser
import com.vkr.ircbot.io.SimpleCommand

class AdminCommandManager extends CommandManager(AdminCommandParser) {

  override def register() {
    commands += new SimpleCommand("join")
  }

}