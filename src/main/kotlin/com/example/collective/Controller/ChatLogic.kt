//package com.example.collective.Controller

import com.example.collective.Controller.WebSocketConfig
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.web.socket.*
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.CopyOnWriteArrayList

class Person(){
    var id = ""
    var name = "#"
    var newMessage = ""
    var commands = charArrayOf('#')
    var messageCount = 0
    fun inf():String{
        var noName =  "$id: $newMessage"
        var message = name
        if(name != "#"){
            message = "$name $newMessage"
        }
        return message
    }
}

class ChatLogic:TextWebSocketHandler() {
    var currentUser = CopyOnWriteArrayList<WebSocketSession>()
    val allSession = 0
    var person = Person()

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        super.handleTextMessage(session, message)
//        session.sendMessage(message)
        person.messageCount++
        person.newMessage =  "${message.payload}"

        if(message.payload == "command"){
            session.sendMessage(TextMessage("type username with a # at the beginning "))

        }else if(message.payload[0] == person.commands[0]){
            person.name = message.payload.replace("#","")
        }else{
            for (ses in currentUser){
                ses.sendMessage(TextMessage(person.inf()))
            }
        }
    }

    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)
currentUser.add(session)

        person.id = session.id
        for (ppl in currentUser){
            ppl.sendMessage(TextMessage("${session.id} user joined"))
        }
        println(session)
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        super.afterConnectionClosed(session, status)

        for (i in currentUser){
            if(i.id == session.id){
                currentUser.remove(i)
            }
        }
        println(currentUser+"disconnected user")
    }

    fun newChat(sessionsnum:Int){
        return
    }

}

private operator fun Char.get(i: Int): Any {
    return 0
}


