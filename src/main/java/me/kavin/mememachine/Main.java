package me.kavin.mememachine;

import javax.security.auth.login.LoginException;

import me.kavin.mememachine.command.CommandManager;
import me.kavin.mememachine.listener.DiscordListener;
import me.kavin.mememachine.utils.Timer;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Main extends ListenerAdapter{
	public static JDA api;
	public static Timer uptime = new Timer();

	public static void main(String[] args) {
		try {
			JDABuilder builder = new JDABuilder(AccountType.BOT).setToken(System.getenv().get("BOT_TOKEN"));
			api = builder.buildAsync();
			api.setAutoReconnect(true);
			DiscordListener.init();
			uptime.reset();
			new CommandManager();
		} catch (LoginException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}