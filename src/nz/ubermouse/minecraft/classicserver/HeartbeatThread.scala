package nz.ubermouse.minecraft.classicserver

import java.net.URL
import utils.Base62
import util.Random
import java.io.{InputStreamReader, OutputStream, BufferedReader}

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
    while(true) {
      val conn = new URL("http://minecraft.net/heartbeat.jsp?port=25565" +
                                                           "&max=32" +
                                                           "&name=${ServerConfig.serverName}" +
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
