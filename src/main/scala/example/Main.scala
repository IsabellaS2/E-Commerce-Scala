import Main.ResultsCard
import com.raquo.laminar.api.L.*
import example.styles.GlobalStyles
import frontroute.*
import io.circe.parser.decode
import models.ProductModel
import org.scalajs.dom
import org.scalajs.dom.{HTMLElement, document}

import java.net.URI
import scala.language.{implicitConversions, postfixOps}

object Main {

  // OBJECTS
  object NavBarComponent {
    def apply(): HtmlElement = {
      div(
        backgroundColor := "#87CB9E",
        display.flex,
        justifyContent.spaceBetween,
        alignItems.center,
        padding := "40px",
        div(
          a(href := "/", "Home", color.white, marginRight := "30px",fontSize := "30px"),
          a(href := "/basket", "Basket", color.white, marginRight := "30px",fontSize := "30px"),
          a(href := "/details", "Details", color.white, marginRight := "30px",fontSize := "30px"),
          a(href := "/settings", "Settings", color.white, marginRight := "30px",fontSize := "30px"),
          a(href := "/results", "Results", color.white, marginRight := "30px",fontSize := "30px")
        ),
        div(
          span("Congo", color.white, marginRight := "20px",fontFamily := "Kablammo" ,fontSize := "30px"),
          button("Cart", onClick --> { _ => println("Cart clicked") }, backgroundColor := "white", color := "forestgreen")
        )
      )
    }
  }






  object HomeCardComponent {
    def apply(title: String, image: URI, linkUri: String) =
      a(href := linkUri,
        div(
          img(
            src := image.toString,
            alt := "Scala logo",
            widthAttr := 100,
            heightAttr := 100,
            homeCardImage
          ),
            h4(title)
            ,homeCardDiv
        ),
        homeCardTextStyling
      )
  }

  object HomeCarouselSlide {
    def apply(title: String, description: String, image: URI, linkUri: String) =
      a(href := linkUri,
        div(
          div (
            div(h2(title), titleStyling),
            h2(description),
          ),
          carouselTextSyling,
          div (
            img(
              src := image.toString
            ),
            carouselImageStyling
          ),
          homeCarouselContainerStyling
        )
      ,textstylechangelater)
  }

  object ProductDetails {
    def apply(title: String, description: String, image: URI, linkUri: String) =
      div(
        h2(title),
        p(description),
        img(src := image.toString),
        div("button"),

        a(href := linkUri,
          div("button")
        )
      )
  }

// creating cart component here
  object CartComponent {

  private val cartItems = Var(List.empty[ProductModel])

  def addItem(item: ProductModel): Unit = {
    cartItems.update(items => items :+ item)
  }

  def removeItem(item: ProductModel): Unit ={
    cartItems.update(items => items.filterNot(_ == item))

  def apply(): HtmlElement ={
    val cartItemsSignal = cartItems.signal

    div(
      h2("Shopping Cart"),
      ul(
        children <-- cartItemsSignal.map { items =>
          items.map { item =>
            li(
              s"${item.name} - ${item.price}",
              button("Remove", onClick --> { _ => removeItem(item) })
            )
          }
        }
      )
    )
  }
  }
}





  // STYLING
  //Carousel
  val homeCarouselContainerStyling = List (
    display.flex,
    flexDirection.row,
    justifyContent.spaceBetween,
    padding("10px"),
    width("100vw"),
    maxWidth("fit-content"),
    marginLeft("auto"),
    marginRight("auto"),
    gap("400px"),
    border("1px solid #000000"),
    backgroundColor("#F9F7F7"),
    borderRadius("10px"),
    textDecoration.none,
  )

  val carouselImageStyling = List (
    display.flex,
    flexDirection.row,
    alignSelf.end,
    width("150px"),
    height("150px")
  )

  val carouselTextSyling = List (
    display.flex,
    flexDirection.column,
    textAlign.left,
    textDecoration.none
  )

  val titleStyling = List (
    fontSize("96px"),
    color("#379080"),
    fontFamily("Kablammo"),
    margin("0px"),
    padding("0px")
  )

  val textstylechangelater = List (
    textDecoration.none
  )

  //Home Cards
  val homeCardContainerStyling = List(
    display.flex,
    flexDirection.row,
    justifyContent.spaceEvenly,
    alignItems.center,
    width("100%"),
    marginTop("200px"),
    marginBottom("200px"),
    backgroundColor("#379080")
  )

  val homeCardImage = List (
    width("100%"),
  )

  val homeCardDiv = List(
    width("300px"),
    backgroundColor("#87CB9E"),
    margin("10px"),
    padding("15px"),
    borderRadius("10px"),
    border("1px solid #000000"),
    boxShadow("0px 4px 6px rgba(0, 0, 0, 0.6)")
  )

  val homeCardTextStyling = List(
    width("300px"),
    textDecoration("none"),
    color("#222020"),
    fontSize("29px"),
    fontFamily("Geologica"),
    textAlign("left"),
    margin("0px"),
    padding("0px")
  )

  val homeContainerStyling = List(
    backgroundColor("#379080"),
    width("100vw"),
    height("100vh"),
    backgroundSize("cover")
  )


  //Details Page
  val detailsPageBackgroundStyling = List(
    backgroundColor("#87CB9E"),
    width("100vw"),
    height("100vh"),
    backgroundSize("cover")
  )

  val leftDetailsPageStyling = List (
    width("100px"),
  )

  val rightDetailsPageStyling = List(
    width("100px"),
  )

  val buttonStyling = List(
    backgroundColor("#379080"),
    textAlign("center"),
    color("white")
  )

  val detailsPageStyling = List(
    display.flex,
    flexDirection.row,
  )


  // ROUTES
  val myApp = routes(
    div(
      NavBarComponent(),
      firstMatch(
        path("basket") {
          div(
            basketPage
          )
        },
        path("details") {
          div(
            detailsPage
          )
        },
        path("settings") {
          div(
            settingsPage
          )
        },
        path("results") {
          resultsPage()
        },
        div(
          homePage,
        )
      )
    )
  )

  //DIVS
  val homeCardContainer = div(
    HomeCardComponent(
      "Check out our cool list of products",
      URI("https://www.freeiconspng.com/uploads/happy-smiley-face-icon-5.png"),
      "/results",
    ),
    homeCardContainerStyling
  )

  val navBar = div(
    NavBarComponent()
  )

  val homeCarouselContainer = div (
      HomeCarouselSlide(
        title = "CONGO",
        description = "Shop Smart,  Live Easy – Your One-Stop Online Store!",
        image = URI("src/main/resources/images/monkeyimage.jpeg"),
        linkUri = "/results",
      )
  )

  val itemDetails = div (

  )
// Quantity
def QuantityButton(): Element = {
  val counter = com.raquo.airstream.state.Var(0)
  val counterSignal = counter.signal
  div(
    button(
      tpe := "button",
      "Quantity is ",
      child.text <-- counter,
      onClick --> { event => counter.update(c => c + 1) },
    ),
  )
}

  def AddToBasket (): Element ={
    val counter = Var(0)
    val counterSignal = counter.signal
    div(
      button(
        tpe := "button",
        "Add to Basket",
      )
    )
  }


  val detailsPageContent = div(
    div(
      h1("Product Title"),
      p("image goes here"),
      p("add to basket"),
      leftDetailsPageStyling,
    ),

    div(
      p("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
      QuantityButton(),
      AddToBasket(),
      rightDetailsPageStyling
    ),
    detailsPageStyling
  )



  // OBJECTS
  object ResultsCard {
    def apply(title: String, description: String, price: String ,image: URI) =
      div(
        display.flex,
        flexDirection.row,
        alignItems.center,
        gap("20px"),
        border("20px solid #ADEBB3"),
        padding("20px"),
        backgroundColor("#white"),
        borderRadius("8px"),
        boxShadow("0px 4px 6px rgba(0, 0, 0, 0.1)"),

        div(
          img(
            src := image.toString,
            alt := title,
            widthAttr := 100,
            heightAttr := 100
          ),

        ),
        div(
          display.flex,
          flexDirection.column,
          textAlign.left,
          h3(title),
          p(price),
          p(description)
        )
      )
  }


  //CALLING PAGES
  val homePage = div(
    homeContainerStyling, // Apply the green background here
    div(
      homeCarouselContainer,
      homeCardContainer
    )
  )

  val basketPage = div(
  )

  val detailsPage = div(
    detailsPageContent,
    detailsPageBackgroundStyling
  )

  def counterButton(): Element = {
    val counter = Var(0)
    val counterSignal = counter.signal
    val counterTimes2Signal: Signal[Int] = counterSignal.map(_ * 2)
    div(
      button(
        tpe := "button",
        "count is ",
        child.text <-- counter,
        onClick --> { event => counter.update(c => c + 1) },
      ),
      div(
        child.text <-- counterTimes2Signal
      ),
      h1("Results"),
      img("src/main/resources/images/monkeyimage.jpeg"),
      h3("Lorem oproduct details aaaaaa"),
    )
  }

  val settingsPage = counterButton()

  def createProductElement(model: ProductModel): HtmlElement = ResultsCard(model.name, model.description, "$" + s"${model.price.toString}", URI(model.imageUrl.getOrElse("src/main/resources/images/monkeyimage.jpeg")))

  def resultsPage() = {
    val productsVar = Var(List.empty[ProductModel])
    val productElements = productsVar.signal.map(list => list.map(product => createProductElement(product)))
    div(
      div(
        h1("Results"),
        div(
          children <-- productElements
        )
      ),
      FetchStream.get("http://127.0.0.1:8081/products") --> {
        responseText => {
          println("" + responseText)
          val products = decode[List[ProductModel]](responseText)
          productsVar.set(products.getOrElse(List.empty))
        }
      }
    )
  }
  def main(args: Array[String]): Unit = {
    val appContainer = document.getElementById("app")
    if (appContainer != null) {
      render(appContainer, myApp)
    } else {
      println("Error: Element with ID 'app' not found.")
    }
  }
}
