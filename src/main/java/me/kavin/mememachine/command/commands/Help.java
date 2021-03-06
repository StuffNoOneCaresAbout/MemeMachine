package me.kavin.mememachine.command.commands;

import me.kavin.mememachine.command.Command;
import me.kavin.mememachine.command.CommandManager;
import me.kavin.mememachine.utils.ColorUtils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Help extends Command {
	public Help() {
		super(">help", "`Shows this message`");
	}

	@Override
	public void onCommand(String message, MessageReceivedEvent event) {
		try {
			{
				EmbedBuilder meb = new EmbedBuilder();
				meb.setColor(ColorUtils.getRainbowColor(2000));
				meb.setTitle("Meme Machine's Commands:");
				meb.setDescription(
						"Help sent! Check DMs! ✅\n If you have dm's disabled, click [here](https://mememachinebot.ml/commands.php)");
				event.getChannel().sendMessage(meb.build()).queue();
			}
			{
				EmbedBuilder meb = new EmbedBuilder();
				meb.setColor(ColorUtils.getRainbowColor(2000));
				meb.setTitle("Meme Machine's Commands:");
				PrivateChannel pc = event.getAuthor().openPrivateChannel().submit().get();
				for (Command cmd : CommandManager.commands) {
					if (meb.getFields().size() >= 25) {
						pc.sendMessage(meb.build()).queue();
						meb.clearFields();
						meb.setTitle(" ");
						meb.addField(cmd.getPrefix(), cmd.getHelp() + '\n', false);
					} else {
						meb.addField(cmd.getPrefix(), cmd.getHelp() + '\n', false);
					}

				}
				if (meb.getFields().size() > 0)
					pc.sendMessage(meb.build()).queue();
			}
		} catch (Exception e) {
		}
	}
}