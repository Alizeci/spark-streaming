package ejercicios.streaming.structured

import ejercicios.streaming.SparkSessionWrapper
import org.apache.spark.sql.streaming.Trigger.ProcessingTime

object StructuredKafkaStreaming extends SparkSessionWrapper {

  def main(args: Array[String]): Unit = {

    import spark.implicits._

    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "spark_sql_test_topic")
      .load()

    val values = df.selectExpr("CAST(value AS STRING)").as[String]

    values.writeStream
      .trigger(ProcessingTime("5 seconds"))
      .outputMode("append")
      .format("console")
      .start()
      .awaitTermination()
  }

}