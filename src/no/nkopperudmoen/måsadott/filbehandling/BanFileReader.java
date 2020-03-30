package no.nkopperudmoen.måsadott.filbehandling;

import no.nkopperudmoen.måsadott.util.Messages;
import no.nkopperudmoen.måsadott.util.TemporaryBan;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.UUID;

public class BanFileReader implements Serializable {
    public HashMap<UUID, TemporaryBan> getTempBans() throws IOException, ClassNotFoundException {
        InputStream is = Files.newInputStream(Paths.get(Messages.plugin.getDataFolder() + "\\banlist.jobj"));
        ObjectInputStream in = new ObjectInputStream(is);
        return (HashMap<UUID, TemporaryBan>) in.readObject();
    }

}
