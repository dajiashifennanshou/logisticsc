<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>线下入库</title>
	<link rel="stylesheet" href="/logisticsc/Clound/assets/css/ui.jqgrid.css" />
	<link href="/logisticsc/resources/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
	<link href="/logisticsc/resources/platform/bootstrap-css/bootstrap-theme.min.css" rel="stylesheet">
	<link href="/logisticsc/resources/platform/bootstrap-css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<!-- 全局样式 -->
	<link href="/logisticsc/resources/platform/css/external-introduction.css" rel="stylesheet">
	<link href="/logisticsc/resources/platform/css/table.css" rel="stylesheet">
	<script type="text/javascript" src="/logisticsc/Clound/js/yc_public.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/JoinElseJs.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/jquery.jqGrid.js"></script>
	<script src="/logisticsc/Clound/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="/logisticsc/Clound/js/in_storage/in_storage.js"></script>
	<script type="text/javascript">
		var editLineHtml = null;
		var mobilePartten = /^\d{11}$/;
		var telephonePartten = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
		var vehicleNumberPartten = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
		var isValid = false;
		$(function(){
			//构建货物
			buildCargoEditLineHtml();
			inStorage_.init();
			$('.select2').css('width','41.7%').select2({allowClear:true});
		});
		// 删除货物
		function deleteCargo(obj){
			var data = buildCargoInfoData();
			if(data.length <= 1){
				$("#promptModal").modal('show');
				$("#promptModalMsgs").html("不能没有货物");
			}else{
				if(confirm('确定要删除吗？')){
					$(obj).parent().parent().remove();
				}
			}
		}
		// 构建货物信息数据
		function buildCargoInfoData(){
			var cargoInfos = [];
			var trs = $('#cargoTable tr');
			for(var i = 1; i < trs.length; i++){
				var tds = $(trs[i]).children();
				var cargo = new Object();
				cargo.goodsName = $(tds[0]).children()[0].value;
				cargo.goodsBrand = $(tds[1]).children()[0].value;
				cargo.model = $(tds[2]).children()[0].value;
				cargo.goodsType=$(tds[3]).children()[0].value;
				cargo.goodsNum = $(tds[4]).children()[0].value; 
				//cargo.setNumber = $(tds[5]).children()[0].value;
				cargo.weight = $(tds[6]).children()[0].value;
				cargo.volume = $(tds[7]).children()[0].value;
				cargo.park = $(tds[8]).children()[0].value;
				cargoInfos.push(cargo)
			}
			return cargoInfos;
		}
		// 添加货物编辑行
		function addCargoEditLine(){
			$('#cargoTable tbody').append(editLineHtml);
		}
		// 构建 货物信息行编辑html
		function buildCargoEditLineHtml(){
			editLineHtml = $('#cargoTable tbody').html();
		}
		
	</script>
</head>
<body>
<div class="widget-box">
	<div class="widget-header widget-header-blue widget-header-flat">
		<h4 class="widget-title lighter">线下入库</h4>
	</div>
	<div class="widget-body">
		<div class="widget-main">
			<div class="step-content pos-rel">
				<form class="form-horizontal" id="add_in_form"  role="form">
					<!-- input标签 -->
					<div class="step-pane active" data-step="1">	
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="waybillNo">经销商</label>
							<div class="col-sm-9">
								<select group="val" name="dealerId" valited="required" class="select2-container select2-allowclear select2 tag-input-style">
										<option value="">请选择下单经销商</option>
										<c:if test="${userList!=null }">
											<c:forEach items="${userList}" var="user">
												<option value="${user.id}"> ${user.trueName }-${user.joinName}-${user.loginName }</option>
											</c:forEach>
										</c:if>
								</select>
							</div>
						</div>
						<div class="row con1">
							<a class="btn btn-default btn-sm" onclick="addCargoEditLine()">添加货物</a>
						</div>
						<div class="row col-md-12 con1">
							<table class="table col-md-12 text-center" id="cargoTable">
								<thead>
									<tr>
										<th class="text-center">货物名称</th>
										<th class="text-center">货物品牌</th>
										<th class="text-center">货物型号</th>
										<th class="text-center">货物类型</th>
										<th class="text-center">件数</th>
										<th class="text-center">套数</th>
										<th class="text-center">重量(t)</th>
										<th class="text-center">体积(m³)</th>
										<th class="text-center">包装信息</th>
										<th class="text-center">操作</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td ><input type="text" style="width:90px;" class="edit-table-input" value=""></td>
										<td><input type="text" style="width:90px;" class="edit-table-input" value=""></td>
										<td><input type="text" style="width:90px;" class="edit-table-input" value=""></td>
										<!-- 货物类型 -->
										<td>
											<select name="packageInfo" class="edit-table-select">
												<c:forEach items="${goodsTypes}" var="goodsType">
													<option value="${goodsType.id}">${goodsType.softName}</option>
												</c:forEach>
											</select>
										</td>
										<td><input style="width:90px;" type="text" class="edit-table-input" value="" ></td>
										<td><input style="width:90px;" type="text" class="edit-table-input" value="" ></td>
										<td><input style="width:90px;" type="text" class="edit-table-input" value="" ></td>
										<td><input style="width:90px;" type="text" class="edit-table-input" value="" ></td>
										<td>
											<select name="packageInfo" class="edit-table-select">
												<c:forEach items="${packageTypes}" var="packageType">
													<option value="${packageType.id}">${packageType.name}</option>
												</c:forEach>
											</select>
										</td>
										<td><a href="javascript:;" style="text-decoration: none;" onclick="deleteCargo(this)">删除</a></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</form>
			</div>
			<div class="wizard-actions">
				<a onclick="inStorage_.prevSubmit();" disabled="disabled" class="btn  btn-prev" data-last="Finish">
					<i class="ace-icon fa fa-arrow-left"></i>
					上页
				</a>
				<a onclick="inStorage_.addSubmitSelf('add_in_form');" class="btn btn-success btn-next" data-last="Finish">
					提交
					<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</a>

			</div>
				
			</div>
		</div>
</div>
</body>
</html>