package Demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jDemo {

    static Logger logger = LogManager.getLogger(Log4jDemo.class);

    public static void main(String[] args) {

        System.out.println("this is a message");

        logger.info("this is an info message");
        logger.warn("this is a warning message");
        logger.error("this is an error message");
        logger.fatal("fatal message");

    }
}
