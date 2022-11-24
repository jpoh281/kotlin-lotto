package lotto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {

    @DisplayName("0 ~ 5 길이의 배열범위 테스트")
    @ParameterizedTest(name = "배열 ({0})로또를 생성할 수 없다")
    @MethodSource("generateUnder6Source")
    fun `정수 배열의 길이가 5 이하인 경우 에러를 반환한다`(numbers: List<Int>) {
        assertThrows<IllegalArgumentException> {
            Lotto(numbers)
        }
    }

    @Test
    fun `정수 배열의 길이가 6이며 중복된 숫자가 없는 배열을 넣으면 로또 객체가 생성된다`() {
        assertThat(Lotto(listOf(1, 2, 3, 4, 5, 6))).isExactlyInstanceOf(Lotto::class.java)
    }

    @Test
    fun `정수 배열의 길이가 6이며 중복된 숫자가 포함된 배열을 넣으면 에러를 반환한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5, 5))
        }
    }

    @DisplayName("7 이상 길이의 배열범위 테스트")
    @ParameterizedTest(name = "배열 ({0})로또를 생성할 수 없다")
    @MethodSource("generateOver7Source")
    fun `정수 배열의 길이가 7 이상인 경우 에러를 반환한다`(numbers: List<Int>) {
        assertThrows<IllegalArgumentException> {
            Lotto(numbers)
        }
    }

    companion object {
        @JvmStatic
        fun generateUnder6Source(): List<Arguments> {
            return listOf(
                Arguments.of(listOf<Int>()),
                Arguments.of(listOf(1)),
                Arguments.of(listOf(1, 2)),
                Arguments.of(listOf(1, 2, 3)),
                Arguments.of(listOf(1, 2, 3, 4)),
                Arguments.of(listOf(1, 2, 3, 4, 5))
            )
        }

        @JvmStatic
        fun generateOver7Source(): List<Arguments> {
            return listOf(
                Arguments.of(listOf(1, 2, 3, 4, 5, 6, 7)),
                Arguments.of(listOf(1, 2, 3, 4, 5, 6, 7, 8)),
            )
        }
    }
}
