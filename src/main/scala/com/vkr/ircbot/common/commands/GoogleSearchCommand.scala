package com.vkr.ircbot.common
import com.vkr.ircbot.io.Command
import com.vkr.ircbot.io.SimpleCommand
import java.net.URL
import java.net.URLEncoder
import java.io.InputStreamReader
import java.io.BufferedReader
import com.vkr.ircbot.io.CommandManager

class GoogleSearchCommand(prefix: String) extends SimpleCommand(prefix) {

  val googleUrl = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q="
  val charset = "UTF-8"

  override def getValue(command: String): Array[String] = {
    val elems = command.split(" ")
    val keyword = CommandManager.join(elems.slice(4, elems.length), ' ')
    val channel = elems(2)
    val url = new URL(googleUrl + URLEncoder.encode(keyword, charset))
    val reader = new InputStreamReader(url.openStream(), charset);
    val results = scala.util.parsing.json.JSON.parseFull(new BufferedReader(reader).readLine())
    
    val contentCommand = "PRIVMSG " + channel + " : " + GoogleResultsParser.getResultsAsString(results, "content").replaceAll("""<(?!\/?a(?=>|\s.*>))\/?.*?>""", "")
    val urlCommand = "PRIVMSG " + channel + " : " + GoogleResultsParser.getResultsAsString(results, "url")
    
    Array(contentCommand, urlCommand)
  }
}

object GoogleResultsParser {
  def getResultsAsString(results: Option[Any], elem: String) : String = results match {
     case Some(m: Map[String, Any]) =>
        m("responseData") match {
          case s: String =>  s
          case l: List[Any] => l.toString()
          case m: Map[Any, Any] => m("results") match {
              case list: List[Map[Any, Any]] => list(0) match {
                case map: Map[Any,Any] => map(elem).toString()
              }
          }
        }
     case None => ""
  } 
}
	
	