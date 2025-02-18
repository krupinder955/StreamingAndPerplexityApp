package com.olaandroidassignment.streaming.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.olaandroidassignment.R
import com.olaandroidassignment.streaming.models.StreamingData
import com.olaandroidassignment.streaming.repositories.StreamingRepository
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class StreamingViewModel(private val application: Application):AndroidViewModel(application) {

    var list=ArrayList<StreamingData>()
    private val streamingMutableLiveData=MutableLiveData<StreamingData>()
    val streamingLiveData:LiveData<StreamingData> get() = streamingMutableLiveData

    private val listener=object:WebSocketListener(){

        override fun onOpen(webSocket: WebSocket, response: Response) {
            Log.d("WebSocket", "Connection established: ${response.message}")
            val subscribeMessage = application.applicationContext.getString(R.string.type_subscribe_channels_name_ticker_product_ids_btc_usd)
            webSocket.send(subscribeMessage)
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            //parsing the received json into SteamingData data class
            val streamingData=Gson().fromJson(text, StreamingData::class.java)

            //adding the new received data at the first position in the list
            if(streamingData.sequence!=0L)
                list.add(0,streamingData)

            //notifying the view that new data has been received
            streamingMutableLiveData.postValue(streamingData)
            Log.d("WebSocket Message Received", "Received message: $text")
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            Log.d("WebSocket", "Received bytes: $bytes")
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            Log.d("WebSocket", "Closing connection: $reason")
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            Log.d("WebSocket", "Closed connection: $reason")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            Log.e("WebSocket", "Error: ${t.message}")
        }
    }
    private val streamingRepository=StreamingRepository(listener)

    /**
     * This method is used to start web socket connection
     */
    fun startWebSocketConnection() {
        streamingRepository.startWebSocketConnection()
    }

    /**
     * This method accepts a string message
     * and used to send that message through web socket
     */
    fun sendMessage(message: String) {
        streamingRepository.sendMessage(message)
    }

    /**
     * This method is used to close connection
     */
    fun closeConnection() {
        streamingRepository.closeConnection()
    }

}