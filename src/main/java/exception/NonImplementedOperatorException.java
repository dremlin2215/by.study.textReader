package exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NonImplementedOperatorException extends RuntimeException {

    private static final Logger LOGGER = LogManager.getLogger(NonImplementedOperatorException.class);
    private static final long serialVersionUID = 32L;

    public NonImplementedOperatorException(String message) {
        super(message);
        LOGGER.warn("NonImplementedOperatorException \n");
    }

    public NonImplementedOperatorException() {
        super();
        LOGGER.warn("NonImplementedOperatorException \n");
    }

    public NonImplementedOperatorException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.warn("NonImplementedOperatorException \n");
    }

    public NonImplementedOperatorException(Throwable cause) {
        super(cause);
        LOGGER.warn("NonImplementedOperatorException \n");
    }

    protected NonImplementedOperatorException(String message, Throwable cause,
                                              boolean enableSuppression,
                                              boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        LOGGER.warn("NonImplementedOperatorException \n");
    }
}
