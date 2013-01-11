package nz.ubermouse.minecraft.classicserver

import java.net.Socket
import java.io.DataInputStream
import packets.PacketRegistry

/**
 * Created with IntelliJ IDEA.
 * User: UberMouse
 * Date: 11/01/13
 * Time: 02:30
 * To change this template use File | Settings | File Templates.
 */
class Client(val connection:Socket) {

  var connected = true
  val input = new DataInputStream(connection.getInputStream)

  val inputThread = new Thread(new Input)
  inputThread.start()

  class Input extends Runnable {
    def run() {
      while(Server.isConnected && connected) {
        val opCode = input.read().asInstanceOf[Byte]
        if (opCode == -1) {
          connected = false
          return
        }
        val packet = PacketRegistry(opCode, Main.server, Client.this)
        val message = new Array[Byte](packet.length)
        input.read(message, 0, packet.length)
        packet.process(message)
      }
    }
  }
}
