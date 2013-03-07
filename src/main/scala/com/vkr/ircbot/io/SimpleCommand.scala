package com.vkr.ircbot.io

class SimpleCommand(prefix: String) extends Command {
	
	override def getPrefix() : String = {
	  prefix
	}
	
	override def getValue(command: String) : Array[String] = {
	  Array(command)
	}
	
}