package nz.ubermouse.minecraft.classicserver.packets

import nz.ubermouse.minecraft.classicserver.{Client, Server}
import java.nio.ByteBuffer

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

  def process(message:Array[Byte])

  def write()
}
