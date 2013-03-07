package com.vkr.ircbot

import java.net.Socket
import com.vkr.ircbot.network.irc.IrcReader
import com.vkr.ircbot.network.irc.IrcWriter
import com.vkr.ircbot.io.SocketWriter
import com.vkr.ircbot.network.irc.IrcClient

class Client(server: String, port: Int) extends IrcClient {

  var socket: Socket = null
  var reader: IrcReader = null
  var writer: IrcWriter = null

  override def start() {
    connect()
    init()
    register()
  }

  override def disconnect() {
    if (!socket.isClosed())
      socket.close()
  }
  
  override def getSocket(): Socket = {
    socket
  }

  private def connect() {
    socket = new Socket(server, port)
  }

  private def init() {
    reader = new IrcReader(this)
    writer = new IrcWriter(this)

    var t = new Thread(reader)
    var t2 = new Thread(writer)

    t2.start()
    t.start()

  }
  
  private def register() {
      var writer = new SocketWriter(socket)
      writer.write("USER vkrbot vkrbot bla :vkrbot")
	  writer.write("NICK misiek")
	  writer.write("JOIN #twojastara")
  }
}