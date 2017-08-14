var exec = require('cordova/exec');
var PLUGIN_NAME = 'CordovaPinPlugin';

exports.showPin = function(arg0, success, error) {
    exec(success, error, PLUGIN_NAME, 'showPin', [arg0]);
};

exports.closePin = function(arg0, success, error) {
    exec(success, error, PLUGIN_NAME, 'closePin', [arg0]);
};

exports.clearPin = function(arg0, success, error) {
    exec(success, error, PLUGIN_NAME, 'clearPin', [arg0]);
};