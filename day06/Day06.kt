class Day06 {

  fun part1(input: String): Int {
    val windowSize = 4
    var cursor = 0
    var markerFound = false

    while (cursor + windowSize < input.length) {
      val window = input.subSequence(cursor, cursor + windowSize)
      if (isMarker(window)) break
      cursor++
    }

    return cursor + windowSize
  }

  fun part2(input: String): Int {
    return 0
  }

  private fun isMarker(markerStr: CharSequence): Boolean {
    markerStr.asIterable().forEachIndexed { i, c ->
      markerStr.asIterable().forEachIndexed { j, d ->
        if (i != j) {
          if (c == d) {
            return false
          }
        }
      }
    }

    return true
  }
}
