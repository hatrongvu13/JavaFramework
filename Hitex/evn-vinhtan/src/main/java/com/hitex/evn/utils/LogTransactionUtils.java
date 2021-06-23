package com.hitex.evn.utils;



import com.hitex.evn.model.logTransaction.BaseRequestDataLog;
import com.hitex.evn.model.logTransaction.BaseResponseDataLog;
import com.hitex.evn.model.logTransaction.LogTransactionBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.net.InetAddress;

public class LogTransactionUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogTransactionUtils.class);

    public void copyProperties(BaseRequestDataLog baseRequestDataLog, LogTransactionBase logTransactionBase) {

        if (baseRequestDataLog != null) {
            logTransactionBase.setSessionId(baseRequestDataLog.getSessionId());
//            logTransactionBase.setUsername(baseRequestDataLog.getUsername());
            logTransactionBase.setWsCode(baseRequestDataLog.getWsCode());
            String hostName = "Unknown";
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                hostName = inetAddress.getHostName();
                hostName = hostName + "-" + System.getProperty("catalina.base");
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
            logTransactionBase.setHostName(hostName);
            if (baseRequestDataLog.getBaseInfo() != null) {
                BeanUtils.copyProperties(baseRequestDataLog.getBaseInfo(), logTransactionBase);
            }
        }
    }

    public void copyProperties(BaseResponseDataLog baseRequestDataLog, LogTransactionBase logTransactionBase) {
        if (baseRequestDataLog != null) {
            BeanUtils.copyProperties(baseRequestDataLog, logTransactionBase);
        }
    }
}
