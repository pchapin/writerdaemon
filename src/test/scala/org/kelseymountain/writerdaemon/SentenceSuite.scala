package org.kelseymountain.writerdaemon

import scala.collection.immutable.ArraySeq

class SentenceSuite extends UnitSuite:

  test("Sentence Constructor") {

    // Try a few basic cases. Check all valid punctuation characters.
    val s1 = new Sentence(ArraySeq.unsafeWrapArray(Array("Hello")))
    val s2 = new Sentence(ArraySeq.unsafeWrapArray(Array("Hello", ",", "world")))
    val s3 = new Sentence(ArraySeq.unsafeWrapArray(Array("\"", "'", ".", "?", "!", "-", ",", ";", ":")))

    assert(s1.wordCount == 1)
    assert(s2.wordCount == 2)  // Punctuation characters are not words.
    assert(s3.wordCount == 0)  // Punctuation characters are not words.

    // Zero-length sentences are not allowed.
    val thrownException = intercept[Sentence.InvalidSentenceException] {
      val s = new Sentence(ArraySeq.unsafeWrapArray(Array[String]()))
    }
    thrownException.getMessage should equal ("Zero length sentence")

    // Try some invalid words to see if they are rejected.
    val invalidWords =
      Array("", "Hello$", "$Hello", "Hell$o", "Hello World", " Hello", "Hello ", "$")
    for (invalidWord <- invalidWords)
      val thrownException = intercept[Sentence.InvalidSentenceException] {
        val s = new Sentence(ArraySeq.unsafeWrapArray(Array(invalidWord)))
      }
      thrownException.getMessage should equal  ("Sentence with invalid word: " + invalidWord)
    end for
  }

end SentenceSuite
