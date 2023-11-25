package ejercicios.streaming.structured

import ejercicios.streaming.SparkSessionWrapper

object StructuredNetworkWordCount extends SparkSessionWrapper {

  def main(args: Array[String]): Unit = {
    import spark.implicits._
    val lineas = spark.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 9999)
      .load()

    val palabras = lineas.as[String].flatMap(_.split(" "))
    val numPalabras = palabras.groupBy("value").count()

    val query = numPalabras.writeStream
      .outputMode("update")
      .format("console")
      .start

    query.awaitTermination(60000)
  }

}
