package no.nkopperudmoen.måsadott.filbehandling;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;

public interface ConfigReader {

    FileConfiguration loadConfig() throws IOException, InvalidConfigurationException;

}
