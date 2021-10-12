package models

import akka.stream.Attributes.Name
import models.common.ID
import play.api.libs.json._



case class Category(name:String,parentCategory:Option[ID[Category]],id: ID[Category]=ID[Category](0L))

object Category{
  //implicit val config = JsonConfiguration(optionHandlers = OptionHandlers.WritesNull)
  implicit val categoryFormat=Json.format[Category]
}