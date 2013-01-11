package nz.ubermouse.minecraft.classicserver.packets

import collection.immutable.HashMap
import nz.ubermouse.minecraft.classicserver.{Client, Server}
import classicminecraft._
import client.Connection

/**
 * Created with IntelliJ IDEA.
 * User: UberMouse
 * Date: 11/01/13
 * Time: 15:27
 * To change this template use File | Settings | File Templates.
 */
object PacketRegistry {
  private val registrationMap = Map(0x0 -> classOf[Connection])

  def apply(opCode:Byte, server:Server, client:Client) = {
    val constructor = registrationMap(opCode).getDeclaredConstructors()(0)
    constructor.newInstance(server, client).asInstanceOf[Packet]
  }
}
