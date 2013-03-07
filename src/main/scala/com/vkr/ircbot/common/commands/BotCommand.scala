package com.vkr.ircbot.common.commands
import java.io.InputStreamReader
import java.net.URLEncoder
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import com.vkr.ircbot.io.CommandManager
import com.vkr.ircbot.io.SimpleCommand

class BotCommand(prefix: String) extends SimpleCommand(prefix) {

  override def getValue(command: String): Array[String] = {
    val elems = command.split(" ")
    val keyword = CommandManager.join(elems.slice(4, elems.length), ' ')
    val channel = elems(2)
    val method = new HttpPost("http://www.pandorabots.com/pandora/talk-xml?botid=a11e306b3e345f72&input=" + URLEncoder.encode(keyword, "UTF-8"))
    val client = new DefaultHttpClient()
    var msg = "";
    try {
      val resp = client.execute(method)
      val reader = new InputStreamReader(resp.getEntity().getContent())
      val results = scala.xml.XML.load(reader)
      msg = (results \ "that").text
    } catch {
      case e: Exception => {
        msg = "fuck off."
      }
    }
    Array("PRIVMSG " + channel + " :" + msg)
  }
}