package test.utils

import java.util.UUID

import kamon.http4s.DefaultNameGenerator
import org.http4s.Request

import scala.util.Try

class KamonNameGenerator extends DefaultNameGenerator {

  def isValidUUID(s: String): Boolean = Try(UUID.fromString(s)).isSuccess

  override def generateHttpClientOperationName[F[_]](request: Request[F]): String = {
    val authority = request.uri.authority.map(_.toString).getOrElse("")

    val path = request.uri.path.split("/").filter(_.nonEmpty).toList match {
      case other => other.mkString("/", "/", "")
    }

    authority + path
  }
}
