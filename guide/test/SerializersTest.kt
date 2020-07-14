// This file was automatically generated from serializers.md by Knit tool. Do not edit.
package example.test

import org.junit.Test
import kotlinx.knit.test.*

class SerializersTest {
    @Test
    fun testExampleSerializer01() {
        captureOutput("ExampleSerializer01") { example.exampleSerializer01.main() }.verifyOutputLines(
            "{\"rgb\":65280}"
        )
    }

    @Test
    fun testExampleSerializer02() {
        captureOutput("ExampleSerializer02") { example.exampleSerializer02.main() }.verifyOutputLines(
            "Color(rgb: kotlin.Int)"
        )
    }

    @Test
    fun testExampleSerializer03() {
        captureOutput("ExampleSerializer03") { example.exampleSerializer03.main() }.verifyOutputLines(
            "Box(contents: Color)"
        )
    }

    @Test
    fun testExampleSerializer04() {
        captureOutput("ExampleSerializer04") { example.exampleSerializer04.main() }.verifyOutputLines(
            "\"00ff00\""
        )
    }

    @Test
    fun testExampleSerializer05() {
        captureOutput("ExampleSerializer05") { example.exampleSerializer05.main() }.verifyOutputLines(
            "65280"
        )
    }

    @Test
    fun testExampleSerializer06() {
        captureOutput("ExampleSerializer06") { example.exampleSerializer06.main() }.verifyOutputLines(
            "{\"background\":\"fffffff\",\"foreground\":\"000000\"}"
        )
    }

    @Test
    fun testExampleSerializer07() {
        captureOutput("ExampleSerializer07") { example.exampleSerializer07.main() }.verifyOutputLines(
            "{\"r\":0,\"g\":255,\"b\":0}"
        )
    }

    @Test
    fun testExampleSerializer08() {
        captureOutput("ExampleSerializer08") { example.exampleSerializer08.main() }.verifyOutputLines(
            "{\"r\":0,\"g\":255,\"b\":0}"
        )
    }

    @Test
    fun testExampleSerializer09() {
        captureOutput("ExampleSerializer09") { example.exampleSerializer09.main() }.verifyOutputLines(
            "1455494400000"
        )
    }

    @Test
    fun testExampleSerializer10() {
        captureOutput("ExampleSerializer10") { example.exampleSerializer10.main() }.verifyOutputLines(
            "{\"name\":\"Kotlin\",\"stableReleaseDate\":1455494400000}"
        )
    }

    @Test
    fun testExampleSerializer12() {
        captureOutput("ExampleSerializer12") { example.exampleSerializer12.main() }.verifyOutputLinesStart(
            "Exception in thread \"main\" kotlinx.serialization.SerializationException: Serializer for class 'Date' is not found.",
            "Mark the class as @Serializable or provide the serializer explicitly."
        )
    }

    @Test
    fun testExampleSerializer14() {
        captureOutput("ExampleSerializer14") { example.exampleSerializer14.main() }.verifyOutputLines(
            "{\"name\":\"kotlinx.serialization\",\"language\":\"Kotlin\"}"
        )
    }

    @Test
    fun testExampleSerializer15() {
        captureOutput("ExampleSerializer15") { example.exampleSerializer15.main() }.verifyOutputLines(
            "{\"name\":\"kotlinx.serialization\",\"stars\":9000}"
        )
    }
}
