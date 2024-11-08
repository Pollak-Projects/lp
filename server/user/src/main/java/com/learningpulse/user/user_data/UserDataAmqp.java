package com.learningpulse.user.user_data;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDataAmqp {
    private static final Logger logger = LoggerFactory.getLogger(UserDataAmqp.class);
    private final UserDataRepository userDataRepository;

    @RabbitListener(queues = "checkUser")
    public Boolean checkUser(String subString) {
        // This is here because UUID.toString() adds quotes around the string representation of the UUID
        String withOutQuotesSubString = subString.replace("\"", "");
        logger.atDebug().log("Checking user: " + withOutQuotesSubString);
        UUID sub = UUID.fromString(withOutQuotesSubString);
        return userDataRepository.findByUserId(sub).hasElement().block();
    }
}
