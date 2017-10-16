package uk.org.shadowmagic.dividebysheepsolver

import scala.collection.immutable.Queue

case class SearchContext(queue: Queue[(Level, Seq[Move])], seen: Map[Level, Seq[Move]], success: Boolean) {
}
