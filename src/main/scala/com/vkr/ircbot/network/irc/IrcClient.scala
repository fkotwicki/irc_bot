package com.vkr.ircbot.network.irc
import java.net.Socket

trait IrcClient {
	def start()
	
	def disconnect()
	
	def getSocket() : Socket = null
}