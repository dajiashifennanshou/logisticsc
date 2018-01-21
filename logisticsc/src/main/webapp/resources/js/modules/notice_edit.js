/*编辑行业信息，公告，常见问题*/
var p2pServerUrl = $('#p2pServerUrl').val();
BUI.use('bui/form', function(Form) {
	var form = new Form.HForm({
		srcNode : '#J_Form'
	});

	form.render();
});