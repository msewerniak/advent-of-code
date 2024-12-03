package com.ms.aoc


import spock.lang.Specification

class AocDay2 extends Specification {

    def "should return proper value for example input"() {
        given:
        def lines = new File('src/test/resources/aoc2024/day2a-example.txt').readLines()
        def list = prepare(lines)

        expect:
        day2a(list) == 2
        day2b(list) == 4
    }

    def "should return proper value for my input"() {

        given:
        def lines = new File('src/test/resources/aoc2024/day2a-input.txt').readLines()
        def list = prepare(lines)

        expect:
        day2a(list) == 224
        day2b(list) == 293
    }

    List<List<Integer>> prepare(List<String> lines) {
        lines.collect { it.split(" ").collect { it as Integer } }
    }

    int day2a(List<List<Integer>> list) {

        def n = 0

        list.forEach { n += isSafe(it) }

        n
    }

    int isSafe(List<Integer> r) {
        if (r.size() <= 1) {
            return 1
        }

        if (r[1] == r[2]) {
            return 0
        }

        def increasing = r[0] < r[1]

        for (i in 1..<r.size()) {

            def d = increasing ? r[i] - r[i - 1] : r[i - 1] - r[i]

            if (d < 1 || d > 3) {
                return 0
            }
        }

        return 1
    }

    int day2b(List<List<Integer>> list) {

        def n = 0

        list.forEach { n += isSafeWithProblemDampener(it) }

        n
    }

    int isSafeWithProblemDampener(List<Integer> r) {
        
        if(isSafe(r) == 1) {
            return 1
        }

        for (i in 0..<r.size()) {

            if (isSafeAfetrRemove(r.clone() as List<Integer>, i) == 1) {
                return 1
            }
        }
        
        return 0
    }

    int isSafeAfetrRemove(List<Integer> rr, int i) {
        rr.remove(i)
        isSafe(rr)
    }
}