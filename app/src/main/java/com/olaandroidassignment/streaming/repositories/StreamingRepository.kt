package com.olaandroidassignment.streaming.repositories

import okhttp3.WebSocketListener

class StreamingRepository(webSocketListener: WebSocketListener) {

    private val webSocketRepository=WebSocketRepository(webSocketListener)

    /**
     * This method is used to start web connection
     */
    fun startWebSocketConnection() {
        webSocketRepository.startWebSocket()
    }

    /**
     * This method accepts a string message
     * and used to send that message through web socket
     */
    fun sendMessage(message: String) {
        webSocketRepository.sendMessage(message)
    }

    /**
     * This method is used to close connection
     */
    fun closeConnection() {
        webSocketRepository.closeConnection()
    }

}