package util

import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path

class Input {
    companion object {
        private val YEAR = 2022
        private val cookie: String = Files.readString(Path.of("res").resolve(".cookie.txt"))

        fun get(day: Int): String {
            val input = Path.of("res").resolve("december_$day").resolve("input.txt")
            return try {
                Files.readString(input)
            } catch (e: IOException) {
                try {
                    download(day, input)
                } catch (ex: IOException) {
                    throw RuntimeException(ex)
                } catch (ex: InterruptedException) {
                    throw RuntimeException(ex)
                }
            }
        }

        fun lines(day: Int): List<String> =
            get(day)
                .split("\n")
                .dropLastWhile { it.isEmpty() }
                .toList()

        fun ints(day: Int): IntArray {
            return get(day)
                .split("\n")
                .dropLastWhile { it.isEmpty() }
                .map { Integer.parseInt(it) }
                .toIntArray()
        }

        private fun download(day: Int, input: Path): String {
            Files.createDirectories(input.parent)
            val link = "https://adventofcode.com/$YEAR/day/$day/input"
            val process = Runtime.getRuntime().exec(arrayOf("curl", "--cookie", cookie, link))
            process.waitFor()
            val content = String(process.inputStream.readAllBytes())
            Files.createFile(input)
            Files.writeString(input, content)
            return content
        }
    }
}