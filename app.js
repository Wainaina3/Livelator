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


//passport aunthetication
var passport = require('passport');
var MCABackendStrategy = require('bms-mca-token-validation-strategy').MCABackendStrategy;

passport.use(new MCABackendStrategy());

app.use(passport.initialize());

app.get('/protected', passport.authenticate('mca-backend-strategy', {session: false }),
    function(request, response){
        console.log("Securty context", request.securityContext)    
        response.status(200).send("Success! got the request");
    }
);

app.get('/audio',function(request,response){
  console.log("request from android");
  response.status(200).send("Success! request");
})

//translation
var mt_credentials = extend({
  url: 'https://gateway.watsonplatform.net/language-translation/api',
  username: 'user name to access MT service',
  password: 'password to access MT service',
  version: 'v2'
}, bluemix.getServiceCreds('language_translator')); // VCAP_SERVICES
var translation_credentials = bluemix.getServiceCreds('language_translator');

console.log("credentials");
console.log("credentials from bluemix....=>>>> " + JSON.stringify(translation_credentials));
console.log(mt_credentials);


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

//speech transcription

var stt_credentials = extend({
  url: 'https://stream.watsonplatform.net/speech-to-text/api',
  username: 'user name to access STT service',
  password: 'password to access STT service',
  version: 'v1'
}, bluemix.getServiceCreds('speech_to_text')); // VCAP_SERVICES
var fs = require('fs');

//show credentials
var sttcredentials = bluemix.getServiceCreds('speech_to_text');

//initialize stt service
var speech_texted = watson.speech_to_text(stt_credentials);

var params = {
  model: 'en-US_BroadbandModel',
  content_type: 'audio/flac',
  continuous: true,
  'interim_results': true,
  'max_alternatives': 3,
  'word_confidence': false,
  timestamps: false,
  keywords: ['trials', 'good', 'hello'],
  'keywords_threshold': 0.5
};

// Create the stream.
var recognizeStream = speech_texted.createRecognizeStream(params);

// Pipe in the audio.
fs.createReadStream('audio-file.flac').pipe(recognizeStream);

// Pipe out the transcription to a file.
recognizeStream.pipe(fs.createWriteStream('transcription.txt'));

// Get strings instead of buffers from 'data' events.
recognizeStream.setEncoding('utf8');

// Listen for events.
recognizeStream.on('results', function(event) { onEvent('Results:', event); });
recognizeStream.on('data', function(event) { onEvent('Data:', event); });
recognizeStream.on('error', function(event) { onEvent('Error:', event); });
recognizeStream.on('close', function(event) { onEvent('Close:', event); });
recognizeStream.on('speaker_labels', function(event) { onEvent('Speaker_Labels:', event); });

// Displays events on the console.
function onEvent(name, event) {
  console.log(name, JSON.stringify(event, null, 2));
};

// Get token from Watson using your credentials
app.get('/token', function(req, res) {
  authorization.getToken({url: credentials.url}, function(err, token) {
    if (err) {
      console.log('error:', err);
      res.status(err.code);
    }
    res.send(token);
  });
});







// start server on the specified port and binding host
app.listen(appEnv.port, '0.0.0.0', function() {
  // print a message when the server starts listening
  console.log("server starting on " + appEnv.url);
});
