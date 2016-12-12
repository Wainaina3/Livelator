
'use strict';

// Module dependencies
var express    = require('express'),
  morgan       = require('morgan'),
  bodyParser   = require('body-parser');

module.exports = function (app) {
  // Only loaded when running in Bluemix
  // if (process.env.VCAP_APPLICATION) {
  //   require('./security')(app);
  // }

  // Configure Express
  app.use(bodyParser.urlencoded({ extended: true }));
  app.use(bodyParser.json());
  app.use(morgan('dev'));

  // Setup static public directory

};