package com.vkr.ircbot.common
import com.vkr.ircbot.io.CommandParser

object  UserCommandParser extends CommandParser {
	
	override def parse(command: String) : String = {
	  var prefix  = ""
	  var elems = command.split("\\s")
	  if(elems.length >= 4) {
	    prefix += elems(3).substring(1)
	  } 
	  prefix.toLowerCase()
	}
}