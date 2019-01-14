package test

import cats.effect._
import cats.implicits._
import test.Configuration.HttpTimeoutConfig
import kamon.http4s.middleware._
import org.http4s.HttpRoutes
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.blaze._
import org.http4s.server.middleware.Timeout
import org.http4s.dsl.io._
import test.logging.Logging
import scala.concurrent.ExecutionContext

object Main extends IOApp with Logging {

  val ec = new ExecutionContextAware(ExecutionContext.global)

  override def run(args: List[String]): IO[ExitCode] = {
    val config = Configuration()
    val stream = for {
      exitCode            <- BlazeServerBuilder[IO]
        .bindHttp(9000, "0.0.0.0")
        .withHttpApp(configuredHttpService(Router("/" -> endpoints), config.httpConfig).orNotFound)
        .withIdleTimeout(config.httpConfig.idleTimeout)
        .withExecutionContext(ec)
        .serve
    } yield exitCode
    stream.compile.drain.as(ExitCode.Success)
  }

  private def configuredHttpService(service: HttpRoutes[IO], timeoutConfig: HttpTimeoutConfig): HttpRoutes[IO] =
    server.KamonSupport(
      Timeout(timeoutConfig.requestTimeout)(service)
    )

  def endpoints: HttpRoutes[IO] =
    HttpRoutes.of {
      case GET -> Root / "test" =>
        logger.info("Log test")
        Ok("test")
    }
}

class ExecutionContextAware(underlying: ExecutionContext) extends ExecutionContext {
  override def execute(runnable: Runnable): Unit = underlying.execute(new RunnableContextAware(runnable))
  override def reportFailure(cause: Throwable): Unit = underlying.reportFailure(cause)
}

class RunnableContextAware(underlying: Runnable) extends Runnable {
  private val traceContext = kamon.Kamon.currentContext

  override def run(): Unit =
    kamon.Kamon.withContext(traceContext) {
      underlying.run()
    }
}
