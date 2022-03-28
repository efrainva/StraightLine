package com.example.collective.Controller

import ChatLogic
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry


//@MessageMapping()
//class Chat(){
//}
//
@Configuration
@EnableWebSocket
class WebSocketConfig:WebSocketConfigurer{
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(ChatLogic(),"chat")
    }
}