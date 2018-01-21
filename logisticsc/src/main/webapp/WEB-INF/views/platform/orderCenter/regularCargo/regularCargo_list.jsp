<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的常用货物</title>
<!-- jquery -->
<script src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js"></script>
<!-- 全局样式 -->
<link href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<!-- 分页 -->
<script src="${configProps['resources']}/platform/js/pager.js"></script>
<link rel="stylesheet" href="${configProps['resources']}/platform/css/pager.css" />
<!-- bootstrap-->
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap日期-->
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap-datetimepicker.js"></script>
<!-- bootstrap日期中文-->
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap-datetimepicker.zh-CN.js"></script>
<!-- bootstrap兼容IE -->
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<script src="${configProps['resources']}/platform/js/regularCargoList.js"></script>
</head>
<body>
<jsp:include page="../../top.jsp"></jsp:include>
<div class="container-self">
	<div class="frame_left">
		<jsp:include page="../order_left.jsp"></jsp:include>
	</div>
	<div class="demo-content platformStyle">
		<div class="clearfix">
			<form class="form-inline" style="margin-top: 13px; margin-left: 18px;">
			 <label >货物名称：</label>
				  <input type="text" id="cargoName" class="form-control" name="cargoName" placeholder="货物名称">
				  <button type="button" onclick="selectCommonCargo()" class="btn btn-info">查询</button>
				  <button type="button" onclick="displayCommonCargo()" class="btn btn-info">添加常发货物</button>
				  <button type="button" class="btn" id="anniu-yellow" onclick="deleteCommonCargo()">删除</button>
			</form>
		</div>
		<div class="bill-zong">
			<table class="table table-striped" id="bill-table1">
				<thead>
					<tr>
						<th></th>
						<th>货物名称</th>
						<th>货物品牌</th>
						<th>货物型号</th>
						<!-- <th>单件重量(T)</th>
						<th>单件体积(m³)</th> -->
						<th>包装信息</th>
						<th>备注信息</th>
					</tr>
				</thead>
				<tbody id="getCommonCargo">
				</tbody>
			</table>
			<div id="pageList">
			</div>
		</div>
	</div>
</div>	
<!-- 常发货物 -->
<div id="addCommonCargo" class="modal  fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
				<!-- BEGIN form-->
				<form class="form-horizontal form-bordered" id="add_common_cargo">
					<div class="modal-header modal-info modal-success">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
						<h4 class="modal-title" id="myModalLabel">增加常发货物</h4>
						<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
					</div>
					<div class="modal-body add-role-body" >
						<div class="form-group"  style="margin-top: 14px;" >
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;货物名称：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="cargoNameMsg"></label>
								<input type="text" style="display:inline-block;width:250px" id="cargoNames" name="cargoName" class="form-control required" placeholder="请输入货物名称"/>
							</div>
						</div>
						<div class="form-group"  style="margin-top: 28px;" >
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;货物品牌：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="cargoBrandMsg"></label>
								<input type="text" style="display:inline-block;width:250px" id="cargoBrand" name="cargoBrand" class="form-control required" placeholder="请输入货物品牌"/>
							</div>
						</div>
						<div class="form-group"  style="margin-top: 28px;" >
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;货物型号：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="modelMsg"></label>
								<input type="text" style="display:inline-block;width:250px" id="model" name="model" class="form-control required" placeholder="请输入货物型号"/>
							</div>
						</div>
						<!-- <div class="form-group"  style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;单件重量：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="singleWeightMsg"></label>
								<input type="text" style="display:inline-block;width:250px" id="singleWeight" name="singleWeight" class="form-control required" placeholder="请输入货物单件重量"/><span>(T)</span>
							</div>
						</div>
						<div class="form-group"  style="margin-top: 28px;">
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;单件体积：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="singleVolumeMsg"></label>
								<input type="text" style="display:inline-block;width:250px" id="singleVolume" name="singleVolume" class="form-control required" placeholder="请输入货物单件体积"/><span>(m³)</span>
							</div>
						</div> -->
						<div class="form-group"  style="margin-top: 28px;" >
							<label class="control-label col-sm-4"><span style="color: red">*</span>&nbsp;&nbsp;包装信息：</label>
							<div class="col-sm-4">
								<label class="login-sjh" id="packingInfoMsg"></label>
								<select id="packingInfos" class="form-control fcjselect" name="packingInfo">
					          	</select>
							</div>
						</div>
						<div class="form-group"  style="margin-top: 28px;">
							<label class="control-label col-sm-4">备注：</label>
							<div class="col-sm-8">
								<label class="login-sjh" id="remarksMsg"></label>
								<textarea class="form-control" rows="3" style="display:inline-block;width:250px" id="remarks" name="remarks" placeholder="请输入货物备注"></textarea>
							</div>
						</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-success btn-save" id="baocun">保存</button>
					</div>
				</div>	
			</form>
		</div>
	</div>
</div>
<div id="successModal" class="modal fade" tabindex="-1" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header modal-info modal-success">
				<h4 class="modal-title" id="myModalLabel">页面提示</h4>
				<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
			</div>
			<div class="modal-body success-info">
				 <h5 style="color: #6f9fd9!important;font-weight:bold">成功提示:<span id="successModalMsgs"></span></h5>
			</div>
		</div>
	</div>
</div>
<div id="errorModal" class="modal  fade" tabindex="-1" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header modal-info modal-success">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                <h4 class="modal-title" id="myModalLabel" style="color: red !important;">操作失败！</h4>
                <a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
            </div>
            <div class="modal-body success-info">
                <h5 style="color: red !important;font-weight:bold">错误提示:<span id="errorModalMsgs"></span></h5>
            </div>
        </div>
    </div>
</div>
<div id="promptModal" class="modal  fade" tabindex="-1" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header modal-info modal-success">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                    <h4 class="modal-title" id="myModalLabel" style="color: #cccccc !important;">温馨提示！</h4>
              		<a class="close" data-dismiss="modal" style="margin-top:-23px;">×</a>
                </div>
                <div class="modal-body success-info">
                    <h5 style="color: #cccccc!important;font-weight:bold">温馨提示:<span id="promptModalMsgs"></span></h5>
                </div>
            </div>
        </div>
    </div>
 <div style="clear: both;"></div>
<jsp:include page="../../bottom.jsp"></jsp:include>
</body>
</html>