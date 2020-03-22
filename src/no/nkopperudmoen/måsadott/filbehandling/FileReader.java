package no.nkopperudmoen.måsadott.filbehandling;

import no.nkopperudmoen.måsadott.exceptions.InvalidPlayerException;
import org.bukkit.entity.Player;

import java.io.IOException;

public interface FileReader {

    public Player getPlayerFile() throws InvalidPlayerException;
    //TODO load playerdata to playerObject on playerjoin
    //Save to playerdata every 5 min
}
