window.System = {};

//设置cookie
System.setCookie = function(sName,sValue) {
	document.cookie = sName + "=" + sValue;
}

//获cookie
System.getCookie = function(sName) {
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


//判断各种浏览器，找到正确的方法,全屏
System.launchFullscreen = function(element) {
  if(element.requestFullscreen) {
    element.requestFullscreen();
  } else if(element.mozRequestFullScreen) {
    element.mozRequestFullScreen();
  } else if(element.webkitRequestFullscreen) {
    element.webkitRequestFullscreen();
  } else if(element.msRequestFullscreen) {
    element.msRequestFullscreen();
  }
}

System.fullScreen = function() {
	System.launchFullscreen(document.documentElement);
}

//退出全屏模式!
System.exitFullscreen = function() {
  if(document.exitFullscreen) {
    document.exitFullscreen();
  } else if(document.mozCancelFullScreen) {
    document.mozCancelFullScreen();
  } else if(document.webkitExitFullscreen) {
    document.webkitExitFullscreen();
  }
}

//分页表示数据页大小
System.pageSize = 15;
//分页表示数据当前页码
System.pageIndex = 1;
//分页表示数据页大小
/*{
    "limit " : System.pageSize, 
    "pageIndex " : System.pageIndex
}*/

/*window.getUrl = function(url) {
	var suffix = ".shtml";
	return url+suffix;
}*/
window.getUrl = function(url) {
	return url;
}


/*删除数组指定元素
 *baoremove
 *数组.remove(index);
 * */
Array.prototype.remove = function(dx) { 
    if(isNaN(dx)||dx>this.length){return false;} 
    this.splice(dx,1); 
}; 

window.Service = {};
Service.ctx = getRootPath();

//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    //return(localhostPaht+projectName);
    return projectName;
}
