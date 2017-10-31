package priv.rdo.sdaspring.task04

import spock.lang.Specification
import spock.lang.Unroll

class MathTest extends Specification {

    @Unroll
    def "maximum of two numbers a = #a, b = #b, c = #c"() {
        expect:
            Math.max(a, b) == c

        where:
            a | b | c
            1 | 3 | 3
            7 | 4 | 7
            0 | 0 | 0
    }
}