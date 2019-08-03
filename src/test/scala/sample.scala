package sample

import nequi.zio.logger._

import scalaz.Scalaz._

import zio._

object SampleApp extends App {
  def run(args: List[String]): ZIO[Environment, Nothing, Int] = {
    val logged: ZIO[Logger, Nothing, Unit] = info(42)

    val provided: ZIO[Any, Nothing, Unit] = logged.provideSome(_ => Slf4jLogger.create)

    provided.fold(
      _ => 1,
      _ => 0
    )
  }
}
