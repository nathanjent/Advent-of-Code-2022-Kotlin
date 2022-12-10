import org.junit.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.Assert.*
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.util.stream.Stream

public class Day06Tests {
  private val inputFile = "day06/input.txt"
  private val day = Day06()

  companion object {
    @JvmStatic
    fun testData(): Stream<Arguments> {
      return Stream.of(
        arguments("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 7),
        arguments("bvwbjplbgvbhsrlpgdmjqwftvncz", 5),
        arguments("nppdvjthqldpwncqszvftbrmjlhg", 6),
        arguments("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 10),
        arguments("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 11),
      )
    }
  }

  @ParameterizedTest
  @MethodSource("testData")
  fun example1Test(input: String, expected: Int) {

    assertEquals(expected, day.part1(input))
  }
  
  @Test
  fun part1Test() {
    val input = File(inputFile).readText()
    val expected = 42

    assertEquals(expected, day.part1(input))
  }
  
  @ParameterizedTest
  @MethodSource("testData")
  fun example2Test(input: String, expected: Int) {
    assertEquals(expected, day.part2(input))
  }
  
  @Test
  fun part2Test() {
    val input = File(inputFile).readText()
    val expected = 42

    assertEquals(expected, day.part2(input))
  }
}
