var exec = require('cordova/exec');

/**
download({url:'',destinationPath:''})
*/
exports.download = function (arg0, success, error) {
    exec(success, error, 'DownloadUtils', 'download', [arg0]);
};

/**
download(path)
*/
exports.readFilePaths = function (arg0, success, error) {
    exec(success, error, 'DownloadUtils', 'readFilePaths', [arg0]);
};

exports.getDirectory = function (arg0,success, error) {
    exec(success, error, 'DownloadUtils', 'getDirectory', [arg0]);
};

/**
download({path:'',type:''})
*/
exports.openFile = function (arg0, success, error) {
    exec(success, error, 'DownloadUtils', 'openFile', [arg0]);
};
