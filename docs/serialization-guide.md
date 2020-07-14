# Kotlin Serialization Guide

Kotlin Serialization is cross-platform and multi-format framework for data serialization &mdash;
converting trees of objects to strings, byte arrays, or other _serial_ representations and back.
Kotlin Serialization fully supports and enforces Kotlin type system, making sure only valid 
objects can be deserialized. 
 
Kotlin Serialization is not just a library. It is a compiler plugin that is bundled with the Kotlin
compiler distribution itself. Build configuration is explained in [README.md](../README.md#setup). 
Once the project is set up, we can start serializing some classes.  

## Table of contents

* [Basic Serialization](basic-serialization.md) (**start reading here**)
* [Builtin Classes](builtin-classes.md)
* [Serializers](serializers.md)
* [Polymorphism](polymorphism.md)
* [JSON Configuration](json-configuration.md)
* [Alternative and custom formats (experimental)](formats.md)