package ua.greencampus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @author Nikolay Yashchenko
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/dialogs");
        registry.enableSimpleBroker("/dialog");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/ws/chat/dialogs").withSockJS();
        stompEndpointRegistry.addEndpoint("/ws/chat/dialog/{userFromId}/{userToId}").withSockJS();
        stompEndpointRegistry.addEndpoint("/ws/chat/dialog/{userFromId}/{userToId}/send").withSockJS();
    }
}
