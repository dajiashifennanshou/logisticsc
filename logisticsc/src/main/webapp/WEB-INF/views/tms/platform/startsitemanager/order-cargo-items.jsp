<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>
<%-- <link href="${configProps['resources']}/tms/base/css/base.css" rel="stylesheet"> --%>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.bui-grid-table .bui-grid-cell-inner{padding: 5px 0px;}
.bui-grid-table .bui-grid-hd-inner{padding: 6px 0px;}
.form-horizontal .controls{height: 40px;}
.form-horizontal .control-label{width: 80px;}
.form-horizontal .control-detail{display: inline-block;line-height: 30px;}
.form-horizontal select{width: 166px;}
.button-box{ width: auto; margin: auto; }
.button-box .button{ padding: 2px 15px; margin: 0px 10px;}
</style>
</head>
<body>
	<div class="panel">
		<div class="panel-header">
			<h3>基本信息</h3>
		</div>
		<div class="panel-body">
			<form class="form-horizontal">
				<div class="row-fluid">
					<div class="control-group span8">
						<label class="control-label">订单号</label>
						<div class="controls">
							<span class="control-detail">${orderItems.orderNumber}</span>
						</div>
					</div>
					<div class="control-group span8">
						<label class="control-label">运单号</label>
						<div class="controls">
							<span class="control-detail">${orderItems.wayBillNumber}</span>
						</div>
					</div>
					<div class="control-group span8">
						<label class="control-label">下单时间</label>
						<div class="controls">
							<span class="control-detail">
			            		<fmt:formatDate value="${orderItems.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			            	</span>
						</div>
					</div>
				</div>
			</form>
			<form class="form-horizontal">
				<div class="row-fluid">
					<div class="control-group span4">
						<label class="control-label">发货人</label>
						<div class="controls">
							<span class="control-detail">${orderItems.consignorName}</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">手机号</label>
						<div class="controls">
							<span class="control-detail">${orderItems.consignorPhoneNumber}</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">地址</label>
						<div class="controls">
							<span class="control-detail">
								${orderItems.consignorProvinceVal }&nbsp;
								${orderItems.consignorCityVal }&nbsp;
								${orderItems.consignorCountyVal }
							</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">详细地址</label>
						<div class="controls">
							<span class="control-detail">${orderItems.consignorAddress}</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">固定电话</label>
						<div class="controls">
							<span class="control-detail">${orderItems.consignorTelephone}</span>
						</div>
					</div>
				</div>
			</form>
			<form class="form-horizontal">
				<div class="row-fluid">
					<div class="control-group span4">
						<label class="control-label">城配司机</label>
						<div class="controls">
							<span class="control-detail">${orderItems.driverName}</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">司机电话</label>
						<div class="controls">
							<span class="control-detail">${orderItems.driverPhone}</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">车牌号</label>
						<div class="controls">
							<span class="control-detail">${orderItems.vehicleNumber}</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">车辆类型</label>
						<div class="controls">
							<span class="control-detail">${orderItems.vehicleTypeVal}</span>
						</div>
					</div>
				</div>
			</form>
	       	<form class="form-horizontal">
				<div class="row-fluid">
					<div class="control-group span4">
						<label class="control-label">收货人</label>
						<div class="controls">
							<span class="control-detail">${orderItems.consigneeName}</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">手机号</label>
						<div class="controls">
							<span class="control-detail">${orderItems.consigneePhoneNumber}</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">地址</label>
						<div class="controls">
							<span class="control-detail">
								${orderItems.consigneeProvinceVal }&nbsp;
								${orderItems.consigneeCityVal }&nbsp;
								${orderItems.consigneeCountyVal }
							</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">详细地址</label>
						<div class="controls">
							<span class="control-detail">${orderItems.consigneeAddress}</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">固定电话</label>
						<div class="controls">
							<span class="control-detail">${orderItems.consigneeTelephone}</span>
						</div>
					</div>
				</div>
			</form>
			<form class="form-horizontal">
				<div class="row-fluid">
					<div class="control-group span4">
						<label class="control-label">发货方式</label>
						<div class="controls">
							<span class="control-detail">
			            		<c:if test="${orderItems.sendCargoType == 0}">自送网点</c:if>
			            		<c:if test="${orderItems.sendCargoType==1}">上门取货</c:if>
			            	</span>
						</div>
					</div>
					<c:if test="${orderItems.sendCargoType == 1}">
						<div class="control-group span5">
							<label class="control-label">预约时间</label>
							<div class="controls">
								<span class="control-detail">
				            		<fmt:formatDate value="${orderItems.deliveryDate}" pattern="yyyy-MM-dd"/>&nbsp;
				            		${orderItems.deliveryStartTime}-${orderItems.deliveryEndTime}
				            	</span>
							</div>
						</div>
					</c:if>
					<div class="control-group span5">
						<label class="control-label">收货方式</label>
						<div class="controls">
							<span class="control-detail">
			            		<c:if test="${orderItems.receiveCargoType==0}">客户自提</c:if>
			            		<c:if test="${orderItems.receiveCargoType==1}">送货上门</c:if>
			            	</span>
						</div>
					</div>
				</div>
			</form>
			<form class="form-horizontal">
				<div class="row-fluid">
					<div class="control-group span4">
						<label class="control-label">专线投保</label>
						<div class="controls">
			            	<span class="control-detail">
			            		<c:if test="${orderItems.isInsurance==0}">否</c:if>
			            		<c:if test="${orderItems.isInsurance==1}">是</c:if>
			            	</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">投保金额</label>
						<div class="controls">
							<span class="control-detail">
								<span class="control-detail">${orderItems.insuranceMoney}</span>
			            	</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">发票信息</label>
						<div class="controls">
							<span class="control-detail">
								<c:if test="${orderItems.receiptType==0}">不需要</c:if>
			            		<c:if test="${orderItems.receiptType==1}">普通发票</c:if>
			            		<c:if test="${orderItems.receiptType==2}">增值发票</c:if>
			            	</span>
						</div>
					</div>
            		<c:if test="${orderItems.receiptType==1}">
            			<div class="control-group span5">
							<label class="control-label">发票抬头</label>
							<div class="controls">
								<span class="control-detail">
				            		<c:if test="${orderItems.receiptTitle==0}">个人</c:if>
				            		<c:if test="${orderItems.receiptTitle==1}">企业</c:if>
				            	</span>
							</div>
						</div>
            		</c:if>
            		<c:if test="${orderItems.receiptTitle==1}">
            			<div class="control-group span5">
							<label class="control-label">公司名称</label>
							<div class="controls">
				            	<span class="control-detail">${orderItems.receiptCompanyName }</span>
							</div>
						</div>
            		</c:if>
				</div>
			</form>
	    </div>
	</div>
	<div class="panel">
		<div class="panel-header">
			<h3>货物信息 </h3>
		</div>
		<div class="panel-body">
			<div id="editCargoGrid"></div>
		</div>
		<form class="form-horizontal">
			<div class="row-fluid">
				<div class="control-group span8">
					<label class="control-label">总重量</label>
					<div class="controls">
						<span class="control-detail" id="totalWeight"></span> 吨
					</div>
				</div>
				<div class="control-group span8">
					<label class="control-label">总体积</label>
					<div class="controls">
						<span class="control-detail" id="totalVolume"></span> 立方米
					</div>
				</div>
				<div class="control-group span8">
					<label class="control-label">预估基础运费</label>
					<div class="controls">
						<span class="control-detail">${orderItems.estimateFreight} 元</span>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="panel">
		<div class="panel-header">
			<h3>增值服务</h3>
		</div>
		<div class="panel-body">
			<form class="form-horizontal">
				<div class="row-fluid">
					<div class="control-group span4">
						<label class="control-label">回单</label>
						<div class="controls">
							<span class="control-detail">
			            		<c:if test="${orderItems.platformOrderAdditionalServer.isReceipt==0 }">否</c:if>
			            		<c:if test="${orderItems.platformOrderAdditionalServer.isReceipt==1 }">是</c:if>
			            	</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">等款放货</label>
						<div class="controls">
							<span class="control-detail">
			            		<c:if test="${orderItems.isWaitPay==0 }">否</c:if>
			            		<c:if test="${orderItems.isWaitPay==1 }">是</c:if>
			            	</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">装货</label>
						<div class="controls">
							<span class="control-detail">
			            		<c:if test="${orderItems.platformOrderAdditionalServer.isLoadCargo==0 }">否</c:if>
			            		<c:if test="${orderItems.platformOrderAdditionalServer.isLoadCargo==1 }">是</c:if>
			            	</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">楼层</label>
						<div class="controls">
							<span class="control-detail">
			            		${orderItems.platformOrderAdditionalServer.loadCargoFloor }
			            	</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">电梯</label>
						<div class="controls">
							<span class="control-detail">
			            		<c:if test="${orderItems.platformOrderAdditionalServer.loadCargoIsElevator==0 }">否</c:if>
			            		<c:if test="${orderItems.platformOrderAdditionalServer.loadCargoIsElevator==1 }">是</c:if>
			            	</span>
						</div>
					</div>
				</div>
			</form>
			<form class="form-horizontal">
				<div class="row-fluid">
					<div class="control-group span4">
						<label class="control-label">代收款</label>
						<div class="controls">
			            	<span class="control-detail">
			            		<c:if test="${orderItems.platformOrderAdditionalServer.isCollectionDelivery==0 }">否</c:if>
			            		<c:if test="${orderItems.platformOrderAdditionalServer.isCollectionDelivery==1 }">是</c:if>
			            	</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">金额</label>
						<div class="controls">
			            	<span class="control-detail">${orderItems.platformOrderAdditionalServer.collectionDeliveryMoney }</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">卸货</label>
						<div class="controls">
			            	<span class="control-detail">
			            		<c:if test="${orderItems.platformOrderAdditionalServer.isUnloadCargo==0 }">否</c:if>
			            		<c:if test="${orderItems.platformOrderAdditionalServer.isUnloadCargo==1 }">是</c:if>
			            	</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">楼层</label>
						<div class="controls">
			            	<span class="control-detail">${orderItems.platformOrderAdditionalServer.unloadCargoFloor }</span>
						</div>
					</div>
					<div class="control-group span5">
						<label class="control-label">电梯</label>
						<div class="controls">
			            	<span class="control-detail">
			            		<c:if test="${orderItems.platformOrderAdditionalServer.unloadCargoIsElevator==0 }">否</c:if>
			            		<c:if test="${orderItems.platformOrderAdditionalServer.unloadCargoIsElevator==1 }">是</c:if>
			            	</span>
						</div>
					</div>
				</div>
			</form>
	   	</div>
	</div>
	<div class="panel">
		<div class="panel-header">
			<h3>费用信息</h3>
		</div>
		<div class="panel-body">
			<form class="form-horizontal">
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">代收款</label>
						<div class="controls">
			            	<span class="control-detail">
			            		${orderItems.platformOrderAdditionalServer.collectionDeliveryMoney}
			            	</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">手续费</label>
						<div class="controls">
							<span class="control-detail">
			            		${orderItems.platformOrderBill.agencyFundPoundage}
			            	</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">投保金额</label>
						<div class="controls">
							<span class="control-detail">
			            		${orderItems.insuranceMoney}
			            	</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">保险费</label>
						<div class="controls">
							<span class="control-detail">
			            		${orderItems.platformOrderBill.insurance }
			            	</span>
						</div>
					</div>
				</div>
			</form>
			<form class="form-horizontal">
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">提货费</label>
						<div class="controls">
			            	<span class="control-detail">
			            		<c:if test="${not empty orderItems.platformOrderBill.takeCargoCost}">
			            			${orderItems.platformOrderBill.takeCargoCost }
			            		</c:if>
			            		<c:if test="${empty orderItems.platformOrderBill.takeCargoCost}">
			            			${orderItems.platformOrderBill.estimateTakeCargoCost }
			            		</c:if>
			            	</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">装卸费</label>
						<div class="controls">
							<span class="control-detail">
			            		<c:if test="${not empty orderItems.platformOrderBill.loadCargoCost or not empty orderItems.platformOrderBill.unloadCargoCost }">
			            			${orderItems.platformOrderBill.loadCargoCost + orderItems.platformOrderBill.unloadCargoCost }
			            		</c:if>
			            		<c:if test="${empty orderItems.platformOrderBill.loadCargoCost and empty orderItems.platformOrderBill.unloadCargoCost}">
			            			${orderItems.platformOrderBill.estimateLoadCargoCost + orderItems.platformOrderBill.estimateUnloadCargoCost }
			            		</c:if>
			            	</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">其他费用</label>
						<div class="controls">
			            	<span class="control-detail">
			            		${orderItems.platformOrderBill.otherCost }
			            	</span>
						</div>
					</div>
				</div>
			</form>
			<form class="form-horizontal">
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">基础运费</label>
						<div class="controls">
			            	<span class="control-detail">
			            		<c:if test="${not empty orderItems.platformOrderBill.freight}">
			            			${orderItems.platformOrderBill.freight }
			            		</c:if>
			            		<c:if test="${empty orderItems.platformOrderBill.freight}">
			            			${orderItems.platformOrderBill.estimateFreight }
			            		</c:if>
			            	</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">支付方式</label>
						<div class="controls">
			            	<span class="control-detail">
			            		<c:if test="${orderItems.payType==0}">现付</c:if>
			            		<c:if test="${orderItems.payType==1}">到付</c:if>
			            		<c:if test="${orderItems.payType==2}">预付</c:if>
			            	</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">合计</label>
						<div class="controls">
							<span class="control-detail">
			            		<c:if test="${not empty orderItems.platformOrderBill.totalCost}">
			            			${orderItems.platformOrderBill.totalCost }
			            		</c:if>
			            		<c:if test="${empty orderItems.platformOrderBill.totalCost}">
			            			${orderItems.platformOrderBill.estimateTotalCost }
			            		</c:if>
			            	</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">等款放货</label>
						<div class="controls">
			            	<span class="control-detail">
			            		<c:if test="${orderItems.isWaitPay == 0}">否</c:if>
			            		<c:if test="${orderItems.isWaitPay == 1}">是</c:if>
			            	</span>
						</div>
					</div>
				</div>
			</form>
	   	</div>
	</div>
	<div class="row-fluid" style="margin-bottom: 10px;">
		<table class="button-box">
			<tr>
				<td><button class="button" onclick="history.back()">返回</button></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
				Toolbar, Format) {
			var Grid = Grid;
			var columns = [
	   	        {title : '货物名称',dataIndex : 'name'}, 
	   	        {title : '类型', dataIndex : 'model'},
	   	     	{title : '品牌',dataIndex : 'brand'}, 
	   	        {title : '件数', dataIndex : 'number'},
	   	     	{title : '套数',dataIndex : 'setNumber'}, 
	   	        {title : '重量', dataIndex : 'singleWeight'},
	   	     	{title : '体积', dataIndex : 'singleVolume'},
	   	     	{title : '计费方式',dataIndex : 'countCostMode',renderer:function(val){
			   	     	switch(val){
			   	     	case 0:return "按重量";
			   	     	case 1:return "按体积";
			   	     	case 2:return "按件数";
			   	     	case 3:return "按套数";
			   	     	}
	   	     	}},
	   	        {title : '包装信息', dataIndex : 'packageTypeName'},
	        ];
			var datas = ${jsonCargo};
			grid = new Grid.Grid({
				render : '#editCargoGrid',
				columns : columns,
				items : datas,
				forceFit : true,
			}).render();
		});

		countWeightAndVolume();
		function countWeightAndVolume(){
			var weight = 0;
			var volume = 0;
			var datas = ${jsonCargo};
			for(var i = 0; i < datas.length; i++){
				weight += new Number(datas[i].singleWeight);
				volume += new Number(datas[i].singleVolume);
			}
			$('#totalWeight').text(weight);
			$('#totalVolume').text(volume);
		}
	</script>
</body>
</html>