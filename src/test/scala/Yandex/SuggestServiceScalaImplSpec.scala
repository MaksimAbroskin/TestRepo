package Yandex

import org.scalatest.FunSuite

import scala.collection.SortedSet
import scala.jdk.CollectionConverters._

class SuggestServiceScalaImplSpec extends FunSuite {
  test("1") {
    val names = List("company", "compan", "compa", "comp", "com", "co", "c")
    val expectedMap = Map(
      "c" -> SortedSet("company", "compan", "compa", "comp", "com", "co", "c"),
      "co" ->  SortedSet("company", "compan", "compa", "comp", "com", "co"),
      "com" ->  SortedSet("company", "compan", "compa", "comp", "com"),
      "comp" ->  SortedSet("company", "compan", "compa", "comp"),
      "compa" ->  SortedSet("company", "compan", "compa"),
      "compan" ->  SortedSet("company", "compan"),
      "company" ->  SortedSet("company")
    )
    assert(new SuggestServiceScalaImpl(names.asJava).getNamesMap == expectedMap)
  }
  test("2") {
    val names = List("company")
    val namesSet = SortedSet("company")
    val expectedMap = Map(
      "c" -> namesSet,
      "co" ->  namesSet,
      "com" ->  namesSet,
      "comp" ->  namesSet,
      "compa" ->  namesSet,
      "compan" ->  namesSet,
      "company" ->  namesSet
    )
    assert(new SuggestServiceScalaImpl(names.asJava).getNamesMap == expectedMap)
  }
}
