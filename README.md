# Refract in Java

Simple POC of using JS lib we use in JVM. It
uses [Nashorn](http://openjdk.java.net/projects/nashorn/) to run JS
code in JVM.

The approach is to use [browserify](http://browserify.org/) and bundle
everything in js file which is then evaluated by JVM and accessed from
Java. See `Minim.java` for the Java code and `index.js` for the JS code.

## Dependencies

See `package.json`.


## Build

`make deps` and then `make`.

## Usage

`java Minim <refract.json>`

It prints all annotation messages found the the parse result.

Use `warnings.json` file in the repo if none other is available to you.




