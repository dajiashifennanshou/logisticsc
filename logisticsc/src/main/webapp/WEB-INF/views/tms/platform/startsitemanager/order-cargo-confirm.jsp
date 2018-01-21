<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单货物确认</title>
<%-- <link href="${configProps['resources']}/tms/base/css/base.css" rel="stylesheet"> --%>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<style type="text/css">
.bui-grid-table .bui-grid-cell-inner{padding: 5px 0px;}
.bui-grid-table .bui-grid-hd-inner{padding: 6px 0px;}
.form-horizontal .controls{height: 40px;}
.form-horizontal .control-label{width: 80px;}
.form-horizontal .control-detail{display: inline-block;line-height: 30px;}
.form-horizontal select{width: 146px;}
.form-horizontal input{width: 120px;}
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
			<form class="form-horizontal" id="baseInfoForm">
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">订单号</label>
						<div class="controls">
							<span class="control-detail" id="orderNumber">${orderItems.orderNumber}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">下单时间</label>
						<div class="controls">
							<span class="control-detail">
			            		<fmt:formatDate value="${orderItems.orderTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			            	</span>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">发货人</label>
						<div class="controls">
							<span class="control-detail">${orderItems.consignorName}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">手机号</label>
						<div class="controls">
							<span class="control-detail">${orderItems.consignorPhoneNumber}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">地址</label>
						<div class="controls">
							<span class="control-detail">
								${orderItems.consignorProvinceVal }&nbsp;
								${orderItems.consignorCityVal }&nbsp;
								${orderItems.consignorCountyVal }
							</span>
							<input type="hidden" id="consignorCounty" value="${orderItems.consignorCounty}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">详细地址</label>
						<div class="controls">
							<span class="control-detail">${orderItems.consignorAddress}</span>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">固定电话</label>
						<div class="controls">
							<span class="control-detail">${orderItems.consignorTelephone}</span>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">收货人</label>
						<div class="controls">
							<span class="control-detail">${orderItems.consigneeName}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">手机号</label>
						<div class="controls">
							<span class="control-detail">${orderItems.consigneePhoneNumber}</span>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">地址</label>
						<div class="controls">
							<span class="control-detail">
								${orderItems.consigneeProvinceVal }&nbsp;
								${orderItems.consigneeCityVal }&nbsp;
								${orderItems.consigneeCountyVal }
							</span>
							<input type="hidden" id="consigneeCounty" value="${orderItems.consigneeCounty}">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">详细地址</label>
						<div class="controls">
							<span class="control-detail">${orderItems.consigneeAddress}</span>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span5">
						<label class="control-label">固定电话</label>
						<div class="controls">
							<span class="control-detail">${orderItems.consigneeTelephone}</span>
						</div>
					</div>
				</div>
				<c:if test="${orderItems.sendCargoType == 0}">
					<div class="row-fluid">
						<div class="control-group span6">
							<label class="control-label">城配司机</label>
							<div class="controls">
								<span class="control-detail">${orderItems.driverName}</span>
							</div>
						</div>
						<div class="control-group span6">
							<label class="control-label">司机电话</label>
							<div class="controls">
								<span class="control-detail">${orderItems.driverPhone}</span>
							</div>
						</div>
						<div class="control-group span6">
							<label class="control-label">车牌号</label>
							<div class="controls">
								<span class="control-detail">${orderItems.vehicleNumber}</span>
							</div>
						</div>
						<div class="control-group span6">
							<label class="control-label">车辆类型</label>
							<div class="controls">
								<span class="control-detail">${orderItems.vehicleTypeVal}</span>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${orderItems.sendCargoType==1}">
					<div class="row-fluid">
						<div class="control-group span6">
							<label class="control-label">城配司机</label>
							<div class="controls">
								<span class="control-detail">${ladingOrder.driverName}</span>
							</div>
						</div>
						<div class="control-group span6">
							<label class="control-label">司机电话</label>
							<div class="controls">
								<span class="control-detail">${ladingOrder.driverMobile}</span>
							</div>
						</div>
						<div class="control-group span6">
							<label class="control-label">车牌号</label>
							<div class="controls">
								<span class="control-detail">${ladingOrder.licensePlateNo}</span>
							</div>
						</div>
						<%-- <div class="control-group span6">
							<label class="control-label">车辆类型</label>
							<div class="controls">
								<select id="vehicleType">
									<c:forEach items="${vehicleTypes}" var="vehicleType">
										<option value="${vehicleType.id}">${vehicleType.name}</option>
									</c:forEach>
								</select>
							</div>
						</div> --%>
					</div>
				</c:if>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">发货方式</label>
						<div class="controls">
							<span class="control-detail">
			            		<c:if test="${orderItems.sendCargoType == 0}">自送网点</c:if>
			            		<c:if test="${orderItems.sendCargoType==1}">上门取货</c:if>
			            	</span>
						</div>
					</div>
					<c:if test="${orderItems.sendCargoType == 1}">
						<div class="control-group span6">
							<label class="control-label">预约时间</label>
							<div class="controls">
								<span class="control-detail">
				            		<fmt:formatDate value="${orderItems.deliveryDate}" pattern="yyyy-MM-dd"/>&nbsp;
				            		${orderItems.deliveryStartTime}-${orderItems.deliveryEndTime}
				            	</span>
							</div>
						</div>
					</c:if>
					<div class="control-group span6">
						<label class="control-label">收货方式</label>
						<div class="controls">
							<span class="control-detail">
			            		<c:if test="${orderItems.receiveCargoType==0}">客户自提</c:if>
			            		<c:if test="${orderItems.receiveCargoType==1}">送货上门</c:if>
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
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
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
            			<div class="control-group span6">
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
            			<div class="control-group span6">
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
		<%-- <form class="form-horizontal">
			<div class="row-fluid">
				<div class="control-group span8">
					<label class="control-label">总重量</label>
					<div class="controls">
						<span class="control-detail">${orderItems.estimateWeight}</span> 吨
					</div>
				</div>
				<div class="control-group span8">
					<label class="control-label">总体积</label>
					<div class="controls">
						<span class="control-detail">${orderItems.estimateVolume}</span> 立方米
					</div>
				</div>
				<div class="control-group span8">
					<label class="control-label">运费</label>
					<div class="controls">
						<span class="control-detail">${orderItems.estimateFreight}</span> 元
					</div>
				</div>
			</div>
		</form> --%>
	</div>
	<div class="panel">
		<div class="panel-header">
			<h3>费用信息</h3>
		</div>
		<div class="panel-body" id="wayBillCostInfo">
			<form class="form-horizontal" id="costInfoForm">
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">代收货款</label>
						<div class="controls">
							<span id="agencyFund" class="control-detail">0</span> 元
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">手续费</label>
						<div class="controls">
							<span id="agencyFundPoundage" class="control-detail">0</span> 元
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">投保金额</label>
						<div class="controls">
							<c:if test="${empty orderItems.insuranceMoney}">
								<span id="insuranceMoney" class="control-detail">0</span> 元	
							</c:if>
							<c:if test="${not empty orderItems.insuranceMoney}">
								<span id="insuranceMoney" class="control-detail">${orderItems.insuranceMoney}</span> 元	
							</c:if>
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">保险费</label>
						<div class="controls">
							<span id="insurance" class="control-detail">0</span> 元
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">提货费</label>
						<div class="controls">
							<input type="text" id="takeCargoCost" value="" onchange="validateNumber(this), countTotalMoney()"> 元
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">送货费</label>
						<div class="controls">
							<input type="text" id="sendCargoCost" value="" onchange="validateNumber(this), countTotalMoney()"> 元
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">装货费</label>
						<div class="controls">
							<input type="text" id="loadCargoCost" value="" onchange="validateNumber(this), countTotalMoney()"> 元
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">卸货费</label>
						<div class="controls">
							<input type="text" id="unloadCargoCost" value="" onchange="validateNumber(this), countTotalMoney()"> 元
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group span6">
						<label class="control-label">其他费用</label>
						<div class="controls">
							<input type="text" class="input-normal" id="otherCost" onchange="validateNumber(this), countTotalMoney()">
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">基础运费</label>
						<div class="controls">
							<span id="freight" class="control-detail">${orderItems.estimateFreight}</span> 元
						</div>
					</div>
					<div class="control-group span6">
						<label class="control-label">合计</label>
						<div class="controls">
							<span id="total" class="control-detail">0</span> 元
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="row-fluid" style="margin-bottom: 10px;">
		<table class="button-box">
			<tr>
				<td><button class="button button-primary" onclick="submitConfirmData()">确定</button></td>
				<td><button class="button" onclick="history.go(-1)">取消</button></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
		var additionalServerConf = ${additionalServerConf}; // 增值服务配置信息
		var startOutletsPriceRangeConfs = ${startOutletsPriceRangeConfs}; // 提送货管理配置(起始网点)
		var endOutletsPriceRangeConfs = ${endOutletsPriceRangeConfs}; // 提送货管理配置(目的网点)
		var additionalServer = ${additionalServer}; // 货主选择的增值服务
		var baseInfoForm, costInfoForm;
		var mobilePartten = /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/;
		$(function(){
			var data = ${jsonCargo};
			buildEditCargoGrid(data);
			countOrderCostInfo();
			loadForm();
		});

		// 计算订单费用信息
		function countOrderCostInfo(){
			// 代收货款
			if(additionalServer.isCollectionDelivery == 1){
				$('#agencyFund').text(additionalServer.collectionDeliveryMoney);
			}
			// 代收货款手续费
			if(additionalServerConf != null && additionalServerConf.isCollectionDelivery == 1){
				var collectionDeliveryRatio = additionalServerConf.collectionDeliveryRatio;
				if(collectionDeliveryRatio != null && collectionDeliveryRatio != '' && typeof(collectionDeliveryRatio) != 'undefined'){
					var collectionDeliveryMoney = $('#agencyFund').text();
					$('#agencyFundPoundage').text(new Number(collectionDeliveryMoney) * new Number(collectionDeliveryRatio) / 100);
				}
			}
			// 保险费
			var insuranceMoney = $('#insuranceMoney').text();
			if(lineInsuranceRatio != null){
				var lineInsuranceRatio = additionalServerConf.lineInsuranceRatio;
				if(insuranceMoney != null && insuranceMoney != '' && lineInsuranceRatio != null && lineInsuranceRatio != ''){
					$('#insurance').text(new Number(insuranceMoney) * new Number(lineInsuranceRatio) / 100);
				}
			}
			
			// 提货费
			var sendCargoType = ${orderItems.sendCargoType};
			if(sendCargoType == 1){
				$('#takeCargoCost').val(countTakeCargoCost());
			}
			// 送货费
			$('#sendCargoCost').val(countSendCargoCost());
			// 装货费
			$('#loadCargoCost').val(countLoadCargoCost());
			// 卸货费
			$('#unloadCargoCost').val(countUnloadCargoCost());
			
			countTotalMoney();
		}
		
		// 构建货物编辑表格
		function buildEditCargoGrid(data){
			var packageType = getPackageType();
			var countCostMode = getCountCostMode();
			BUI.use(['bui/grid','bui/data'],function(Grid,Data){
		        var Store = Data.Store;
		        var columns = [
			        {title : '货物名称',dataIndex : 'name',editor : {xtype : 'text', rules : {required : true}}}, //editor中的定义等用于 BUI.Form.Field.Text的定义
			        {title : '品牌', dataIndex : 'brand',editor : {xtype : 'text'}},
			        {title : '型号', dataIndex : 'model',editor : {xtype : 'text'}},
			        {title : '包装',dataIndex : 'packageType', editor : {xtype :'select',items : packageType},
			        	renderer : Grid.Format.enumRenderer(packageType)
			        },
			        {title : '件数', dataIndex : 'number',editor : {xtype : 'number'}, renderer : function(value,obj,index){
			        	countCargoFreight(obj, index);
			        	return value;
			        }},
			        {title : '套数', dataIndex : 'setNumber',editor : {xtype : 'number'}, renderer : function(value,obj,index){
			        	countCargoFreight(obj, index);
			        	return value;
			        }},
			        {title : '重量', dataIndex : 'totalWeight',editor : {xtype : 'number'}, renderer : function(value,obj,index){
			        	/* countCargoFreight(obj, index);
			        	countOrderCostInfo(); */
			        	return value;
			        }},
			        {title : '体积', dataIndex : 'totalVolume',editor : {xtype : 'number'}, renderer : function(value,obj,index){
			        	/* countCargoFreight(obj, index);
			        	countOrderCostInfo(); */
			        	return value;
			        }},
			        {title : '计费方式',dataIndex : 'countCostMode', editor : {xtype :'select',items : countCostMode, rules : {required : true}},
			        	renderer : Grid.Format.enumRenderer(countCostMode)
			        },
			        {title : '单价', dataIndex : 'price',editor : {xtype : 'number'}, renderer : function(value,obj,index){
			        	countCargoFreight(obj, index);
			        	return value;
			        }}
		        ];
		        var editing = new Grid.Plugins.CellEditing({
		      		triggerSelected : false //触发编辑的时候不选中行
		    	});
		      	store = new Store({
		        	data : data,
		        	autoLoad:true
		      	});
		      	grid = new Grid.Grid({
		    		width : $('#editCargoGrid').width(),
		        	render:'#editCargoGrid',
		        	columns : columns,
		        	forceFit : true,
		        	tbar:{ //添加、删除
		            	items : [{
		              		btnCls : 'button button-small',
		              		text : '<i class="icon-plus"></i>添加',
		              		listeners : {
		                		'click' : addFunction
		              		}
		            	}, {
		           			btnCls : 'button button-small',
		              		text : '<i class="icon-remove"></i>删除',
		              		listeners : {
		                		'click' : delFunction
		              		}
		            	}]
		      		},
		        	plugins : [editing,Grid.Plugins.CheckSelection],
		        	store : store
		       	});
		    	grid.render();

		    	//添加记录
		    	function addFunction(){
		      		store.addAt({});
		    	}
		    	//删除选中的记录
		    	function delFunction(){
		    		var selections = grid.getSelection();
		    		if(selections.length != 0){
		    			BUI.Message.Confirm('确定要删除吗？',function(){
				      		store.remove(selections);
			    		},'question');
		    		}
		    	}
		  	});
		}
		
		// 计算货物运费信息
		function countCargoFreight(obj, index){
			var total = 0;
			var items = grid.getItems();
			for(var i = 0; i < items.length; i++){
				var row = items[i];
				var number = isNull(row.number) ? 0 : row.number;
				var setNumber = isNull(row.setNumber) ? 0 : row.setNumber;
				var weight = isNull(row.totalWeight) ? 0 : row.totalWeight;
				var volume = isNull(row.totalVolume) ? 0 : row.totalVolume;
				var price = isNull(row.price) ? 0 : row.price;

				var rowTotalPrice = 0;
				
				var countCostMode = row.countCostMode;
				if(countCostMode == 0){ // 按重量
					rowTotalPrice = weight * price;
				}else if(countCostMode == 1){ // 按体积
					rowTotalPrice = volume * price;
				}else if(countCostMode == 2){ // 按件数
					rowTotalPrice = number * price;
				}else if(countCostMode == 3){ // 按套数
					rowTotalPrice = setNumber * price;
				}
				total += rowTotalPrice;
			}
			if(total != 0){
				$('#freight').text(total);
			}
			countTotalMoney();
		}
		
		// 计算总金额
		function countTotalMoney(){
			var agencyFundPoundage = $('#agencyFundPoundage').text(); // 手续费
			var insurance = $('#insurance').text(); // 保险费
			var takeCargoCost = $('#takeCargoCost').val(); // 提货费
			var sendCargoCost = $('#sendCargoCost').val(); // 送货费
			var loadCargoCost = $('#loadCargoCost').val(); // 装货费
			var unloadCargoCost = $('#unloadCargoCost').val(); // 卸货费
			var freight = $('#freight').text(); // 运费
			var otherCost = $('#otherCost').val();
			var total = new Number(agencyFundPoundage) + new Number(insurance) + 
			new Number(takeCargoCost) + new Number(sendCargoCost) + new Number(loadCargoCost) + 
			new Number(unloadCargoCost) + new Number(freight) + new Number(otherCost);
			$('#total').text(total.toFixed(2));
		}
		
		function isNull(v){
			if(v == null || v == '' || typeof(v) == 'undefined'){
				return true;
			}
			return false;
		}
		
		// 获取包装类型
		function getPackageType(){
			var selectData = '{';
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/deliverycargo/getpackagetype.shtml',
				async : false,
				success : function(result){
					for(var i = 0; i < result.length; i++){
						var value = result[i].id;
						var text = result[i].name;
						if(i == result.length - 1){
							selectData += '"' + value + '" : "' + text + '"';
						}else{
							selectData += '"' + value + '" : "' + text + '", ';
						}
					}
				}
			});
			selectData += '}';
			return JSON.parse(selectData);
		}

		//获取计费方式
		function getCountCostMode(){
			var selectData = '{';
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/deliverycargo/getcountcostmode.shtml',
				async : false,
				success : function(result){
					for(var i = 0; i < result.length; i++){
						var value = result[i].value;
						var text = result[i].name;
						if(i == result.length - 1){
							selectData += '"' + value + '" : "' + text + '"';
						}else{
							selectData += '"' + value + '" : "' + text + '", ';
						}
					}
				}
			});
			selectData += '}';
			return JSON.parse(selectData);
		}
		
		function loadForm(){
			var errorTpl = '<span class="x-icon x-icon-small x-icon-error" data-title="{error}">!</span>';
			BUI.use('bui/form',function(Form){
				baseInfoForm = new Form.Form({
					srcNode : '#baseInfoForm',
					errorTpl : errorTpl
				}).render();
			})
			BUI.use('bui/form',function(Form){
				costInfoForm = new Form.Form({
					srcNode : '#costInfoForm',
					errorTpl : errorTpl
				}).render();
			})
		}
		
		// -------------------------------------
		
		// 获取 编辑表格 的 总重量、总体积
		function getTotalWeightAndVolume(){
			var total_weight = 0;
			var total_volume = 0;
			var items = grid.getItems();
			for(var i = 0; i < items.length; i++){
				var row = items[i];
				var weight = isNull(row.totalWeight) ? 0 : row.totalWeight;
				var volume = isNull(row.totalVolume) ? 0 : row.totalVolume;
				total_weight += weight;
				total_volume += volume;
			}
			var result = new Object();
			result.totalWeight = total_weight;
			result.totalVolume = total_volume;
			return result;
		}
		
		// 计算提货费
		function countTakeCargoCost(){
			var takeCargoCost = 0;
			var weight_price = 0;
			var volume_price = 0;
			var weight_volume_obj = getTotalWeightAndVolume();
			var consignorCounty = $('#consignorCounty').val();
			if(startOutletsPriceRangeConfs != null){
				for(var i = 0; i < startOutletsPriceRangeConfs.length; i++){
					if(startOutletsPriceRangeConfs[i].county == consignorCounty){
						weight_price = startOutletsPriceRangeConfs[i].weight;
						volume_price = startOutletsPriceRangeConfs[i].volume;
						break;
					}
				}
			}
			var weightCost = new Number(weight_volume_obj.totalWeight) * new Number(weight_price);
			var volumeCost = new Number(weight_volume_obj.totalVolume) * new Number(volume_price);
			if(weightCost > volumeCost){
				takeCargoCost = weightCost;
			}else{
				takeCargoCost = volumeCost;
			}
			return takeCargoCost;
		}
		
		// 计算送货费
		function countSendCargoCost(){
			var sendCargoCost = 0;
			var weight_price = 0;
			var volume_price = 0;
			var weight_volume_obj = getTotalWeightAndVolume();
			var consignorCounty = $('#consigneeCounty').val();
			if(endOutletsPriceRangeConfs != null){
				for(var i = 0; i < endOutletsPriceRangeConfs.length; i++){
					if(endOutletsPriceRangeConfs[i].county == consignorCounty){
						weight_price = endOutletsPriceRangeConfs[i].weight;
						volume_price = endOutletsPriceRangeConfs[i].volume;
						break;
					}
				}
			}
			var weightCost = new Number(weight_volume_obj.totalWeight) * new Number(weight_price);
			var volumeCost = new Number(weight_volume_obj.totalVolume) * new Number(volume_price);
			if(weightCost > volumeCost){
				sendCargoCost = weightCost;
			}else{
				sendCargoCost = volumeCost;
			}
			return sendCargoCost;
		}
		
		// 计算装货费
		function countLoadCargoCost(){
			var loadCargoCost = 0;
			var weight_volume_obj = getTotalWeightAndVolume();
			var isLoadCargo = additionalServer.isLoadCargo;
			if(isLoadCargo == 1){ // 选择装货
				// 计算不上楼 费用
				var weightCargoCost = new Number(additionalServerConf.heavyCargo) * new Number(weight_volume_obj.totalWeight);
				var volumeCargoCost = new Number(additionalServerConf.bulkyCargo) * new Number(weight_volume_obj.totalVolume);
				var noFloorLowPrice = new Number(additionalServerConf.noUpstairsLowPrice);
				loadCargoCost = getMaxValue([weightCargoCost,volumeCargoCost,noFloorLowPrice]);
				// 如果 上楼 计算 上楼收费
				var loadCargoFloor = additionalServer.loadCargoFloor;
				if(loadCargoFloor > 0){
					// 判断是否有电梯
					var loadCargoIsElevator = additionalServer.loadCargoIsElevator;
					if(loadCargoIsElevator == 1){ // 有电梯
						loadCargoCost += loadCargoCost * new Number(additionalServerConf.elevatorAdditional) / 100;
					}else{ // 无电梯
						loadCargoCost += loadCargoCost * new Number(additionalServerConf.noElevatorAdditional) * new Number(loadCargoFloor) / 100;
					}
					var floorLowPrice = new Number(additionalServerConf.goUpstairsLowPrice);
					if(floorLowPrice > loadCargoCost){
						loadCargoCost = floorLowPrice;
					}
				}
			}
			return loadCargoCost;
		}
		
		// 计算卸货费
		function countUnloadCargoCost(){
			var unloadCargoCost = 0;
			var weight_volume_obj = getTotalWeightAndVolume();
			var isUnloadCargo = additionalServer.isUnloadCargo;
			if(isUnloadCargo == 1){ // 选择卸货
				// 计算不上楼 费用
				var weightCargoCost = new Number(additionalServerConf.heavyCargo) * new Number(weight_volume_obj.totalWeight);
				var volumeCargoCost = new Number(additionalServerConf.bulkyCargo) * new Number(weight_volume_obj.totalVolume);
				var noFloorLowPrice = new Number(additionalServerConf.noUpstairsLowPrice);
				unloadCargoCost = getMaxValue([weightCargoCost,volumeCargoCost,noFloorLowPrice]);
				// 如果 上楼 计算 上楼收费
				var unloadCargoFloor = additionalServer.unloadCargoFloor;
				if(unloadCargoFloor > 0){
					// 判断是否有电梯
					var unloadCargoIsElevator = additionalServer.unloadCargoIsElevator;
					if(unloadCargoIsElevator == 1){ // 有电梯
						unloadCargoCost += unloadCargoCost * new Number(additionalServerConf.elevatorAdditional) / 100;
					}else{ // 无电梯
						unloadCargoCost += unloadCargoCost * new Number(additionalServerConf.noElevatorAdditional) * new Number(unloadCargoFloor) / 100;
					}
					var floorLowPrice = new Number(additionalServerConf.goUpstairsLowPrice);
					if(floorLowPrice > loadCargoCost){
						unloadCargoCost = floorLowPrice;
					}
				}
			}
			return unloadCargoCost;
		}
		
		function getMaxValue(arr){
			for(var i = 0; i < arr.length - 1; i++){
				for(var j = 0; j < arr.length - i - 1; j++){
					if(arr[j] < arr[j + 1]){
						var temp = arr[j];
						arr[j] = arr[j + 1];
						arr[j + 1] = temp;
					}
				}
			}
			return arr[0];
		}
		
		function validateNumber(obj){
			if(isNaN(obj.value)){
				obj.value = '';
			}
		}
		
		// 提交数据验证
		function validateSubmitData(){
			var cityDriverPhone = $('#cityDriverPhone').val();
			if(cityDriverPhone != null && cityDriverPhone != '' && typeof(cityDriverPhone) != 'undefined'){
				if(!mobilePartten.test(cityDriverPhone)){
		            BUI.Message.Alert('司机电话不正确','warning');
		            return false;
		        }
			}
			return true;
		}
		
		// 提交确认数据
		function submitConfirmData(){
			if(!validateSubmitData()){
				return;
			}
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/tmsorder/saveconfirmorder.shtml',
				data : {
					/* 'cityDriverStr' : JSON.stringify(buildCityDriver()), */
					'orderCargoStr' : JSON.stringify(setWeightVolume()),
					'orderBillStr' : JSON.stringify(buildOrderBill())
				},
				success : function(result){
					if(result == 1){
						BUI.Message.Alert('操作成功',function(){
							history.go(-1);
						},'success');
					}else{
						BUI.Message.Alert('操作失败','error');
					}
				}
			});
		}
		
		function setWeightVolume(){
			var items = grid.getItems();
			for(var i = 0; i < items.length; i++){
				var item = items[i];
				item.singleWeight = item.totalWeight;
				item.singleVolume = item.totalVolume;
			}
			return items;
		}
		
		// 构建城配司机 数据
		/* function buildCityDriver(){
			var cityDriver = new Object();
			cityDriver.cityDriverName = $('#cityDriverName').val();
			cityDriver.cityDriverPhone = $('#cityDriverPhone').val();
			cityDriver.vehicleNumber = $('#vehicleNumber').val();
			cityDriver.vehicleType = $('#vehicleType').val();
			return cityDriver;
		} */
		
		// 构建 账单数据
		function buildOrderBill(){
			var orderNumber = $('#orderNumber').text();
			var agencyFundPoundage = $('#agencyFundPoundage').text(); // 手续费
			var insurance = $('#insurance').text(); // 保险费
			var takeCargoCost = $('#takeCargoCost').val(); // 提货费
			var sendCargoCost = $('#sendCargoCost').val(); // 送货费
			var loadCargoCost = $('#loadCargoCost').val(); // 装货费
			var unloadCargoCost = $('#unloadCargoCost').val(); // 卸货费
			var freight = $('#freight').text(); // 运费
			var otherCost = $('#otherCost').val(); // 其他费用
			var total = $('#total').text(); // 合计
			var orderBill = new Object();
			orderBill.orderNumber = orderNumber;
			orderBill.agencyFundPoundage = agencyFundPoundage;
			orderBill.insurance = insurance;
			orderBill.takeCargoCost = takeCargoCost;
			orderBill.sendCargoCost = sendCargoCost;
			orderBill.loadCargoCost = loadCargoCost;
			orderBill.unloadCargoCost = unloadCargoCost;
			orderBill.freight = freight;
			orderBill.otherCost = otherCost;
			orderBill.totalCost = total;
			return orderBill;
		}
	</script>
</body>
</html>