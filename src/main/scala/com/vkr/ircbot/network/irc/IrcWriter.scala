package com.vkr.ircbot.network.irc
import java.net.Socket
import java.io.PrintWriter
import java.io.OutputStreamWriter
import java.util.Scanner
import com.vkr.ircbot.io.SocketWriter
import java.lang.InterruptedException
import com.vkr.ircbot.common.AdminCommandManager

class IrcWriter(client: IrcClient) extends Runnable {

  var writer = new SocketWriter(client.getSocket())
  var input = new Scanner(System.in)
  var commandWriter = new CommandWriter(writer, new AdminCommandManager())

  override def run() {
    try {
      var line = ""
      while (true) {
        line = input.nextLine();
        commandWriter.write(line)
      }
    } catch {
      case e: InterruptedException => {
        writer.close()
        input.close()
      }
    }
  }

}