class Day03 {
  fun part1(input: Iterable<String>): Int {
    val result = input
      .filter { !it.isBlank() }
      .map { it.toCharArray() }
      .map {
        val half = it.size / 2
        Pair(it.sliceArray(0..half-1), it.sliceArray(half..it.size-1))
      }
      .map {
        it.first.intersect(
          it.second.asIterable())
      }
      .map { it.first() }
      .map { itemPriority(it) }
      
      return result.sum()
  }

  fun part2(input: Iterable<String>): Int {
    var result = input
      .filter { !it.isBlank() }
      .map { it.toCharArray() }
      .chunked(3)
      .map {
      val i1 = it[0].intersect(
        it[1].asIterable())
      val i2 = it[1].intersect(
        it[2].asIterable())
        i1.intersect(i2)
      }
      .flatMap { it.asIterable() }
      .map { itemPriority(it) }
      
      return result.sum()
  }

  private fun itemPriority(it: Char): Int {
    return when (it) {
         in 'a'..'z' -> it - 'a' + 1
         in 'A'..'Z' -> it - 'A' + 27
         else -> throw Exception()
    }
  }
}