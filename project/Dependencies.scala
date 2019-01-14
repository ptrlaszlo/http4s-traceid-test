import sbt._

object Dependencies {
  object Version {
    lazy val http4s             = "0.20.0-M4"
    lazy val ficus              = "1.4.3"
    lazy val logback            = "1.2.3"
    lazy val logstash           = "4.11"
    lazy val janino             = "3.0.8"
    lazy val kamon              = "1.1.3"
    lazy val kamonHttp4s        = "1.0.11"
    lazy val kamonExecutors     = "1.0.2"
    lazy val kamonSystemMetrics = "1.0.0"
    lazy val kamonPrometheus    = "1.1.1"
    lazy val kamonLogback       = "1.0.2"
  }

  lazy val http4s = Seq(
    "org.http4s"                       %% "http4s-dsl"                  % Version.http4s,
    "org.http4s"                       %% "http4s-blaze-server"         % Version.http4s,
    "org.http4s"                       %% "http4s-blaze-client"         % Version.http4s,
    "org.http4s"                       %% "http4s-circe"                % Version.http4s
  )

  lazy val logging = Seq(
    "com.iheart"                      %% "ficus"                        % Version.ficus,
    "ch.qos.logback"                   %  "logback-classic"             % Version.logback  % Runtime,
    "net.logstash.logback"             %  "logstash-logback-encoder"    % Version.logstash,
    "org.codehaus.janino"              %  "janino"                      % Version.janino   % Runtime
  )

  lazy val monitoring = Seq(
    "io.kamon"                         %% "kamon-core"                  % Version.kamon,
    "io.kamon"                         %% "kamon-http4s"                % Version.kamonHttp4s,
    "io.kamon"                         %% "kamon-executors"             % Version.kamonExecutors,
    "io.kamon"                         %% "kamon-system-metrics"        % Version.kamonSystemMetrics,
    "io.kamon"                         %% "kamon-prometheus"            % Version.kamonPrometheus,
    "io.kamon"                         %% "kamon-logback"               % Version.kamonLogback
  )

  lazy val all = http4s ++ logging ++ monitoring
}
