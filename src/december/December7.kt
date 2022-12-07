package december

import util.Input

class December7 : December<Int, Int> {
    override fun star1(): Int = 0

    override fun star2(): Int = 0

    private fun buildTree() : FileTree {
        Input.lines(7)
    }

    private class FileTree {
        private val root = Directory("/", null)
        private var currentDirectory = root
        fun cd(name: String) {
            currentDirectory = currentDirectory.cd(name)
        }
        fun ls() : List<File> = currentDirectory.ls()
        fun ld() : List<File> = currentDirectory.ld()

        private interface File {
            val name: String
            val size: Int
        }
        private class Directory(override val name: String, private val parent: Directory?) : File {
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
        }
        private data class Document(override val name: String, override val size: Int) : File
    }
}