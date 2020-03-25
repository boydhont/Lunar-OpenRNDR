package lunar

import org.openrndr.math.Vector3
import java.util.*

class LVector {
    var x: Double
    var y: Double
    var z: Double

    constructor() {
        this.x = 0.0
        this.y = 0.0
        this.z = 0.0
    }

    constructor(x: Double, y: Double) {
        this.x = x
        this.y = y
        this.z = 0.0
    }

    constructor(x: Double, y: Double, z: Double) {
        this.x = x
        this.y = y
        this.z = z
    }

    constructor(f: DoubleArray) {
        this.x = f[0]
        this.y = f[1]
        this.z = f[2]
    }

    constructor(f: ArrayList<Double>) {
        this.x = f[0]
        this.y = f[1]
        this.z = f[2]
    }

    constructor(v: org.openrndr.math.Vector3) {
        this.x = v.x
        this.y = v.y
        this.z = v.z
    }
}