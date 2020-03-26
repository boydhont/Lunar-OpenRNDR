import lunar.LMesh
import lunar.LVector
import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.math.IntVector2

fun main() = application {

    configure {
        title = "Lunar"

        width = 1920/2
        height = 1080

        position = IntVector2(1920/2,0)
    }

    program {

        extend {
            drawer.background(ColorRGBa.BLACK)

            var mesh : LMesh = lunar.meshes.planeMesh(500.0, 500.0, 10,10)
            for(i : Int in mesh.vertices.indices) mesh.vertices[i] = lunar.vectors.rotatedVector(mesh.vertices[i], lunar.vectors.zAxisVector(1.0), Math.PI*.125)
            for(i : Int in mesh.vertices.indices) mesh.vertices[i] = lunar.vectors.rotatedVector(mesh.vertices[i], lunar.vectors.xAxisVector(1.0), Math.PI*.25)
            for(i : Int in mesh.vertices.indices) mesh.vertices[i] = lunar.vectors.addedVector(mesh.vertices[i], LVector(400.0,400.0,0.0))

            drawer.fill = null
            drawer.stroke = ColorRGBa.WHITE
            drawer.strokeWeight = 1.0

            lunar.render.meshWireFrame2D(this, mesh)
            //drawer.circle(mouse.position, 100.0)
        }
    }
}