package com.vkr.ircbot.io

trait Command {
	
	def getPrefix() : String = null
	
	def getValue(command: String) : Array[String] = null
	
}