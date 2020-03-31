package no.nkopperudmoen.måsadott.filbehandling;

import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.Rover.Home;
import no.nkopperudmoen.måsadott.Rover.PlayerTime;
import no.nkopperudmoen.måsadott.Rover.Rover;

import no.nkopperudmoen.måsadott.util.Debugger;
import no.nkopperudmoen.måsadott.util.Messages;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

public class PlayerFileReader {
    public void loadPlayer(Rover p) throws IOException, ClassNotFoundException {
        try {
            InputStream oi = Files.newInputStream(Paths.get(Messages.plugin.getDataFolder().toPath() + "\\Players\\" + p.getUuid() + ".jobj"));
            ObjectInputStream in = new ObjectInputStream(oi);
            p.homes.addAll((ArrayList<Home>) in.readObject());
            Debugger.debug("LASTET HJEM FOR SPILLER " + p.getUuid());
            PlayerTime ontime = (PlayerTime) in.readObject();
            Debugger.debug("LASTET ONTIME FOR SPILLER " + p.getUuid());
            if (ontime == null) {
                p.setOntime(new PlayerTime());
            } else {
                p.setOntime(ontime);
            }

        } catch (NoSuchFileException e) {
            Files.createFile(Paths.get((Main.getPlugin(Main.class).getDataFolder().toPath() + "\\Players\\" + p.getUuid() + ".jobj")));
            p.setOntime(new PlayerTime());
        }


    }

    public ArrayList<Home> loadHomes(Player p) throws IOException, ClassNotFoundException {
        InputStream oi = Files.newInputStream(Paths.get(Messages.plugin.getDataFolder().toPath() + "\\Players\\" + p.getUniqueId() + ".jobj"));
        ObjectInputStream in = new ObjectInputStream(oi);
        return (ArrayList<Home>) in.readObject();
    }

    public PlayerTime getOntime(UUID uuid) throws IOException, ClassNotFoundException {
        InputStream oi = Files.newInputStream(Paths.get(Messages.plugin.getDataFolder() + "\\Players\\" + uuid + ".jobj"));
        ObjectInputStream in = new ObjectInputStream(oi);
        in.readObject();
        return (PlayerTime) in.readObject();
    }

    public String toString(ArrayList<Home> list) {
        String homes = "";
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                homes += list.get(i).getName();
            } else {
                homes += list.get(i).getName() + ", ";
            }
        }
        return homes;
    }
}
