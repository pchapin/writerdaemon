package org.pchapin.writerdaemon

/**
 * Class representing a single sentence. Instances of this class hide the manner in which
 * sentence text is stored. All access to the text is through the public methods.
 *
 * @param elements A sequence of strings that nominally represent the words in this sentence.
 * Each punctuation mark in the sentence, including the terminating mark, should be included as
 * separate elements.
 */
class Sentence(private val elements: Seq[String]) extends collection.Iterable[String] {
  import Sentence._

  // Is this check worth making?
  // Right now a "sentence" completely composed of punctuation characters is accepted.
  if (elements.isEmpty)
    throw new InvalidSentenceException("Zero length sentence")

  // Make sure all the sentence elements are normal words or single valid punctuation characters.
  for (element <- elements) {
    if (!Word.isPunctuation(element) && !Word.isWord(element))
      throw new InvalidSentenceException("Sentence with invalid word: " + element)
  }

  /**
   * @return An iterator over the elements of a sentence (words and punctuation).
   */
  def iterator: Iterator[String] = elements.iterator

  /**
   * Applies a function to each sentence element (including punctuation) in this sentence in
   * order, discarding the result.
   *
   * @param f The function to apply.
   */
  override def foreach[U](f: String => U): Unit = elements foreach f


  /**
   * Applies a function to each word (not including punctuation) in this sentence in order,
   * discarding the result.
   *
   * @param f The function to apply.
   */
  def foreachWord[U](f: String => U): Unit = elements filter { Word.isWord } foreach f


  /**
   * @return The number of words in the sentence not including punctuation.
   */
  def wordCount: Int = elements count { Word.isWord }


  /**
   * @return The total number of sentence elements (words and punctuation).
   */
  override def size: Int = elements.size
}


object Sentence {

  /**
   * Exception thrown when an invalid sequence of strings is used as a sentence.
   */
  class InvalidSentenceException(message: String) extends Exception(message)
}
