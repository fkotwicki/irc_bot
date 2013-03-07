package com.vkr.ircbot.io

trait CommandParser {
	def parse(command: String) : String = null
}