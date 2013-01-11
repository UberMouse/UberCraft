package nz.ubermouse.minecraft.classicserver.packets

import java.nio.ByteBuffer
import java.security.InvalidParameterException

/**
 * Created with IntelliJ IDEA.
 * User: UberMouse
 * Date: 11/01/13
 * Time: 15:03
 * To change this template use File | Settings | File Templates.
 */
class ByteBufferWrapper(buffer:ByteBuffer) {

  def getString() = {
    val temp = new Array[Byte](64)
    for (i <- 0 until 64)
      temp(i) = buffer.get()
    new String(temp, "US-ASCII").trim()
  }

  def putString(data:String) {
    val bytes = data.getBytes("UTF-8")
    buffer.put(bytes)
  }

  def put(data:Int) {
    if(data > Byte.MaxValue || data < Byte.MinValue)
      throw new InvalidParameterException("data integer value does not fit within a Byte")
    buffer.put(data.asInstanceOf[Byte])
  }
}
