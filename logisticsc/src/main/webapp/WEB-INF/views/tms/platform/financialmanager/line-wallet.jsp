<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>专线钱包</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>我的财富</h3>
			</div>
			<div class="panel-body">
				<div class="row" style="line-height: 30px;">
					<div class="span3"><span>账户余额：1000</span></div>
					<div class="span3"><span>冻结金额：1000</span></div>
					<div class="span3"><span>可提现金额：1000</span></div>
					<div class="span2"><button class="button">充值</button></div>
					<div class="span2"><button class="button" onclick="takeCash()">提现</button></div>
					<div class="span2"><button class="button">转账</button></div>
				</div>
			</div>
		</div>
		<div class="panel">
			<div class="panel-header">
				<h3>我的银行卡</h3>
			</div>
			<div class="panel-body">
				<div class="row" style="line-height: 30px;">
					<div class="span3"><span>招商银行：**********9999</span></div>
					<div class="span2"><button class="button">添加</button></div>
				</div>
			</div>
		</div>
		<div class="panel">
			<div class="panel-header">
				<h3>预存运费</h3>
			</div>
			<div class="panel-body">
				预存运费列表
			</div>
		</div>
		<div class="panel">
			<div class="panel-header">
				<h3>费用明细</h3>
			</div>
			<div class="panel-body">
				费用明细
			</div>
		</div>
	</div>
	<div class="hide" id="takeCashDialog">
		<form class="form-horizontal" id="takeCashForm">
			<input type="hidden" id="signWayBillNumber" name="wayBillNumber">
			<div class="row">
				<div class="control-group">
					<label class="control-label">金额：</label>
					<div class="controls">
						<input type="text" name="money" class="input-normal" data-rules="{required:true,number:true}">
					</div>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
	var takeCashDialog, takeCashForm;
	$(function(){
		loadTakeCashDialog();
	});
	
	function loadTakeCashDialog(){
		BUI.use(['bui/overlay'],function(Overlay){
			takeCashDialog = new Overlay.Dialog({
		    	title:'提现',
		        width:460,
		        contentId:'takeCashDialog',
		        success:function () {
		        	if(!takeCashForm.isValid()){
		        		return;
		        	}
		        	$.ajax({
		        		type : 'post',
		        		url : '<%=request.getContextPath()%>/tms/financial/savetakecashapply.shtml',
		        		data : $('#takeCashForm').serialize(),
		        		success : function(result){
		        			if(result == 1){
		        				BUI.Message.Alert('操作成功','success');
		        				takeCashDialog.close();
		        			}else{
		        				BUI.Message.Alert('操作失败','error');
		        			}
		        		}
		        	});
		        }
		    });
			takeCashDialog.set('effect',{effect : 'fade', duration : 400});
		});
	}
	
	function loadForm(){
		BUI.use('bui/form',function(Form){
			takeCashForm = new Form.Form({
				srcNode : '#takeCashForm',
			}).render();
		})
	}
	
	function takeCash(){
		takeCashDialog.show();
		loadForm();
	}
	</script>
</body>
</html>