package december

import util.Input

class December7 : December<Int, Int> {
    override fun star1(): Int = FileSystem.fs
        .ldr()
        .map { it.size }
        .filter { it <= 100000 }
        .sum()

    override fun star2(): Int = FileSystem.fs
        .ldr()
        .map { it.size }
        .filter { it >= FileSystem.fs.size - 40000000 }
        .min()

    private class FileSystem {
        companion object {
            var fs: FileSystem

            init {
                val tree = FileSystem()
                var line = 0
                val lines = Input.lines(7)
                while(line < lines.size) {
                    val command = lines[line].split(" ").drop(1)
                    if(command[0] == "cd") {
                        tree.cd(command[1])
                        line++
                    } else if(command[0] == "ls") {
                        var lsLine : List<String>
                        while (++line < lines.size) {
                            lsLine = lines[line].split(" ")
                            if(lsLine[0] == "$") break
                            if(lsLine[0] == "dir") {
                                tree.mkdir(lsLine[1])
                            } else {
                                tree.touch(lsLine[1], lsLine[0].toInt())
                            }
                        }
                    } else throw IllegalStateException()
                }
                tree.cd("/")
                fs = tree
            }
        }

        private val root = Directory("/", null)
        private var currentDirectory = root
        val size get() = currentDirectory.size

        fun cd(name: String) {
            currentDirectory = currentDirectory.cd(name)
        }
        fun ls() : List<File> = currentDirectory.ls()
        fun ld() : List<Directory> = currentDirectory.ld()
        fun ldr() : List<Directory> = currentDirectory.ldr()
        fun mkdir(name: String) = currentDirectory.addDirectory(name)
        fun touch(name: String, size: Int) = currentDirectory.addDocument(name, size)

        interface File {
            val name: String
            val size: Int
        }
        class Directory(override val name: String, private val parent: Directory?) : File {
            override val size : Int
                get() = files.sumOf { it.size }
            private val files: ArrayList<File> = ArrayList()

            fun addDocument(name: String, size: Int) = files.add(Document(name, size))
            fun addDirectory(name: String) = files.add(Directory(name, this))
            fun cd(name: String) : Directory {
                return when (name) {
                    "/" -> parent?.cd(name) ?: this
                    ".." -> parent ?: this
                    else -> files.first { it.name == name } as Directory
                }
            }
            fun ls(): List<File> = files.toList()
            fun ld(): List<Directory> = files.filterIsInstance<Directory>()
            fun ldr(): List<Directory> = ld().union(ld().flatMap { it.ldr() }).toList()
        }
        data class Document(override val name: String, override val size: Int) : File
    }
}