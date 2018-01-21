var p2pServerUrl = $('#p2pServerUrl').val();
var catId = $('#categoryId').val();
var sourceCat = $('#sourceCat').val();
var editUrl = p2pServerUrl + '/manage/catalogue/notice/edit.shtml?categoryId='
		+ catId + "&sourceCat=" + sourceCat;

var deleteUrl = p2pServerUrl
		+ '/manage/catalogue/notice/delete.shtml?categoryId='
		+ catId + "&sourceCat=" + sourceCat;
function delNotice(id) {
	$.post(deleteUrl, {
		id : id
	});
};

var Grid = BUI.Grid, Format = Grid.Format, Data = BUI.Data;
var Grid = Grid, Store = Data.Store;
var columns = [
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
	/*	{
			title : '作者',
			dataIndex : 'createUser',
			width : "50"
		},*/
		{
			title : '状态',
			dataIndex : 'status',
			widht : '30',
			renderer : function(value, obj) {
				var param = JSON.stringify(obj.status);
				statusDesc = {
					'0' : '删除',
					'1' : '正常'
				}
				return statusDesc[param];
			}
		},
		{
			title : '操作',
			dataIndex : 'option',
			width : '100',
			renderer : function(value, obj) {
				var param = JSON.stringify(obj.id);
				return "<a class='button-info button-default btn1' href='"
						+ editUrl + '&id='
						+ param
						+ "'>编辑</a> &nbsp;&nbsp;"
						+ "<a class='button-danger button-default btn2' href='javascript:delNotice("
						+ param + ")'>删除</a>";
			}
		} ];

var store = new Store({
	url : p2pServerUrl + '/manage/catalogue/notice/list.shtml?categoryId='
			+ $('#categoryId').val(),
	proxy : {// 设置请求相关的参数
		method : 'get',
		// dataType : 'jsonp', //返回数据的类型
		limitParam : 'pageSize', // 一页多少条记录
		pageIndexParam : 'pageNum' // 页码
	/* startParam : 'startNum' //起始记录 */

	},
	autoLoad : true,
	pageSize : 10,
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