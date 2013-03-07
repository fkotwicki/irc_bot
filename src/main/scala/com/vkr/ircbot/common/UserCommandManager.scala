package com.vkr.ircbot.common
import com.vkr.ircbot.io.CommandManager
import com.vkr.ircbot.io.CommandParser
import com.vkr.ircbot.io.SimpleCommand
import com.vkr.ircbot.common.commands.SayCommand
import com.vkr.ircbot.common.commands.YoutubeSearchCommand
import com.vkr.ircbot.common.commands.BotCommand
import com.vkr.ircbot.common.commands.WikiCommand
import com.vkr.ircbot.common.commands.GoogleSearchCommand

class UserCommandManager extends CommandManager(UserCommandParser) {
	
  override def register() {
    commands += new SayCommand("!say")
    commands += new GoogleSearchCommand("!google")
    commands += new YoutubeSearchCommand("!yt")
    commands += new BotCommand("krystynka:")
    commands += new WikiCommand("!ask")
  }
  
}