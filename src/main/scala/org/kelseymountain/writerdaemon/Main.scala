package org.kelseymountain.writerdaemon

import java.io.{FilenameFilter, File}

object Main:

  def main(args: Array[String]): Unit =
    val configurableItems = Map("AbbreviationFile" -> ConfigurationSettings.basicStringValidator _)
    val defaultConfiguration = Map("AbbreviationFile" -> "wd-abbreviations.txt")
    val settings = new ConfigurationSettings(configurableItems)

    settings.setDefaults(defaultConfiguration)
    settings.readConfigurationFile("wd-configuration.txt")

    println("WriterDaemon (batch mode) v0.0 \"Genesis\"")

    // For now I'm just interested in counting words in LaTeX documents.
    // TODO: Integrate the LaTeX word counting logic into a more coherent and abstract design.

    val currentFolder = new File(".")
    val fileList = currentFolder.listFiles(new FilenameFilter {
      def accept(dir: File, name: String): Boolean = name.endsWith(".tex")
    })

    val totalCount = (fileList map { file =>
      print("Processing: " + file)
      val counter = new WordCounter(file)
      val count = counter.wordCount
      println(" (" + count + " words)")
      count
    }).sum

    println("Total word count = " + totalCount)
  end main

end Main
