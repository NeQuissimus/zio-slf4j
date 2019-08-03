package nequi.zio.logger

import org.slf4j

import zio.ZIO

import sourcecode.FullName

trait Logger {
  val logger: Logger.Service[Any]
}

object Logger {
  trait Service[R] {
    def trace(a: String): ZIO[R, Nothing, Unit]
    def debug(a: String): ZIO[R, Nothing, Unit]
    def info(a: String): ZIO[R, Nothing, Unit]
    def warn(a: String): ZIO[R, Nothing, Unit]
    def error(a: String): ZIO[R, Nothing, Unit]
  }
}

trait Slf4jLogger extends Logger {
  val inner: slf4j.Logger

  val logger = new Logger.Service[Any] {
    def trace(a: String): ZIO[Any, Nothing, Unit] = ZIO.effectTotal(inner.trace(a))
    def debug(a: String): ZIO[Any, Nothing, Unit] = ZIO.effectTotal(inner.debug(a))
    def info(a: String): ZIO[Any, Nothing, Unit]  = ZIO.effectTotal(inner.info(a))
    def warn(a: String): ZIO[Any, Nothing, Unit]  = ZIO.effectTotal(inner.warn(a))
    def error(a: String): ZIO[Any, Nothing, Unit] = ZIO.effectTotal(inner.error(a))
  }
}

object Slf4jLogger {
  def create(implicit clazz: FullName) = {
    val clazzName: String = clazz.value.stripSuffix(".clazz")

    new Slf4jLogger {
      val inner = slf4j.LoggerFactory.getLogger(clazzName)
    }
  }
}
