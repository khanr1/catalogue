import com.typesafe.config.{Config, ConfigFactory}
import models.Category
import models.common.ID
import play.api.data.{Form, FormError, Forms, Mapping}
import play.api.data.Forms._
import play.api.data.format.Formatter
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

import scala.util.Try
val config: Config = ConfigFactory.load()
val db = DatabaseConfig.forConfig[JdbcProfile]("slick.dbs.default",config)

val ceci =ID[Category](5L)
ID.unapply(ceci)

val some=Some(3)==Some(3)

"5".toLong

implicit def IDFormatter[A]: Formatter[ID[A]] =new Formatter[ID[A]]{
  override def bind(key: String, data: Map[String, String]): Either[Seq[FormError], ID[A]] =
    data.get(key).map{ value =>
      Try{
        Right(ID[A](value.toLong))
      } getOrElse Left(Seq(FormError(key,"Error.Long",Nil)))
    } getOrElse Left(Seq(FormError(key,"Error.required",Nil)))

  override def unbind(key: String, id: ID[A]): Map[String, String] = Map(key->id.value.toString)
}

val categoryMapping: Mapping[Category] =mapping(
  "name"-> text,
  "parentID"->optional(of[ID[Category]]),
  "id"->default(of[ID[Category]], ID[Category](-1L))
)(Category.apply)(Category.unapply)

val categoryForm=Form(categoryMapping)

val categoryForm: Form[Category]= Form(
  mapping(
    "name" -> nonEmptyText,
    "parentCategory" -> optional(of[ID[Category]]),
    "id" -> default(of[ID[Category]], ID[Category](-1L))
  )(Category.apply)(Category.unapply)
)

val anyData=Map("name"->"test","parentID"->"5")
val CategoryData=categoryForm.bind(anyData).get
