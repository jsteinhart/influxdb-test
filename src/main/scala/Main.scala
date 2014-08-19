import breeze.stats.distributions.Gaussian
import org.influxdb._
import com.github.nscala_time.time.Imports._

object Main {

  val n = 10000

  def main( args:Array[String]) = {


    val db = new Client( username="tester", password="tester", database="test")

    val norm = Gaussian.distribution(0,1)
    val samples = norm.sample(n)
    val walk = samples.scanLeft(0.0)(_+_).tail
    val now = DateTime.now.getMillis
    val time = now-n+1 to now


    val series = new Series("stuff",
      Array("walk","time"),
      Array(walk.toArray, time.toArray))


    //walk.foreach{println}

  }

}
