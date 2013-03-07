package com.vkr.ircbot.io

trait Writer {
	def write(message: String)
	
	def close()
}