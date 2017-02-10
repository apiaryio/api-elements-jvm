/*
  Nashorn has not __defineGetter__ so we make one.
*/
if (!Object.prototype.__defineGetter__ &&
    Object.defineProperty({},"x",{get: function(){return true}}).x) {
  Object.defineProperty(Object.prototype, "__defineGetter__",
                        {enumerable: false, configurable: true,
                         value: function(name,func)
                         {Object.defineProperty(this,name,
                                                {get:func,enumerable: true,configurable: true});
                         }});
  Object.defineProperty(Object.prototype, "__defineSetter__",
                        {enumerable: false, configurable: true,
                         value: function(name,func)
                         {Object.defineProperty(this,name,
                                                {set:func,enumerable: true,configurable: true});
                         }});
}
/*
  Browserify solves the require so we don't need anything like jvm-npm.js
*/
var minim = require('minim');
var namespace = minim.namespace();
var minimPraseResult = require('minim-parse-result');

namespace.use(minimPraseResult);

var ParseResult = namespace.getElementClass('parseResult');

/*
  Simple function which takes JSON string of refract and returns ParseResult
*/
module.exports = function loadAPI(api)  {
  var result = (new ParseResult()).fromRefract(JSON.parse(api));
  return result;
};

