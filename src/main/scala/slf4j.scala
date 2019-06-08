package nequi.zio.logger

import org.slf4j

import scalaz.Show
import scalaz.zio.{ IO, UIO }

trait InnerLogger[T] {
  def inner: T
}

trait Slf4jLogger {
  def clazz: sourcecode.FullName

  lazy val clazzName: String = clazz.value.stripSuffix(".clazz")

  implicit lazy val logger: Logger with InnerLogger[slf4j.Logger] = new Logger with InnerLogger[slf4j.Logger] {
    val inner: slf4j.Logger = slf4j.LoggerFactory.getLogger(clazzName)

    def trace[A](a: A)(implicit S: Show[A]): UIO[Unit] = IO.effectTotal(inner.trace(S.shows(a)))
    def debug[A](a: A)(implicit S: Show[A]): UIO[Unit] = IO.effectTotal(inner.debug(S.shows(a)))
    def info[A](a: A)(implicit S: Show[A]): UIO[Unit]  = IO.effectTotal(inner.info(S.shows(a)))
    def warn[A](a: A)(implicit S: Show[A]): UIO[Unit]  = IO.effectTotal(inner.warn(S.shows(a)))
    def error[A](a: A)(implicit S: Show[A]): UIO[Unit] = IO.effectTotal(inner.error(S.shows(a)))
  }
}
