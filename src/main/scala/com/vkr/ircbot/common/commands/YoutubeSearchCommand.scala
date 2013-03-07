package com.vkr.ircbot.common.commands
import com.vkr.ircbot.io.SimpleCommand
import java.net.URL
import java.io.InputStreamReader
import java.net.URLEncoder
import java.io.BufferedReader
import scala.xml.Elem
import scala.collection.mutable.ArrayBuffer
import com.vkr.ircbot.io.CommandManager

class YoutubeSearchCommand(prefix: String) extends SimpleCommand(prefix) {

  val ytUrl = "http://gdata.youtube.com/feeds/videos?vq="
  val ytOptions = "&max-results=3&orderby=relevance&sortorder=descending"
  val charset = "UTF-8"

  override def getValue(command: String): Array[String] = {
    val elems = command.split(" ")
    val keyword = CommandManager.join(elems.slice(4, elems.length), ' ')
    val channel = elems(2)
    val url = new URL(ytUrl + URLEncoder.encode(keyword, charset) + ytOptions)
    val reader = new InputStreamReader(url.openStream(), charset);
    val results = scala.xml.XML.load(reader)
    
    val c = new ArrayBuffer[String]
    for {
      entry <- results \ "entry"
    } for {
      title <- entry \ "title"
      link <- entry \ "link"  if ((link \ "@rel") text).equals("alternate")
    } yield {
     c  += "PRIVMSG " + channel + " : " + title.text + " - " + (link \ "@href").text
    }
    val a = new Array[String](3)
    c.copyToArray(a)
    a
  }

  object YoutubeResultsParser {
  }
}