var exec = require('cordova/exec');
var PLUGIN_NAME = 'CordovaPinPlugin';

exports.showPin = function(arg0, success, error) {
    exec(success, error, PLUGIN_NAME, 'showPin', [arg0]);
};
