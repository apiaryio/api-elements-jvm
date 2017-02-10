all: build

build:

	node_modules/.bin/browserify --standalone loadAPI index.js -o bundle.js -t [ babelify --presets [ es2015 ] ]
	javac Minim.java

deps:
	rm -rf node_modules
	npm install

clean:
	rm -rf bundle.js
	rm -rf Minim.class


.PHONY: clean deps
