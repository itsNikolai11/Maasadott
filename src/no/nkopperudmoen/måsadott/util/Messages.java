package no.nkopperudmoen.måsadott.util;

import no.nkopperudmoen.måsadott.Main;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Messages {
    public static Plugin plugin = Main.getPlugin(no.nkopperudmoen.måsadott.Main.class);
    static FileConfiguration config = Main.lang;
    //ANSI-farger for console
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    //TABLIST format
    public static String TABLIST_HEADER = ChatColor.translateAlternateColorCodes('&', config.getString("TablistHeader"));
    public static String TABLIST_FOOTER = ChatColor.translateAlternateColorCodes('&', config.getString("TablistFooter"));

    //CHAT-Format
    public static String CHAT_FORMAT = ChatColor.translateAlternateColorCodes('&', config.getString("ChatFormat"));
    public static String CONNECT = ChatColor.translateAlternateColorCodes('&', config.getString("Connect"));
    public static String DISCONNECT = ChatColor.translateAlternateColorCodes('&', config.getString("Disconnect"));
    //Meldinger til spiller(e)
    public static String playerIsAfk = ChatColor.translateAlternateColorCodes('&', config.getString("PlayerIsAfk"));
    public static String playerIsNotAfk = ChatColor.translateAlternateColorCodes('&', config.getString("PlayerIsNotAfk"));
    public static String PREFIX = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix") + " ");
    public static String NO_ACCESS = ChatColor.translateAlternateColorCodes('&', config.getString("NoAccess"));
    public static String TELEPORTING = ChatColor.translateAlternateColorCodes('&', config.getString("Teleporting"));
    public static String RTP_BLOCKED = ChatColor.translateAlternateColorCodes('&', config.getString("RtpBlocked"));
    public static String RTP_TELEPORTED = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("RtpTeleported"));
    public static String TPA_SENDT = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("TpaSendt"));
    public static String TPA_MOTATT = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("TpaMotatt"));
    public static String TPA_GODTATT = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("TpaGodtatt"));
    public static String TPA_AVSLÅTT = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("TpaAvslått"));
    public static String AVSLO_TPA = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("AvsloTpa"));
    public static String TPA_INGEN = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("TpaIkkeFunnet"));
    public static String TPA_SELV = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("TpaSelv"));
    public static String TPA_NOT_ONLINE = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("TpaNotOnline"));
    public static String TPAHERE = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("TpaHere"));
    public static String ONTIME = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("Ontime"));
    public static String ONTIME_RANKUP = ChatColor.translateAlternateColorCodes('&', config.getString("OntimeRankup"));
    public static String RANKLIST = ChatColor.translateAlternateColorCodes('&', config.getString("Ranklist"));

    public static String DELETE_HOME = ChatColor.translateAlternateColorCodes('&', config.getString("HomeDelete"));
    public static String SET_HOME = ChatColor.translateAlternateColorCodes('&', config.getString("HomeSet"));
    public static String HOME_LIST = ChatColor.translateAlternateColorCodes('&', config.getString("HomeList"));
    public static String HOME_NOT_FOUND = ChatColor.translateAlternateColorCodes('&', config.getString("HomeNotFound"));
    //SOL
    public static String SOL = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("Sol"));
    public static String IKKE_NOK_STEMMER = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("SolIkkeNokStemmer"));
    public static String SOL_COOLDOWN = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("SolCooldown"));
    public static String SOL_IKKE_REGN = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("RegnerIkke"));
    public static String SOL_ALLEREDE_STEMT = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("AlleredeStemt"));
    public static String STEMME_REGISTRERT = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("StemmeRegistrert"));
    public static String SOL_BLOKKERT = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("SolBlokkert"));
    public static String SOL_STARTET = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("SolAvstemning"));
    public static String SOL_INSTANT = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("SolInstant"));








}
