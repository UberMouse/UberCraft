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
    while(true) {
      if (listeningSocket.isClosed)
        return
      val connection = listeningSocket.accept()
      println(s"Recieved connection from: ${connection.getInetAddress.getHostAddress}")
      connection.setSoTimeout(30000)
      new Thread(new Accept(connection)).start()
    }
  }

  class Accept(client:Socket) extends Runnable {

    def run() {
      val packet = new BufferedReader(new InputStreamReader(client.getInputStream))
      while(packet.ready())
        println(packet.read().asInstanceOf[Byte])
    }
  }
}
