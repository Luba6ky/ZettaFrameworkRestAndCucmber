package api.utils.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

    private static final Logger logger = LogManager.getLogger(Log.class);
    public static String env = "";

    public static void startLog(String testClassName) {
        logger.info(testClassName + " Tests are Starting...");
    }

    public static void endLog(String testClassName) {
        logger.info(testClassName + " Tests are Ending...");
    }

    public static void info(String message) {
        logger.info("----- " + message + " -----");
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(Exception e, String message) {
        info(e.getMessage());
        logger.error(message);
    }

    public static void fatal(String message) {
        logger.fatal(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }
}
