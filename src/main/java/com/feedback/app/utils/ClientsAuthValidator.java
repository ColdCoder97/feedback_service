package com.feedback.app.utils;

import com.feedback.app.controller.FeedbackController;
import com.feedback.app.exception.AuthenticationFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import static com.feedback.app.utils.Constants.AUTHENTICATION_FAILED;

/**
 * The security program helps to get secured input data
 * @author  Yamini S
 * @version 1.0
 * @since   2020-07-21
 */
public class ClientsAuthValidator {
    private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    public static boolean validateClient(String clientName,String clientValue) {
         Map<String,String> clients=new HashMap<>();
        clients.put("tcs","feedback_yamini");
        logger.info("client name:" + clientName + "client value:" + clientValue);
        if (clientName != null) {
            String key = clients.get(clientName);
            logger.info("Key Value:" + key);
            if (key!=null && key.equals(clientValue)) {
                logger.info("client validation success");
                return true;
            }
            else {
                throw new AuthenticationFailureException(AUTHENTICATION_FAILED);
            }
        }
        return false;
    }
}

