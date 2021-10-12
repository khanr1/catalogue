package controllers

import models.Category
import models.common.ID
import play.api.data.Forms._
import play.api.data._
import play.api.data.format.Formatter
import play.api.libs.json.Json
import play.api.mvc._
import repository.interpreter.SlickCategoryRepositoryImplementation
import services.interpreter.CategoryServiceInterpreter

import javax.inject._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Try
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class CategoryController @Inject()(val controllerComponents: ControllerComponents) extends BaseController with play.api.i18n.I18nSupport{


  implicit def LongFormatter[A]: Formatter[Long] =new Formatter[Long]{
    override def bind(key: String, data: Map[String, String]): Either[Seq[FormError], Long] =
      data.get(key).map{ value =>
        Try{
          Right(value.toLong)
        } getOrElse Left(Seq(FormError(key,"Error.Long",Nil)))
    } getOrElse Left(Seq(FormError(key,"Error.ID",Nil)))

    override def unbind(key: String, id: Long): Map[String, String] = Map(key->id.toString)
  }

  implicit def IDFormatter[A]: Formatter[ID[A]] =new Formatter[ID[A]]{
    override def bind(key: String, data: Map[String, String]): Either[Seq[FormError], ID[A]] =
      data.get(key).map{ value =>
        Try{
          Right(ID[A](value.toLong))
        } getOrElse Left(Seq(FormError(key,"Error.Long",Nil)))
      } getOrElse Left(Seq(FormError(key,"Error.required",Nil)))

    override def unbind(key: String, id: ID[A]): Map[String, String] = Map(key->id.value.toString)
  }


  private val categoryForm: Form[Category]= Form(
    mapping(
      "name" -> nonEmptyText,
      "parentCategory" -> optional(of[ID[Category]]),
      "id" -> default(of[ID[Category]], ID[Category](-1L))
    )(Category.apply)(Category.unapply)
  )



  def listCategories: Action[AnyContent] =Action.async{ implicit request =>
    val categories: Future[Seq[Category]] =CategoryServiceInterpreter.listAll(SlickCategoryRepositoryImplementation)
    categories.map(x=>Ok(views.html.category.categoryList(x)))
    //categories.map(x=> Ok(Json.toJson(x)))
  }

  def listData: Action[AnyContent] =Action.async{ implicit request =>
    val categories: Future[Seq[Category]] =CategoryServiceInterpreter.listAll(SlickCategoryRepositoryImplementation)
    categories.map(x=> Ok(Json.toJson(x)))
  }

  def createCategory=Action.async{ implicit request =>
    Future.successful(Ok(views.html.category.createCategoryForm(categoryForm)))

  }

  def saveCategory=Action(parse.json){ implicit request =>
    val categoryJson= request.body.validate[Category]
    categoryJson.fold(
      errors => {
        BadRequest("error")
      },
      category=> {
        Ok("Good")
      }
    )


//      categoryForm.bindFromRequest().fold(
//        formWithError=> Future.successful(BadRequest(views.html.category.createCategoryForm(formWithError))),
//        category     => Future(Ok("created "+ category))
//      )

  }









  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
}
