<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>货运交易系统-后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${configProps['resources']}/boot.js"></script>
</head>
<body>
	<!-- grid -->
	<div class="demo-content">
	  		<form action="">
				<table>
					<tr>
						<td>类别：<select id="type" name="type" onchange="fnSelect();">
								<option value="misbaseinfo">资管</option>
								<option value="trustbaseinfo">信托</option>
								<option value="fundbaseinfo">基金</option>
						</select></td>
						<td>资管公司：<input type="text"></input></td>
					</tr>
				</table>
			</form>
			<div class="demo-content">
		        <div id="grid">
		          
		        </div>
			</div>
    </div>
	<script type="text/javascript">
        BUI.use(['bui/grid','bui/data'],function(Grid,Data){
            var Grid = Grid,
          Store = Data.Store,
          enumObj = {"1" : "选项一","2" : "选项二","3" : "选项三"},
          columns = [
            {title : '编号',dataIndex :'id'},
            {title : '文本',dataIndex :'a'}, //editor中的定义等用于 BUI.Form.Field.Text的定义
            {title : '数字', dataIndex :'b'},
            {title : '日期',dataIndex :'c'},
            {title : '单选',dataIndex : 'd',renderer : Grid.Format.enumRenderer(enumObj)},
            {title : '多选',dataIndex : 'e',renderer : Grid.Format.multipleItemsRenderer(enumObj)},
            {title : '操作',renderer : function(){
              return '<span class="grid-command btn-edit">编辑</span>'
            }}
          ];
 
        var 
          editing = new Grid.Plugins.DialogEditing({
            contentId : 'content', //设置隐藏的Dialog内容
            triggerCls : 'btn-edit', //触发显示Dialog的样式
            editor: {
              title: '编辑'
            },
            autoSave : true //自动添加和更新
          }),
          store = new Store({
            autoLoad:true,
            url : 'data/records.php',
            //autoSync : true, //保存数据后自动调用store.load()方法
            proxy : {
              method : 'POST', //更改为POST
              save : 'data/save.php' //会附加一个saveType 的参数，add,remove,update
              //也可以使用一下方式：
              //save : {
              //  addUrl : 'data/add.php',
              //  removeUrl : 'data/remove.php',
              //  updateUrl : 'data/update.php'
              //}
            }
          }),
          grid = new Grid.Grid({
            render:'#grid',
            columns : columns,
            width : '100%',
            forceFit : true,
            tbar:{ //添加、删除
                items : [{
                  btnCls : 'button button-small',
                  text : '<i class="icon-plus"></i>添加',
                  listeners : {
                    'click' : addFunction
                  }
                },
                {
                  btnCls : 'button button-small',
                  text : '<i class="icon-remove"></i>删除',
                  listeners : {
                    'click' : delFunction
                  }
                }]
            },
            plugins : [editing,Grid.Plugins.CheckSelection],
            store : store
          });
 
        grid.render();
 
        //保存成功时的回调函数,其实更好的方式是直接在保存成功后调用store.load()方法，更新所有数据
        store.on('saved',function (ev) {
          var type = ev.type, //保存类型，add,remove,update
            saveData = ev.saveData, //保存的数据
            data = ev.data; //返回的数据
 
          //TO DO
          if(type == 'add'){ //新增记录时后台返回id
            saveData.id = data.id;
            grid.updateItem(saveData);
            //store.update()
            //BUI.Message.Alert('添加成功！');
          }else if(type == 'update'){
            //BUI.Message.Alert('更新成功！');
          }else{
            //BUI.Message.Alert('删除成功！');
          }
        });
        //保存或者读取失败
        store.on('exception',function (ev) {
          BUI.Message.Alert(ev.error);
        });
 
        //添加记录
        function addFunction(){
          var newData = {b : 0};
          editing.add(newData,'a'); //添加记录后，直接编辑
        }
        //删除选中的记录
        function delFunction(){
          var selections = grid.getSelection(),
            ids = BUI.Array.map(selections,function (item) {
              return item.id;
            });
          store.remove(selections);
          store.save('remove',{ids : ids.join(',')}); //save的第三个参数是回调函数
        }          
      });
    </script>
</body>