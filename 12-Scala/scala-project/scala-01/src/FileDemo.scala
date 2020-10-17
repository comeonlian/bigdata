import scala.io.Source

object FileDemo {

  def main(args: Array[String]): Unit = {
    val content = Source.fromFile("F:/Java-Dev/taobao-it18/11-Scala/hello.txt");
    val lines = content.getLines();
    for (line <- lines) {
      println(line)
    }

  }

}
