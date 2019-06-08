package nequi.zio.logger

import scalaz.Show
import scalaz.zio.{ IO, Task }

private[logger] trait Logging {
  def trace[A: Show](a: A)(implicit L: Logger): Task[Unit] = L.trace(a)
  def debug[A: Show](a: A)(implicit L: Logger): Task[Unit] = L.debug(a)
  def info[A: Show](a: A)(implicit L: Logger): Task[Unit]  = L.info(a)
  def warn[A: Show](a: A)(implicit L: Logger): Task[Unit]  = L.warn(a)
  def error[A: Show](a: A)(implicit L: Logger): Task[Unit] = L.error(a)
}
