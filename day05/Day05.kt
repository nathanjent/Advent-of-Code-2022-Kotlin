class Day05 {
   
  interface Rule {}

  interface Location: Rule {
    val marker: Char
  }

  data class Empty(
    override val marker: Char = ' '
  ): Location

  data class Crate(
    override val marker: Char
  ): Location

  data class Row(
    val locations: List<Location>
  ): Rule

  data class Move(
    val count: Int,
    val from: Int,
    val to: Int,
  ): Rule

  class Unknown(): Rule
  
  operator fun Regex.contains(text: CharSequence): Boolean = this.matches(text)
  
  fun part1(input: Iterable<String>): String {
    val rules = input.map { rule ->
      if (rule.isBlank()) return@map Unknown()
      when (rule[0]) {
        ' ', '[' -> parseRow(rule) ?: Unknown()
        'm' -> parseMove(rule)
        else -> Unknown()
      }
    }
    .filter { it !is Unknown }
    .partition { it is Row }

    val stacks = mutableMapOf<Int, ArrayDeque<Location>>()
    for (row in rules.first.map { it as Row }) {
      row.locations.forEachIndexed {
        i: Int, location: Location ->
        val stackIndex = i + 1
        val stack = stacks.getOrDefault(
          stackIndex,
          ArrayDeque(mutableListOf<Location>()),
        )
        when (location) {
          is Crate -> stack.addFirst(location)
          is Empty -> {}
          else -> throw Exception("Unknown location type [ $location ]")
        }
      }
    }

    for (move in rules.second.map { it as Move }) {
      val fromStack = stacks[move.from]
      for (i in 0..move.count) {
        val location = fromStack?.removeFirstOrNull()
        if (location != null && location is Crate) {
          throw Exception("What?")
          stacks[move.to]?.addFirst(location)
        }
      }
    }

    if (true) throw Exception("${stacks.get(0)}")
    return stacks.map { it.value.firstOrNull() }
      .filterNotNull()
      .joinToString()
  }

  fun part2(input: Iterable<String>): String {
    return "!"
  }
  
  private fun parseMove(move: String): Move {
    val stackMoves = move.split(' ').map {
      when (it) {
        in "1".."9" -> it
        "move", "from", "to" -> null
        else -> throw Exception("Unhandled move index [ $it ]")
      }
    }
    .filterNotNull()

    return Move(
      stackMoves[0].toInt(),
      stackMoves[1].toInt(),
      stackMoves[2].toInt(),
    )
  }

  private fun parseRow(row: String): Row? {
    return Row(row.chunked(4).map {
      when (it[0]) {
        '[' -> {
          when (it[1]) {
            in 'A'..'Z' -> Crate(it[1])
            else -> throw Exception("Unmarked crate error [ $it ]")
          }
        }
        ' ' -> {
          when (it[1]) {
            in '1'..'9' -> return null
            else -> Empty()
          }
        }
        else -> Empty()
      }
    })
  }
}