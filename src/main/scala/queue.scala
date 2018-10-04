package nequi.zio.logger

import java.util.ArrayDeque

import scalaz.Show
import scalaz.zio.IO

trait QueueLogger {
  sealed trait Level
  case object Trace extends Level
  case object Debug extends Level
  case object Info  extends Level
  case object Warn  extends Level
  case object Error extends Level

  val queue = new ArrayDeque[(Level, String)]

  implicit lazy val logger: Logger = new Logger {
    def trace[A](a: A)(implicit S: Show[A]): IO[Throwable, Unit] =
      IO.syncThrowable(queue.offer((Trace, S.shows(a)))).void
    def debug[A](a: A)(implicit S: Show[A]): IO[Throwable, Unit] =
      IO.syncThrowable(queue.offer((Debug, S.shows(a)))).void
    def info[A](a: A)(implicit S: Show[A]): IO[Throwable, Unit] = IO.syncThrowable(queue.offer((Info, S.shows(a)))).void
    def warn[A](a: A)(implicit S: Show[A]): IO[Throwable, Unit] = IO.syncThrowable(queue.offer((Warn, S.shows(a)))).void
    def error[A](a: A)(implicit S: Show[A]): IO[Throwable, Unit] =
      IO.syncThrowable(queue.offer((Error, S.shows(a)))).void
  }
}
