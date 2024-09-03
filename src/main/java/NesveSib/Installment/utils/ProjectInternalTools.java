package NesveSib.Installment.utils;

import NesveSib.Installment.security.PasswordEncryptor;
import ch.qos.logback.classic.Logger;
import lombok.extern.java.Log;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProjectInternalTools {

    public static Logger getLogger(String className) {
        return (Logger) LoggerFactory.getILoggerFactory().getLogger(className);
    }

    public static PasswordEncryptor passwordEncryptor = new PasswordEncryptor();

    public static String trimPhoneNumber(String phoneNumber) {
        if (phoneNumber.startsWith("09"))
            return phoneNumber.substring(1);
        else if (phoneNumber.startsWith("98"))
            return phoneNumber.substring(2);
        return phoneNumber;
    }
}
