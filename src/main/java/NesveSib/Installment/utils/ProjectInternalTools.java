package NesveSib.Installment.utils;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProjectInternalTools {

    public static Logger logger = (Logger) LoggerFactory.getILoggerFactory().getLogger("log");


}
