<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>线路管理</title>
  <link href="../../assets/code/demo.css" rel="stylesheet">
  <link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/dpl.css" rel="stylesheet">
  <link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/bui.css" rel="stylesheet">
</head>
<body>
	<h2>线路管理</h2>

<div>
	<form>
		<button>新增</button>
		<button>删除</button>
		<button>编辑</button>
		始发地：<input type="text" name="">
		目的地：<input type="text" name="">
		服务类型：<input type="text" name="">
		<button style="width:50px">搜索</button>
	</form>
</div>
  <div class="demo-content">
    <div class="row">
      <div class="span16">
        <div id="grid">
          
        </div>
      </div>
    </div>
    
 
  <script src="http://g.tbcdn.cn/fi/bui/jquery-1.8.1.min.js"></script>
  <script src="http://g.alicdn.com/bui/seajs/2.3.0/sea.js"></script>
  <script src="http://g.alicdn.com/bui/bui/1.1.21/config.js"></script>
 
<!-- script start --> 
    <script type="text/javascript">
        BUI.use(['bui/grid','bui/data'],function(Grid,Data){
            var Grid = Grid,
          Store = Data.Store,
          columns = [
            {title : '服务类型',dataIndex :'a', width:100},
            {id: '123',title : '线路',dataIndex :'b', width:100},
            {title : '收费信息',dataIndex : 'c',width:100},
            {title : '运输方式',dataIndex : 'c',width:100},
            {title : '时效',dataIndex : 'c',width:100},
            {title : '状态',dataIndex : 'c',width:100},
            {title : '对外发布',dataIndex : 'c',width:100}
          ];
 
        /**
         * 自动发送的数据格式：
         *  1. start: 开始记录的起始数，如第 20 条,从0开始
         *  2. limit : 单页多少条记录
         *  3. pageIndex : 第几页，同start参数重复，可以选择其中一个使用
         *
         * 返回的数据格式：
         *  {
         *     "rows" : [{},{}], //数据集合
         *     "results" : 100, //记录总数
         *     "hasError" : false, //是否存在错误
         *     "error" : "" // 仅在 hasError : true 时使用
         *   }
         * 
         */
        var store = new Store({
            url : 'data/grid-data.php',
            autoLoad:true, //自动加载数据
            params : { //配置初始请求的参数
              a : 'a1',
              b : 'b1'
            },
            pageSize:3	// 配置分页数目
          }),
          grid = new Grid.Grid({
            render:'#grid',
            columns : columns,
            loadMask: true, //加载数据时显示屏蔽层
            store: store,
            // 底部工具栏
            bbar:{
                // pagingBar:表明包含分页栏
                pagingBar:true
            }
          });
 
        grid.render();
      });
    </script>
<!-- script end -->
  </div>
</body>
</html>