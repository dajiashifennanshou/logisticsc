<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帮助列表</title>
<link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/dpl.css" rel="stylesheet">
<link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/bui.css" rel="stylesheet">
<script src="http://g.tbcdn.cn/fi/bui/jquery-1.8.1.min.js"></script>
<script src="http://g.alicdn.com/bui/seajs/2.3.0/sea.js"></script>
<script src="http://g.alicdn.com/bui/bui/1.1.21/config.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/resources/sysKindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="<%=request.getContextPath()%>/resources/sysKindeditor/lang/zh_CN.js"></script>
</head>
 <style>
    .bui-tree-list{
      overflow: auto;
    }
  </style>
<body>
    <div class="row">
      <div class="span6" style="margin-left: 25px;width: 20%;">
        <div class="panel">
          <div class="panel-header clearfix">
            <h3 class="pull-left">双击查看内容</h3>
            <div id="tbar" class="pull-right"></div>
          </div>
          <div class="panel-body" id= "tree">
           
          </div>
        </div>
        
      </div>
       <div class="span22 offset3" style="margin-left: 4px; width: 75%;display:none;" id="neirong">
         <form class="form-horizontal" id="add_help_content">
	        <div class="panel">
	          <div class="panel-header clearfix">
	            <h3 class="pull-left">内容</h3>
	            <div id="tbar" class="pull-right"></div>
	          </div>
	          <div class="panel-body">
	           		<textarea name="content" id="introduce" class="control-row4 input-large"></textarea>
	          </div>
	           <div class="row"> 
	           			<input type="text" style="display:none;" name="id" id="id"/>      
			          <div class="form-actions offset3" style="margin-right: 32px; float: right;">
			            <input type="button" id="zengjia"  class="button button-primary" value="确认发布"/>
			            <button type="reset" class="button" style="margin-left:10px;">重置</button>
			          </div>
				</div>
			</div>
		</form>
      </div>
          <div id="content" class="hide">
		      <form class="form-horizontal" id="add_help">
		        <div class="row">
		          <div class="control-group span8">
		            <label class="control-label">帮助名称：</label>
		            <div class="controls">
		              <input type="text" id="helpName" name="helpName" class="input-normal control-text" placeholder="请输入帮助名称">
		            </div>
		          </div>
<!-- 		          <div class="control-group span8">
		            <label class="control-label">页面类型：</label>
		            <div class="controls">
		              <select class="input-normal" name="helpIsPage" > 
		              	<option value="0">无页面</option>
		                <option value="1">单页面</option>
		                <option value="2">列表</option>
		              </select>
		            </div>
		          </div> -->
		        </div>
		        <div class="row">
		          <div class="control-group span15">
		            <label class="control-label">备注：</label>
		            <div class="controls control-row4">
		              <textarea class="input-large" id="helpRemark" type="text" name="helpRemark" placeholder="帮助备注"></textarea>
		            </div>
		          </div>
		        </div>
		      </form>
    	</div>
    </div>
 <script type="text/javascript">
 var helpId=0;  //获取树形节点ID
 var kindEditor; // kindEditor 编辑器对象
 $(document).ready(function(){
	 $("#zengjia").click(function(){
		 if(helpId == 0){
			 BUI.Message.Alert("请选择！！！","success");
		 }else{
			 var form =  $("#add_help_content").serialize();
			 form += "&helpId="+helpId;
			 $.ajax({
  			  	url:'<%=request.getContextPath()%>/system/help/doHelpContent.shtml',
  			  	type:'post',
  			  	data:form,
  			  	dataType:'json',
  				success:function(data){
  					if (data.result == true) {
  						BUI.Message.Alert("操作成功！！！","success");
						}else{
							BUI.Message.Alert("操作失败！！！","success");
						}
		        	},
		        	error:function(){
		        		BUI.Message.Alert("操作失败！！！","error");
		        	}
    			});
		 }
		
	 });
	});
 var options = {
			filterMode : false,
			uploadJson : "${configProps['project']}/system/help/upload.shtml",
			allowUpload : true,
			width: "100%", 
		    height: "400",
			afterCreate : function() {
				var self = this;
				self.sync();
			},
			afterChange : function() {
				var self = this;
				self.sync();
			},
			afterBlur : function() {
				var self = this;
				self.sync();
			},
			items : [
						'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
						'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
						'insertunorderedlist', '|', 'image', 'link']
	};
 KindEditor.ready(function(K) {
	 kindEditor = K.create('#introduce',options);
 });

        BUI.use(['bui/tree','bui/data','bui/toolbar','bui/editor'],function (Tree,Data,Toolbar,Editor) {
        	var treeStore = new Data.TreeStore({ 
            	map : {
            		helpName : 'text',
                    id : 'id',
                    helps : 'children',
                },
                autoLoad : true,
        		url : "${configProps['project']}/system/help/getHelp.shtml"
            });
        
      var tree = new Tree.TreeList({
        render : '#tree',
        showLine : true,
        height:"400",
        store : treeStore
      });
      tree.render();
 
      var store = tree.get('store'),
        editor = new Editor.Editor({
          field : { //设置编辑的字段
            rules : {
              required : true
            }
          },
          autoUpdate : false, //不自动更新对应DOM的文本，而使用store更新数据
          changeSourceEvent : null
        });
      editor.render();
 
      editor.on('accept',function(){
        var text = editor.getValue(),
          node = editor.get('curNode');
        node.text = text;
        store.update(node);
      });
 
      //显示编辑器
      function showEditor(node){
        var element = tree.findElement(node);
        editor.setValue(node.text);
 
        editor.set('curNode',node); //缓存当前编辑的记录
        editor.set('align',{ //设置对齐
          node : $(element).find('.x-tree-icon-wraper'),
          points : ['tr','tl']
        });
        editor.show();
        editor.focus(); //获取焦点
 
      }
      //双击编辑
      tree.on('itemdblclick',function(e){
    	  var node = e.item;
    	  helpId = node.id;
    	  $.ajax({
			  	url:'<%=request.getContextPath()%>/system/help/getHelpContent.shtml',
			  	type:'post',
			  	data:{id:helpId},
			  	dataType:'json',
				success:function(data){
					if (data.result == true) {
						$("#id").val(data.data.id);
						kindEditor.html(data.data.content);
						 $("#neirong").show();
						}else{
							$("#id").val("")
							kindEditor.html("");
							$("#neirong").show();
						}
		        	},
		        	error:function(){
		        		BUI.Message.Alert("操作失败！！！","error");
		        	}
  			});
      });
      
      var bar = new Toolbar.Bar({
        render : '#tbar',
        elCls : 'button-group',
        children : [
          {
            elCls : 'button button-small',
            content : '添加',
            handler : function(){
            	BUI.use('bui/overlay',function(Overlay){
      			        var dialog = new Overlay.Dialog({
      			          title:'增加帮助中心',
      			          width:500,
      			          height:320,
      			          closeAction : 'destroy', //每次关闭dialog释放
      			          //配置DOM容器的编号
      			          contentId:'content',
      			          success:function () {
      			       		var selectedNode = tree.getSelected(),
               				 newNode = {text : '新增节点'};
      			       	 var form =  $("#add_help").serialize();
      			       	 	if(selectedNode != null){
	       			       		 form += "&parentId="+selectedNode.id;
	       			       		}
      			        	 $.ajax({
 		        			  	url:'<%=request.getContextPath()%>/system/help/doHelp.shtml',
 		        			  	type:'post',
 		        			  	data:form,
 		        			  	dataType:'json',
 		        			  	headers : {"ClientCallMode" : "ajax"},
 		        				success:function(data){
 		        					if (data.result == true) {
 		        						dialog.close();
 		        						BUI.Message.Alert("操作成功！！！",function(){
 		        							$("#helpName").val("");
 		        							$("#helpRemark").val("");
 	 		        						store.load();
 		        						},"success");
 		        						 
 									}else{
 										dialog.close();
 										BUI.Message.Alert("操作失败！！！",function(){
 	 		        						store.load();
 		        						},"success");
 									}
 					        	},
 					        	error:function(){
 					        		BUI.Message.Alert("操作失败！！！","error");
 					        	}
 		          			});
      			          }
      			        });
      	        dialog.show();
      	    });
            } 
          },
          {
            
            elCls : 'button button-small',
            content : '删除',
            handler : function(){
              var selectedNode = tree.getSelected();
              if(selectedNode){
            	  $.ajax({
       			  	url:'<%=request.getContextPath()%>/system/help/deleteHelp.shtml',
       			  	type:'post',
       			  	data:{id:selectedNode.id},
       			  	dataType:'json',
       			  	headers : {"ClientCallMode" : "ajax"},
       				success:function(data){
       					if (data.result == true) {
       						BUI.Message.Alert("操作成功！！！",function(){
	        				store.load();
       						},"success");
							}else{
								BUI.Message.Alert("操作失败！！！",function(){
			        				store.load();
	       						},"success");
							}
			        	},
			        	error:function(){
			        		BUI.Message.Alert("操作失败！！！","error");
			        	}
         			});
              }
              
            }
          }
        ]
      });
      bar.render();
    });
</script>    
</body>
</html>