package views.helper.bootstrap

import views.html

object bs {
  import views.html.helper.FieldConstructor
  implicit val myFields = FieldConstructor(html.helper.bootstrap.bsFieldConstructor.f)
}
