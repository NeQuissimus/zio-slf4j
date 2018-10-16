package nequi.zio.logger

import com.github.ghik.silencer.silent

import utest._

object Slf4j extends TestSuite {
  val tests: Tests = Tests {
    "Must allow for extraction of slf4j.Logger" - innerLogger
  }

  def innerLogger(): Unit = {
    @silent object MustCompile extends Slf4jLogger {
      val clazz               = implicitly
      val a: org.slf4j.Logger = logger.inner
    }
  }
}
