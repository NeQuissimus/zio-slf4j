package nequi.zio.logger

import org.slf4j

import scalaz.Show
import scalaz.zio.IO

trait Slf4jLogger {
  def clazz: sourcecode.FullName

  lazy val clazzName: String = clazz.value.stripSuffix(".clazz")

  implicit lazy val logger: Logger = new Logger {
    val inner: slf4j.Logger = slf4j.LoggerFactory.getLogger(clazzName)

    def trace[A](a: A)(implicit S: Show[A]): IO[Throwable, Unit] = IO.syncThrowable(inner.trace(S.shows(a)))
    def debug[A](a: A)(implicit S: Show[A]): IO[Throwable, Unit] = IO.syncThrowable(inner.debug(S.shows(a)))
    def info[A](a: A)(implicit S: Show[A]): IO[Throwable, Unit]  = IO.syncThrowable(inner.info(S.shows(a)))
    def warn[A](a: A)(implicit S: Show[A]): IO[Throwable, Unit]  = IO.syncThrowable(inner.warn(S.shows(a)))
    def error[A](a: A)(implicit S: Show[A]): IO[Throwable, Unit] = IO.syncThrowable(inner.error(S.shows(a)))
  }
}
