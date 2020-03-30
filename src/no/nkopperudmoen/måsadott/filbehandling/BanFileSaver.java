package no.nkopperudmoen.måsadott.filbehandling;

import no.nkopperudmoen.måsadott.util.Messages;
import no.nkopperudmoen.måsadott.util.TemporaryBan;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BanFileSaver implements Serializable {
    public void saveBans() throws IOException {
        OutputStream os = Files.newOutputStream(Paths.get(Messages.plugin.getDataFolder() + "\\banlist.jobj"));
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(TemporaryBan.tempbans);
        out.close();
    }
}
