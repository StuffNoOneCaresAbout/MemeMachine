package me.kavin.mememachine.command.commands;

import me.kavin.mememachine.command.Command;
import me.kavin.mememachine.utils.ColorUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Invite extends Command {
	public Invite() {
		super(">invite", "`Gives you a link to invite me`");
	}

	@Override
	public void onCommand(String message, MessageReceivedEvent event) {
		try {
			{
				EmbedBuilder meb = new EmbedBuilder();
				meb.setTitle("Invite: ");
				meb.setColor(ColorUtils.getRainbowColor(2000));
				meb.setDescription("You can invite me by clicking " + "[here]("
						+ "https://discordapp.com/oauth2/authorize?client_id=" + event.getJDA().getSelfUser().getId()
						+ "&permissions=8&scope=bot" + ")");
				event.getAuthor().openPrivateChannel().submit().get().sendMessage(meb.build()).queue();
			}
			{
				EmbedBuilder meb = new EmbedBuilder();
				meb.setTitle("Invite: ");
				meb.setColor(ColorUtils.getRainbowColor(2000));
				meb.setDescription("Check your DM's, if you have them disabled, invite it though the github!");
				event.getChannel().sendMessage(meb.build()).queue();
			}
		} catch (Exception e) {
		}
	}
}