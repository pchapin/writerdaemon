package org.pchapin.writerdaemon

/**
 * Class representing an arbitrary unit of text, such as a single document or chapter. The
 * precise significance of the text in a SourceText object is unspecified and application
 * defined. However, the text is treated by this class as an ordered collection of paragraphs.
 * Instances of this class hide the manner in which the paragraphs are stored. All access to the
 * paragraphs is through the public methods.
 *
 * @param paragraphs A sequence of paragraph objects that represents the content of this source
 * text.
 */
class SourceText(private val paragraphs: Seq[Paragraph]) extends Iterable[Paragraph] {

  /**
   * @return An iterator over the paragraphs of a source file.
   */
  def iterator: Iterator[Paragraph] = paragraphs.iterator

  /**
   * Applies a function to each paragraph in this source text in order, discarding the result.
   *
   * @param f The function to apply.
   */
  override def foreach[U](f: Paragraph => U): Unit = paragraphs foreach f

  /**
   * @return The number of paragraphs in the source text.
   */
  override def size: Int = paragraphs.size
}
