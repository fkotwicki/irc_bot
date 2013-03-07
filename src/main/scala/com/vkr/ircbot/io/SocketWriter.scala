package com.vkr.ircbot.io
import java.io.PrintWriter
import java.net.Socket
import java.io.OutputStreamWriter

class SocketWriter(socket: Socket) extends Writer {
	
	val writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)
	
	override def write(message: String) {
	  if(message != null) {
	    writer.println(message)
	  }
	}
	
	def close() {
	  writer.close()
	}
}