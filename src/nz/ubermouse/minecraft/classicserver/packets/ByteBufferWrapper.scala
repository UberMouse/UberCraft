package nz.ubermouse.minecraft.classicserver.packets

import java.nio.ByteBuffer

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
}
