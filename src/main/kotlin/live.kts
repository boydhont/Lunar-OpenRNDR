@file:Suppress("UNUSED_LAMBDA_EXPRESSION")
import org.openrndr.Program
import org.openrndr.color.ColorRGBa

{ program: Program ->
    program.apply {
        extend {
            drawer.background(ColorRGBa.BLACK)

            drawer.fill = ColorRGBa.WHITE
            drawer.stroke = null

            drawer.circle(mouse.position, 100.0)
        }
    }
}