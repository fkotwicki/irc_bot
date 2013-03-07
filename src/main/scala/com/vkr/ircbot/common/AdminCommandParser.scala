package com.vkr.ircbot.common
import com.vkr.ircbot.io.CommandParser

object AdminCommandParser extends CommandParser {
	
	override def parse(command: String) : String = {
	  var prefix  = ""
	  var elems = command.split("\\s")
	  if(elems.length >= 2) {
	    prefix = elems(0)
	  } 
	  prefix.toLowerCase()
	}
}