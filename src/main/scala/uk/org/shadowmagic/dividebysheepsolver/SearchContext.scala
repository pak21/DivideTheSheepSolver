package uk.org.shadowmagic.dividebysheepsolver

import scala.collection.immutable.Queue

case class SearchContext(queue: Queue[Level], seen: Set[Level], success: Boolean) {
}
