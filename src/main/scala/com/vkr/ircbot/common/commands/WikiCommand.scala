package com.vkr.ircbot.common.commands
import com.vkr.ircbot.io.SimpleCommand
import com.vkr.ircbot.io.CommandManager
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import java.io.InputStreamReader
import java.net.URLEncoder

class WikiCommand(prefix: String) extends SimpleCommand(prefix) {

  override def getValue(command: String): Array[String] = {
    val elems = command.split(" ")
    val keyword = CommandManager.join(elems.slice(4, elems.length), ' ')
    val channel = elems(2)
    val method = new HttpPost("http://pl.wikipedia.org/w/api.php?action=opensearch&format=xml&limit=1&search=" + URLEncoder.encode(keyword, "UTF-8"))
    val client = new DefaultHttpClient()
    var msg = "";
    try {
      val resp = client.execute(method)
      val reader = new InputStreamReader(resp.getEntity().getContent())
      val results = scala.xml.XML.load(reader)
      msg = (results \ "Section" \ "Item" \ "Description").text
      if (msg.isEmpty())
        msg = "Nie wiem"
    } catch {
      case e: Exception => {
        msg = "Nie wiem"
      }
    }
    Array("PRIVMSG " + channel + " :" + msg)
  }
}