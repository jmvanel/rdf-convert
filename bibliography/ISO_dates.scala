import scala.io.Source

/** convert dates in format "14/08/1981" into ISO 8601, "1981-08-14", cf http://dataabinitio.com/?p=449
 * Usage:
 * scala ISO_dates column_dates.txt > new.txt
 *
 * where column_dates.txt is a single column with old style dates, typically copied from a spreadsheet column,
 * and pasted back after transformation through this program.
 * Be sure to check that the cells format in new column for pasting is category text, not number .
 *
 * Maybe TODO: process CSV file for given column ...
 */
object ISO_dates {

  def process(filename: String): Unit = {

    for (line <- Source.fromFile(filename).getLines)  {
      Console.err.println(line)
      val newLine = makeISOdate(line)
      println(newLine)
    }
  }

  def makeISOdate( odlStyleDate: String): String = {
      val parts = odlStyleDate.split("""/""")
      if( parts.size == 3 ) {
        parts(2) + "-" + parts(1) + "-" + parts(0)
      } else
        odlStyleDate
  }

  def main(args:Array[String]): Unit = {
    val filename = args(0)
    process(filename)
  }
}
