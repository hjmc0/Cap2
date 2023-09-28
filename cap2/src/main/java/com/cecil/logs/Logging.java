package com.cecil.logs;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logging {
    private static Logger logger = Logger.getLogger("Logs");
    
    private Logging() {
    };
    
    public static void openLog(String TellerInput) {
        try {
            FileHandler fileHandler = new FileHandler("TellerLogs", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.setUseParentHandlers(false);
            logger.addHandler(fileHandler);
            logger.setLevel(Level.INFO);
            logger.info(TellerInput);
            fileHandler.close();
        } catch (SecurityException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
