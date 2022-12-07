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

  data class Stack(
    override val marker: Char
  ): Location

  data class Move(
    val count: Int?,
    val from: Int?,
    val to: Int?,
  ): Rule

  fun part1(input: Iterable<String>): String {
    val result = input.map {
      rule -> {
        val chars = rule.toCharArray().asIterable()
        
        // take 4 chars, ex. '[N] '
        chars.chunked(4).map {
          when (it[0]) {
            ' ' -> {
              when (it[1]) {
                ' ' -> Empty()
                in '0'..'9' -> Stack(it[1])
                else -> throw Exception("Unhandled crate location")
              }
            }
            '[' -> {
              when (it[1]) {
                in 'A'..'Z' -> Crate(it[1])
                else -> throw Exception("Unhandled crate marker")
              }
            }
            'm' -> parseMove(rule)
            else -> throw Exception("Unhandled start of rule")
          }
        }
      }
    }
  
    return "!"
  }

  fun part2(input: Iterable<String>): String {
    return "!"
  }

  private fun parseMove(move: String): Move {
    val stackMoves = move.split(' ').map {
      when (it) {
        in "1".."9" -> it
        "move", "from", "to" -> null
        else -> throw Exception("Unhandled move index")
      }
    }
    .filter { it != null }
    
    val count = stackMoves[0]?.toInt()
    val from = stackMoves[1]?.toInt()
    val to = stackMoves[2]?.toInt()
    
    return Move(count, from, to)
  }
}