/** Here is a simple Markdown to CSV script ; obviously it is bad style but it works ...
*/
object md2csv extends App {
  val lines = scala.io.Source.fromFile(args(0)).getLines.toSeq
  var tit1 = "" ; var tit2 = "" ; var tit3 = ""
  for (l <- lines) {
    if (l.startsWith("# ")) {tit1 = l}
    else if (l.startsWith("## ")) {tit2 = l}
    else if (l.startsWith("### ")) {tit3 = l }
    else println( s"$tit1, $tit2, $tit3, $l") }
}

