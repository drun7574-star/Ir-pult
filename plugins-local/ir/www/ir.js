var exec = require('cordova/exec');
exports.transmit = function (freq, pattern, ok, err) {
  exec(ok, err, 'IrLocal', 'transmit', [freq, pattern]);
};
exports.hasIrEmitter = function (ok, err) {
  exec(ok, err, 'IrLocal', 'has', []);
};
