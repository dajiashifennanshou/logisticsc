<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自定义运单费用</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">

</head>
<body>
	<div class="row-fluid">
		<div class="panel">
			<div class="panel-header">
				<h3>自定义运单费用</h3>
			</div>
			<div class="panel-body">
				<div class="row-fluid">
					<div class="span24">
						<div id="customWayBillCostList"></div>
						<div id="customWayBillCostListBar"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="hide" id="addDialog">
			<form class="form-horizontal" id="customWayBillCostForm">
				<input type="hidden" id="id" name="id">
				<div class="row">
					<div class="control-group">
						<label class="control-label">名称：</label>
						<div class="controls">
							<input type="text" id="name" name="name" class="input-normal" data-rules="{required:true}">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="control-group">
						<label class="control-label">排列顺序：</label>
						<div class="controls">
							<input type="text" id="sort" name="sort" class="input-normal" data-rules="{number:true}">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script type="text/javascript">
	var store, grid, addDialog, customWayBillCostForm;
	$(function(){
		loadCustomWayBillCostList();
		loadAddDialog();
		loadForm();
	});
	
	// 加载运单列表
	function loadCustomWayBillCostList(){
		BUI.use([ 'bui/grid', 'bui/data', 'bui/toolbar', 'bui/format' ], function(Grid, Data,
				Toolbar, Format) {
			var Grid = Grid;
			var Store = Data.Store;
			var columns = [ {
				title : '名称',
				elCls : 'center',
				dataIndex : 'name'
			}, {
				title : '排列顺序',
				elCls : 'center',
				dataIndex : 'sort'
			}];
			
			store = new Store({
				url : '<%=request.getContextPath()%>/tms/waybillcost/getcustomwaybillcost.shtml',
				autoLoad : true,
				//remoteSort : true,
				pageSize : 10
			});
			grid = new Grid.Grid({
				render : '#customWayBillCostList',
				columns : columns,
				store : store,
				forceFit : true,
				plugins : [Grid.Plugins.CheckSelection],
				tbar:{
	                // items:工具栏的项， 可以是按钮(bar-item-button)、 文本(bar-item-text)、 默认(bar-item)、 分隔符(bar-item-separator)以及自定义项 
	                items:[{
	                	btnCls : 'button button-normal',
	                    text:'添加',
	                    handler : function(){
	                    	addDialog.show();
	                    	customWayBillCostForm.clearFields();
	                    }
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'编辑',
	                    handler : edit
	                }, {
	                    btnCls : 'button button-normal',
	                    text:'删除',
	                    handler : deleteCost
	                }]
	            }
			});

			var bar = new Toolbar.NumberPagingBar({
				render : '#customWayBillCostListBar',
				elCls : 'pagination pull-right',
				store : store
			});
			bar.render();
			grid.render();
		});
	}
	
	// 加载 签收 弹出框
	function loadAddDialog(){
		BUI.use(['bui/overlay'],function(Overlay){
			addDialog = new Overlay.Dialog({
		    	title:'添加自定义运单费用',
		        width:460,
		        contentId:'addDialog',
		        success:function () {
		        	save();
		        }
		    });
			addDialog.set('effect',{effect : 'fade', duration : 400});
		});
	}
	
	function loadForm(){
		BUI.use('bui/form',function(Form){
			customWayBillCostForm = new Form.Form({
				srcNode : '#customWayBillCostForm',
			}).render();
		})
	}
	
	// 保存
	function save(){
		customWayBillCostForm.valid();
		if(!customWayBillCostForm.isValid()){
			return;
		}
		$.ajax({
			type : 'post',
			url : '<%=request.getContextPath()%>/tms/waybillcost/save.shtml',
			data : $('#customWayBillCostForm').serialize(),
			success : function(result){
				if(result == 1){
					BUI.Message.Alert('保存成功',function(){
						store.load();
					},'success');
				}else{
					BUI.Message.Alert('保存失败','error');
				}
				addDialog.close();
			}
		});
	}
	
	function edit(){
		var selection = grid.getSelection();
		if(selection.length == 0){
			BUI.Message.Alert('请选择','warning');
			return;
		}else if(selection.length > 1){
			BUI.Message.Alert('只能选择一个条记录','warning');
			return;
		}
		addDialog.show();
		customWayBillCostForm.clearFields();
		$('#id').val(selection[0].id);
		$('#name').val(selection[0].name);
		$('#sort').val(selection[0].sort);
	}
	
	// 删除
	function deleteCost(){
		var selection = grid.getSelection();
		if(selection.length == 0){
			BUI.Message.Alert('请选择','warning');
			return;
		}
		BUI.Message.Confirm('确定要删除吗？',function(){
			var ids = [];
			for(var i = 0; i < selection.length; i++){
				ids.push(selection[i].id);
			}
			$.ajax({
				type : 'post',
				url : '<%=request.getContextPath()%>/tms/waybillcost/delete.shtml',
				data : {'ids':JSON.stringify(ids)},
				success : function(result){
					if(result == 1){
						BUI.Message.Alert('删除成功',function(){
							store.load();
						},'success');
					}else{
						BUI.Message.Alert('删除失败','error');
					}
				}
			});
		},'question');
	}
	</script>
</body>
</html>