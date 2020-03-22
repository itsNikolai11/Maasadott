package no.nkopperudmoen.måsadott.filbehandling;

import no.nkopperudmoen.måsadott.Rover.Rover;

import java.io.IOException;

public interface FileSaver {
    void savePlayerFile(Rover p) throws IOException;
}
