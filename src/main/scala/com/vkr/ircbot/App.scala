package com.vkr.ircbot


/**
 * @author ${user.name}
 */
object App {
  
  def main(args : Array[String]) {
	  val c = new Client("irc.oftc.net", 6667)
	  c.start()
  }

}
