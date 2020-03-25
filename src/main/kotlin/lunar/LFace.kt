package lunar

import java.util.*

class LFace {
    var a: Int
    var b: Int
    var c: Int

    constructor(a: Int, b: Int, c: Int) {
        this.a = a
        this.b = b
        this.c = c
    }

    constructor(d: IntArray) {
        this.a = d[0]
        this.b = d[1]
        this.c = d[2]
    }

    constructor(d: ArrayList<Int>) {
        this.a = d[0]
        this.b = d[1]
        this.c = d[2]
    }
}