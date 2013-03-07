package com.vkr.ircbot.io
import scala.collection.mutable.ArrayBuffer

abstract class CommandManager() {

  var commands: ArrayBuffer[Command] = new ArrayBuffer[Command]()
  var parser: CommandParser = null;
  
  def this(parser: CommandParser) = {
    this()
    this.parser = parser
    register()
  }
  
  def executeCommand(commandString: String): Array[String] = {
    var command = findCommand(commandString)
    if (command != null)
      command.getValue(commandString)
    else
      null
  }

  def findCommand(command: String): Command = {
    val prefix = parser.parse(command)
    if (command != null && !commands.isEmpty) {
      for (command: Command <- commands) {
        if (command.getPrefix().equals(prefix))
        	return command
      }
    }
    return null
  }

  def register()
}

object CommandManager {

  def join(elems: Array[String], separator: Char): String = {
    var sb = StringBuilder.newBuilder
    for (elem: String <- elems) {
      sb.append(elem).append(separator)
    }
    sb.toString()
  }
}