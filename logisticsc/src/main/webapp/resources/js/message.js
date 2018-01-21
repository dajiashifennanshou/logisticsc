/**
 *消息弹出框
 */

//消息
function message(msg, status) {
	parent.layer.alert(msg, {icon: status});
}

//成功消息
function success(msg) {
	if(!msg || typeof(msg)=="undefined" || msg=='') {
		msg = '操作成功';
	}
	message(msg, 6);
}

//错误消息
function error(msg) {
	if(!msg || typeof(msg)=="undefined" || msg=='') {
		msg = '操作失败';
	}
	message(msg, 5);
}

//加载1
function load1(type,time) {
	if(!time && typeof(time)=="undefined" && time==0) {
		time = 2000;
	}
	layer.load(type);
	if(time== null || time==0) {
		//此处演示关闭
		setTimeout(function(){
			layer.closeAll('loading');
		}, time);
	}
}
