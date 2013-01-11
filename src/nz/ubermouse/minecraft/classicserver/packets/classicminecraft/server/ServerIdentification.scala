package nz.ubermouse.minecraft.classicserver.packets.classicminecraft.server

import nz.ubermouse.minecraft.classicserver.packets.Packet
import nz.ubermouse.minecraft.classicserver.{ServerConfig, Client, Server}
import java.io.PrintStream
import java.nio.ByteBuffer
import nz.ubermouse.minecraft.classicserver.utils.Logger

/**
 * Created with IntelliJ IDEA.
 * User: UberMouse
 * Date: 11/01/13
 * Time: 20:00
 *
 * Server Identification Packet
 * Response to a joining player. The user type indicates whether a player is an op (0x64) or not (0x00)
 * Current protocol version is 0x07.
 * Packet Structure
 * ID: 0x00
 * Protocol Version: Byte
 * Server Name: String
 * Server MOTD: String
 * User Type: Byte
 *
 */
class ServerIdentification(server:Server, client:Client) extends Packet(server, client) {
  val length = 131
  val dynamic = false
  val opCode = 0x00
  val name = "Server Identification"

  def processPacket(message: Array[Byte]) {}

  protected def writePacket(buffer: ByteBuffer) {
    buffer.put(0x07)
    buffer.putString(ServerConfig.serverName)
    buffer.putString(ServerConfig.messageOfTheDay)
    buffer.put(0x00)
  }
}
