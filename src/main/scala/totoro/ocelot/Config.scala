package totoro.ocelot

import java.io.File

import com.typesafe.config.ConfigFactory

/**
  * App configuration hub
  * Loads configuration from `ocelot.conf` file or uses defaut values
  */

object Config {
  private val configFile = new File("ocelot.conf")
  private val config = if (configFile.exists())
    try {
      ConfigFactory.parseFile(configFile)
    } catch {
      case c: Throwable =>
        println("Incorrect configuration file!")
        c.printStackTrace()
        println("Using default values...")
        null
    }
  else {
    println("No configuration file found!\nUsing default values...")
    null
  }

  val Host: String = getString("http.host", "0.0.0.0")
  val Port: Int = getInt("http.port", 37107)

  private def getString(path: String, default: String): String = {
    if (config != null) config.getString(path)
    else default
  }
  private def getInt(path: String, default: Int): Int = {
    if (config != null) config.getInt(path)
    else default
  }
}
