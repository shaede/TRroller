import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import javax.security.auth.login.LoginException;
import javax.swing.text.html.Option;

import jdaInterface.Listener;
import prng.algorithms;
import prng.distros;

import static net.dv8tion.jda.api.interactions.commands.OptionType.*;


//Main program execution
//Named arguments we care about
// -Dtoken='bot id string' switch is MANDATORY and requires a string that contains the bot id that corresponds to this code.  Not including this will throw a loginerror trying to connect the JDA
// -Dglobal='true' || 'false' switch upserts the commands globally.  This is y by default
// -Dlocal='guild id string'  contains strings defining which guild ids to push local commands to. If not used, then does not upsert commands to a local guild

public class diceRoller {

    public static void main(String[] args) throws LoginException {
        //System.out.print("ID: " + System.getProperty("token") + "\tGlobal: " + System.getProperty("global") + "\tlocal: " + System.getProperty("local"));
        try {
            /*Create the JDA instance*/
            JDA jda = JDABuilder.createLight(System.getProperty("token"), GatewayIntent.GUILD_MESSAGES)
                    .addEventListeners(new Listener())
                    .setActivity(Activity.playing("Engaged and ready to roll"))
                    .build();
            jda.awaitReady();

        /*Add the commands.  Current Commands:
            //roll: roll with options
            //troll: roll with subcommand rather than options
            //algo
            //dice-jail
            */
             CommandData roll_command_options = new CommandData("roll","Command. Roll dem dice!" )
                    .addOptions( new OptionData(STRING, "roll_type","How you gonna roll?", false)
                                    .addChoice("gm", "gm")
                    ,            new OptionData(INTEGER, "quantity","Input. Lazy jerk, fine I'll roll more than one dice", false)
                    ,            new OptionData(STRING, "dice_type","Input. What kinda dice?  Default is a 20", false)
                                    .addChoice("d20", "d20")
                                    .addChoice("hiLow", "hiLow")
                                    .addChoice("d6", "d6")
                                    .addChoice("d4", "d4")
                                    .addChoice("d8", "d8")
                                    .addChoice("d12", "d12")
                    ,            new OptionData(INTEGER, "modifier", "Input. Ugh, you're even making me do basic math for you?", false));


             CommandData jail_command = new CommandData("jail", "Dice too unlucky. Fix 'em up, Pray Fervently, or Outright Cheat")
                     .addSubcommands(
                               new SubcommandData("reseed", "Fix your dice! Reseed the current randomizer instance")
                                       .addOptions(new OptionData(BOOLEAN,"is","To reseed or not to reseed?")
                                               //.addChoice("yes","true")
                                               //.addChoice("no","false")
                                               )
                             , new SubcommandData("algo","Pray, PRAY to the gods of dice!  Change the algorithm!")
                                     .addOptions(new OptionData(STRING,"algo_type","Choose wisely.")
                                             .addChoice("basic", String.valueOf(algorithms.basic))
                                             .addChoice("crypto", String.valueOf(algorithms.secureBasic))
                                             .addChoice("blumBlumShub", String.valueOf(algorithms.secureBasic))
                                             .addChoice("fibonacci", String.valueOf(algorithms.fibonacci))
                                             .addChoice("shiftRegister",String.valueOf(algorithms.shiftRegister))
                                     )
                     ,          new SubcommandData("distro","Cheater.  I see you over there.  One of them is a trap.  Serves you right.  Cheater.")
                                     .addOptions(new OptionData(STRING, "distro_type","Choose wisely.  Cheater.")
                                             .addChoice("uniform", String.valueOf(distros.normal))
                                             .addChoice("normal", String.valueOf(distros.normal))
                                             .addChoice("beta", String.valueOf(distros.beta))));

            /* Adds commands to either global, guild, or both*/
            if (System.getProperty("global").isEmpty() || System.getProperty("global").equalsIgnoreCase("true")) {
                System.out.println("Adding global slash commands");
                CommandListUpdateAction commands = jda.updateCommands();
                commands = commands.addCommands(roll_command_options, jail_command);
                commands.queue();
            }
            if(System.getProperty("local")!=null) {
                Guild jdaGuild = jda.getGuildById(System.getProperty("local"));
                assert jdaGuild != null : "Did not get the specified guild";
                System.out.println("Adding local slash commands to " + jdaGuild);

                CommandListUpdateAction commands = jdaGuild.updateCommands();
                commands = commands.addCommands(roll_command_options, jail_command);
                commands.queue();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
