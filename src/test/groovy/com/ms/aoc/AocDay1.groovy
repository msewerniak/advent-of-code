package com.ms.aoc


import spock.lang.Specification

class AocDay1 extends Specification {

    def "should return proper value for example input"() {
        given:
        def lines = new File('src/test/resources/aoc2024/day1a-example.txt').readLines()
        def lists = prepare(lines)

        expect:
        day1a(lists.v1, lists.v2) == 11
        
        day1b(lists.v1, lists.v2) == 31
    }

    def "should return proper value for my input"() {

        given:
        def lines = new File('src/test/resources/aoc2024/day1a-input.txt').readLines()
        def lists = prepare(lines)

        expect:
        day1a(lists.v1, lists.v2) == 1222801
        day1b(lists.v1, lists.v2) == 22545250
    }

    Tuple2<List<Integer>, List<Integer>> prepare(List<String> lines) {
        def l1 = []
        def l2 = []

        lines.each {
            def r = it.split("   ")
            l1.add(r[0] as Integer)
            l2.add(r[1] as Integer)
        }

        new Tuple2<>(l1, l2)
    }

    /* Calculate distances */

    int day1a(List<Integer> l1, List<Integer> l2) {
        l1.sort()
        l2.sort()

        def distance = 0

        for (i in 0..<l1.size()) {
            distance += Math.abs(l1[i] - l2[i])
        }

        distance
    }

    int day1b(List<Integer> l1, List<Integer> l2) {

        def score = 0

        for (i in 0..<l1.size()) {
            score += l1[i] * l2.findAll { it == l1[i] }.size()
        }

        score
    }
}