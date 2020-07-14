// This file was automatically generated from json-configuration.md by Knit tool. Do not edit.
package example.test

import org.junit.Test
import kotlinx.knit.test.*

class JsonConfigurationTest {
    @Test
    fun testExampleJson01() {
        captureOutput("ExampleJson01") { example.exampleJson01.main() }.verifyOutputLines(
            "{",
            "    \"name\": \"kotlinx.serialization\",",
            "    \"language\": \"Kotlin\"",
            "}"
        )
    }

    @Test
    fun testExampleJson02() {
        captureOutput("ExampleJson02") { example.exampleJson02.main() }.verifyOutputLines(
            "{\"name\":\"kotlinx.serialization\"}"
        )
    }

    @Test
    fun testExampleJson03() {
        captureOutput("ExampleJson03") { example.exampleJson03.main() }.verifyOutputLines(
            "Project(name=kotlinx.serialization)"
        )
    }

    @Test
    fun testExampleJson04() {
        captureOutput("ExampleJson04") { example.exampleJson04.main() }.verifyOutputLines(
            "[{\"name\":\"kotlinx.serialization\"},\"Serialization\",{\"name\":\"kotlinx.coroutines\"},\"Coroutines\"]"
        )
    }

    @Test
    fun testExampleJson05() {
        captureOutput("ExampleJson05") { example.exampleJson05.main() }.verifyOutputLines(
            "Project(name=kotlinx.serialization, language=Kotlin)"
        )
    }

    @Test
    fun testExampleJson06() {
        captureOutput("ExampleJson06") { example.exampleJson06.main() }.verifyOutputLines(
            "{\"value\":NaN}"
        )
    }

    @Test
    fun testExampleJson07() {
        captureOutput("ExampleJson07") { example.exampleJson07.main() }.verifyOutputLines(
            "{\"#class\":\"owned\",\"name\":\"kotlinx.coroutines\",\"owner\":\"kotlin\"}"
        )
    }
}
