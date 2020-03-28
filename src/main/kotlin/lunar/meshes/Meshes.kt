package lunar.meshes

import lunar.LFace
import lunar.LMesh
import lunar.LVector
import java.util.*


fun boxMesh(boxWidth: Double, boxDepth: Double, boxHeight: Double, columns: Int, rows: Int, layers: Int) : LMesh{
    var boxMesh: LMesh = LMesh()

    val step: LVector = LVector(boxWidth/columns, boxDepth/rows, boxHeight/layers)

    var indexTable: Array<Array<Array<Int>>> =
        Array(columns+1) {
            Array(rows+1){
                Array(layers+1){-1}
            }
        }

    //Create vertices
    var index: Int = 0
    for(i in indexTable.indices){
        for(j in indexTable[0].indices){
            for(k in indexTable[0][0].indices){
                boxMesh.vertices.add(LVector(i*step.x, j*step.y, k*step.z))
                indexTable[i][j][k] = index
                index++
            }
        }
    }

    //Create faces
    for (j in 1 until indexTable[0].size) {
        for (k in 1 until indexTable[0][0].size) {
            //Left Plane
            boxMesh.faces.add(
                LFace(
                    indexTable[0][j - 1][k - 1],
                    indexTable[0][j][k - 1],
                    indexTable[0][j - 1][k]
                )
            )
            boxMesh.faces.add(
                LFace(
                    indexTable[0][j][k - 1],
                    indexTable[0][j][k],
                    indexTable[0][j - 1][k]
                )
            )

            //Right Plane
            val l: Int = indexTable.size - 1
            boxMesh.faces.add(
                LFace(
                    indexTable[l][j][k - 1],
                    indexTable[l][j - 1][k - 1],
                    indexTable[l][j][k]
                )
            )
            boxMesh.faces.add(
                LFace(
                    indexTable[l][j - 1][k],
                    indexTable[l][j][k],
                    indexTable[l][j - 1][k - 1]
                )
            )
        }
    }

    for (i in 1 until indexTable.size) {
        for (k in 1 until indexTable[0][0].size) {
            //Front plane
            boxMesh.faces.add(
                LFace(
                    indexTable[i-1][0][k],
                    indexTable[i][0][k-1],
                    indexTable[i-1][0][k-1]
                )
            )
            boxMesh.faces.add(
                LFace(
                    indexTable[i-1][0][k],
                    indexTable[i][0][k],
                    indexTable[i][0][k-1]
                )
            )

            //Back Plane
            val l: Int = indexTable[0].size - 1
            boxMesh.faces.add(
                LFace(
                    indexTable[i][l][k-1],
                    indexTable[i][l][k],
                    indexTable[i-1][l][k-1]
                )
            )
            boxMesh.faces.add(
                LFace(
                    indexTable[i-1][l][k-1],
                    indexTable[i][l][k],
                    indexTable[i-1][l][k]
                )
            )
        }
    }

    for (i in 1 until indexTable.size) {
        for (j in 1 until indexTable[0].size) {
            //Bottom Plane
            boxMesh.faces.add(
                LFace(
                    indexTable[i-1][j-1][0],
                    indexTable[i][j][0],
                    indexTable[i-1][j][0]
                )
            )
            boxMesh.faces.add(
                LFace(
                    indexTable[i][j-1][0],
                    indexTable[i][j][0],
                    indexTable[i-1][j-1][0]
                )
            )

            //Top Plane
            val l: Int = indexTable[0][0].size - 1
            boxMesh.faces.add(
                LFace(
                    indexTable[i][j][l],
                    indexTable[i][j-1][l],
                    indexTable[i-1][j-1][l]
                )
            )
            boxMesh.faces.add(
                LFace(
                    indexTable[i][j][l],
                    indexTable[i-1][j-1][l],
                    indexTable[i-1][j][l]
                )
            )
        }
    }

    return boxMesh
}

fun planeMesh(planeWidth: Double, planeHeight: Double, columns: Int, rows: Int): LMesh {
    val planeMesh = LMesh()
    val vectorGrid: ArrayList<ArrayList<LVector>> =
        lunar.vectors.rectangularGrid(planeWidth, planeHeight, columns + 1, rows + 1)

    //add vertices
    for (array in vectorGrid) for (v in array) planeMesh.vertices.add(v)

    //add faces
    for (i in 1 until vectorGrid.size) {
        for (j in 1 until vectorGrid[0].size) {
            val indexMap = intArrayOf(
                (i - 1) * vectorGrid[0].size + (j - 1),
                i * vectorGrid[0].size + (j - 1),
                (i - 1) * vectorGrid[0].size + j,
                i * vectorGrid[0].size + j
            )
            planeMesh.faces.add(LFace(indexMap[0], indexMap[1], indexMap[2]))
            planeMesh.faces.add(LFace(indexMap[3], indexMap[2], indexMap[1]))
        }
    }

    return planeMesh
}

fun triangleMesh(a: LVector, b: LVector, c: LVector): LMesh {
    val mesh = LMesh()
    mesh.vertices.add(a)
    mesh.vertices.add(b)
    mesh.vertices.add(c)
    mesh.faces.add(LFace(0, 1, 2))
    return mesh
}

fun faceVertices(face: LFace, parentMesh: LMesh): ArrayList<LVector> {
    val vectors = ArrayList<LVector>()
    vectors.add(parentMesh.vertices[face.a])
    vectors.add(parentMesh.vertices[face.b])
    vectors.add(parentMesh.vertices[face.c])
    return vectors
}

fun meshVertices(mesh: LMesh): ArrayList<LVector> {
    return mesh.vertices
}

fun meshEdges(mesh: LMesh): ArrayList<ArrayList<LVector>> {
    val edges = ArrayList<ArrayList<LVector>>()
    for (i in mesh.vertices.indices) {
        val a = mesh.vertices[i]
        for (j in mesh.vertices.indices) {
            if (j > i) {
                val b = mesh.vertices[j]
                var isPartner = false
                for (f in mesh.faces) {
                    var aFound = false
                    var bFound = false
                    if (i == f.a || i == f.b || i == f.c) aFound = true
                    if (j == f.a || j == f.b || j == f.c) bFound = true
                    if (aFound && bFound) isPartner = true
                }
                if (isPartner) {
                    val edge = ArrayList<LVector>()
                    edge.add(a)
                    edge.add(b)
                    edges.add(edge)
                }
            }
        }
    }
    return edges
}

fun culledFacesMesh(mesh: LMesh, pattern: ArrayList<Boolean>): LMesh {
    mesh.faces = lunar.lists.dispatchedLists(mesh.faces, pattern)[0]
    return mesh
}

fun culledVerticesMesh(mesh: LMesh, pattern: ArrayList<Boolean>): LMesh {
    mesh.vertices = lunar.lists.dispatchedLists(mesh.vertices, pattern)[0]
    var culledIndexes = ArrayList<Int>()
    for (i in mesh.vertices.indices) culledIndexes.add(i)
    culledIndexes = lunar.lists.dispatchedLists(culledIndexes, pattern)[0]
    val culledFaces = ArrayList<LFace>()
    for (f in mesh.faces) {
        var fixedIndexA = false
        var fixedIndexB = false
        var fixedIndexC = false
        for (i in culledIndexes.indices) {
            if (culledIndexes[i] == f.a) {
                f.a = i
                fixedIndexA = true
            }
            if (culledIndexes[i] == f.b) {
                f.b = i
                fixedIndexB = true
            }
            if (culledIndexes[i] == f.c) {
                f.c = i
                fixedIndexC = true
            }
        }
        if (fixedIndexA && fixedIndexB && fixedIndexC) culledFaces.add(LFace(f.a, f.b, f.c))
    }
    mesh.faces = culledFaces
    return mesh
}

fun deletedFacesMesh(mesh: LMesh, indexes: ArrayList<Int>): LMesh {
    val pattern = ArrayList<Boolean>()
    for (i in indexes.indices) {
        if (indexes[i] == i) pattern.add(false)
        else pattern.add(true)
    }
    return culledFacesMesh(mesh, pattern)
}

fun deletedVerticesMesh(mesh: LMesh, indexes: ArrayList<Int>): LMesh
{
    val pattern = ArrayList<Boolean>()
    for (i in indexes.indices) {
        if (indexes[i] == i) pattern.add(false)
        else pattern.add(true)
    }
    return culledVerticesMesh(mesh, pattern)
}

fun joinedMesh(meshes: ArrayList<LMesh>): LMesh
{
    val joinedMesh = LMesh()
    for (m in meshes) {
        val indexSize = joinedMesh.vertices.size
        val verticesList = ArrayList<ArrayList<LVector>>()
        verticesList.add(joinedMesh.vertices)
        verticesList.add(m.vertices)
        joinedMesh.vertices = lunar.lists.combinedList(verticesList)
        for (f in m.faces) {
            val updatedFace = LFace(f.a + indexSize, f.b + indexSize, f.c + indexSize)
            joinedMesh.faces.add(updatedFace)
        }
    }
    return joinedMesh
}