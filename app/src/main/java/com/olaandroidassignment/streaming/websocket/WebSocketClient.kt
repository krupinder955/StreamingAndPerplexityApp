package com.olaandroidassignment.streaming.websocket

import com.olaandroidassignment.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class WebSocketClient(private val listener: WebSocketListener) {

    private var webSocket: WebSocket? = null
    private val okHttpClient: OkHttpClient = OkHttpClient()
    private val request = Request.Builder()
        .url(BuildConfig.STREAMING_URL)
        .build()

    /**
     * This method is used to start the websocket
     */
    fun startWebSocket(){
        webSocket=okHttpClient.newWebSocket(request, listener)
    }

    /**
     * This method accepts a string message
     * and used to send that message through web socket
     */
    fun sendMessage(message: String) {
        webSocket?.send(message)
    }

    /**
     * This method is used to close the web socket
     */
    fun closeConnection() {
        webSocket?.close(1000, "Client Closed")
    }

}