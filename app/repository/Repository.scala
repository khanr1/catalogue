package repository

import models.common.ID

/** Base Repository type
 * @tparam F the repository effect wrapper
 * @tparam Something the object the repository is made for*/
trait Repository[F[_],Something] {
  /** Create a new record of something
   * @param something
   * @return */
  def insert(something: Something):F[Something]

    /** Retrieve a record from the database with the corresponding id.
    *
    * @param id
    * @return
    */
  def query(id:ID[Something]):F[Option[Something]]

  /** List all the  records from the database.
   *
   * @param id
   * @return
   */
  def getAll:F[Seq[Something]]




}
