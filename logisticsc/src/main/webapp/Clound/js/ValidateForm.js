/**
 * author:feng
 * 表单有效性校验，只针对表单
 */
$.extend($.fn,{
	FengValid:function(page){
		if ( $( this[ 0 ] ).is( "form" ) ) {
			var result=true;
			var form=this[0];
			if(page) form=$(form).children("[data-step="+page+"]")[0];
			$(form).find("[valited]").each(function(i,ele){
				//要验证的类型 --集合
				var thisType=$(ele).attr("valited").split(',');
				//当前标签的name
				var thisName=$(ele).attr("name") || $(ele).attr("id");
				//当前标签的值
				var thisValue=$(ele).val();
				//当前标签的提示label的值
				var thisLabel=$("[for="+thisName+"]");
				var emsg=thisLabel.html() || thisName;
				//遍历验证类型
				for(var i=0;i<thisType.length;i++){
					var type=thisType[i];
					//如果没有这个验证属性，则不作响应
					if(!_ValidateForm.messages[type]) continue;
					//如果有一项验证失败，就直接ruturn
					if(!_ValidateForm[type].call(this,thisValue,ele)){
						_ValidateForm.error(emsg,type,ele);
						result=false;
						break;
					}
				}
				if(!result) return false;
			});
			return result;
		}else{
			console.log("这不是一个有效的form表单..");
			return false;
		}
	}
});
var _ValidateForm={
		error:function(tips,type,ele){
			var msg=_ValidateForm.messages[type];
			if(type=='float') {
				var length=$(ele).attr('float');
				msg=msg.replace('{{length}}',length || 2);
			}
			if(type='number'){
				var length=$(ele).attr('length');
				msg=msg.replace('{{length}}',length || 5);
			}
			yc_public.tips(msg.replace(/{{name}}/,tips));
			$(ele).focus();
		},
		required:function(value,ele){
				return _ValidateForm.strIsNull(value);
		},
		number:function(value,ele){
			var length=$(ele).attr('length') || 5;
			if(value.length>length){
				return false;
			}
			//如果可用，就返回true
			return false || /^(?:-?\d+|-?\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test( value );
		},
		float:function(value,ele){
			var length=$(ele).attr('float') || 2;
			//获取限制小数点后几位
			eval("var reg=/^(([1-9]+)|([0-9]+\.[0-9]{1,"+length+"}))$/");
			return false || reg.test( value );
		},
		phone:function(value,ele){
			//如果可用，就返回true
			return false || /^1\d{10}$/.test( value );
		},
		email:function(value,ele){
			//如果可用，就返回true
			return false || /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/.test( value );;
		},
		date:function(value,ele){
			return false || !/Invalid|NaN/.test( new Date( value ).toString() );
		},
		carNumber:function(value,ele){
			//车牌号验证
			return false || /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/.test(value)
		},
		compareDate:function(value,ele){
			//获取他要比较的那个数据，此属性代表大于指定值
			var grenter=$(ele).attr("grenter");
			var less=$(ele).attr("less");
			var tDate=new Date(value);
			var eDate;
			if(!greeter && !less){
				return false;
			}
			if(grenter){
				eDate=new Date($('[name='+grenter+']').val());
				if(tDate<eDate){
					return false;
				}
			}else{
				eDate=new Date($('[name='+less+']').val());
				if(tDate>eDate){
					return false;
				}
			}
			return true;
		},
		url:function(value,ele){
			//如果可用，就返回true
			return false || /^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})).?)(?::\d{2,5})?(?:[/?#]\S*)?$/i.test( value );
		},
		card:function(value,ele){
			//如果可用，就返回true
			return false || /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test( value );
		},
		strIsNull:function(str){
			if(str && str!='[]' && str!=[] && str.length>0){
				return true;
			}else{
				return false;
			}
		},
		messages: {
			required: "【{{name}}】不能为空",
			remote: "请修正此项【{{name}}】",
			email: "请为【{{name}}】输入一个正确的邮箱地址",
			url: "请为【{{name}}】输入一个有效的网络地址.",
			date: "请为【{{name}}】输入一个有效的日期.",
			phone:"请为【{{name}}】输入一个有效的移动电话号码.",
			compareDate:"【{{name}}】日期具有比较值.",
			dateISO: "Please enter a valid date ( ISO ).",
			number: "该项【{{name}}】只能输入数字,且不能多于{{length}}位!!",
			float: "该项【{{name}}】限制小数点{{length}}后位.",
			card:"该项【{{name}}】必须为身份证号码..",
			carNumber:"请为【{{name}}】输入一个合法的车牌号.",
			digits: "Please enter only digits.",
			creditcard: "Please enter a valid credit card number.",
			equalTo: "Please enter the same value again.",
			maxlength: "",
			minlength: "",
			rangelength: "",
			range: "",
			max: "",
			min: ""
		}
}
