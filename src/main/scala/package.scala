package nequi.zio

import scalaz.Show

import zio.ZIO

package object logger extends Logger.Service[Logger] {
  implicit class ScalazLogger(private val logger: Logger.Service[Logger]) {
    final def trace[A](a: A)(implicit S: Show[A]): ZIO[Logger, Nothing, Unit] = logger.trace(S.shows(a))
    final def debug[A](a: A)(implicit S: Show[A]): ZIO[Logger, Nothing, Unit] = logger.debug(S.shows(a))
    final def info[A](a: A)(implicit S: Show[A]): ZIO[Logger, Nothing, Unit]  = logger.info(S.shows(a))
    final def warn[A](a: A)(implicit S: Show[A]): ZIO[Logger, Nothing, Unit]  = logger.warn(S.shows(a))
    final def error[A](a: A)(implicit S: Show[A]): ZIO[Logger, Nothing, Unit] = logger.error(S.shows(a))
  }

  final def trace(a: String): ZIO[Logger, Nothing, Unit] = ZIO.accessM(_.logger.trace(a))
  final def debug(a: String): ZIO[Logger, Nothing, Unit] = ZIO.accessM(_.logger.debug(a))
  final def info(a: String): ZIO[Logger, Nothing, Unit]  = ZIO.accessM(_.logger.info(a))
  final def warn(a: String): ZIO[Logger, Nothing, Unit]  = ZIO.accessM(_.logger.warn(a))
  final def error(a: String): ZIO[Logger, Nothing, Unit] = ZIO.accessM(_.logger.error(a))
}
