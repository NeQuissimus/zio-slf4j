package nequi.zio.logger

import scalaz.Show
import scalaz.zio.{ IO, UIO }

trait Logger {
  def trace[A: Show](a: A): UIO[Unit]
  def debug[A: Show](a: A): UIO[Unit]
  def info[A: Show](a: A): UIO[Unit]
  def warn[A: Show](a: A): UIO[Unit]
  def error[A: Show](a: A): UIO[Unit]
}
