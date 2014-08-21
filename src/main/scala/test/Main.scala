package test

import breeze.stats.distributions.Gaussian
import org.influxdb._
import com.github.nscala_time.time.Imports._
import com.typesafe.scalalogging.slf4j._

import scala.util.parsing.json.JSON
import org.json4s.jackson.Serialization
import org.json4s.NoTypeHints
import org.json4s.jackson.Serialization._

object Main extends LazyLogging {

  implicit val formats = Serialization.formats(NoTypeHints)

  val n = 1000*60*5 //5 minutes

  def main( args:Array[String]):Unit = {

    logger.debug("about to initialize client")
    val db = new Client( username="bpr", password="BprBpr", database="bpr")
    logger.debug("client initialized")


    val (resp, err) = db.query("DROP SERIES stuff")
    logger.debug("response: " + resp)
    logger.debug("error: " + err)


    val norm = Gaussian.distribution(0,1)
    val samples = norm.sample(n)
    val walk:Array[Any] = samples.scanLeft(0.0)(_+_).tail.toArray

    val now = DateTime.now.getMillis
    val time:Array[Any] = (now-n+1 to now).toArray

    val data:Array[Array[Any]] = (0 to n-1).map{i => Array[Any](walk(i), time(i))}.toArray


    val series = new Series("stuff",
      Array("walk","time"),
      data)

    val tosubmit = Array(series)

    //val payload = write(tosubmit)

    //logger.trace("Series to submit" + payload)


    logger.debug("about to write series")
    db.writeSeriesWithTimePrecision(tosubmit, "ms").foreach{logger.error(_)}

    db.close()
    //walk.foreach{println}

  }

}
