import scala.io.Source

/** convert dates in format "14/08/1981" into ISO 8601, "1981-08-14", cf http://dataabinitio.com/?p=449
 * Usage:
 * scala ISO_dates column_dates.txt > new.txt
 */
object ISO_dates {

  def process(filename: String): Unit = {

    for (line <- Source.fromFile(filename).getLines)  {
      Console.err.println(line)
      val parts = line.split("""/""")
      val newLine = if( parts.size == 3 ) {
        parts(2) + "-" + parts(1) + "-" + parts(0)
      } else {
        line
      }
      println(newLine)
    }
  }

  def main(args:Array[String]): Unit = {
    val filename = args(0)
    process(filename)
  }
}
