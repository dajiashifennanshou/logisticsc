__CreateJSPath = function (js) {
    var scripts = document.getElementsByTagName("script");
    var path = "";
    for (var i = 0, l = scripts.length; i < l; i++) {
        var src = scripts[i].src;
        if (src.indexOf(js) != -1) {
            var ss = src.split(js);
            path = ss[0];
            break;
        }
    }
    var href = location.href;
    href = href.split("#")[0];
    href = href.split("?")[0];
    var ss = href.split("/");
    ss.length = ss.length - 1;
    href = ss.join("/");
    if (path.indexOf("https:") == -1 && path.indexOf("http:") == -1 && path.indexOf("file:") == -1 && path.indexOf("\/") != 0) {
        path = href + "/" + path;
    }
    return path;
}

var bootPATH = __CreateJSPath("boot.js");

document.write('<script src="' + bootPATH + 'js/jquery-1.8.1.min.js" type="text/javascript"></sc' + 'ript>');
document.write('<script src="' + bootPATH + 'newbui/js/bui.js" type="text/javascript"></sc' + 'ript>');
document.write('<script src="' + bootPATH + 'js/config.js" type="text/javascript"></sc' + 'ript>');
document.write('<script src="' + bootPATH + 'js/sys/systemUtil.js" type="text/javascript"></sc' + 'ript>');
document.write('<script src="' + bootPATH + 'js/sys/json2.min.js" type="text/javascript" ></sc' + 'ript>');
document.write('<link href="' + bootPATH + 'newbui/css/bs3/dpl.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH + 'newbui/css/bs3/bui.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH + 'newbui/main.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH + 'css/font-awesome.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH + 'newbui/new-file.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH + 'css/page.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + bootPATH + 'platform/img/xslwl56.ico" rel="shortcut icon" type="image/x-icon" />');


//skin
var skin = getCookie("easyuiSkin");
if (skin) {
    document.write('<link href="' + bootPATH + 'themes/' + skin + '/easyui.css" rel="stylesheet" type="text/css" />');
}


function setCookie(sName,sValue){
	document.cookie=sName+"="+sValue;
}

////////////////////////////////////////////////////////////////////////////////////////
function getCookie(sName) {
    var aCookie = document.cookie.split("; ");
    var lastMatch = null;
    for (var i = 0; i < aCookie.length; i++) {
        var aCrumb = aCookie[i].split("=");
        if (sName == aCrumb[0]) {
            lastMatch = aCrumb;
        }
    }
    if (lastMatch) {
        var v = lastMatch[1];
        if (v === undefined) return v;
        return unescape(v);
    }
    return null;
}