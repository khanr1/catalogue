package services.interpreter

import cats.data.Reader
import cats.{Monad, ~>, _}
import cats.implicits._
import com.typesafe.config.{Config, ConfigFactory}
import database.DatabaseComponent
import models.Category
import repository.Repository
import services.CategoryService
import slick.basic.DatabaseConfig
import slick.dbio.DBIO
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait CategoryServiceInterpreter extends CategoryService[Future,DBIO] with DatabaseComponent{

  override val evalDb: DBIO ~> Future = new  (DBIO ~> Future) {


    override def apply[A](fa: DBIO[A]): Future[A] = localDb.db.run(fa)
  }

  override val m = Monad[Future]

  

}

object CategoryServiceInterpreter extends CategoryServiceInterpreter