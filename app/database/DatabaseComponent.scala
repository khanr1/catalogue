package database

import com.typesafe.config.{Config, ConfigFactory}
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

trait DatabaseComponent {
  //** load the configuration"
  private val config: Config = ConfigFactory.load()
  /** load the database with configuraton name name */
  val localDb: DatabaseConfig[JdbcProfile] =DatabaseConfig.forConfig[JdbcProfile]("slick.dbs.default",config)
  val databaseProfile:JdbcProfile=localDb.profile

}
