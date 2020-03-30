package no.nkopperudmoen.måsadott;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import no.nkopperudmoen.måsadott.commands.*;
import no.nkopperudmoen.måsadott.commands.economy.Money;
import no.nkopperudmoen.måsadott.commands.stab.*;
import no.nkopperudmoen.måsadott.commands.tpa.TPA;
import no.nkopperudmoen.måsadott.commands.tpa.TpaHere;
import no.nkopperudmoen.måsadott.commands.tpa.Tpaccept;
import no.nkopperudmoen.måsadott.commands.tpa.Tpdeny;
import no.nkopperudmoen.måsadott.controllers.TabListController;
import no.nkopperudmoen.måsadott.events.*;
import no.nkopperudmoen.måsadott.filbehandling.BanFileReader;
import no.nkopperudmoen.måsadott.filbehandling.BanFileSaver;
import no.nkopperudmoen.måsadott.util.RankFileReader;
import no.nkopperudmoen.måsadott.controllers.PlayerController;
import no.nkopperudmoen.måsadott.commands.Ontime;
import no.nkopperudmoen.måsadott.util.Messages;
import no.nkopperudmoen.måsadott.util.TemporaryBan;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private PluginManager pm = Bukkit.getPluginManager();
    private Logger logger = Bukkit.getLogger();
    private PluginDescriptionFile pdf = getDescription();
    private FileConfiguration config = getConfig();
    public static FileConfiguration lang;
    private File langFile;
    public Main plugin = this;
    public static Permission permission = null;
    public static Chat chat = null;
    public static Economy economy = null;

    public void onEnable() {
        config.options().copyDefaults(true);
        saveConfig();
        loadLang();

        registerPlayerFiles();
        registerCommands();
        registerEvents();
        registerPerms();
        registerVault();
        try {
            loadBanList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Bukkit.getServer().getOnlinePlayers().size() > 0) {
            onReload();
        }
        RankFileReader.loadRankFile();
        System.out.println(Messages.ANSI_GREEN + "[" + pdf.getName() + "] ble lastet inn! Versjon " + pdf.getVersion() + Messages.ANSI_RESET);

    }

    public void loadLang() {
        langFile = new File(getDataFolder(), "lang.yml");
        if (!langFile.exists()) {
            langFile.getParentFile().mkdirs();
            saveResource("lang.yml", false);
        }
        lang = new YamlConfiguration();
        try {
            saveResource("lang.yml", true);
            lang.load(langFile);


        } catch (IOException | InvalidConfigurationException e) {
            getLogger().log(Level.SEVERE, Messages.ANSI_RED + "[" + pdf.getName() + "]" + "Kunne ikke laste språkfil!" + Messages.ANSI_RESET);
            Bukkit.getServer().getPluginManager().disablePlugin(this);

        }

    }

    public void onReload() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            if (PlayerController.getPlayer(p) == null) {
                PlayerController.createPlayer(p);
            }

        }
        System.out.println(Messages.ANSI_YELLOW + "[" + pdf.getName() + "] Reload oppdaget! Oppretter brukerobjekter og starter oppgaver.." + Messages.ANSI_RESET);

    }


    public void registerVault() {
        RegisteredServiceProvider<Permission> permProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        RegisteredServiceProvider<Economy> econProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (permProvider != null) {
            permission = permProvider.getProvider();
            System.out.println(Messages.ANSI_YELLOW + pdf.getName() + " koblet til " + permProvider.getProvider().getName() + Messages.ANSI_RESET);
        } else {
            System.err.println(Messages.ANSI_RED + "[" + pdf.getName() + "] Ingen permission-plugin funnet! Ontime-ranking vil ikke fungere." + Messages.ANSI_RESET);
        }
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
            System.out.println(Messages.ANSI_YELLOW + "[" + pdf.getName() + "] koblet til " + chatProvider.getProvider().getName() + Messages.ANSI_RESET);
        } else {
            System.err.println(pdf.getName() + " Ingen chat-plugins funnet!" + Messages.ANSI_RESET);
        }
        if (econProvider != null) {
            economy = econProvider.getProvider();
            System.out.println(Messages.ANSI_GREEN + " Økonomi funnet! Koblet til " + econProvider.getProvider().getName() + Messages.ANSI_RESET);
        } else {
            System.err.println(Messages.ANSI_RED + pdf.getName() + "Ingen economy-plugins funnet!" + Messages.ANSI_RESET);
        }


    }

    public void onDisable() {

    }

    void loadBanList() throws IOException {
        BanFileReader reader = new BanFileReader();
        BanFileSaver saver = new BanFileSaver();
        File f = new File(Messages.plugin.getDataFolder() + "\\banlist.jobj");
        if (!f.exists() && !f.isDirectory()) {
            Files.createFile(Paths.get(Messages.plugin.getDataFolder() + "\\banlist.jobj"));
        }
        try {

            TemporaryBan.tempbans = reader.getTempBans();
            saver.saveBans();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void registerPlayerFiles() {
        File dir = new File(this.getDataFolder() + "\\Players\\");
        if (dir.mkdirs()) {
            this.getLogger().log(Level.FINE, "[" + pdf.getName() + "]" + Messages.ANSI_GREEN + "Opprettet bane for spillerdata!" + Messages.ANSI_RESET);
        }
    }

    public void registerCommands() {
        getCommand("msg").setExecutor(new PrivateMessage(this));
        getCommand("r").setExecutor(new PrivateMessageReply(this));
        getCommand("spawn").setExecutor(new Spawn(config));
        getCommand("setspawn").setExecutor(new SetSpawn(config, this));
        getCommand("sethome").setExecutor(new SetHome(this));
        getCommand("home").setExecutor(new TPHome(this));
        getCommand("back").setExecutor(new Back(this));
        getCommand("rtp").setExecutor(new Rtp(this));
        getCommand("tpa").setExecutor(new TPA(this));
        getCommand("tpaccept").setExecutor(new Tpaccept());
        getCommand("tpdeny").setExecutor(new Tpdeny());
        getCommand("tpahere").setExecutor(new TpaHere(this));
        getCommand("ontime").setExecutor(new Ontime(this));
        getCommand("rank").setExecutor(new Rank());
        getCommand("ranklist").setExecutor(new Ranklist());
        getCommand("warn").setExecutor(new Warn());
        getCommand("homes").setExecutor(new HomeList());
        getCommand("delhome").setExecutor(new DeleteHome());
        getCommand("sol").setExecutor(new Sol());
        getCommand("kick").setExecutor(new Kick());
        getCommand("c").setExecutor(new ClearChat());
        getCommand("tp").setExecutor(new Teleport());
        getCommand("tphere").setExecutor(new TPHere());
        getCommand("klem").setExecutor(new Klem());
        getCommand("kyss").setExecutor(new Kyss());
        getCommand("a").setExecutor(new StabChat());
        getCommand("ban").setExecutor(new Ban());
        getCommand("tempban").setExecutor(new Tempban());
        getCommand("unban").setExecutor(new Unban());
        getCommand("vanish").setExecutor(new Vanish());
        getCommand("gamemode").setExecutor(new GameMode());
        getCommand("by").setExecutor(new By());
        getCommand("bal").setExecutor(new Money());

    }


    public void registerPerms() {

    }

    public void registerEvents() {
        pm.registerEvents(new PlayerManager(this), this);
        pm.registerEvents(new BackListener(), this);
        pm.registerEvents(new AfkCheck(), this);
        pm.registerEvents(new TeleportCancel(), this);
        pm.registerEvents(new TextColor(), this);
        pm.registerEvents(new ChatFormat(), this);
        pm.registerEvents(new URLSpotter(), this);
        pm.registerEvents(new TabListController(), this);
        pm.registerEvents(new SignCommandEvent(), this);
        pm.registerEvents(new VanishManager(), this);
        pm.registerEvents(new MuteManager(), this);
        pm.registerEvents(new MobMoneyDrop(), this);

    }
}
