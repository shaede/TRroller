package Commands;
//not using
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import prng.psuedoRandomNumberGenerator;

import java.util.List;
import java.util.Objects;

// roll 1 d20 + x
// roll 1 d2 + x
// roll highOrLow
// roll gm 1 d20 + x

public class roll extends ListenerAdapter {
    public final String name ="roll";
    public final String description="Roll Dem Dice";
    private final psuedoRandomNumberGenerator prng = psuedoRandomNumberGenerator.getPrng();

    public roll(){};

    @Override
    public void onSlashCommand(SlashCommandEvent event)
    {
        if (event.getName().equals(("roll")))
        {
            /*delay just in case we take more than 3 seconds*/
            event.deferReply().queue();
            String tagname = Objects.requireNonNull(event.getOption("name")).getAsString();
            String message = rollDice(event.getOptions());
        /*ToDO  Send the official response*/

        }
    }

    private String rollDice(List<OptionMapping> mapping)
    {
        String message = mapping.toString();
        return message;
    }
}
