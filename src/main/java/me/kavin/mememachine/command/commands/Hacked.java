package me.kavin.mememachine.command.commands;

import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

import me.kavin.mememachine.command.Command;
import me.kavin.mememachine.utils.ColorUtils;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Hacked extends Command {

	public Hacked() {
		super(">hacked", "`Searches haveibeenpwned.com to shows whether the given email has been hacked`");
	}

	@Override
	public void onCommand(String message, MessageReceivedEvent event) {
		try {
			if (message.toLowerCase().startsWith(getPrefix())) {
				String[] split = message.split(" ");
				if (split.length != 1) {
					EmbedBuilder meb = new EmbedBuilder();

					meb.setTitle("Have i been pwned");
					meb.setColor(ColorUtils.getRainbowColor(2000));

					HttpResponse<String> response = Unirest.get(
							"https://haveibeenpwned.com/api/v2/unifiedsearch/" + URLEncoder.encode(split[1], "UTF-8"))
							.asString();

					if (response.getStatus() == 200) {
						JSONArray breaches = new JSONObject(response.getBody()).getJSONArray("Breaches");
						breaches.forEach(breach -> {
							JSONObject jBreach = new JSONObject(breach.toString());
							meb.addField(jBreach.getString("Title"),
									(jBreach.getBoolean("IsVerified") ? "`Verified`" : "`Unverified`") + "\n", false);
						});
					} else {
						meb.addField("Good Job", "You have done a great job in securing your passwords!", true);
					}

					event.getChannel().sendMessage(meb.build()).complete();
				} else {
					event.getChannel().sendMessage("`Please provide an email as your argument like` \n>hacked <email>")
							.complete();
				}
			}
		} catch (Exception e) {
		}
	}
}