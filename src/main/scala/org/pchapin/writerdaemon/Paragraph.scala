package org.pchapin.writerdaemon

/**
 * Class representing a single paragraph of text. Instances of this class hide the manner in
 * which paragraph text is stored. All access to the text is through the public methods.
 *
 * @param sentences A sequence of sentences that represents the content of this paragraph.
 */
class Paragraph(private val sentences: Seq[Sentence]) extends collection.Iterable[Sentence] {

  /**
   * @return An iterator over the sentences of the paragraph.
   */
  def iterator: Iterator[Sentence] = sentences.iterator

  /**
   * Applies a function to each sentence in this paragraph in order, discarding the result.
   *
   * @param f The function to apply.
   */
  override def foreach[U](f: Sentence => U): Unit = sentences foreach f

  /**
   * @return The number of sentences in the paragraph.
   */
  override def size: Int = sentences.size
}