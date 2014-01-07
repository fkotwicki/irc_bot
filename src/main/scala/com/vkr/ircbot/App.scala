package com.vkr.ircbot


/**
 * @author ${user.name}
 */
object App {
  
  def main(args : Array[String]) {
	  val c = new Client("arch.edu.pl", 6667)
	  c.start()
  }

}
