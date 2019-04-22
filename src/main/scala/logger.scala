package nequi.zio.logger

import scalaz.Show
import scalaz.zio.IO

trait Logger {
  def trace[A: Show](a: A): IO[Nothing, Unit]
  def debug[A: Show](a: A): IO[Nothing, Unit]
  def info[A: Show](a: A): IO[Nothing, Unit]
  def warn[A: Show](a: A): IO[Nothing, Unit]
  def error[A: Show](a: A): IO[Nothing, Unit]
}
