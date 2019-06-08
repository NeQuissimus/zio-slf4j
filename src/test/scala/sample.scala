package sample

import nequi.zio.logger._

import scalaz._
import scalaz.zio._

import Scalaz._

object SampleApp extends App {
  def run(args: List[String]): ZIO[Environment, Nothing, Int] = {
    val logged: ZIO[Logger, Nothing, Unit] = info("Hello World")

    val provided: ZIO[Any, Nothing, Unit] = logged.provideSome(_ => Slf4jLogger.create)

    provided.fold(
      _ => 1,
      _ => 0
    )
  }
}
