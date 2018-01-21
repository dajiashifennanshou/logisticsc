<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>POS机管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">

</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>POS机管理</h3>
			</div>
			<div class="panel-body">
				<div class="row-fluid">
					<div class="span24">
				        <form id="searchForm" class="well form-inline">
					        <label class="control-label">专线/物流商：</label>
					        <select id="companyInfo" onchange="loadOutletsInfo(this.value)"></select>
					        
					        <label class="control-label">网点：</label>
					        <select id="outletsInfo"></select>
				        	
				            <input type="text" id="condition" class="input-normal" placeholder="POS机编号/登录账号">
				            <button type="button" class="button" onclick="search()">搜索</button>
				        </form>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span24">
						<div id="platformOrderList"></div>
						<div id="platformOrderListBar"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="hide" id="bindDialog">
		<form class="form-horizontal" id="bindForm">
			<input type="hidden" id="outletsId" name="outletsId">
			<div class="row">
				<div class="control-group">
					<label class="control-label">专线/物流商：</label>
					<div class="controls">
						<label class="control-label" id="companyName" style="text-align: left; width: 200px;"></label>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">网点：</label>
					<div class="controls">
						<label class="control-label" id="name" style="text-align: left; width: 200px;"></label>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">POS机编号：</label>
					<div class="controls">
						<input type="text" name="posSn" data-rules="{required:true,isBind:''}">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">登录密码：</label>
					<div class="controls">
						<input type="password" name="loginPwd" data-rules="{required:true,number:true,length:6}">
					</div>
				</div>
			</div>
		</form>
	</div>
	<div class="hide" id="unbindDialog">
		<form class="form-horizontal" id="unbindForm">
			<input type="hidden" id="outletsId" name="outletsId">
			<div class="row">
				<div class="control-group">
					<label class="control-label">专线/物流商：</label>
					<div class="controls">
						<label class="control-label" id="companyName_unbind" style="text-align: left; width: 200px;"></label>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">网点：</label>
					<div class="controls">
						<label class="control-label" id="name_unbind" style="text-align: left; width: 200px;"></label>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">POS机：</label>
					<div class="controls">
						<select id="posBind_unbind"></select>
					</div>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
	var store, grid, bindDialog, bindForm, unbindDialog, unbindForm;
	$(function(){
		loadPosBindList();
		loadBindDialog();
		loadUnbindDialog();
		loadForm();
		loadCompanyInfo();
	});
	
	// 加载POS机绑定列表
	function loadPosBindList(){
		BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
				Toolbar, Format) {
			var Grid = Grid;
			var Store = Data.Store;
			var columns = [ {
				title : '专线/物流商',
				elCls : 'center',
				dataIndex : 'companyName'
			}, {
				title : '组织代码',
				elCls : 'center',
				dataIndex : 'companyCode'
			}, {
				title : '网点',
				width : 130,
				elCls : 'center',
				dataIndex : 'name'
			}, {
				title : 'POS机编号',
				elCls : 'center',
				dataIndex : 'posBinds',
				renderer : function(value){
					var result = '';
					for(var i = 0; i < value.length; i++){
						if(value[i].posSn != null){
							result += value[i].posSn + '<br/>';
						}
					}
					return result;
				}
			}, {
				title : '登录账号',
				elCls : 'center',
				dataIndex : 'posBinds',
				renderer : function(value){
					var result = '';
					for(var i = 0; i < value.length; i++){
						if(value[i].loginAccount != null){
							result += value[i].loginAccount + '<br/>';
						}
					}
					return result;
				}
			} ];
			
			store = new Store({
				url : '<%=request.getContextPath()%>/system/pos/getposbindlist.shtml',
				autoLoad : true,
				proxy : { method : 'post' },
				//remoteSort : true,
				pageSize : 10
			});
			grid = new Grid.Grid({
				render : '#platformOrderList',
				columns : columns,
				store : store,
				forceFit : true,
				plugins : [Grid.Plugins.CheckSelection],
				tbar:{
	                items:[ {
	                    btnCls : 'button button-normal',
	                    text:'绑定',
	                    handler: bindPos
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'解绑',
	                    handler: unbindPos
	                } ]
	            }
			});

			var bar = new Toolbar.NumberPagingBar({
				render : '#platformOrderListBar',
				elCls : 'pagination pull-right',
				store : store
			});
			bar.render();
			grid.render();
		});
	}
	
	// 查询
	function search(){
		var params = {
			'companyId' : $('#companyInfo').val(),
			'outletsId' : $('#outletsInfo').val(),
			'condition' : $('#condition').val()
		}
		store.load(params);
	}
	
	function loadForm(){
		BUI.use('bui/form',function(Form){
			bindForm = new Form.Form({
				srcNode : '#bindForm'
			}).render();
			
			Form.Rules.add({
				name : 'isBind',
				msg : '该POS机已被绑定',
				validator : function(value, baseValue, formatMsg){
					if(validPosIsBind(value)){
						return formatMsg;
		        	}
				}
			});
		})
	}
	
	// 加载 绑定 弹出框
	function loadBindDialog(){
		BUI.use(['bui/overlay'],function(Overlay){
			bindDialog = new Overlay.Dialog({
		    	title:'绑定POS机',
		        width:450,
		        //closeAction : 'destroy', //每次关闭dialog释放
		        contentId:'bindDialog',
		        success:function () {
		        	if(!bindForm.isValid()){
		        		return;
		        	}
		        	$.ajax({
		        		type : 'post',
		        		url : '<%=request.getContextPath()%>/system/pos/bindpos.shtml',
		        		data : $('#bindForm').serialize(),
		        		success : function(result){
		        			if(result == 1){
		        				BUI.Message.Alert('绑定成功','success');
		        				bindDialog.close();
		        				search();
		        			}else{
		        				BUI.Message.Alert('绑定失败','success');
		        			}
		        		}
		        	});
		        }
		    });
            bindDialog.set('effect',{effect : 'fade', duration : 400});
		});
	}
	
	// 加载 解绑 弹出框
	function loadUnbindDialog(){
		BUI.use(['bui/overlay'],function(Overlay){
			unbindDialog = new Overlay.Dialog({
		    	title:'解绑POS机',
		        width:450,
		        //closeAction : 'destroy', //每次关闭dialog释放
		        contentId:'unbindDialog',
		        success:function () {
		        	var posBindId = $('#posBind_unbind').val();
		        	$.ajax({
		        		type : 'post',
		        		url : '<%=request.getContextPath()%>/system/pos/unbindpos.shtml',
		        		data : {'posBindId':posBindId},
		        		success : function(result){
		        			if(result == 1){
		        				BUI.Message.Alert('绑定成功','success');
		        				unbindDialog.close();
		        				search();
		        			}else{
		        				BUI.Message.Alert('绑定失败','success');
		        			}
		        		}
		        	});
		        }
		    });
            unbindDialog.set('effect',{effect : 'fade', duration : 400});
		});
	}
	
	// 绑定POS机
	function bindPos(){
		var selection = grid.getSelection();
		if(selection.length != 1){
			BUI.Message.Alert('请选择一条记录','warning');
			return;
		}
		$('#outletsId').val(selection[0].id);
		$('#companyName').text(selection[0].companyName);
		$('#name').text(selection[0].name);
		if(!validOutletsIsBindCard(selection[0].id)){
			BUI.Message.Alert('该网点未绑定银行卡','warning');
			return;
		}
		$('input[name="posSn"]').val('');
		$('input[name="loginPwd"]').val('');
		bindDialog.show();
	}
	
	// 解绑POS机
	function unbindPos(){
		var selection = grid.getSelection();
		if(selection.length != 1){
			BUI.Message.Alert('请选择一条记录','warning');
			return;
		}
		$('#companyName_unbind').text(selection[0].companyName);
		$('#name_unbind').text(selection[0].name);
		
		var posBinds = selection[0].posBinds;
		var html = '';
		for(var i = 0; i < posBinds.length; i++){
			var id = posBinds[i].posBindId;
			if(id == null){
				BUI.Message.Alert('该网点没有绑定POS机','warning');
				return;
			}
			html += '<option value="'+id+'">'+posBinds[i].posSn+'</option>';
		}
		$('#posBind_unbind').html(html);
		unbindDialog.show();
	}
	
	// 验证网点管理员 是否绑定 银行卡
	function validOutletsIsBindCard(outletsId){
		var flag = false;
		$.ajax({
			type : 'post',
			async : false,
			url : '<%=request.getContextPath()%>/system/pos/validoutletsisbindcard.shtml',
			data : {'outletsId':outletsId},
			success : function(result){
				flag = result;
			}
		});
		return flag;
	}
	
	// 验证 POS机是否已绑定
	function validPosIsBind(posSn){
		var flag = false;
		$.ajax({
			type : 'post',
			async : false,
			url : '<%=request.getContextPath()%>/system/pos/validposisbind.shtml',
			data : {'posSn':posSn},
			success : function(result){
				flag = result;
			}
		});
		return flag;
	}
	
	// 加载专线/物流商数据
	function loadCompanyInfo(){
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/system/pos/getcompanyinfolist.shtml',
			success : function(result){
				var html = '<option value="">请选择专线/物流商</option>';
				for(var i = 0; i < result.length; i++){
					html += '<option value="'+result[i].id+'">'+result[i].companyName+'</option>';
				}
				$('#companyInfo').html(html);
			}
		});
	}
	
	// 加载网点信息
	function loadOutletsInfo(companyId){
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/system/pos/getoutletsinfolist.shtml',
			data : {'companyId' : companyId},
			success : function(result){
				var html = '<option value="">请选择网点</option>';
				for(var i = 0; i < result.length; i++){
					html += '<option value="'+result[i].id+'">'+result[i].name+'</option>';
				}
				$('#outletsInfo').html(html);
			}
		});
	}
	</script>
</body>
</html>