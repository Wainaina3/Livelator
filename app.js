/*eslint-env node*/

//------------------------------------------------------------------------------
// node.js starter application for Bluemix
//------------------------------------------------------------------------------

// This application uses express as its web server
// for more info, see: http://expressjs.com
var express = require('express');

// cfenv provides access to your Cloud Foundry environment
// for more info, see: https://www.npmjs.com/package/cfenv
var cfenv = require('cfenv');

//require extend
var extend = require('extend');

// create a new express server
var app = express();

//load configs
require('./config/express')(app);

// serve the files out of ./public as our main files
app.use(express.static(__dirname + '/public'));

// get the app environment from Cloud Foundry
var appEnv = cfenv.getAppEnv();

var bluemix = require('./config/bluemix');

//get the watson cloud 
var watson = require('watson-developer-cloud');

// //credentials on bluemix
// var credentials = extend(config, bluemix.getServiceCreds('speech_to_text'));
// var authorization = watson.authorization(credentials);


// // Get token from Watson using your credentials
// app.get('/token', function(req, res) {
//   authorization.getToken({url: credentials.url}, function(err, token) {
//     if (err) {
//       console.log('error:', err);
//       res.status(err.code);
//     }
//     res.send(token);
//   });
// });



//Initiate a language translation object
// var language_translation = watson.language_translation({
//   username: '{c3bba1cf-13e4-4862-979a-96083fdcc7db}',
//   password: '{rhndTyzR10sw}',
//   version: 'v2'
// });

var mt_credentials = extend({
  url: 'https://gateway.watsonplatform.net/language-translation/api',
  username: 'user name to access MT service',
  password: 'password to access MT service',
  version: 'v2'
}, bluemix.getServiceCreds('langlated')); // VCAP_SERVICES
var translation_credentials = bluemix.getServiceCreds('langlated');

console.log("credentials");
console.log("credentials from bluemix....=>>>> " + JSON.stringify(translation_credentials));
//console.log(mt_credentials);


var language_translation = watson.language_translation(mt_credentials);


app.post('/api/translate', function(req, res, next) {
  
  var params = extend({ 'X-WDC-PL-OPT-OUT': req.header('X-WDC-PL-OPT-OUT')}, req.body);
  console.log(' ---> params == ' + JSON.stringify(params)); //L.R.
  
  language_translation.translate(params, function(err, models) {
  if (err)
    return next(err);
  else
    res.json(models);
  });
});




// start server on the specified port and binding host
app.listen(appEnv.port, '0.0.0.0', function() {
  // print a message when the server starts listening
  console.log("server starting on " + appEnv.url);
});
