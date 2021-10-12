package repository

import cats.data.Reader
import models.Category
import models.common.ID
import play.libs.F

trait CategoryRepository[F[_]] extends Repository[F,Category]{
  def getMainCategory:F[Seq[Category]]

}

