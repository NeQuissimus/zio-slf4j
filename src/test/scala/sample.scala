package sample

import nequi.zio.logger._

import zio._

// Very simple ZIO app
object SampleApp extends App {
  def run(args: List[String]): URIO[Any, ExitCode] = {

    // Some logic that logs
    def logged(i: Int): ZIO[Logger, Nothing, Int] = info(s"Got ${i}") *> ZIO.effectTotal(i + 2)

    // Provide a standard Slf4j logger
    val provided: ZIO[Any, Nothing, Int] = logged(42).provideSome(_ => Slf4jLogger.create)

    // Adjust app exit code to signal success
    provided.map(_ => ExitCode.success)
  }
}
