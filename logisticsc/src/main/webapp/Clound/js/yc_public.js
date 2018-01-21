var request = { 
		QueryString : function(val) {
		var uri = window.location.href; 
		var re = new RegExp("" +val+ "\=([^\&\?]*)", "ig"); 
		return ((uri.match(re))?(uri.match(re)[0].substr(val.length+1)):null); 
	}, 
	QueryStrings : function() {
	var uri = window.location.search; 
	var re = /\w*\=([^\&\?]*)/ig; 
	var retval=[]; 
	while ((arr = re.exec(uri)) != null) 
		retval.push(arr[0]); 
		return retval; 
	}, 
	setQuery : function(val1, val2) {
		var a = this.QueryStrings(); 
		var retval = ""; 
		var seted = false; 
		var re = new RegExp("^" +val1+ "\=([^\&\?]*)$", "ig"); 
		for(var i=0; i<a.length; i++) { 
			if (re.test(a[i])) {
				seted = true; 
				a[i] = val1 +"="+ val2; 
			} 
		} 
		retval = a.join("&"); 
		return "?" +retval+ (seted ? "" : (retval ? "&" : "") +val1+ "=" +val2); 
	} 
}
//引入box
document.write('<script src="/logisticsc/Clound/assets/js/bootbox.js"></script>')
var yc_public={
	/**
	 * 获取屏幕高度
	 * @returns {Number}
	 */
	height:function(){
		var height = $(window).height() - 107;
		return height;
	},
	/**
	 * 封装的ajax方法
	 * @param options
	 */
	ajax:function(options){
		if(options.load){
			//打开加载框
			yc_public.loading('ajax_loading');
		}
		$.ajax({
			type:options.type || "post",
			url:options.url || "www.baidu.com",
			dataType:options.dataType || "json",
			async:options.async || false,
			data:options.data || null,
			success:function(data){
				if(options.result=="add"){
					//添加
					yc_public.add_mod_success(data);
				}else if(options.result=="mod"){
					//修改
					yc_public.add_mod_success(data);
				}else if(options.result=="del"){
					if(data.code==0){
						yc_public.dialog({"msg":data.msg||"提交成功!","callback":function(){
							grid.trigger("reloadGrid");
						}});
					}else{
						yc_public.dialog({"msg":data.msg||"提交失败!"});
					}
				}else if(options.success){
					options.success(data);
				}else{
					yc_public.alert({msg:yc_public.decode(data)});
				}
				//关闭加载框
				if(options.load){
					yc_public.closeLoading('ajax_loading');
				}
			},
			error:function(){
				//关闭加载框
				if(options.load){
					yc_public.closeLoading('ajax_loading');
				}
				yc_public.alert({msg:"异常.."});
			}
		});
	},
	ValitedLoadData:function(data){
		if(data.code!=0){
			if(data.code==10001){
				yc_public.dialog({msg:data.msg,callback:function(){
					window.parent.location="yc-login.yc";
				}})
			}
		}
	},
	/**
	 * 添加和修改成功的回调方法
	 * @param data
	 */
	add_mod_success:function(sdata){
		if(sdata.code==0){
			yc_public.dialog({"msg":sdata.msg||"提交成功!","color":"green","callback":function(){
				window.parent.f_refreshTab(sdata.lw);
				window.parent.f_showTab(sdata.lw);
				window.parent.f_removeTab(sdata.cw);
			}});
		}else{
			yc_public.dialog({"msg":sdata.msg || "提交失败!"});
		}
	},
	/**
	 * 弹窗提示
	 * @param options
	 */
	dialog:function(options){
		if(options.color){
			options.msg='<font color="'+options.color+'">'+options.msg||"--"+'</font>';
		}
		//添加的成功返回
		bootbox.dialog({
			title:options.title||"系统提示",
			message: options.msg, 
			buttons: {
				"success" : {
					"label" : "确定",
					"className" : "btn-sm btn-primary",
					"callback":function(){
						if(options.callback)options.callback();
					}
				}
			},
			onEscape:function(){
				if(options.callback)options.callback();
			}
		});
	},alert:function(options){
		
		//添加的成功返回
		bootbox.dialog({
			title:options.title||"警告",
			message: "<font color='red'>"+options.msg||""+"</font>", 
			buttons: {
				"success" : {
					"label" : "确定",
					"className" : "btn-sm btn-primary"
				}
			}
		});
	},
	confirm:function(options){
		if(options.color){
			options.msg='<font color="'+options.color+'">'+options.msg||"--"+'</font>';
		}
		bootbox.dialog({
			title:options.title||"系统提示",
			message: options.msg||"你确定吗？", 
			buttons: {
				"success" : {
					"label" : "确定",
					"className" : "btn-sm btn-primary",
					"callback":function(){
						if(options.callback)options.callback();
					}
				},
				"cancel":{
					"label":"取消",
					"className":"btn-sm btn-danger",
					"callback":function(){
						this.modal("hide");
					}
				}
			}
		});
	},
	loading:function(id){
		//var mbox='<div style="color:black;position:absolute;left:45%;top:50%;border:2px solid blue;background-color:white;width:200px;height:40px;line-height:30px;align:center;">正在执行...</div>';
		var mbox='<img style="z-index:9999;position:absolute;left:46%;top:38%;" src="/logisticsc/Clound/img/loading.gif" />';
		$('html').append('<div id='+ (id || "loading_")+' style="width:100%;height:100%;position:absolute;opacity:0.6;left:0;top:0;background-color:black;z-index:999;">'+mbox+'</div>');
	},
	closeLoading:function(id){
		$("#"+(id || "loading_")).remove();
	},
	/**
	 * 将字符串转换为json
	 * @param str
	 * @returns
	 */
	encode:function(str){
		return $.parseJSON(str);
	},
	/**
	 * 将json转换为字符串
	 * @param josn
	 * @returns
	 */
	decode:function(json){
		return JSON.stringify(json);
	},
	/**
	 * 比较两个值
	 */
	equals:function(a,b){
		if(a==b){
			
		}
	},
	/**
	 * 一个提示框，页面上需要友好提示时使用
	 * 
	 * autoClose:是否需要自动关闭，默认true
	 * times：自动关闭的延时时间
	 */
	tips:function(content,autoClose,times){
		var html='<div class="alert alert-success" style="align:center;position:absolute;top:20px;width:100%;" >'+
			'<button type="button" id="tips_close_10" class="close" data-dismiss="alert">'+
				'<i class="ace-icon fa fa-times"></i>'+
			'</button>'+
	
			'<strong>'+
				'<i class="ace-icon fa fa-check"></i>'+
				'Tips:'+
			'</strong>'+
	
			content+
			'<br />'+
		'</div>';
		$("html").append(html);
		//如果给了任意值，则表示，无需自动关闭
		if(autoClose || true){
			setTimeout(function(){
				$("#tips_close_10").trigger("click");
			},times || 3000);
		}
	},
	/**
	 * 给from表单赋值，通过表单name属性和json内的key属性对应来完成
	 * Author:Feng
	 */
	setData:function(formId,jsonData){
		var json=jsonData || null;
		for(var key in json){
			var v=json[key];
			//为其赋值
			var ele=$("#"+formId+" [name="+key+"]");
			if(ele.length==1){
				ele.val(v);
			}
		}
	},
	//获取指定form表单的数据，并转换为json
	getData:function(formId){
		//if(!formId) return;
		var from=$("#"+formId);
		var json={};
		//处理文本框
		var input=from.find("[group=val]");
		
		input.each(function(i,ele){
			var v=$(ele).val();
			var key=$(ele).attr("name");
			if(v instanceof  Array){
				v=v.toString();
			}
			if(!v){
				v=null;
			}
			json[key]=v;
		});
		return json;
	},
	EqualsObjec:function(x,y){
			// If both x and y are null or undefined and exactly the same 
			if ( x === y ) { 
			 return true; 
			} 
			 
			// If they are not strictly equal, they both need to be Objects 
			if ( ! ( x instanceof Object ) || ! ( y instanceof Object ) ) { 
			 return false; 
			} 
			 
			//They must have the exact same prototype chain,the closest we can do is
			//test the constructor. 
			if ( x.constructor !== y.constructor ) { 
			 return false; 
			} 
			  
			for ( var p in x ) { 
			 //Inherited properties were tested using x.constructor === y.constructor
			 if ( x.hasOwnProperty( p ) ) { 
			 // Allows comparing x[ p ] and y[ p ] when set to undefined 
			 if ( ! y.hasOwnProperty( p ) ) { 
			  return false; 
			 } 
			 
			 // If they have the same strict value or identity then they are equal 
			 if ( x[ p ] === y[ p ] ) { 
			  continue; 
			 } 
			 
			 // Numbers, Strings, Functions, Booleans must be strictly equal 
			 if ( typeof( x[ p ] ) !== "object" ) { 
			  return false; 
			 } 
			 
			 // Objects and Arrays must be tested recursively 
			 if ( ! Object.equals( x[ p ], y[ p ] ) ) { 
			  return false; 
			 } 
			 } 
			} 
			 
			for ( p in y ) { 
			 // allows x[ p ] to be set to undefined 
			 if ( y.hasOwnProperty( p ) && ! x.hasOwnProperty( p ) ) { 
			 return false; 
			 } 
			} 
			return true; 
	}
}
var tabItemButton={
		toAdd:function(id,name,url){
			window.parent.f_addTab(id,name,url);
		},
		toEdit:function(id,name,url){
			
		},
		
}
















