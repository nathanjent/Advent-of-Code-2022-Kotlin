class Day07 {

  data class File(val name: String, val size: Int)

  class FileSystem {
    private val fileSystemStructure = mutableMapOf(Pair("/", mutableMapOf<String, Int>()))
    private var pwd: String = "/"

    fun cd(path: String) {
      if (path == "..") {
        pwd = pwd.substring(0, pwd.lastIndexOf("/"))
      }

      pwd += path
    }

    fun ls(): List<File> {
      return fileSystemStructure.getOrDefault(pwd, mutableMapOf<String, Int>()).map { File(it.key, it.value) }
    }

    fun add(path: String, size: Int = 0) {
      val dirContents = fileSystemStructure.getOrPut(pwd) { mutableMapOf<String, Int>() }
      dirContents[path] = size
    }

    fun df(path: String): Int {
      val dirContents = fileSystemStructure.get(path)
      return dirContents?.values?.sum() ?: 0
    }
  }

  fun part1(input: Iterable<String>): Int {
    val lines = input.toList()
    val fileSystem = FileSystem()
    var cursor = 0

    main@ while (cursor < lines.size) {
      var line = lines[cursor]
      if (line.startsWith("$")) {
        if (line.startsWith("$ cd")) {
          val path = line.split(" ")[line.length - 1]
          fileSystem.cd(path)
        }

        if (line == "$ ls") {
          cursor++
          ls@ while (cursor < lines.size) {
            line = lines[cursor++]
            if (line.startsWith("dir")) {
              val path = line.split(" ")[1]
              fileSystem.add(path)
            } else if (line.startsWith("$")) {
              continue@main
            } else {
              val words = line.split(" ")
              val size = words[0].toInt()
              fileSystem.add(words[1], size)
            }
          }
        }
      }

      cursor++
    }

    return 0
  }

  fun part2(input: Iterable<String>): Int {
    return 0
  }
}
