package nz.ubermouse.minecraft.classicserver

import java.net.{URLEncoder, URL}
import utils.Base62
import util.Random
import java.io.{InputStreamReader, BufferedReader}
import ServerConfig.port

/**
 * Created with IntelliJ IDEA.
 * User: UberMouse
 * Date: 11/01/13
 * Time: 00:34
 * To change this template use File | Settings | File Templates.
 */
class HeartbeatThread extends Runnable {

  val salt = Base62.encode(Math.abs(Random.nextLong()))

  def run() {
    while(Server.isConnected) {
      val conn = new URL(s"http://minecraft.net/heartbeat.jsp?port=${ServerConfig.port}" +
                                                           s"&max=${ServerConfig.maxPlayers}" +
                                                           s"&name=${URLEncoder.encode(ServerConfig.serverName, "UTF-8")}" +
                                                           "&public=True" +
                                                           "&version=7" +
                                                           "&salt=$salt" +
                                                           "&users=1")
                     .openConnection()
      conn.setDoOutput(true)
      val reader = new BufferedReader(new InputStreamReader(conn.getInputStream))
      while(reader.ready()) {
        val line = reader.readLine()
        if (line.matches("http://minecraft.net/classic/play/[a-zA-Z0-9]*"))
          ServerInformation.serverUrl = line
        println(line)
      }

      Thread.sleep(90000)
    }
  }
}
