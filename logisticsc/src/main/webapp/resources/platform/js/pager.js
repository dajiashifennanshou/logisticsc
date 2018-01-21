// JavaScript Document
	//container 容器，count 总页数 pageindex 当前页数 urlReq请求地址
function setPage(container, count, pageindex,urlReq) {
var container = container;
var containerId = $(container).attr("id");
var count = count;
var pageindex = pageindex;
var urlReq = urlReq;
var a = [];
var hrefs = $(".unclick");
for( var m=0; m<hrefs.length; m++) {
	hrefs[m].removeAttribute("href");
	hrefs[m].setAttribute("disabled","disabled");
}
if(count>0) {
  //总页数少于10 全部显示,大于10 显示前3 后3 中间3 其余....
  if (pageindex == 1) {
    a[a.length] = "<a href=\"javascript:;\" class=\"prev unclick\">上一页</a>";
  } else {
    a[a.length] = "<a href=\"javascript:;\" class=\"prev\">上一页</a>";
  }
  a[a.length] = "<a href=\"javascript:;\"  >首页</a>";
  function setPageList() {
    if (pageindex == i) {
      a[a.length] = "<a href=\"javascript:;\" class=\"on\">" + i + "</a>";
    } else {
      a[a.length] = "<a href=\"javascript:;\">" + i + "</a>";
    }
  }

  //总页数小于10
  if (count <= 10) {
    for (var i = 1; i <= count; i++) {
      setPageList();
    }
  }
  
  //总页数大于10页
  else {
    if (pageindex <= 4) {
      for (var i = 1; i <= 5; i++) {
        setPageList();
      }
      a[a.length] = "...<a href=\"javascript:;\">" + count + "</a>";
    } else if (pageindex >= count - 3) {
      a[a.length] = "<a href=\"javascript:;\">1</a>...";
      for (var i = count - 4; i <= count; i++) {
        setPageList();
      }
    }
    else { //当前页在中间部分
      a[a.length] = "<a href=\"javascript:;\">1</a>...";
      for (var i = parseInt(pageindex) - 2; i <= parseInt(pageindex) + 2; i++) {
        setPageList();
      }
      a[a.length] = "...<a href=\"javascript:;\">" + count + "</a>";
    }
  }
  a[a.length] = "<a href=\"javascript:;\"  >末页</a>";
  if (pageindex == count) {
    a[a.length] = "<a href=\"javascript:;\" class=\"next unclick\">下一页</a>";
  } else {
    a[a.length] = "<a href=\"javascript:;\" class=\"next\">下一页</a>";
  }
  container.innerHTML = a.join("");
  //+"跳转到<input readonly class='gotoPage unclick' type='text' class='wpage' style='width:30px;height:30px;margin-top:5px;border-radious:5px;'/>页<a class='gopage unclick' href='javascript:;' >GO</a>"
  //事件点击
  var pageClick = function() {
    var oAlink = container.getElementsByTagName("a");
    var inx = pageindex; //初始的页码
    oAlink[0].onclick = function() { //点击上一页
      if (inx == 1) {
    	  return false;
      }
      inx--;
      setPage(container, count, inx,urlReq);
      $("#"+containerId).parent().find(".currentPage").val(inx);
      urlReq();
    };
 
    for (var i = 1; i < oAlink.length - 1; i++) { //点击页码
      oAlink[i].onclick = function() {
        inx = parseInt(this.innerHTML);
        setPage(container, count, inx,urlReq);
        $("#"+containerId).parent().find(".currentPage").val(inx);
        urlReq();
      }
    }
    oAlink[1].onclick = function() { //点击首页
    	inx = 1;
        setPage(container, count, inx,urlReq);
        $("#"+containerId).parent().find(".currentPage").val(inx);
        urlReq();
      };
      
      oAlink[oAlink.length - 2].onclick = function() { //点击末页
    	  inx = count;
          setPage(container, count, inx,urlReq);
          $("#"+containerId).parent().find(".currentPage").val(inx);
          urlReq();
        };
/*        oAlink[oAlink.length-1].onclick = function() {// 点击跳转至第几页
        	inx = $(container).find(".gotoPage").val();
        	if(inx>count){
        		return;
        	}
        	$("#"+containerId).parent().find(".currentPage").val(inx);
        	setPage(container, count, inx,urlReq);
        	urlReq();
        }*/;
    oAlink[oAlink.length - 1].onclick = function() { //点击下一页
      if (inx == count) {
        return false;
      }
      inx++;
      setPage(container, count, inx,urlReq);
      $("#"+containerId).parent().find(".currentPage").val(inx);
      urlReq();
    }
  } ()
}
else {
	a[a.length] = "<a href=\"#\" class=\"unclick\"  >首页</a>" + 
						"<a href=\"#\" class=\"prev unclick\">上一页</a>"+
							  "<a href=\"#\" class=\"next unclick\">下一页</a>" +
							  "<a href=\"#\"  class=\"unclick\"  >末页</a>";
	container.innerHTML = a.join("");
	//
	//+"跳转到<input class='gotoPage' type='text' class='wpage' style='width:30px;height:30px;margin-top:5px;border-radious:5px;'/>页<a class='gopage' href='javascript:;' >GO</a>"
}
}
