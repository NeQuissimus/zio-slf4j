package nequi.zio.logger

import scalaz.Show
import scalaz.zio.IO

trait Logger {
  def trace[A: Show](a: A): IO[Throwable, Unit]
  def debug[A: Show](a: A): IO[Throwable, Unit]
  def info[A: Show](a: A): IO[Throwable, Unit]
  def warn[A: Show](a: A): IO[Throwable, Unit]
  def error[A: Show](a: A): IO[Throwable, Unit]
}
