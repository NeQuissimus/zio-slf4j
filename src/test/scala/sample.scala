package sample

import nequi.zio.logger._

import sourcecode.FullName

import scalaz._
import scalaz.zio._

import Scalaz._

object Sample extends App with Slf4jLogger with SampleApp {
  val clazz: FullName = implicitly
}

trait SampleApp { self: App with Slf4jLogger =>
  def run(args: List[String]): ZIO[Environment, Nothing, Int] = (
    info("Hello World") *>
    error("Oh oh!") *>
    ???
  ).fold(
    _ => 1,
    _ => 0
  )
}
