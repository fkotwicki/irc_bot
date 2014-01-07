package com.vkr.ircbot.common

import com.vkr.ircbot.io.CommandManager
import com.vkr.ircbot.io.CommandParser
import com.vkr.ircbot.io.SimpleCommand
import com.vkr.ircbot.common.commands.PongCommand
import com.vkr.ircbot.common.commands.CtcpCommand

class ServerCommandManager extends CommandManager(ServerCommandParser) {

  override def register() {
	  commands += new PongCommand("ping")
    commands += new CtcpCommand("CTCP")
  }

}