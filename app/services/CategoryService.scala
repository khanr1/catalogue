package services

import cats.data.Reader
import cats.{FlatMap, Monad, ~>}
import models.Category
import models.common.ID

import repository.Repository


trait CategoryService[F[_],DbEffect[_]] extends Service[F,DbEffect,Category] {

  def evalDb:DbEffect~>F
  /** Create a category
   * @param category
   * @return */
  def create(category: Category):Reader[Repository[DbEffect,Category],F[Category]]= {
    Reader( (repo:Repository[DbEffect,Category])=> evalDb(repo.insert(category)))
  }
  /** Read infomation related to the category with ID id:
   * @param id
   * @return */
  def read(id:ID[Category]): Reader[Repository[DbEffect,Category],F[Option[Category]]] = {
    Reader((repo:Repository[DbEffect,Category])=> evalDb(repo.query(id)))
  }
  /** return the name of a category with ID id
   * @param id
   * @return */
  def getName(id:ID[Category]):Reader[Repository[DbEffect,Category],F[Option[String]]]=read(id).map(f=>m.map(f)(o=>o.map(_.name)))
  /** return the parent ID of a category of ID id
   * @param id
   * @return */
  def getParentID(id:ID[Category]):Reader[Repository[DbEffect,Category],F[Option[ID[Category]]]]=read(id).map(f=>m.map(f)(o=>o.flatMap(_.parentCategory)))
  /** return all the category */
  def listAll: Reader[Repository[DbEffect, Category], F[Seq[Category]]] = Reader((repo:Repository[DbEffect,Category])=> evalDb(repo.getAll))
  /** return all the main category */
  def listAllMainCategory:Reader[Repository[DbEffect, Category], F[Seq[Category]]]=listAll.map(f=>m.map(f)(list=>list.filter(c=> !(c.parentCategory.isDefined))))
  /** Return all the child of a Category with ID id
   * @param id
   * @return */
  def listAllChilds(id:ID[Category]):Reader[Repository[DbEffect, Category], F[Seq[Category]]]=listAll.map(f=>m.map(f)(s=>s.filter(c=>c.parentCategory.contains(id))))






}
