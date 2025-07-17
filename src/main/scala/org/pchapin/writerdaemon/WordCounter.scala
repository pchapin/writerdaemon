package org.pchapin.writerdaemon

import java.io.{IOException, BufferedReader, File, FileReader}


/**
 * Counts the number of words in the given LaTeX document. The constructor of this class does
 * the count. Additional methods are provided to extract that count from the object. In a future
 * version of this class it might be possible to defer counting or reuse the object in some way
 * to monitor the count on a file that is changing. These features are not yet implemented.
 *
 * @param texFile The file to count. It is assumed to be an existing LaTeX document.
 */
class WordCounter(texFile: File) {

  // Use a finite state machine to recognize words. This is only approximate; fully correct
  // handling of LaTeX is hard!
  private object InputState extends Enumeration {
    val Space, Command, Comment, Word = Value
  }
  import InputState._

  private var state = Space
  private var count = 0

  private def processChar(ch: Char): Unit = {
    def wordChar(ch: Char) = (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')

    state match {
      case Space => ch match {
        case '\\' => state = Command
        case '%'  => state = Comment
        case x if wordChar(x) =>
          state = InputState.Word  // IntelliJ says 'Word' unresolved (because of object Word?)
          count = count + 1
        case _    => state = Space
      }

      case Command => ch match {
        case '\\' => state = Command
        case '%'  => state = Space  // This may not be quite right. Consider: \command%stuff. How is that handled?
        case x if wordChar(x) => state = Command
        case _    => state = Space
      }

      case Comment =>
        if (ch == '\n') state = Space

      case InputState.Word => ch match {
        case '\\' => state = Command
        case '%'  => state = Comment
        case x if wordChar(x) => state = InputState.Word
        case _    => state = Space
      }
    }
  }

  private def doCount(): Unit = {
    val input = new BufferedReader(new FileReader(texFile))
    try {
      // TODO: Use a more elegant way of reading a file a character at a time.
      var rawLine: String = null
      while ({rawLine = input.readLine(); rawLine != null}) {
        for (i <- 0 until rawLine.length) {
          processChar(rawLine.charAt(i))
        }
        processChar('\n')
      }
    }
    catch {
      case ex: IOException =>
        // TODO: The messages below should go to some kind of message server.
        println("Unable to completely process " + texFile + ". Returning count of zero.")
        println("    Message = " + ex.getMessage)
        println("    Class   = " + ex.getClass)
    }
    input.close()
  }

  val wordCount: Int = { doCount(); count }
}
