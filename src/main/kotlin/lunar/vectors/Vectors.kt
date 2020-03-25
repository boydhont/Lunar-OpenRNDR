package lunar.vectors

import lunar.LVector
import org.openrndr.math.Vector3
import java.util.*
import kotlin.math.*


fun constructVector(x: Double, y: Double, z: Double): LVector{
    return LVector(x, y, z)
}

fun deconstructVector(v: LVector): DoubleArray
{
    return doubleArrayOf(v.x, v.y, v.z)
}

fun unitVector(v: LVector): LVector
{
    return dividedVector(v, vectorLength(v))
}

fun xAxisVector(amplitude: Double): LVector
{
    return LVector(amplitude, 0.0, 0.0)
}

fun yAxisVector(amplitude: Double): LVector
{
    return LVector(0.0, amplitude, 0.0)
}

fun zAxisVector(amplitude: Double): LVector
{
    return LVector(0.0, 0.0, amplitude)
}

fun twoPointVector(a: LVector, b: LVector): LVector
{
    return LVector(b.x - a.x, b.y - a.y, b.z - a.z)
}

fun amplitudedVector(v: LVector, amplitude: Double): LVector
{
    val u = unitVector(v!!)
    return multipliedVector(u, amplitude)
}

fun angleBetweenVectors(a: LVector, b: LVector): Double
{
    val d: Double = dotProduct(a, b)
    return acos(d / (vectorLength(a) * vectorLength(b)))
}

fun crossProduct(a: LVector, b: LVector): LVector
{
    return LVector(
        a.y * b.z - a.z * b.y,
        a.z * b.x - a.x * b.z,
        a.x * b.y - a.y * b.x
    )
}

fun dividedVector(v: LVector, factor: Double): LVector
{
    return multipliedVector(v, 1 / factor)
}

fun dotProduct(a: LVector, b: LVector): Double
{
    return (a.x * b.x + a.y * b.y + a.z * b.z)
}

fun vectorLength(v: LVector): Double
{
    return (v.x.pow(2.0) + v.y.pow(2.0) + v.z.pow(2.0)).pow(.5)
}

fun multipliedVector(v: LVector, factor: Double): LVector
{
    return LVector(v.x * factor, v.y * factor, v.z * factor)
}

fun reversedVector(v: LVector): LVector
{
    return multipliedVector(v, -1.0)
}

fun rotatedVector(v: LVector, axis: LVector, angleInRadial: Double): LVector
{
    val u = unitVector(axis)

    val c = cos(angleInRadial)
    val s = sin(angleInRadial)
    val n = (1 - cos(angleInRadial))

    val xA = c + (u.x * u.x * n)
    val xB = (u.x * u.y * n) - (u.z * s)
    val xC = (u.x * u.z * n) + (u.y * s)

    val yA = (u.y * u.x * n) + (u.z * s)
    val yB = c + (u.y * u.y * n)
    val yC = (u.y * u.z * n) + (u.x * s)

    val zA = (u.z * u.x * n) - (u.y * s)
    val zB = (u.z * u.y * n) + (u.x * s)
    val zC = c + (u.z * u.z * n)

    var x = (v.x * xA) + (v.y * xB) + (v.z * xC)
    var y = (v.x * yA) + (v.y * yB) + (v.z * yC)
    var z = (v.x * zA) + (v.y * zB) + (v.z * zC)

    x = (x * 10.0.pow(5.0)).roundToInt() / 10.0.pow(5.0)
    y = (y * 10.0.pow(5.0)).roundToInt() / 10.0.pow(5.0)
    z = (z * 10.0.pow(5.0)).roundToInt() / 10.0.pow(5.0)

    return LVector(x, y, z)
}

fun addedVector(a: LVector, b: LVector): LVector
{
    return LVector(a.x + b.x, a.y + b.y, a.z + b.z)
}

fun Vector3(v: LVector): Vector3
{
    return Vector3(v.x, v.y, v.z)
}

fun closestVector(v: LVector, collection: ArrayList<LVector>): LVector
{
    var closestVector = collection[0]
    var smallestDistance: Double = vectorDistance(closestVector, v) + 1
    for (o in collection) {
        val distance: Double = vectorDistance(v, o)
        if (distance < smallestDistance && distance != 0.0) {
            closestVector = o
            smallestDistance = distance
        }
    }
    return closestVector
}

fun closestVectors(v: LVector, collection: ArrayList<LVector>, tolerance: Double): ArrayList<LVector>
{
    var closestVectors = ArrayList<LVector>()
    var smallestDistance: Double = vectorDistance(collection[0], v) + 1
    for (o in collection) {
        val distance: Double = vectorDistance(v, o)
        if (distance >= smallestDistance - tolerance && distance <= smallestDistance + tolerance) {
            closestVectors.add(o)
        }
        if (distance < smallestDistance - tolerance && distance != 0.0) {
            closestVectors = ArrayList()
            closestVectors.add(o)
            smallestDistance = distance
        }
    }
    return closestVectors
}

fun vectorDistance(a: LVector, b: LVector): Double
{
    return vectorLength(twoPointVector(a, b))
}

fun cylindricalToCartesianVector(v: LVector): LVector
{
    return LVector(v.x * cos(v.y), v.x * sin(v.y), v.z)
}

fun polarToCartesianVector(v: LVector): LVector
{
    val x = v.x * sin(v.y) * cos(v.z)
    val y = v.x * sin(v.y) * sin(v.z)
    val z = v.x * cos(v.y)
    return LVector(x, y, z)
}

fun randomVectors(regionWidth: Double, regionDepth: Double, regionHeight: Double, amount: Int): ArrayList<LVector> {
    val randomVectors = ArrayList<LVector>()
    for (i in 0 until amount) {
        val v = LVector(
            Math.random() * regionWidth,
            Math.random() * regionDepth,
            Math.random() * regionHeight
        )
        randomVectors.add(v)
    }
    return randomVectors
}

fun rectangularGrid(planeWidth: Double, planeHeight: Double, columns: Int, rows: Int): ArrayList<ArrayList<LVector>>
{
    val vectorGrid = ArrayList<ArrayList<LVector>>()
    val stepWidth = planeWidth / (columns - 1)
    val stepHeight = planeHeight / (rows - 1)
    for (i in 0 until rows) {
        val row = ArrayList<LVector>()
        for (j in 0 until columns) {
            row.add(LVector((j * stepWidth), (i * stepHeight)))
        }
        vectorGrid.add(row)
    }
    return vectorGrid
}