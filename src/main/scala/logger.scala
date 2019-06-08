package nequi.zio.logger

import org.slf4j

import scalaz.Show
import scalaz.zio.{ Queue, UIO, ZIO }

import sourcecode.FullName

trait Logger {
  val logger: Logger.Service[Any]
}

object Logger {
  trait Service[R] {
    def trace[A: Show](a: A): ZIO[R, Nothing, Unit]
    def debug[A: Show](a: A): ZIO[R, Nothing, Unit]
    def info[A: Show](a: A): ZIO[R, Nothing, Unit]
    def warn[A: Show](a: A): ZIO[R, Nothing, Unit]
    def error[A: Show](a: A): ZIO[R, Nothing, Unit]
  }
}

trait Slf4jLogger extends Logger {
  val inner: slf4j.Logger

  val logger = new Logger.Service[Any] {
    def trace[A](a: A)(implicit S: Show[A]): ZIO[Any, Nothing, Unit] = ZIO.effectTotal(inner.trace(S.shows(a)))
    def debug[A](a: A)(implicit S: Show[A]): ZIO[Any, Nothing, Unit] = ZIO.effectTotal(inner.debug(S.shows(a)))
    def info[A](a: A)(implicit S: Show[A]): ZIO[Any, Nothing, Unit]  = ZIO.effectTotal(inner.info(S.shows(a)))
    def warn[A](a: A)(implicit S: Show[A]): ZIO[Any, Nothing, Unit]  = ZIO.effectTotal(inner.warn(S.shows(a)))
    def error[A](a: A)(implicit S: Show[A]): ZIO[Any, Nothing, Unit] = ZIO.effectTotal(inner.error(S.shows(a)))
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
