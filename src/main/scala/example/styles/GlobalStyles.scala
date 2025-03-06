package example.styles

import com.raquo.laminar.api.L.{HtmlElement, Mod, cls}
import scalacss.ProdDefaults.*
import scalacss.internal.mutable.GlobalRegistry
import scala.concurrent.duration.*

import scala.language.{implicitConversions, postfixOps}

object GlobalStyles extends StyleSheet.Inline {

  import dsl.*

    implicit def applyStyle(styleA: StyleA): Mod[HtmlElement] =
      cls(styleA.className.value)

  val body: StyleA = style("body") (
    margin(0 px),
    height(1000 px)
  )

  val header: StyleA = style("header")(
    color := "#F9F7F7",
    backgroundColor := "#379080",
    margin.auto,
    fontSize(56 px),
    display.flex,
    flexDirection.row,
    justifyContent.flexStart,
    fontFamily:=!"Kablammo",
    paddingLeft(20 px)
  )

//  val backgroundImage: StyleA = style("backgroundImage")(
//    images
//  )

  val homeCardContainer: StyleA = style("homeCardContainer")(
    display.flex,
    flexDirection.row,
    justifyContent.spaceAround,
    paddingBottom(100 px)
  )

  val homeCardTitle: StyleA = style("homeCardTitle")(
    color := "#222020",
    fontSize(18 px),
    margin.auto
  )

  val homeCardButton: StyleA = style("homeCardButton")(
    backgroundColor := "#87CB9E",
    // padding(1.rem, 1.em),
    padding(29 px),
    borderRadius(18 px),
    width(358 px),
    borderColor :=! "#296B5F",
    borderWidth :=! 12.px,
    border solid,
//    borderStyle :=! "dotted"
  )
  //how to do border color


  val anchorTag: StyleA = style("anchorTag")(
    textDecoration := "none"
  )

  val HomeCarousel: StyleA = style("HomeCarousel")(
    backgroundColor := "#379080",
    width(1000 px),
    display.flex,
    flexDirection.row,
    justifyContent.spaceAround
  )

  val CarouselImage: StyleA = style("CarouselImage")(
    width(100px)
  )


  val HomeCarouselSlide: StyleA = style("HomeCarouselSlide")(
    borderRadius(25 px),
//    borderColor("#FF0000")
    width(700 px),
    backgroundColor := "#FF0000"
  )
}

//colours giving random error looks like we are using the old way
