package nz.ubermouse.minecraft.classicserver.packets.classicminecraft.server

import nz.ubermouse.minecraft.classicserver.packets.Packet
import nz.ubermouse.minecraft.classicserver.{Client, Server}
import java.nio.ByteBuffer

/**
 * Created with IntelliJ IDEA.
 * User: UberMouse
 * Date: 11/01/13
 * Time: 22:43
 * Level Init Packet
 * Sent by the server to notify client of incoming level data.
 * Packet Structure
 * ID: 0x02
 */
class LevelInit(server:Server, client:Client) extends Packet(server, client) {
  val length = 1
  val dynamic = false
  val opCode = 0x02
  val name = "Level Init"

  protected def processPacket(message: Array[Byte]) {}

  protected def writePacket(buffer: ByteBuffer) {}
}
