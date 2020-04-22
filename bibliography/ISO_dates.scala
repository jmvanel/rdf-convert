import scala.io.Source

import java.text.SimpleDateFormat
import java.util.{Calendar, Date}


/** convert dates in format "14/08/1981" into ISO 8601, "1981-08-14", cf http://dataabinitio.com/?p=449
 * Usage:
 * scala ISO_dates column_dates.txt > new.txt
 *
 * or
 *
 * scala ISO_dates column_dates.txt 4 > new.txt
 *
 * where column_dates.txt is a single column with old style dates, typically copied from a spreadsheet column,
 * and pasted back after transformation through this program.
 * Be sure to check that the cells format in new column for pasting is category text, not number .
 *
 * Maybe TODO: process CSV file for given column ...
 */
object ISO_dates {

  def processSimple(filename: String): Unit = {
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

  def processDateShift(filename: String, yearsShift: Int): Unit = {
    for (line <- Source.fromFile(filename).getLines)  {
      val newLine =
      parseDate(line) match {
        case Left( (y:Int,m:Int,d) ) =>
          val calendar = addYears(y,m,d, yearsShift)
          sdf.format(calendar.getTime())
        case Right(s) => s
      }
      println(newLine)
    }
  }

  def parseDate( odlStyleDate: String): Either[(Int, Int, Int), String] = {
    val parts = odlStyleDate.split("""/""")
    if( parts.size == 3 )
      Left( (parts(2).toInt, parts(1).toInt, parts(0).toInt) )
    else
      Right(odlStyleDate)
  }


  def addYears(y:Int,m:Int,d:Int, ys:Int): Calendar = {
    val calendar = new Calendar.Builder()
                  .setCalendarType("iso8601")
      // CAUTION: the month numbering is 0-based, see https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.Builder.html#setDate-int-int-int-
                  .setDate(y,m-1,d).build()
    calendar.add(Calendar.YEAR, ys)
    // calendar.roll(Calendar.YEAR, ys)
    calendar
  }

  private val dateFmt = "yyyy-MM-dd"
  private val sdf = new SimpleDateFormat(dateFmt)

/*  def daysAgo(days: Int): String = {
    val calendar = Calendar.getInstance()
    calendar.roll(Calendar.DAY_OF_YEAR, -days)
    sdf.format(calendar.getTime())
  } */

  def main(args:Array[String]): Unit = {
    val filename = args(0)
    Console.err.println(s"args ${args.mkString(",")}")
    if ( args.size == 1 )
      processSimple(filename)
    else {
      val shift = args(1).toInt
      processDateShift(filename, args(1).toInt )
      println(s"Added $shift years to dates.")
    }
  }
}
