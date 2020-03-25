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

            drawer.fill = ColorRGBa.WHITE
            drawer.stroke = null

            drawer.circle(mouse.position, 100.0)
        }
    }
}