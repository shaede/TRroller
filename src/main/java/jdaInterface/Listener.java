package jdaInterface;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import prng.distros;
import prng.psuedoRandomNumberGenerator;
import prng.algorithms;


public class Listener extends ListenerAdapter {
    private final prng.psuedoRandomNumberGenerator prng;
    public Listener ()
    {
        prng = psuedoRandomNumberGenerator.getPrng();
    }
    @Override
    public void onSlashCommand(SlashCommandEvent event)
    {
        //Only accept commands from guilds
        //System.out.println("event received");
        //ReplyAction replyAction = event.deferReply(true);
        InteractionHook hook = event.getHook();
        switch (event.getName())
        {
            case "roll":
                OptionMapping e_qty = event.getOption("quantity");
                OptionMapping e_type = event.getOption("dice_type");
                OptionMapping e_mod = event.getOption("modifier");
                OptionMapping e_roll = event.getOption("roll_type");
                //I am not sorry.  Ternary statements, all the way.
                roll(event,
                        e_qty != null ? (int) e_qty.getAsDouble() : 1, //if no qty, default to 1
                        e_type != null ? e_type.getAsString() : "d20",//if no type, default to d20
                        e_mod != null ? (int) e_mod.getAsDouble() : 0, //if no modifier, default to 0
                        e_roll != null && e_roll.getAsString().equals("gm")); //if gm is not passed, it is false
                //System.out.println("Command string: " + event.getCommandString());
                //System.out.println("Command Path: " + event.getCommandPath());
                break;
            case "jail":
            {
                OptionMapping e_reseed = event. getOption("is");
                OptionMapping e_algo = event. getOption("algo_type");
                OptionMapping e_distro = event.getOption("distro_type");
                reseed(event
                        , e_reseed == null || e_reseed.getAsBoolean()
                        , e_algo != null ? e_algo.getAsString() : "nochange"
                        , e_distro != null ? e_distro.getAsString() : "nochange"
                );
            }
            case "cue":
            {
                cue(event);
            }
        }
    }

    private InteractionHook cue(SlashCommandEvent event)
    {
        String eventString = prng.rollLetter() + String.valueOf(prng.roll30());
        return event.reply(eventString).setEphemeral(true).complete();
    }
    private InteractionHook roll(SlashCommandEvent event, int m_qty, String m_type, int m_mod, boolean m_gm) {
        //event.getHook().setEphemeral(m_gm); //sets ephemeral if gm is set to true
        String eventString = "*Rolling ";
        int result = 0;
        int multiplier = 1;
        if (m_qty > 1) //check for negative quantities
        {
            eventString = eventString.concat(m_qty + " " + m_type + "s ");
            multiplier = m_qty;
        } else {
            eventString = eventString.concat(m_qty + " " + m_type + " ");
        }
        if (m_type.equals("hiLow")) {
            eventString = eventString.concat("**" +prng.rollHiLo() + "** ");
            for (int i = 1 ; i< multiplier; i++)
            {
                eventString = eventString.concat("and **" + prng.rollHiLo() + "** ");
            }
        } else {
            result = result + m_mod;
            if (m_mod >= 0)
                eventString = eventString.concat("+ ");
            else
                eventString = eventString.concat("- ");
            eventString = eventString.concat(m_mod + "*: ");
            int t = 0;
            switch (m_type) {
                default:
                    t = prng.roll20();
                    result +=t;
                    eventString = eventString.concat(t + " ");
                    for (int i = 1; i < multiplier; i++) {
                        t = prng.roll20();
                        result += t;
                        eventString = eventString.concat("+ " + t + " ");
                    }
                    eventString = m_mod != 0 ? eventString.concat(" + " + m_mod + " = **" + result + "**" ) : eventString.concat(" = **" + result + "**");
                    break;
                case "d6":
                    t = prng.roll6();
                    result+=t;
                    eventString = eventString.concat(t + " ");
                    for (int i = 1; i < multiplier; i++)
                    {
                        t = prng.roll6();
                        result += t;
                        eventString = eventString.concat("+ " + t + " ");
                    }
                    eventString = m_mod != 0 ? eventString.concat(" + " + m_mod + " = **" + result + "**" ) : eventString.concat(" = **" + result + "**");
                    break;
                case "d4":
                    t = prng.roll4();
                    result+=t;
                    eventString = eventString.concat(t + " ");
                    for (int i = 1; i < multiplier; i++)
                    {
                        t = prng.roll4();
                        result += t;
                        eventString = eventString.concat("+ " + t + " ");
                    }
                    eventString = m_mod != 0 ? eventString.concat(" + " + m_mod + " = **" + result + "**" ) : eventString.concat(" = **" + result + "**");
                    break;
                case "d8":
                    t = prng.roll8();
                    result+=t;
                    eventString = eventString.concat(t + " ");
                    for (int i = 1; i < multiplier; i++)
                    {
                        t = prng.roll8();
                        result += t;
                        eventString = eventString.concat("+ " + t + " ");
                    }
                    eventString = m_mod != 0 ? eventString.concat(" + " + m_mod + " = **" + result + "**" ) : eventString.concat(" = **" + result + "**");
                    break;
                case "d10":
                    t = prng.roll10();
                    result+=t;
                    eventString = eventString.concat(t + " ");
                    for (int i = 1; i < multiplier; i++)
                    {
                        t = prng.roll10();
                        result += t;
                        eventString = eventString.concat("+ " + t + " ");
                    }
                    eventString = m_mod != 0 ? eventString.concat(" + " + m_mod + " = **" + result + "**" ) : eventString.concat(" = **" + result + "**");
                    break;
                case "d12":
                    t = prng.roll12();
                    result+=t;
                    eventString = eventString.concat(t + " ");
                    for (int i = 1; i < multiplier; i++)
                    {
                        t = prng.roll12();
                        result += t;
                        eventString = eventString.concat("+ " + t + " ");
                    }
                    eventString = m_mod != 0 ? eventString.concat(" + " + m_mod + " = **" + result + "**" ) : eventString.concat(" = **" + result + "**");
                    break;
                case "d100":
                    t = prng.roll100();
                    result+=t;
                    eventString = eventString.concat(t + " ");
                    for (int i = 1; i < multiplier; i++)
                    {
                        t = prng.roll100();
                        result += t;
                        eventString = eventString.concat("+ " + t + " ");
                    }
                    eventString = m_mod != 0 ? eventString.concat(" + " + m_mod + " = **" + result + "**" ) : eventString.concat(" = **" + result + "**");
                    break;
            }
        }
        //System.out.println(eventString);

        return m_gm ? event.reply(eventString).setEphemeral(true).complete() : event.reply(eventString).complete();
    }
    private InteractionHook reseed(SlashCommandEvent event, boolean reseed, String algo, String distro){
        String eventString = "Unlucky Bastard.  ";
        if (reseed) {
            prng.reseed();
            eventString = eventString.concat("Reseeded.  ");
        }
        switch (algo)
        {
            case ("nochange"):
                break;
            case ("basic"):
                prng.setM_currentAlgorithm(algorithms.basic);
                eventString = eventString.concat("Gone basic algo.  Schlub.  ");
                break;
            case ("crypto"):
                prng.setM_currentAlgorithm(algorithms.secureBasic);
                eventString = eventString.concat("Crypto algo.  Noice.  ");
                break;
            default:
                eventString = eventString.concat("Still working on your algorithm.  Sorry.  Algo is still " + prng.getM_currentAlgorithm());
                break;
        }
        switch (distro)
        {
            case ("nochange"):
                break;
            case ("uniform"):
                prng.setM_currentDistro(distros.uniform);
                eventString = eventString.concat("Wow.  Hard core.  Good luck?");
                break;
            case("normal"):
                prng.setM_currentDistro(distros.normal);
                eventString = eventString.concat("We all do indeed like bells");
                break;
            default:
                eventString = eventString.concat("Still working on your requested distro.  Sorry.  Distro is still " + prng.getM_currentDistro());
                break;
        }
        return (reseed && algo.equals("nochange") && distro.equals("nochange")) ? event.reply(eventString).setEphemeral(true).complete() : event.reply(eventString).complete();
    }
    private InteractionHook algo(SlashCommandEvent event, OptionMapping algo_type){
            String eventString;
            String m_algo = algo_type != null ? algo_type.getAsString() : "blah";
            switch (m_algo)
            {
                case "basic":
                    eventString = "Someone's a basic schlub.  Algo set to basic";
                    prng.setM_currentAlgorithm(algorithms.basic);
                    break;
                case "secureBasic":
                    eventString = "Someones a bit paranoid.  Algo set to secure basic";
                    prng.setM_currentAlgorithm(algorithms.secureBasic);
                    break;
                case "blumBlumShub":
                    eventString = "Still working on implementation";
                    break;
                default:
                    eventString = "That's pretty lazy. You get rewarded with the basic algo.";
                    prng.setM_currentAlgorithm(algorithms.basic);
            }
        return event.reply(eventString).complete();
    }
    private void help(){
        System.out.println("help");
    }
}
