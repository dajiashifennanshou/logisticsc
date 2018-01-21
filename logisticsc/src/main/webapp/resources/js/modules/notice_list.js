
var Grid = BUI.Grid, Format = Grid.Format, Data = BUI.Data;
var Grid = Grid, Store = Data.Store;
var columns = [
		{
			title : '编号',
			dataIndex : 'id',
			width : '100'
		},
		{
			title : '标题',
			dataIndex : 'title',
			width : '100'
		},
		{
			id : '123',
			title : '发布时间',
			dataIndex : 'createTime',
			renderer : Format.datetimeRenderer,
			width : '150'
		},
		{
			title : '序号',
			dataIndex : 'position',
			width : "50"
		},
		{
			title : '所属分类',
			dataIndex : 'cateString',
			width : "150"
		},
		{
			title : '操作',
			dataIndex : 'option',
			width : '100',
			renderer : function(value, obj) {
				var param = JSON.stringify(obj.id);
				return "<span class='grid-command btn1' onclick='fnEdit("+ param + ")'>编辑</span>"
					 + "<span class='grid-command btn2' onclick='fnDelete("+ param + ")'>删除</span>";

			}
		} ];

var store = new Store({
	url : p2pServerUrl + '/manage/catalogue/notice/list.shtml',
	autoLoad : true,
	pageSize : 5,
// 配置分页数目
});

var grid = new Grid.Grid(
		{
			render : '#grid',
			columns : columns,
			loadMask : true,
			emptyDataTpl : '<div class="centered"><img alt="Crying" src="http://img03.taobaocdn.com/tps/i3/T1amCdXhXqXXXXXXXX-60-67.png"><h2>查询的数据不存在</h2></div>',
			store : store,
			// 头部工具栏
			tbar : {
				items : [ {
					btnCls : 'button button-primary button-small',
					text : '新增',
					handler : function() {
						location.href = editUrl;
					}
				} ]
			},
			// 底部工具栏
			bbar : {
				// pagingBar:表明包含分页栏
				pagingBar : true
			}
		});

grid.render();


//编辑产品
function fnEdit(id) {
	location.href = "${configProps['static.project.url'] }/manage/catalogue/edit.shtml?id=" + id;
}
//删除产品
function fnDelete(contractid) {
	BUI.Message.Confirm('您确定要删除选定的的项吗?', function() {
		$.post("${configProps['static.project.url'] }/manage/catalogue/delete.shtml?id=" + id,
				function(data) {
					if (data.success) {
						BUI.Message.Alert(data.msg, function() {
							store.load();
						}, 'success');
					} else {
						BUI.Message.Alert(data.msg, 'error');
					}
				}, 'json');
	}, 'question');
}