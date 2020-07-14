<!--- TEST_NAME JsonConfigurationTest -->

## JSON configuration


**Table of contents**:

<!--- TOC -->

  * [Json Instances](#json-instances)
  * [Pretty printing](#pretty-printing)
  * [Encoding defaults](#encoding-defaults)
  * [Ignoring unknown keys](#ignoring-unknown-keys)
  * [Allowing structured map keys](#allowing-structured-map-keys)
  * [Coercing input values](#coercing-input-values)
  * [Allowing special floating point values](#allowing-special-floating-point-values)
  * [Class discriminator](#class-discriminator)

<!--- END -->

### Json Instances

By default, [Json] implementation is quite strict with respect to invalid inputs, enforces Kotlin type safety, and
restricts Kotlin values that can be serialized so that the resulting JSON representations are standard.
Many non-standard JSON features are supported by creating a custom instance of a JSON _format_.    

JSON format configuration can be specified by creating your own [Json] class instance using an existing 
instance, such as a default `Json` object, and a [Json()] builder function. Additional parameters
are specified in a block via [JsonBuilder] DSL. The resulting `Json` format instance is immutable and thread-safe; 
it can be simply stored in a top-level property. 

This chapter shows various configuration features that [Json] supports.

<!--- INCLUDE .*-json-.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
-->

### Pretty printing

JSON can be configured to pretty print the output by setting the [prettyPrint][JsonBuilder.prettyPrint] property.

```kotlin
val format = Json { prettyPrint = true }

@Serializable 
data class Project(val name: String, val language: String)

fun main() {                                      
    val data = Project("kotlinx.serialization", "Kotlin")
    println(format.encodeToString(data))
}
```

> You can get the full code [here](../guide/example/example-json-01.kt).

It gives the following nice result.

```text 
{
    "name": "kotlinx.serialization",
    "language": "Kotlin"
}
``` 

<!--- TEST -->

### Encoding defaults 

Default values of properties don't have to be encoded, because they will be reconstructed during encoding anyway.
It can be configured by the [encodeDefaults][JsonBuilder.encodeDefaults] property.
This is especially useful for nullable properties with null defaults to avoid writing the corresponding 
null values.

```kotlin
val format = Json { encodeDefaults = false }

@Serializable 
class Project(
    val name: String, 
    val language: String = "Kotlin",
    val website: String? = null
)           

fun main() {
    val data = Project("kotlinx.serialization")
    println(format.encodeToString(data))
}
```                                  

> You can get the full code [here](../guide/example/example-json-02.kt).

Produces the following output which has only the `name` property:

```text
{"name":"kotlinx.serialization"}
```                 

<!--- TEST -->

### Ignoring unknown keys

JSON format is often used to read output of 3rd-party services or in otherwise highly-dynamic environment where
new properties could be added as a part of API evolution. By default, unknown keys encountered during deserialization produces an error. 
This behavior can be configured with 
the [ignoreUnknownKeys][JsonBuilder.ignoreUnknownKeys] property.

```kotlin
val format = Json { ignoreUnknownKeys = true }

@Serializable 
data class Project(val name: String)
    
fun main() {             
    val data = format.decodeFromString<Project>("""
        {"name":"kotlinx.serialization","language":"Kotlin"}
    """)
    println(data)
}
```                                  

> You can get the full code [here](../guide/example/example-json-03.kt).

It decodes the object, despite the fact that it is missing the `language` property.
 
```text
Project(name=kotlinx.serialization)
``` 

<!--- TEST -->

### Allowing structured map keys

JSON format does not natively support the concept of a map with structured keys. Keys in JSON objects
are strings and can be used to represent only primitives or enums by default.
A non-standard support for structured keys can be enabled with 
the [allowStructuredMapKeys][JsonBuilder.allowStructuredMapKeys] property.

```kotlin
val format = Json { allowStructuredMapKeys = true }

@Serializable 
data class Project(val name: String)
    
fun main() {             
    val map = mapOf(
        Project("kotlinx.serialization") to "Serialization",
        Project("kotlinx.coroutines") to "Coroutines"
    )
    println(format.encodeToString(map))
}
```                                  

> You can get the full code [here](../guide/example/example-json-04.kt).

The map with structured keys gets represented as `[key1, value1, key2, value2,...]` JSON array.
 
```text
[{"name":"kotlinx.serialization"},"Serialization",{"name":"kotlinx.coroutines"},"Coroutines"]
``` 

<!--- TEST -->

### Coercing input values

JSON formats that are encountered in the wild can be flexible in terms of types and evolve quickly.
This can lead to exceptions during decoding when the actual values do not match the expected values. 
By default [Json] implementation is strict with respect to input types as was demonstrated in
the [Type safety is enforced](basic-serialization.md#type-safety-is-enforced) section. It can be somewhat relaxed using
the [coerceInputValues][JsonBuilder.coerceInputValues] property. 

This property only effects decoding. It treats a limited subset of invalid input values as if the
corresponding property was missing and uses a default value of the corresponding property instead.
The current list of supported invalid values is:

* `null` inputs for a non-nullable types.
* Unknown values for enums.

> This list may be expanded in the future, so that [Json] instance configured with this property becomes even more
> permissive to invalid value in the input, replacing them with defaults.    

Let us take the example from the [Type safety is enforced](basic-serialization.md#type-safety-is-enforced) section.

```kotlin
val format = Json { coerceInputValues = true }

@Serializable 
data class Project(val name: String, val language: String = "Kotlin")

fun main() {
    val data = format.decodeFromString<Project>("""
        {"name":"kotlinx.serialization","language":null}
    """)
    println(data)
}
```                                  

> You can get the full code [here](../guide/example/example-json-05.kt).

We see that invalid `null` value for the `language` property was coerced into the default value.

```text
Project(name=kotlinx.serialization, language=Kotlin)
```    

<!--- TEST -->                

### Allowing special floating point values

As we saw in the [Special floating point values](builtin-classes.md#special-floating-point-values) section, 
by default, special floating-point values like [Double.NaN] and infinities are not supported in JSON.
They can be enabled using the [allowSpecialFloatingPointValues][JsonBuilder.allowSpecialFloatingPointValues]
property.

```kotlin       
val format = Json { allowSpecialFloatingPointValues = true }

@Serializable
class Data(
    val value: Double
)                     

fun main() {
    val data = Data(Double.NaN)
    println(format.encodeToString(data))
}
```                                   

> You can get the full code [here](../guide/example/example-json-06.kt).

This example produces the following non-stardard JSON output, yet it is a widely used encoding for
special values in JVM world.

```text
{"value":NaN}
```   

<!--- TEST -->

### Class discriminator

A key name that specifies a type when you have a polymorphic data can be specified 
with the [classDiscriminator][JsonBuilder.classDiscriminator] property. 

```kotlin        
val format = Json { classDiscriminator = "#class" }

@Serializable
sealed class Project {
    abstract val name: String
}
            
@Serializable         
@SerialName("owned")
class OwnedProject(override val name: String, val owner: String) : Project()

fun main() {
    val data: Project = OwnedProject("kotlinx.coroutines", "kotlin")
    println(format.encodeToString(data))
}  
```

> You can get the full code [here](../guide/example/example-json-07.kt).

In combination with an explicitly specified [SerialName] of the class it provides full
control on the resulting JSON object. 

```text 
{"#class":"owned","name":"kotlinx.coroutines","owner":"kotlin"}
```                   

<!--- TEST -->

<!-- stdlib references -->
[Double.NaN]: https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/-na-n.html

<!--- MODULE /kotlinx-serialization -->
<!--- INDEX kotlinx.serialization -->
[SerialName]: https://kotlin.github.io/kotlinx.serialization/kotlinx-serialization/kotlinx.serialization/-serial-name/index.html
<!--- INDEX kotlinx.serialization.json -->
[Json]: https://kotlin.github.io/kotlinx.serialization/kotlinx-serialization/kotlinx.serialization.json/-json/index.html
[Json()]: https://kotlin.github.io/kotlinx.serialization/kotlinx-serialization/kotlinx.serialization.json/-json.html
[JsonBuilder]: https://kotlin.github.io/kotlinx.serialization/kotlinx-serialization/kotlinx.serialization.json/-json-builder/index.html
[JsonBuilder.prettyPrint]: https://kotlin.github.io/kotlinx.serialization/kotlinx-serialization/kotlinx.serialization.json/-json-builder/pretty-print.html
[JsonBuilder.encodeDefaults]: https://kotlin.github.io/kotlinx.serialization/kotlinx-serialization/kotlinx.serialization.json/-json-builder/encode-defaults.html
[JsonBuilder.ignoreUnknownKeys]: https://kotlin.github.io/kotlinx.serialization/kotlinx-serialization/kotlinx.serialization.json/-json-builder/ignore-unknown-keys.html
[JsonBuilder.allowStructuredMapKeys]: https://kotlin.github.io/kotlinx.serialization/kotlinx-serialization/kotlinx.serialization.json/-json-builder/allow-structured-map-keys.html
[JsonBuilder.coerceInputValues]: https://kotlin.github.io/kotlinx.serialization/kotlinx-serialization/kotlinx.serialization.json/-json-builder/coerce-input-values.html
[JsonBuilder.allowSpecialFloatingPointValues]: https://kotlin.github.io/kotlinx.serialization/kotlinx-serialization/kotlinx.serialization.json/-json-builder/allow-special-floating-point-values.html
[JsonBuilder.classDiscriminator]: https://kotlin.github.io/kotlinx.serialization/kotlinx-serialization/kotlinx.serialization.json/-json-builder/class-discriminator.html
<!--- END -->

