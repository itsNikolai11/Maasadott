package no.nkopperudmoen.måsadott.filbehandling;

import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.Rover.Rover;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PlayerFileSaver implements FileSaver, Serializable {
    @Override
    public void savePlayerFile(Rover p) throws IOException {
        OutputStream out = Files.newOutputStream(Paths.get((Main.getPlugin(Main.class).getDataFolder().toPath() + "\\Players\\" + p.getUuid() + ".jobj")));
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(p.getHomes());

    }
}
