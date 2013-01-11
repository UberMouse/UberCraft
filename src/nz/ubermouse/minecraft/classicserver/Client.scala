package nz.ubermouse.minecraft.classicserver

import java.net.Socket
import java.io.{PrintStream, DataInputStream}
import packets.{Packet, PacketRegistry}
import collection.immutable.Queue
import utils.Logger

/**
 * Created with IntelliJ IDEA.
 * User: UberMouse
 * Date: 11/01/13
 * Time: 02:30
 * To change this template use File | Settings | File Templates.
 */
class Client(val connection:Socket) {

  var connected = true
  var player:Player = null

  val inputStream = new DataInputStream(connection.getInputStream)
  val inputThread = new Thread(new Input(this))
  inputThread.start()

  val outputStream = new PrintStream(connection.getOutputStream)
  val output = new Output(this)
  val outputThread = new Thread(output)
  outputThread.start()

  def write(packet:Packet) {
    output.addToOutput(packet)
  }

  class Input(client:Client) extends Runnable {
    def run() {
      while(Server.isConnected && connected) {
        val opCode = inputStream.read().asInstanceOf[Byte]
        if (opCode == -1) {
          connected = false
          return
        }
        val packet = PacketRegistry(opCode, Main.server, client)
        val message = new Array[Byte](packet.length)
        inputStream.read(message, 0, packet.length)
        packet.process(message)
      }
    }
  }

  class Output(client:Client) extends Runnable {

    private var outputQueue = Queue.empty[Packet]

    def addToOutput(packet:Packet) {
      outputQueue = outputQueue.enqueue(packet)
    }

    def run() {
      while(Server.isConnected && connected) {
        if (!outputQueue.isEmpty) {
          val (packet, queue) = outputQueue.dequeue
          outputQueue = queue
          if (packet != null) {
            Logger().trace(s"Writing ${packet.name} packet to client: ${client.connection.getInetAddress.getHostAddress}", "Networking.Packets")
            packet.write(outputStream)
            outputStream.flush()
            Logger().trace(s"Wrote ${packet.name} packet to client: ${client.connection.getInetAddress.getHostAddress}", "Networking.Packets")
            Thread.sleep(5)
          }
        }
      }
    }
  }
}
