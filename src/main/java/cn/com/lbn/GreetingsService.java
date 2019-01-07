package cn.com.lbn;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GreetingsService {
    private final GreetingsStreams greetingsStreams;
    public GreetingsService(GreetingsStreams greetingsStreams) {
        this.greetingsStreams = greetingsStreams;
    }
    
    public void sendGreeting(final Greetings greetings) {
        System.out.println("Sending greetings: " + greetings);
        MessageChannel messageChannel = greetingsStreams.outboundGreetings();
        messageChannel.send(MessageBuilder
                .withPayload(greetings)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}
