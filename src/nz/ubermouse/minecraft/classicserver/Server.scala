package nz.ubermouse.minecraft.classicserver

/**
 * Created with IntelliJ IDEA.
 * User: UberMouse
 * Date: 11/01/13
 * Time: 14:40
 * To change this template use File | Settings | File Templates.
 */
class Server {

  val heartbeatThread = new Thread(new HeartbeatThread)
  val connectionThread = new Thread(new ConnectionThread)

  heartbeatThread.start()
  connectionThread.start()
}
