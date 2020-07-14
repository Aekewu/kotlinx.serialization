<!--- TEST_NAME FormatsTest -->

# Alternative and custom formats (experimental)

This is the sixth chapter of the [Kotlin Serialization Guide](serialization-guide.md).
It goes beyond JSON and covers alternative and custom formats. Unlike JSON, which is
stable, these are currently experimental features of Kotlin serialization.

<!--- TOC -->

  * [CBOR](#cbor)

<!--- END -->

### CBOR 

[CBOR](https://en.wikipedia.org/wiki/CBOR) is one of the standard compact binary
encodings for JSON, so it supports a subset of [JSON features](json-configuration.md) and
is generally very similar to JSON in use, but produces binary data.

[Cbor] class has [Cbor.encodeToByteArray] and [Cbor.decodeFromByteArray] functions.
Let us take the basic example from the [JSON encoding](basic-serialization.md#json-encoding), 
but encode it using CBOR. 

<!--- INCLUDE
import kotlinx.serialization.*
import kotlinx.serialization.cbor.*

fun ByteArray.toAsciiHexString() = joinToString("") {
    if (it in 32..127) it.toChar().toString() else
        "{${it.toUByte().toString(16).padStart(2, '0').toUpperCase()}}"
}
-->

```kotlin    
@Serializable
data class Project(val name: String, val language: String)

fun main() {
    val data = Project("kotlinx.serialization", "Kotlin") 
    val bytes = Cbor.encodeToByteArray(data)   
    println(bytes.toAsciiHexString())
    val obj = Cbor.decodeFromByteArray<Project>(bytes)
    println(obj)
}
```                                  

> You can get the full code [here](../guide/example/example-formats-01.kt).

We print filtered ASCII representation of the output, writing non-ASCII data in hex, so we see how 
all the original strings are directly represented in CBOR, but the format delimiters itself are binary.

```text 
{BF}dnameukotlinx.serializationhlanguagefKotlin{FF}
Project(name=kotlinx.serialization, language=Kotlin)
```

<!--- TEST -->


<!--- MODULE /kotlinx-serialization -->
<!--- INDEX kotlinx.serialization -->
<!--- INDEX kotlinx.serialization.cbor -->
[Cbor]: https://kotlin.github.io/kotlinx.serialization/kotlinx-serialization/kotlinx.serialization.cbor/-cbor/index.html
[Cbor.encodeToByteArray]: https://kotlin.github.io/kotlinx.serialization/kotlinx-serialization/kotlinx.serialization.cbor/-cbor/encode-to-byte-array.html
[Cbor.decodeFromByteArray]: https://kotlin.github.io/kotlinx.serialization/kotlinx-serialization/kotlinx.serialization.cbor/-cbor/decode-from-byte-array.html
<!--- END -->
   
