package com.ms.aoc


import spock.lang.Specification

class AocDay3 extends Specification {

    def "should return proper value for example input"() {
        given:
        def lines = new File('src/test/resources/aoc2024/day3a-example.txt').readLines()

        expect:
        day3a(lines) == 161
    }

    def "should return proper value for my input"() {

        given:
        def lines = new File('src/test/resources/aoc2024/day3a-input.txt').readLines()

        expect:
        day3a(lines) == 187833789
        day3b(lines) == 94455185
    }

    def "should return proper value for example b input"() {
        given:
        def lines = new File('src/test/resources/aoc2024/day3b-example.txt').readLines()

        expect:
        day3b(lines) == 48
    }

    int day3a(List<String> list) {

        def n = 0

        list.forEach { n += mul(it) }

        n
    }

    int mul(String line) {
        def matcher = line =~ /mul\((\d{1,3})\,(\d{1,3})\)/

        def res = 0
        
        for (int i in 0..<matcher.size()) {
            def n1 = matcher[i][1] as Integer
            def n2 = matcher[i][2] as Integer
            res += n1 * n2
        }
        res
    }

    int day3b(List<String> list) {
        
        def s = "do()" + list.join("")

        mul2(s)
    }

    int mul2(String line) {

        def doesList = line.split("do").findAll { String i -> i.startsWith("()")}

        doesList.collect { mul(it) }.sum() as Integer
    }

}