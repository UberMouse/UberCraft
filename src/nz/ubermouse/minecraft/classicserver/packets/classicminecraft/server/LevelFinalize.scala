package nz.ubermouse.minecraft.classicserver.packets.classicminecraft.server

import nz.ubermouse.minecraft.classicserver.packets.Packet
import nz.ubermouse.minecraft.classicserver.{Server, Client}
import java.nio.ByteBuffer

/**
 * Created with IntelliJ IDEA.
 * User: UberMouse
 * Date: 11/01/13
 * Time: 22:46
 * Level Finalize Packet
 * Sent by the server to notify client of end of level transmission and gives map dimensions. y is height
 * Packet Structure
 * ID: 0x04
 * X: Short
 * Y: Short
 * Z: Short
 */
class LevelFinalize(server:Server, client:Client) extends Packet(server, client) {
  val length = 7
  val dynamic = false
  val opCode = 0x04
  val name = "Level finalize"

  protected def processPacket(message: Array[Byte]) {}

  protected def writePacket(buffer: ByteBuffer) {
    buffer.putShort(50)
    buffer.putShort(50)
    buffer.putShort(50)
  }
}
