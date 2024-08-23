package Configurations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerClass {
  //  public static Logger logger = LogManager.getRootLogger();
  private static final Logger logger = LogManager.getLogger(LoggerClass.class);



    public static void logMessage(String message) {
        logger.info( message);
    }
}
