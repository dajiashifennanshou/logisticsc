//已选中的货物集合
var _goodsObj={
		goodsIdList:new Array(),
		boxIsShow:false
}
$(document).ready(function(){
	
	$('#myTab a:first').tab('show');//初始化显示哪个tab 
    $('#myTab a').click(function (e) {
      e.preventDefault();//阻止a链接的跳转行为 
      $(this).tab('show');//显示当前选中的链接及关联的content 
    })
//    //支付
//    $("#onlineStartTimes").datetimepicker({
//    	format: 'yyyy-mm-dd',
//		language: 'zh-CN',
//		minView: 2,
//		autoclose:true 
//	});
//	$("#onlineEndTimes").datetimepicker({
//		format: 'yyyy-mm-dd',
//		language: 'zh-CN',
//		minView: 2,
//		autoclose:true 
//	});
//	getBySelectedPayment();
//	
//	//退款
//	$("#refundStartTime").datetimepicker({
//		format: 'yyyy-mm-dd',
//		language: 'zh-CN',
//		minView: 2,
//		autoclose:true 
//	});
//	$("#refundEndTime").datetimepicker({
//		format: 'yyyy-mm-dd',
//		language: 'zh-CN',
//		minView: 2,
//		autoclose:true 
//	});
//	getSelectRefund();
//	//
//	$("#posStartTime").datetimepicker({
//		format: 'yyyy-mm-dd',
//		language: 'zh-CN',
//		minView: 2,
//		autoclose:true 
//	});
//	$("#posEndTime").datetimepicker({
//		format: 'yyyy-mm-dd',
//		language: 'zh-CN',
//		minView: 2,
//		autoclose:true 
//	});
	my_goods();
    my_delivery();
    my_cost_center();
})
function showPay(money){
	$("#money").val(0.01);
	$('#payModal').modal({show:true});
}
function wancheng() {
	$("#payModal").modal('hide');
	$("#querenModal").modal('show');
}
/**
 * 新增配送单
 */
function addOrder(form){
	var _form=$("#"+form);
	//_form.validate();
	var valids=_form.FengValid();
	//return;
	//验证通过才能进行下一页或者提交
	if(valids){
		var data=new Object();
		data['goodsIds']=_goodsObj.goodsIdList.toString();
		var subData=$.extend(data,yc_public.getData(form));
		yc_public.confirm({msg:"请确认信息..",callback:function(){
			yc_public.ajax({"url":"../addTmsDeliveryOrder.yc",load:true,"data":subData,success:function(data){
				if(data.code==0){
					yc_public.dialog({"msg":"提交成功!",callback:function(){
						window.location="toMyCloud.shtml";
					}});
				}else{
					yc_public.dialog({"msg":data.msg||"提交失败!"});
				}
			}});
		}});
	}
}
/**
 * 关闭添加窗口
 * @param ele
 */
function tiaozhuan(ele){
	$("#"+ele).hide();
}
/**
 * 当选货完成时
 */
function showAddInfoPan(){
	if(_goodsObj.goodsIdList.length<=0){
		yc_public.alert({msg:'未选择货物..'});
		return;
	}
	$('#addOrderPan').show();
}
/**
 * 当点击添加配送单时
 */
function showSelect(){
	if(_goodsObj.boxIsShow){
		$("#newOrder_").html("新增配送单");
	}else{
		$("#newOrder_").html("取消新增");
	}
	_goodsObj.boxIsShow==true?false:true;
	//显示引导面版
	$("#selectGoods_").toggle();
	//显示checkBox
	$("#_goodsBoxTh").toggle();
	$('td[name=_goodsBoxTd]').toggle();
}
/**
 * 当选择框改变时
 */
function boxchange(ele){
	var s=ele.checked;
	var val=$(ele).val();
	if(s){
		if(!valiedInArray(_goodsObj.goodsIdList,val)){
			$('#goodsNumber_').html(_goodsObj.goodsIdList.push(val));
		}
	}else{
		var index=valiedInArray(_goodsObj.goodsIdList,val)
		if(index){
			_goodsObj.goodsIdList.splice(index,1);
			$('#goodsNumber_').html(_goodsObj.goodsIdList.length);
		}
	}
}
/**
 * 验证字段是否在集合里
 */
function valiedInArray(arr,val){
	for(var v in arr){
		if(arr[v]==val){
			return v;
		}
	}
	return false;
}
//我的货物
function my_goods(){
	var userId=$("#user_id_feng").val();
	var dNo=$("#wayBillNo").val();
	pageindex = $("#my_goods_div").parent().find(".currentPage").val();
	
	if(pageindex<1){
		pageindex=1;
	}
	//获取我的货物的url
	var getMy_goodsUrl="../getTmsGoodsListByDealer.yc";
	//数据集合
	var Conditions = {
			page:1,
			rows:15
		};
	if(dNo){
		Conditions['deliveryNo']=dNo;
	}
	$.ajax({
		url:getMy_goodsUrl,
			data:Conditions,
			type:'post',
			dataType:'json',
			success:function(dataResult){
				var date = dataResult.rows;
				var page = dataResult;
				var body="";
				if(null == date || '' == date || 'undefined' == date || date.length <= 0) {
					body="<tr>"
						+"<td style='color:red;' colspan='16' >没有数据</td>";
						+"</tr>";
					var rechargeRecord=body;
					$("#my_goods_list").html(rechargeRecord);
					$("#my_goods_page").html("");
					return false;
				}
				for (var i = 0; i < date.length; i++) {
					
					if(date[i].outStorageStatus == 0){
						var status ="未出库";
					}else if(date[i].outStorageStatus == 1){
						var status="已出库";
					}
					//如果是添加状态/就要根据已选择的来勾选
					var boxHtml="";
					if(date[i].outStorageStatus==1){
						boxHtml="<td name='_goodsBoxTd' >X</td>";
					}else{
						if(_goodsObj.boxIsShow){
							boxHtml="<td name='_goodsBoxTd'>"+'<input '+(valiedInArray(_goodsObj.goodsIdList,date[i].wayBillNo+'_'+date[i].id)?"checked=chcked":"")+' type="checkBox" name="selectGoodsBox_" onchange="boxchange(this)" value="'+date[i].id+'_'+date[i].wayBillNo+'" />'+"</td>"
						}else{
							boxHtml="<td name='_goodsBoxTd'>"+'<input  type="checkBox" name="selectGoodsBox_" onchange="boxchange(this)" value="'+date[i].id+'_'+date[i].wayBillNo+'" />'+"</td>"
						}
					}
					body+="<tr>"+boxHtml
						+"<td>"+date[i].wayBillNo+"</td>"
						+"<td>"+date[i].goodsName+"</td>"
						+"<td>"+date[i].model+"</td>"
						+"<td>"+date[i].goodsNum+"</td>"
						+"<td>"+date[i].weight+"</td>"
						+"<td>"+date[i].volume+"</td>"
						+"<td>"+status+"</td>"
						+"<td>"+formartDate(date[i].createTime)+"</td>"
						+"</tr>";
				}
				var rechargeRecord=body;
				//将列表数据设置到div内value=""+"<td><input type='checkbox'  name='selectGoodsBox_' /></td>"+
				$("#my_goods_list").html(rechargeRecord);
				//准备page数据
				var foot="<div style='margin-top: 10px; float: right;'>"+
				"<input type='hidden' class='currentPage'/>"+
				"<div id='my_goods_div' class='page'></div></div>";
				$("#my_goods_page").html(foot);
				//隐藏选择框
				if(!_goodsObj.boxIsShow){
					$("#_goodsBoxTh").hide();
					$('td[name=_goodsBoxTd]').hide();
				}
				//div---总页数--当前页--翻页执行的方法
				setPage(document.getElementById("my_goods_div"),page.total,page.page,my_goods);
			},
			error:function(error){
				body="<tr>"
					+"<td style='color:red;' colspan='16' >没有数据</td>";
					+"</tr>";
				var rechargeRecord=body;
				$("#my_goods_list").html(rechargeRecord);
			}
		})
}
//我的配送单
function my_delivery(){
	pageindex = $("#my_delivery_div").parent().find(".currentPage").val();
	var dNo=$("#deliveryNo_1").val();
	if(pageindex<1){
		pageindex=1;
	}
	
	var my_delivery_url="../getTmsDeliveryOrderList.yc";
	var Conditions = {
			page:1,
			rows:14
		};
	if(dNo){
		Conditions['deliveryNo']=dNo;
	}
	$.ajax({
		url:my_delivery_url,
			data:Conditions,
			type:'post',
			dataType:'json',
			success:function(dataResult){
				var date = dataResult.rows;
				var page = dataResult;
				var body="";
				if(null == date || '' == date || 'undefined' == date || date.length <= 0) {
					body="<tr>"
						+"<td style='color:red;' colspan='16' >没有数据</td>";
						+"</tr>";
					var rechargeRecord=body;
					$("#my_delivery_list").html(rechargeRecord);
					$("#my_delivery_page").html("");
					return false;
				}
				for (var i = 0; i < date.length; i++) {
					
					
					body+="<tr>"
						+"<td>"+date[i].deliveryNo+"</td>"
						+"<td>"+date[i].consigneeName+"</td>"
						+"<td>"+date[i].consigneeAddr+"</td>"
						+"<td>"+date[i].consigneePhone+"</td>"
						+"<td>"+(date[i].installCost || 0)+"</td>"
						+"<td>"+(date[i].deliveryCost || 0)+"</td>"
						+"<td>"+Constant.tmsDeliveryOrderStatus[date[i].orderStatus]+"</td>"
						+"<td>"+DateUtil.RemoveZero(date[i].createTime)+"</td>"
						+"</tr>";
				}
				var rechargeRecord=body;
				$("#my_delivery_list").html(rechargeRecord);
				var foot="<div style='margin-top: 10px; float: right;'>"+
				"<input type='hidden' class='currentPage'/>"+
				"<div id='my_delivery_div' class='page'></div></div>";
				$("#my_delivery_page").html(foot);
				setPage(document.getElementById("my_delivery_div"),page.total,page.page,my_delivery);
			},
			error:function(error){
				body="<tr>"
					+"<td style='color:red;' colspan='16' >没有数据</td>";
					+"</tr>";
				var rechargeRecord=body;
				$("#my_delivery_list").html(rechargeRecord);
			}
		})
}
//费用中心
function my_cost_center(){
	pageindex = $("#my_cost_center_div").parent().find(".currentPage").val();
	var dNo=$("#deliveryNo_2").val();
	if(pageindex<1){
		pageindex=1;
	}
	
	var my_delivery_url="../getTmsDeliveryOrderList.yc";
	var Conditions = {
			page:1,
			rows:14
		};
	if(dNo){
		Conditions['deliveryNo']=dNo;
	}
	$.ajax({
		url:my_delivery_url,
			data:Conditions,
			type:'post',
			dataType:'json',
			success:function(dataResult){
				var date = dataResult.rows;
				var page = dataResult;
				var body="";
				if(null == date || '' == date || 'undefined' == date || date.length <= 0) {
					body="<tr>"
						+"<td style='color:red;' colspan='16' >没有数据</td>";
						+"</tr>";
					var rechargeRecord=body;
					$("#my_cost_center_list").html(rechargeRecord);
					$("#my_cost_center_page").html("");
					return false;
				}
				for (var i = 0; i < date.length; i++) {
					
					
					body+="<tr>"
						+"<td>"+date[i].deliveryNo+"</td>"
						+"<td>"+(date[i].installCost || 0)+"</td>"
						+"<td>"+(date[i].deliveryCost || 0)+"</td>"
						+"<td>"+(date[i].agentPaidCost || 0)+"</td>"
						+"<td>"+(date[i].paidCost || 0)+"</td>"
						+"<td>"+Constant.tmsDeliveryOrderStatus[date[i].orderStatus]+"</td>"
						+"<td><a href='javascript:;' onclick='showPay("+date[i].paidCost+");'>支付</></td>"
						+"<td>"+DateUtil.RemoveZero(date[i].createTime)+"</td>"
						+"</tr>";
				}
				var rechargeRecord=body;
				$("#my_cost_center_list").html(rechargeRecord);
				var foot="<div style='margin-top: 10px; float: right;'>"+
				"<input type='hidden' class='currentPage'/>"+
				"<div id='my_cost_center_div' class='page'></div></div>";
				$("#my_cost_center_page").html(foot);
				setPage(document.getElementById("my_cost_center_div"),page.total,page.page,my_delivery);
			},
			error:function(error){
				body="<tr>"
					+"<td style='color:red;' colspan='16' >没有数据</td>";
					+"</tr>";
				var rechargeRecord=body;
				$("#my_cost_center_list").html(rechargeRecord);
			}
		})
}


function formartDate(str){
	var d = new Date();
    d.setTime(str);
    var year = d.getFullYear();
    var month = d.getMonth() < 9 ? "0" + (d.getMonth() + 1) : d.getMonth() + 1;
    var day = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
    var hour = d.getHours();
    var minute = d.getMinutes();
    var second = d.getSeconds();
    return year + "-" + month  + "-" + day + " " + hour + ":" + minute + ":" + second;
}