Streaming & Perplexity Math Model

This Application is build using Kotlin and follows MVVM architecture.

Project structure:

- MainActivity - It contains two buttons "Streaming" & "Perplexity Math Model"
  - "Streaming" button redirects to StreamingActivity
  - "Perplexity Math Model" redirects to Perplexity Activity

- perplexity 
  - activities
    - PerplexityActivity - It has a input box where user will have to enter the comma separated probabilities which lies between 0 & 1 and a text view for showing any possible error if the user input is not as expected and the calculated perplexity if the user input is as expected.
  - viewmodels
    - PerplexityViewmodel - It is view model class which is instantiated in PerplexityActivity class. It contains a method isDouble which accepts a string and returns true if the string is a double value and lies between 0 and 1 otherwise it returns false, a method calculatePerplexity that takes as input a string from a user it sets a error in errorLiveData if input is empty otherwise it splits the input by comma and checks if each comma separated value is a valid double between 0 & 1 if it is not it sets a error in errorLiveData otherwise it calculates the perplexity and sets he value in perplexityLiveData.
  - repositories
    - PerplexityRepository - It is the repository class which is instantiated in  PerplexityViewModel. It contains a method "calculatePerplexity" which takes as input the list of probabilities and returns the calculated perplexity.

- streaming
  - activities
    - StreamingActivity - It displays a list of data received from the web socket as a list in recycler view if internet is available if not it shows a retry button on click of which again internet is checked is it is there socket is connected and button is hidden, any new data received is added to the top of the list. 
  - adapters
    - StreamingAdapter - It is a adapter class for recycler view in the activity StreamingActivity that shows the received that from the web socket.
  - models
    - StreamingData - It is a data class used to map received data object to it.
  - viewmodels
    - StreamingViewModel - It is a view model class which is instantiated in StreamingActivity. This view model contains a object of WebSocketListener which is used to create object of StreamingRepository, a object of MutableLiveData<StreamingData>, object of LiveData<StreamingData>, a object of ArrayList<StreamingData>() which holds the data received by the web socket. There is a method startWebSocket which is used to start the web socket, a method send message which takes a parameter called message which is used to send through web socket in this method, a method closeConnection which is used to close the web socket connection.
  - repositories
    - StreamingRepository - It is repository class instantiated in StreamingViewModel. Its constructor takes as parameter the WebSocketListener which is used to create the object of WebSocketRepository in this class. There is a method startWebSocket which is used to start the web socket, a method send message which takes a parameter called message which is used to send through web socket in this method, a method closeConnection which is used to close the web socket connection.
    - WebSocketRepository - It is a repository class instantiated in StreamingRepository. Its constructor takes as parameter the WebSocketListener which is used to create the object of WebSocketClient in this class. There is a method startWebSocket which is used to start the web socket, a method send message which takes a parameter called message which is used to send through web socket in this method, a method closeConnection which is used to close the web socket connection.
  - websocket
    - WebSocketClient - It is a class used to handle web socket connection, instantiated in WebSocketRepository. It contains a objects of WebSocket, OkHttpClient & Request. It contains a method startWebSocket which connects the web socket, a method send message which takes a parameter called message which is used to send through web socket in this method, a method closeConnection which is used to close the web socket connection.

Dependencies Used:
- viewmodel
- livedata
- okhttp - for implementing websocket
- gson - for conversion of json into corresponding data class

Setup & Installation:
- Clone the repository. 
- Open the project in Android Studio. 
- Build and run the app on an emulator or device.