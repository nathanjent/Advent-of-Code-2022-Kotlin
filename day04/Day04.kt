class Day04 {
  fun part1(input: Iterable<String>): Int {
    return findPairCountBy(
      input,
      {
        ranges ->
          ranges[0].all {
            ranges[1].contains(it)
          } ||
          ranges[1].all {
            ranges[0].contains(it)
          }
      },
    )
  }

  fun part2(input: Iterable<String>): Int {
    return findPairCountBy(
      input,
      {
        it[0].intersect(
          it[1].asIterable())
          .any()
      },
    )
  }

  private fun findPairCountBy(
    input: Iterable<String>,
    predicate: (Array<IntRange>) -> Boolean,
  ): Int {
    return input
      .filter { !it.isBlank() }
      .map { it.split(",") }
      .map { it.map { it.split("-") } }
      .map { it.flatten() }
      .map { it.map { it.toInt() } }
      .map {
        arrayOf(
          it[0]..it[1],
          it[2]..it[3],
      ) }
      .filter { predicate(it) }
      .size
  }
}