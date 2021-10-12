package services


import cats.{Monad, ~>}
import cats.data.Reader
import models.common.ID
import repository.Repository
/** This trait defines all the behaviour that all services should implement to be a service
 *
 * @tparam F[_] abstract away from concrete monad use methods result types wrapper  such as Future.
 * @tparam DbEffect[_] abstract over a concrete "monad"  of the repository layer*/
trait Service[F[_],DbEffect[_],Something] {
  /* forcing F to be a  monad */
  implicit def m: Monad[F]


  def evalDb:DbEffect~>F
  def create(something: Something):Reader[Repository[DbEffect,Something],F[Something]]
  def read(id:ID[Something]):Reader[Repository[DbEffect,Something],F[Option[Something]]]
  def listAll:Reader[Repository[DbEffect,Something],F[Seq[Something]]]
}
