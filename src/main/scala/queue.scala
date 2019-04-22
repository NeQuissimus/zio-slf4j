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
    def trace[A](a: A)(implicit S: Show[A]): IO[Nothing, Unit] =
      IO.effectTotal(queue.offer((Trace, S.shows(a)))).unit
    def debug[A](a: A)(implicit S: Show[A]): IO[Nothing, Unit] =
      IO.effectTotal(queue.offer((Debug, S.shows(a)))).unit
    def info[A](a: A)(implicit S: Show[A]): IO[Nothing, Unit] = IO.effectTotal(queue.offer((Info, S.shows(a)))).unit
    def warn[A](a: A)(implicit S: Show[A]): IO[Nothing, Unit] = IO.effectTotal(queue.offer((Warn, S.shows(a)))).unit
    def error[A](a: A)(implicit S: Show[A]): IO[Nothing, Unit] =
      IO.effectTotal(queue.offer((Error, S.shows(a)))).unit
  }
}
