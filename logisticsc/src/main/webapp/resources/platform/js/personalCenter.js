
$(function(){
	if($("#userType").val() == 4 ||$("#userType").val()==5){
		jine();
	}
});
function jine() {
	$.ajax({
      url :"/logisticsc/personalCenter/getJine.shtml",
      type : 'POST',
      dataType : 'json',
      success:function(data){
     	 if(data.result==true){
     		 var date = data.data;
     		 arr=date.split(":");
     		 $("#jine").text(arr[1]);
        }else  if(data.result==false){
        	$("#jine").text("0.00");
        }
		}
 	 })
}
////获取线路充值数据
//function getLineMoney(){
//	pageindex = $("#videoDiv").parent().find(".currentPage").val();
//	if(pageindex<1){
//		pageindex=1;
//	}
//	var Conditions = {
//			"pageIndex":pageindex
//		};
//	$.ajax({
//		url:"/logisticsc/personalCenter/getLineMoney.shtml",
//		data:Conditions,
//		dataType:"json",
//		type:"post",
//		success:function(dataResult){
//			var date = dataResult.data.params.rows;
//			var page = dataResult.data;
//			var body="";
//			if(null == date || '' == date || 'undefined' == date || date.length <= 0) {
//				body+="<tr>"
//					+"<td style='color:red;' colspan='16' >没有数据</td>";
//					+"</tr>";
//				var lineMoney=body;
//				$("#getLineMoney").html(lineMoney);
//				return false;
//			}
//			for (var i = 0; i < date.length; i++) {			
//				body+="<tr>"
//					+"<td>"+date[i].companyName+"</td>"
//					+"<td>"+"从:"+date[i].startProvince+"-"+date[i].startCity+"<br/>"+
//					"到:"+date[i].endProvince+"-"+date[i].endCity+"</td>"
//					+"<td>"+date[i].money+"</td>"
//					+"<td>"+formartDate(date[i].operTime)+"</td>"
//					+"</tr>";
//			}
//			lineMoney=body;
//			$("#getLineMoney").html(lineMoney);
//			var foot="<div style='margin-top: 10px; float: right;'>"+
//			"<input type='hidden' class='currentPage'/>"+
//			"<div id='videoDiv' class='page'></div></div>";
//			$("#pageList").html(foot);
//			setPage(document.getElementById("videoDiv"),page.params.totalPage,page.pageIndex,getLineMoney);
//		},
//		error:function(error){
//			body+="<tr>"
//				+"<td style='color:red;' colspan='16' >没有数据</td>";
//				+"</tr>";
//			var lineMoney=body;
//			$("#getLineMoney").html(lineMoney);
//		}
//	});
//}
//时间戳转换
//function formartDate(str){
//	var d = new Date();
//    d.setTime(str);
//    var year = d.getFullYear();
//    var month = d.getMonth() < 9 ? "0" + (d.getMonth() + 1) : d.getMonth() + 1;
//    var day = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
//    var hour = d.getHours();
//    var minute = d.getMinutes();
//    var second = d.getSeconds();
//    return year + "-" + month  + "-" + day + " " + hour + ":" + minute + ":" + second;
//}