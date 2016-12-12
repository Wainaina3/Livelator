
//This file will translate texts

var watson = require('watson-developer-cloud');
var language_translation = watson.language_translation({
  username: '{c3bba1cf-13e4-4862-979a-96083fdcc7db}',
  password: '{rhndTyzR10sw}',
  version: 'v2'
});

app.post('/api/translate', function(req, res, next) {
  //console.log('/v2/translate');
  
  var params = extend({ 'X-WDC-PL-OPT-OUT': req.header('X-WDC-PL-OPT-OUT')}, req.body);
  //console.log(' ---> params == ' + JSON.stringify(params)); //L.R.
  
  language_translation.translate(params, function(err, models) {
  if (err)
    return next(err);
  else
    res.json(models);
  });
});


function translate() {
	language_translation.translate({
    text: 'hello',
    source: 'en',
    target: 'es'
  }, function(err, translation) {
    if (err)
      console.log(err)
    else
      console.log(translation);
});

}