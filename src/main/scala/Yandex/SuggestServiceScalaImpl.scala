package Yandex

import java.util
import scala.annotation.tailrec
import scala.collection.{SortedSet, mutable}
import scala.jdk.CollectionConverters._

class SuggestServiceScalaImpl(companyNames: util.List[String])
    extends SuggestService {

  private val names =
    companyNames.asScala.to(collection.mutable.SortedSet).map(_.toLowerCase)

  private val namesMap = mutable.Map.empty[String, SortedSet[String]]

  private var len = 1

  while (names.nonEmpty) {
    @tailrec
    def loop(names: mutable.SortedSet[String], diff: Set[String]): Set[String] = {
      if (names.isEmpty) diff
      else {
        val name = names.head
        val key = name.take(len)
        val (toAdd, toProcess) = names.partition(_.startsWith(key))
        val newDiff =
          if
            (toAdd.size == 1) diff + name
          else if (toAdd.contains(key)) diff + key
          else diff
        namesMap.updateWith(key) {
          case Some(namesSet) => Some(namesSet ++ toAdd)
          case None           => Some(toAdd)
        }
        loop(toProcess, newDiff)
      }
    }

    val diff = loop(names, Set.empty)
    names.subtractAll(diff)
    len += 1
  }

  def suggest(input: String, numberOfSuggest: Integer): util.List[String] =
    namesMap.getOrElse(input.toLowerCase, SortedSet.empty[String]).take(numberOfSuggest).toList.asJava

  private [Yandex] def getNamesMap: Map[String, SortedSet[String]] = namesMap.toMap //for testing purposes only
}
