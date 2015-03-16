protobuf-gcless
===============

Java library for generating java classes from proto files.


This is a fork of https://github.com/dernasherbrezon/protobuf-gcless project.
Please consult this website for documentation.

Additional options added:
  * generate.gson
  * message.extends.class
  * enum.implements.interface (interface must have "int getValue()" method)
  * gson.enum.adapter (needs to be a class with 1 generic type representing enum, needs to handle GSON serialization/deserialization)
  * gson.helper.package (package name where GsonHelper generated class will live)
  * generate.serializer

These must be specified together
  * enum.implements.interface
  * gson.enum.adapter
  * gson.helper.package
