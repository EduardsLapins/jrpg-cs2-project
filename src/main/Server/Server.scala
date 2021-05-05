package Server

import com.corundumstudio.socketio.{AckRequest, Configuration, SocketIOClient, SocketIOServer}
import akka.actor.{Actor, ActorSystem, Props}
import com.corundumstudio.socketio.listener.{ConnectListener, DataListener, DisconnectListener}

class Server{
  val config: Configuration = new Configuration {
    setHostname("localhost")
    setPort(8080)
  }

  val server: SocketIOServer = new SocketIOServer(config)

  server.addConnectListener(new ConnectionListener())
  server.addDisconnectListener(new DisconnectionListener())
  server.addEventListener("add", classOf[String], new AddParty(this))

/*  override def receive: Receive = {
    case
  }*/


  server.start()
/*
  override def postStop(): Unit = {
    println("stopping server")
    server.stop()
  }*/
}

class ConnectionListener() extends ConnectListener {
  override def onConnect(client: SocketIOClient): Unit = {
    println("Connected: " + client)
  }
}

class DisconnectionListener() extends DisconnectListener {
  override def onDisconnect(socket: SocketIOClient): Unit = {
    println("Disconnected: " + socket)
  }
}

class AddParty(server: Server) extends DataListener[String] {

  override def onData(client: SocketIOClient, data: String, ackSender: AckRequest): Unit = {

  }
}

object Server{
  def main(args: Array[String]): Unit = {

  }
}

