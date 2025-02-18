package com.olaandroidassignment.streaming.repositories

import com.olaandroidassignment.streaming.websocket.WebSocketClient
import okhttp3.WebSocketListener

class WebSocketRepository(webSocketListener: WebSocketListener) {

    private val webSocketClient= WebSocketClient(webSocketListener)

    /**
     * This method is used to start web socket connection
     */
    fun startWebSocket() {
        webSocketClient.startWebSocket()
    }

    /**
     * This method accepts a string message
     * and used to send that message through web socket
     */
    fun sendMessage(message: String) {
        webSocketClient.sendMessage(message)
    }

    /**
     * This method is used to close connection
     */
    fun closeConnection() {
        webSocketClient.closeConnection()
    }

}