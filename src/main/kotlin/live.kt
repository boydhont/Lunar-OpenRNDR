import org.openrndr.Program
import org.openrndr.application
import org.openrndr.extra.olive.Olive
import org.openrndr.math.IntVector2

/**
 *  This is a template for a live program.
 *  The first you will run this program it will create a script file at src/main/kotlin/live.kts
 *  This script file can be modified while the program is running.
 *
 *  Please refer to https://guide.openrndr.org/#/10_OPENRNDR_Extras/C03_Live_coding for more
 *  instructions on using the live coding environment.
 */

fun main() = application {
    configure {
        width = 1920/2
        height = 1080

        position = IntVector2(1920/2,0)
    }
    program {
        extend(Olive<Program>())
    }
}