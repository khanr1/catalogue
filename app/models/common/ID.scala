package models.common

import models.Category
import play.api.libs.json.Json
import slick.lifted.MappedTo

case class ID[A](value:Long) extends AnyVal with  MappedTo[Long]

object ID{
  implicit def IDFormat[A]=Json.format[ID[A]]
}
