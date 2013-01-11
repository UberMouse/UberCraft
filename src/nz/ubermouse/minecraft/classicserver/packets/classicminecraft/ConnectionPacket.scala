package nz.ubermouse.minecraft.classicserver.packets.classicminecraft

import nz.ubermouse.minecraft.classicserver.packets.{PacketRegistry, Packet}
import nz.ubermouse.minecraft.classicserver.{Client, Server}
import java.io.{InputStreamReader, BufferedReader}
import java.nio.ByteBuffer

/**
 * Created with IntelliJ IDEA.
 * User: UberMouse
 * Date: 11/01/13
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */
class ConnectionPacket(server:Server, client:Client) extends Packet(server, client) {

  def process(message:Array[Byte]) {
    val buffer = ByteBuffer.allocate(length)
    buffer.put(message)
    buffer.rewind()
    val version = buffer.get()
    if (version != 0x07) {
      println("Invalid protocol version")
      return
    }
    val name = buffer.getString()
    val key = buffer.getString()

  }

  def write() {
    throw new NotImplementedError()
  }

  val length = 129
  val dynamic  = false
}
