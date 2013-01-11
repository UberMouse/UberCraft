package nz.ubermouse.minecraft.classicserver.packets

import nz.ubermouse.minecraft.classicserver.{Client, Server}
import java.nio.ByteBuffer
import java.io.PrintStream
import nz.ubermouse.minecraft.classicserver.utils.Logger

/**
 * Created with IntelliJ IDEA.
 * User: UberMouse
 * Date: 11/01/13
 * Time: 02:21
 * To change this template use File | Settings | File Templates.
 */
abstract class Packet(server:Server, client:Client) {

  implicit def byteBuffer2Wrapper(buffer:ByteBuffer) = new ByteBufferWrapper(buffer)

  val length:Int
  val dynamic:Boolean
  val opCode:Int
  val name:String

  def process(message:Array[Byte]) {
    Logger().trace(s"Processing $name packet from Client: ${client.connection.getInetAddress.getHostAddress}", "Networking.Packets")
    processPacket(message)
    Logger().trace(s"Processed $name packet from Client: ${client.connection.getInetAddress.getHostAddress}", "Networking.Packets")
  }

  protected def processPacket(message:Array[Byte])

  def write(stream:PrintStream) {
    Logger().trace(s"Preparing $name packet for Client: ${client.connection.getInetAddress.getHostAddress}", "Networking.Packet")
    val buffer = ByteBuffer.allocate(length)
    buffer.rewind()
    buffer.put(opCode)
    writePacket(buffer)
    stream.write(buffer.array())
    Logger().trace(s"Prepared $name packet for Client: ${client.connection.getInetAddress.getHostAddress}", "Networking.Packet")
  }

  protected def writePacket(buffer:ByteBuffer)
}
