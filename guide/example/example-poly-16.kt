// This file was automatically generated from polymorphism.md by Knit tool. Do not edit.
package example.examplePoly16

import kotlinx.serialization.*
import kotlinx.serialization.json.*

import kotlinx.serialization.modules.*
import kotlin.reflect.KClass

val module = SerializersModule {
    @Suppress("UNCHECKED_CAST")
    for (baseClass in listOf(Any::class, Project::class) as List<KClass<Any>>) {
        polymorphic(baseClass) {
            subclass(OwnedProject::class)
        }                                                   
    }
}        
val format = Json { serializersModule = module }

interface Project {
    val name: String
}

@Serializable
@SerialName("owned")
class OwnedProject(override val name: String, val owner: String) : Project

@Serializable
class Data(
    val project: Project,
    @Polymorphic val any: Any 
)

fun main() {
    val project = OwnedProject("kotlinx.coroutines", "kotlin")
    val data = Data(project, project)
    println(format.encodeToString(data))
}        
