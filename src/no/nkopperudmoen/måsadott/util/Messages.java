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
    public static String ONTIME_NEXT = ChatColor.translateAlternateColorCodes('&', config.getString("OntimeNext"));
    public static String RANKLIST = ChatColor.translateAlternateColorCodes('&', config.getString("Ranklist"));
    public static String PAYER_NOT_FOUND = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("PlayerNotFound"));
    public static String COOLDOWN = ChatColor.translateAlternateColorCodes('&', config.getString("Cooldown"));
    public static String KLEM = ChatColor.translateAlternateColorCodes('&', config.getString("Klem"));
    public static String KYSS = ChatColor.translateAlternateColorCodes('&', config.getString("Kyss"));
    public static String KLEM_SELV = ChatColor.translateAlternateColorCodes('&', config.getString("KlemSelv"));
    public static String KYSS_SELV = ChatColor.translateAlternateColorCodes('&', config.getString("KyssSelv"));
    public static String BY_HJELP = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("ByHjelp"));

    public static String DELETE_HOME = ChatColor.translateAlternateColorCodes('&', config.getString("HomeDelete"));
    public static String SET_HOME = ChatColor.translateAlternateColorCodes('&', config.getString("HomeSet"));
    public static String HOME_LIST = ChatColor.translateAlternateColorCodes('&', config.getString("HomeList"));
    public static String HOME_NOT_FOUND = ChatColor.translateAlternateColorCodes('&', config.getString("HomeNotFound"));
    //ØKONOMI
    public static String MONEY = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("Money"));

    public static String MOB_DROP= PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("MobDrop"));
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
    //STAB
    public static String KICK = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("Kick"));
    public static String CLEARCHAT = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("ClearChat"));
    public static String TELEPORT = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("Teleport"));
    public static String STABCHAT = ChatColor.translateAlternateColorCodes('&', config.getString("StabChat"));
    public static String BAN = ChatColor.translateAlternateColorCodes('&', config.getString("Ban"));
    public static String TEMPBAN = ChatColor.translateAlternateColorCodes('&', config.getString("Tempban"));
    public static String Unban = ChatColor.translateAlternateColorCodes('&', config.getString("Unban"));
    public static String ENABLE_VANISH = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("VanishPå"));
    public static String DISABLE_VANISH = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("VanishAv"));
    public static String GAMEMODE = PREFIX + ChatColor.translateAlternateColorCodes('&', config.getString("GameMode"));


}
