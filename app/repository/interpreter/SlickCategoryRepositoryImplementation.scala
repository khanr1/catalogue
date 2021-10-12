package repository.interpreter

import cats.data.AndThen
import com.typesafe.config.{Config, ConfigFactory}
import database.DatabaseComponent
import models.Category
import models.common.ID
import repository.CategoryRepository
import slick.basic.DatabaseConfig
import slick.dbio.{DBIO, Effect}
import slick.jdbc.JdbcProfile
import slick.sql.FixedSqlAction



trait SlickCategoryRepositoryImplementation extends CategoryRepository[DBIO] with DatabaseComponent {


  import databaseProfile.api._

  private class Categories(tag:Tag) extends Table[Category](tag,"CATEGORY"){
    def name=column[String]("NAME")
    def parentId=column[Option[ID[Category]]]("PARENT_CATEGORY")
    def id=column[ID[Category]]("ID",O.PrimaryKey,O.AutoInc)

    override def * = (name,parentId,id) <> ((Category.apply _).tupled, Category.unapply)
  }

  private val CategoryTable=TableQuery[Categories]






  override def insert(category: Category): databaseProfile.api.DBIO[Category] = {
    val name: String =category.name
    val parentID: Option[ID[Category]] =category.parentCategory
    (CategoryTable returning CategoryTable.map(_.id) into((cat,id)=>cat.copy(id=id)))+=Category(name,parentID)

  }
  override def query(id: ID[Category]): databaseProfile.api.DBIO[Option[Category]] = CategoryTable.filter(_.id===id).result.headOption
  override def getAll : databaseProfile.api.DBIO[Seq[Category]]= CategoryTable.result

  override def getMainCategory:databaseProfile.api.DBIO[Seq[Category]] = CategoryTable.filter(_.parentId.isDefined).result


}

object SlickCategoryRepositoryImplementation extends SlickCategoryRepositoryImplementation