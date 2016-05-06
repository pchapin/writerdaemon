package org.pchapin.writerdaemon

import org.scalatest.{Assertions, FunSuite}
import org.scalatest.Matchers

class SentenceSuite extends FunSuite with Assertions with Matchers {

  test("Sentence Constructor") {

    // Try a few basic cases. Check all valid punctuation characters.
    val s1 = new Sentence(Array("Hello"))
    val s2 = new Sentence(Array("Hello", ",", "world"))
    val s3 = new Sentence(Array("\"", "'", ".", "?", "!", "-", ",", ";", ":"))

    assert(s1.wordCount == 1)
    assert(s2.wordCount == 2)  // Punctuation characters are not words.
    assert(s3.wordCount == 0)  // Punctuation characters are not words.

    // Zero length sentences are not allowed.
    val thrownException = intercept[Sentence.InvalidSentenceException] {
      val s = new Sentence(Array[String]())
    }
    thrownException.getMessage should equal ("Zero length sentence")

    // Try some invalid words to see if the are rejected.
    val invalidWords =
      Array("", "Hello$", "$Hello", "Hell$o", "Hello World", " Hello", "Hello ", "$")
    for (invalidWord <- invalidWords) {
      val thrownException = intercept[Sentence.InvalidSentenceException] {
        val s = new Sentence(Array(invalidWord))
      }
      thrownException.getMessage should equal  ("Sentence with invalid word: " + invalidWord)
    }
  }
}