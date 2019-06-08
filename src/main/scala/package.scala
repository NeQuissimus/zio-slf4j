package nequi.zio

import scalaz.Show
import scalaz.zio.ZIO

package object logger extends Logger.Service[Logger] {
  final def trace[A: Show](a: A): ZIO[Logger, Nothing, Unit] = ZIO.accessM(_.logger.trace(a))
  final def debug[A: Show](a: A): ZIO[Logger, Nothing, Unit] = ZIO.accessM(_.logger.debug(a))
  final def info[A: Show](a: A): ZIO[Logger, Nothing, Unit]  = ZIO.accessM(_.logger.info(a))
  final def warn[A: Show](a: A): ZIO[Logger, Nothing, Unit]  = ZIO.accessM(_.logger.warn(a))
  final def error[A: Show](a: A): ZIO[Logger, Nothing, Unit] = ZIO.accessM(_.logger.error(a))
}
