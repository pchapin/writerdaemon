package org.pchapin.writerdaemon

import util.matching.Regex

/**
 * This object contains methods for classifying and processing individual words. Currently the
 * words themselves are represented as strings and don't need a separate class. If that changes
 * in the future a companion class to this object could be created.
 */
object Word {

  // Regular expression that matches ordinary words.
  private val wordMatcher = new Regex("^[A-Za-z0-9]+$")

  // Recognized punctuation characters.
  private val validPunctuation = Array("\"", "'", ",", ";", ".", "?", "!", ":", "-")


  /**
   * @param element The sentence element to test.
   * @return True if the given element is a "normal" word; false otherwise.
   */
  def isWord(element: String) = wordMatcher.findFirstIn(element) != None


  /**
   * @param element The sentence element to test.
   * @return True if the given element is a punctuation mark; false otherwise.
   */
  def isPunctuation(element: String): Boolean = validPunctuation.contains(element)
  
}