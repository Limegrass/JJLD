package data.lang

import java.util.regex.Pattern

/**
 * Helpers related to parsing Japanese Vocabulary
 */
class JapaneseVocabulary {
    companion object {
        // Kanji followed by Kana
        private const val WORD_WITH_KANJI_REGEX =
                """\p{script=Han}+[\p{script=Hiragana}|\p{script=Katakana}]*\p{script=Han}*"""
        // Pure Kana
        private const val KANA_REGEX = """[\p{script=Hiragana}|\p{script=Katakana}]+"""
        // Tone, accounting for full-width numbers
        private const val TONE_REGEX = """[\d０-９]+"""

        fun isolateWord(wordSource: String): String {
            val kanjiMatcher = Pattern.compile(WORD_WITH_KANJI_REGEX)
                                      .matcher(wordSource)
            val kanaMatcher = Pattern.compile(KANA_REGEX)
                                     .matcher(wordSource)
            return when {
                kanjiMatcher.find() -> kanjiMatcher.group(0)
                kanaMatcher.find() -> kanaMatcher.group(0)
                else -> wordSource
            }
        }

        fun isolatePitch(wordSource: String): String {
            val toneMatcher = Pattern.compile(TONE_REGEX)
                                     .matcher(wordSource)
            return when {
                toneMatcher.find() -> toneMatcher.group(0)
                else -> ""
            }
        }

        fun String?.isJapaneseInput() : Boolean {
            return when {
                !this.isNullOrBlank() -> this.trim().all {
                    c: Char -> c.toInt() > 255
                }
                else -> false
            }
        }
    }
}
