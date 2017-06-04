
var exec = require('cordova/exec');

var PLUGIN_NAME = 'ShareText';

var ShareText = {
  share: function(phrase, cb) {
    exec(cb, null, PLUGIN_NAME, 'share', [phrase]);
  }
};

module.exports = ShareText;
