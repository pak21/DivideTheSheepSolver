package uk.org.shadowmagic.dividebysheepsolver

trait StateFilter {
  def filterStates(states: Seq[(Level, Seq[Move])], seen: Set[Level]): Map[Level, Seq[Move]]
}
