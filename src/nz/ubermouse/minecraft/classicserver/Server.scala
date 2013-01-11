package nz.ubermouse.minecraft.classicserver

import collection.mutable.ListBuffer
import utils.Logger

/**
 * Created with IntelliJ IDEA.
 * User: UberMouse
 * Date: 11/01/13
 * Time: 14:40
 * To change this template use File | Settings | File Templates.
 */
class Server {
  new Logger("output", "MinecraftClassicServer", Logger.LOGGING_MODE_ALL, "log")
  val clients = new ListBuffer[Client]

  val heartbeatThread = new Thread(new HeartbeatThread)
  val connectionThread = new Thread(new ConnectionThread)
  heartbeatThread.start()
  connectionThread.start()

  def addClient(client:Client) {
    Logger().trace(s"Adding client: ${client.connection.getInetAddress.getHostAddress}", "Networking")
    synchronized {
      clients += client
    }
  }
}

object Server {
  def isConnected = true
}
