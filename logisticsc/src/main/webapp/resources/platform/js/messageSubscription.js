$(document).ready(function(){
	getMessageSubscription();
})

/**
 * 修改 增加我的订阅
 * 
 */
function updateMessageSubscription(){
	var xuanzhong =[];//定义一个数组    
    $('input[name="xuanzhong"]:checked').each(function(){
    	xuanzhong.push($(this).val());
    });
    $.ajax({
		url:"/logisticsc/personalCenter/doMessageSubscription.shtml",
		data: {'xuanzhong' : JSON.stringify(xuanzhong)},
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.result==true){
				$("#successModal").modal('show');
				$("#successModalMsgs").html(data.msg);
				getMessageSubscription();
			}else{
				$("#errorModal").modal('show');
				$("#errorModalMsgs").html(data.msg);
			}
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html(data.msg);
		}
	})
}
/**
 * 我的订阅展示
 */
function getMessageSubscription(){
	$.ajax({
		url:"/logisticsc/personalCenter/getMessageSubscription.shtml",
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.result==true){
				var date = data.data;
				var body="";
				if(null == date || '' == date || 'undefined' == date || date.length <= 0) {
					body+="<tr>"
						+"<td style='color:red;' colspan='16' >没有数据</td>";
						+"</tr>";
					var lineMoney=body;
					$("#getMessage").html(lineMoney);
					return false;
				}
				for (var i = 0; i < date.length; i++) {
					body+="<tr>"
						+"<td>"+date[i].messageType+"</td>"
					if(date[i].noticeType == 0){
						if(date[i].checked == 1){
							body+="<td><input type=checkbox name='xuanzhong' value="+date[i].id+" checked='checked'>短信通知</td>"
							+"<td></td>"
							+"</tr>";
						}else{
							body+="<td><input type=checkbox name='xuanzhong' value="+date[i].id+">短信通知</td>"
							+"<td></td>"
							+"</tr>";
						}
					}else {
						if(date[i].checked == 1){
							body+="<td></td>"
							+"<td><input type=checkbox name='xuanzhong' value="+date[i].id+" checked='checked'>邮箱通知</td>"
							+"</tr>";
						}else{
							body+="<td></td>"
							+"<td><input type=checkbox name='xuanzhong' value="+date[i].id+">邮箱通知</td>"
							+"</tr>";
						}
					}
				}
				lineMoney=body;
				$("#getMessage").html(lineMoney);
			}else{
				$("#errorModal").modal('show');
				$("#errorModalMsgs").html(data.msg);
			}
		},
		error:function(data){
			$("#errorModal").modal('show');
			$("#errorModalMsgs").html(data.msg);
		}
	})
}