package nequi.zio.logger

import scalaz.Show
import scalaz.zio.{ console, IO }

trait ConsoleLogger {
  implicit lazy val logger: Logger = new Logger {
    private def log[A](level: String)(a: A)(implicit S: Show[A]): IO[Throwable, Unit] =
      console.putStrLn(s"[${level}] ${S.shows(a)}")

    def trace[A](a: A)(implicit S: Show[A]): IO[Throwable, Unit] = log("TRACE")(a)
    def debug[A](a: A)(implicit S: Show[A]): IO[Throwable, Unit] = log("DEBUG")(a)
    def info[A](a: A)(implicit S: Show[A]): IO[Throwable, Unit]  = log("INFO")(a)
    def warn[A](a: A)(implicit S: Show[A]): IO[Throwable, Unit]  = log("WARN")(a)
    def error[A](a: A)(implicit S: Show[A]): IO[Throwable, Unit] = log("ERROR")(a)
  }
}
