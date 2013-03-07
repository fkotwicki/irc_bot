package com.vkr.ircbot.network.irc

import java.net.Socket
import java.io.BufferedReader
import java.io.InputStreamReader
import com.vkr.ircbot.io.Reader
import com.vkr.ircbot.io.SocketWriter
import com.vkr.ircbot.common.UserCommandManager
import com.vkr.ircbot.common.ServerCommandManager

class IrcReader(client: IrcClient) extends Runnable with Reader {

  var reader = new BufferedReader(new InputStreamReader(client.getSocket().getInputStream()))
  var writer = new SocketWriter(client.getSocket())
  var userCommandWriter = new CommandWriter(writer, new UserCommandManager())
  var serverCommandWriter = new CommandWriter(writer, new ServerCommandManager())

  override def run() {
    try {
      while (true) {
        read()
      }
    } catch {
      case e: InterruptedException => {
        reader.close()
        writer.close()
      }
    }
  }

  override def read() {
    var line = reader.readLine()
    if (line != null) {
      println(line)
      userCommandWriter.write(line)
      serverCommandWriter.write(line)
    }
  }

}