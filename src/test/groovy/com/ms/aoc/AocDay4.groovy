package com.ms.aoc


import spock.lang.Specification

class AocDay4 extends Specification {

    def "should return proper value for example input"() {
        given:
        def lines = new File('src/test/resources/aoc2024/day4a-example.txt').readLines()

        expect:
        day4a(lines) == 18
        day4b(lines) == 9
    }

    def "should return proper value for my input"() {

        given:
        def lines = new File('src/test/resources/aoc2024/day4a-input.txt').readLines()

        expect:
        day4a(lines) == 2549
        day4b(lines) == 2003

    }

    int day4b(List<String> lines) {

        int n = 0
        
        for (y in 1..<lines.size() - 1) {
            for(x in 1..<lines[0].size() - 1) {
                if(lines[y][x] == 'A') {
                    
                    boolean w = (lines[y-1][x-1] == 'M' && lines[y+1][x+1] == 'S')  || (lines[y-1][x-1] == 'S' && lines[y+1][x+1] == 'M')
                    boolean z = (lines[y-1][x+1] == 'M' && lines[y+1][x-1] == 'S')  || (lines[y-1][x+1] == 'S' && lines[y+1][x-1] == 'M')
                    
                    if(w && z) {
                        n++
                    }
                }
            }
        }
        
        n
    }
    
    int day4a(List<String> list) {

        def n = 0

        list.forEach { n += xmas(it) }
        verticalList(list).forEach { n += xmas(it) }
        diagonal(list).forEach {n += xmas(it)}

        n
    }
    
    int xmas(String line) {
        def matcher = line =~ /XMAS/
        def matcherReverse = line =~ /SAMX/

        matcher.size() + matcherReverse.size()
    }

    def verticalList(List<String> list) {

        def verticalList = []

        for (j in 0..<list[0].size()) {

            def col = []

            for (i in 0..<list.size()) {
                col.add(list[i][j])
            }

            verticalList.add(col.join('') as String)
        }
        verticalList
    }

    def diagonal(List<String> list) {

        def diagonalList = []
        def diagonalList2 = []

        def xsize = list[0].size()
        def ysize = list.size()

        // go over first row
        for (x in 0..<xsize) {
            def s = leftRight(x, xsize, 0, ysize, list)
            diagonalList.add(s)
            
            def xs = rightLeft(x, 0, ysize, list)
            diagonalList2.add(xs)
        }    
        
        // go over first column
        for (y in 1..<ysize) {
            def s = leftRight(0, xsize, y, ysize, list)
            diagonalList.add(s)
            def xs = rightLeft(xsize -1 , y, ysize, list)
            diagonalList2.add(xs)
        }
        
        diagonalList.addAll(diagonalList2)
        
        diagonalList
    }

    private String leftRight(int x, int xsize, int y, int ysize, List<String> list) {
        def d = []
        while (true) {
            if (x >= xsize || y >= ysize) {
                break
            }
            d.add(list[y++][x++])
        }
        return d.join('') as String
    }

    private String rightLeft(int x, int y, int ysize, List<String> list) {
        def d = []
        while (true) {
            if (x < 0 || y >= ysize) {
                break
            }
            d.add(list[y++][x--])
        }
        return d.join('') as String
    }

}