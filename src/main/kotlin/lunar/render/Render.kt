package lunar.render

import lunar.LFace
import lunar.LMesh
import lunar.LVector
import org.openrndr.Program
import org.openrndr.draw.Drawer
import org.openrndr.math.Vector2

fun meshWireFrame2D(program: Program, mesh: LMesh)
{
    var graphicsBuffer: Drawer = program.drawer

    for(f: LFace in mesh.faces)
    {
        var a : LVector = mesh.vertices[f.a]
        var b : LVector = mesh.vertices[f.b]
        var c : LVector = mesh.vertices[f.c]

        var n : LVector = lunar.vectors.unitVector(lunar.vectors.crossProduct(lunar.vectors.twoPointVector(a,c), lunar.vectors.twoPointVector(a,b)))

        graphicsBuffer.lineSegment(Vector2(a.x, a.y),Vector2(b.x, b.y))
        graphicsBuffer.lineSegment(Vector2(b.x, b.y),Vector2(c.x, c.y))
        graphicsBuffer.lineSegment(Vector2(c.x, c.y),Vector2(a.x, a.y))
    }
}