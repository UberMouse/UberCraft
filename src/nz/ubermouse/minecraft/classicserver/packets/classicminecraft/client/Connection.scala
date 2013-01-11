package nz.ubermouse.minecraft.classicserver.packets.classicminecraft.client

import nz.ubermouse.minecraft.classicserver.packets.{PacketRegistry, Packet}
import nz.ubermouse.minecraft.classicserver.{Player, Client, Server}
import java.io.{PrintStream, InputStreamReader, BufferedReader}
import java.nio.ByteBuffer
import nz.ubermouse.minecraft.classicserver.utils.Logger
import nz.ubermouse.minecraft.classicserver.packets.classicminecraft.server.ServerIdentification

/**
 * Created with IntelliJ IDEA.
 * User: UberMouse
 * Date: 11/01/13
 * Time: 14:45
 * Connection Packet
 * Sent by a player joining a server with relevant information. Current protocol version is 0x07.
 * Packet Structure
 * ID: 0x00
 * Protocol Version: Byte
 * Username: String
 * Verification Key: String
 * Unused: Byte
 */
class Connection(server:Server, client:Client) extends Packet(server, client) {

  val length = 131
  val dynamic  = false
  val opCode = 0x00
  val name = "PlayerConnection"

  protected def processPacket(message:Array[Byte]) {
    Logger().trace(s"Processing Connection packet from client: ${client.connection.getInetAddress.getHostAddress}", "Networking.Packets")
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
    client.player = new Player(name, key)
    client.write(new ServerIdentification(server, client))
    Logger().trace(s"Processed Connection packet from client: ${client.connection.getInetAddress.getHostAddress}", "Networking.Packets")
  }

  protected def writePacket(buffer: ByteBuffer) {}
}
