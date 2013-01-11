package nz.ubermouse.minecraft.classicserver

import java.net.{ServerSocket, Socket}
import java.io.{InputStreamReader, BufferedReader}

/**
 * Created with IntelliJ IDEA.
 * User: UberMouse
 * Date: 11/01/13
 * Time: 01:32
 * To change this template use File | Settings | File Templates.
 */
class ConnectionThread extends Runnable {

  val listeningSocket = new ServerSocket(ServerConfig.port)

  def run() {
    while(Server.isConnected) {
      if (listeningSocket.isClosed)
        return
      val connection = listeningSocket.accept()
      println(s"Recieved connection from: ${connection.getInetAddress.getHostAddress}")
      connection.setSoTimeout(30000)
      new Thread(new Accept(connection)).start()
    }
  }

  class Accept(socket:Socket) extends Runnable {

    def run() {
      val client = new Client(socket)
      Main.server.addClient(client)
    }
  }
}
