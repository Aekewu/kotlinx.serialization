// This file was automatically generated from formats.md by Knit tool. Do not edit.
package example.test

import org.junit.Test
import kotlinx.knit.test.*

class FormatsTest {
    @Test
    fun testExampleFormats01() {
        captureOutput("ExampleFormats01") { example.exampleFormats01.main() }.verifyOutputLines(
            "{BF}dnameukotlinx.serializationhlanguagefKotlin{FF}",
            "Project(name=kotlinx.serialization, language=Kotlin)"
        )
    }
}
