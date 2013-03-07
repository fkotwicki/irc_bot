package com.vkr.ircbot.network.irc

import com.vkr.ircbot.io.Writer
import com.vkr.ircbot.io.CommandManager

class CommandWriter(socketWriter: Writer, commandManager: CommandManager) extends Writer {
	
	override def write(message: String) {
	  var command = commandManager.executeCommand(message)
	  if(command != null) {
		  for(c: String <- command) {
			  socketWriter.write(c)
			  Thread.sleep(500) //anti-flood
		  }
	  }
	}
	
	override def close() {
		socketWriter.close()
	}
}