BUI.use([ 'bui/overlay', 'bui/form', 'bui/mask' ], function(Overlay, Form) {
	var form, dialog = new Overlay.Dialog({
		title : '分类添加',
		width : 400,
		height : 200,
		loader : {
			url : $('#p2pServerUrl').val() + '/manage/notice/category/edit.shtml',
			autoLoad : false, // 不自动加载
			lazyLoad : false,
			callback : function() {
				var node = dialog.get('el').find('form');// 查找内部的表单元素
				form = new Form.HForm({
					srcNode : node,
					autoRender : true
				});
			}
		},
		mask : false,
		success : function() {
			// 可以直接action 提交
			form && form.submit(); // 也可以form.ajaxSubmit(params);
		}
	});
	// dialog.show();
	function loadForm(id) {
		form && form.destroy(); // 移除掉之前的表单
		dialog.show();
		var params = {
			parentId: $('#parentId').val(),
			parents: $('#parents').val()
		}; // 加载表单的参数
		if (id) {
			params['id'] = id;
		}
		dialog.get('loader').load(params);
	}
	$('#btnShow').on('click', function() {
		loadForm();
	});
	
	$(".btnEdit").click(function(){
		var id = $(this).attr('data');
		loadForm(id)
	});
	$(".btnDel").click(function(){
		var id = $(this).attr('data');
		var msg = "数据删除将不可恢复，您确认要删除吗？"
		if(confirm(msg)) {
			$('#deleteItemId').val(id);
			$('#deleteItemForm').submit();
		}
	});
	
	
});