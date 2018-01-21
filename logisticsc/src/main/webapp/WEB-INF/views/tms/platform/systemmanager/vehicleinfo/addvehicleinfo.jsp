<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>车辆管理</title>
<link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/dpl.css" rel="stylesheet">
<link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/bui.css" rel="stylesheet">
</head>
<body>
	<form id="add_vehicle" class="form-horizontal" onSubmit="return false;">
		<div class="panel">
	        <div class="panel-header">
	           	<h2>车辆信息</h2>
	        </div>
	        <div class="panel-body">
				<div class="row">
					<div class="control-group span12">
				      	<label class="control-label"><s>*</s>所属网点</label>
			         	<div class="controls">
			             	<input type="text" class="input-normal control-text" disabled="disabled" value="${outletsInfos.name }">
			           	</div>
				  	</div>
				    <%-- <div class="control-group span8">
				      	<label class="control-label"><s>*</s>线路</label>
			        	<div class="controls">
			            	<select name="lineId" data-rules="{required:true}" class="input-normal"> 
			             		<option value=""></option>
				               	<c:forEach var = "lineInfo" items="${lineInfos }" step="1">
				               		<option value="${lineInfo.id }">${lineInfo.startOutletsName }<-->${lineInfo.endOutletsName }</option>
				               	</c:forEach>
			              	</select>
			            </div>
				    </div> --%>
				    <div class="control-group span12">
				    	<label class="control-label"><s>*</s>合作形式</label>
				      	<div class="controls">
				             <select name="cooperationWay" data-rules="{required:true}" class="input-normal"> 
				             		<option value=""></option>
				             		<c:forEach var="cooperationWay" items="${cooperationWays }" step="1">
				             		<option value="${cooperationWay.id }">${cooperationWay.name }</option>
				             </c:forEach>
				             </select>
				     	</div>
				 	</div>
				 </div>
				 <div class="row">
				    <div class="control-group span8">
				    	<label class="control-label"><s>*</s>车牌号</label>
			         	<div class="controls">
			             	<input name="plateNumber" type="text" data-rules="{required:true,v_cpNum:true,isExistVehicle:''}" class="input-normal control-text">
			           	</div>
				   	</div>
					<div class="control-group span8">
				      	<label class="control-label">挂车号</label>
			          	<div class="controls">
			             	<input name="trailerNumber" type="text" data-rules="{v_gcpNum:true}" class="input-normal control-text">
			           	</div>
				   	</div>
				   	<div class="control-group span10">
				       	<label class="control-label"><s>*</s>车型</label>
				        <div class="controls bui-form-group-select">
				          	<select name="vehicleType" data-rules="{required:true}" class="input-small">
				          		<option value=""></option>
				          		<c:forEach var="vehicleType" items="${vehicleTypes }" step="1">
				          			<option value="${vehicleType.id }">${vehicleType.name }</option>
				         		</c:forEach>
				          	</select>&nbsp;&nbsp;
				          	<select name="vehicleLong" class="input-small">
				          		<option value=""></option>
				          		<c:forEach var="vehicleLong" items="${vehicleLongs }">
				          			<option value="${vehicleLong.id }">${vehicleLong.name }</option>
				          		</c:forEach>
				          	</select>
				        </div>
				    </div>
				</div>
				<div class="row">
				  	<div class="control-group span8">
				      	<label class="control-label"><s>*</s>车载体积</label>
			           	<div class="controls" style="position:relative">
			            	<input name="vehicleVolume" type="text" data-rules="{required:true,number:true}" class="input-normal control-text">
			           		<div style="position:absolute;width:30px;height:27px;top:0px;left:130px;text-align:center;">立方</div>
			           	</div>
				  	</div>
					<div class="control-group span8">
				       	<label class="control-label"><s>*</s>车载重量</label>
				       	<div class="controls" style="position:relative">
				          	<input name="vehicleWeight" type="text" data-rules="{required:true,number:true}" class="input-normal control-text" >
				        	<div style="position:absolute;width:30px;height:27px;top:0px;left:130px;text-align:center;">吨</div>
				        </div>
				   	</div>
					<div class="control-group span8">
				       	<label class="control-label"><s>*</s>车架号</label>
				      	<div class="controls">
				          	<input name="vehicleFrameNumber" type="text" data-rules="{required:true,v_cjNum:true}"  
				          		class="input-normal control-text" placeholder="17位数字字母组合 ">
				       	</div>
				    </div>
				</div>
				<div class="row">
				   	<div class="control-group span8">
				      	<label class="control-label"><s>*</s>发动机号</label>
				       	<div class="controls">
				          	<input name="engineNumber" type="text" data-rules="{required:true,v_fdjNum:true}" 
				          		class="input-normal control-text" placeholder="8位数字字母组合"/>
				        </div>
				    </div>
				  	<div class="control-group span8">
				    	<label class="control-label"><s>*</s>运营证号</label>
			          	<div class="controls">
			             	<input name="transportLicenseNo" type="text" data-rules="{required:true}" class="input-normal control-text">
			           	</div>
				  	</div>
					<div class="control-group span8">
				      	<label class="control-label">保险公司</label>
				       	<div class="controls">
				       		<input name="insuranceCompany" type="text" class="input-normal control-text">
				       	</div>
				   	</div>
				</div>
				<div class="row">
				  	<div class="control-group span8">
				      	<label class="control-label">保险单号</label>
				       	<div class="controls">
				          	<input name="insurancePolicyNo" type="text" class="input-normal control-text">
				       	</div>
				  	</div>
					<div class="control-group span8">
				       	<label class="control-label">购买日期</label>
				       	<div class="controls">
				           	<input name="purchaseDate" type="text" class="calendar" />
				      	</div>
				    </div>
				</div>
	       	</div>
		</div>
		<div class="panel">
	        <div class="panel-header">
	           	<h2>车主信息</h2>
	        </div>
	        <div class="panel-body">
				<div class="row">
          			<div class="control-group span8">
			            <label class="control-label">姓名</label>
			            <div class="controls">
			              	<input name="ownerName" type="text" class="input-normal control-text">
			            </div>
          			</div>
				  	<div class="control-group span8">
		            	<label class="control-label">身份证号</label>
		            	<div class="controls">
		              		<input name="ownerIdNumber" type="text" data-rules="{v_IDC:true}" class="input-normal control-text">
		            	</div>
		          	</div>
		          	<div class="control-group span8">
		            	<label class="control-label">手机号</label>
		            	<div class="controls">
		              		<input name="ownerDphoneNumber" type="text" data-rules="{v_phone:true}" class="input-normal control-text">
		            	</div>
		          	</div>
				</div>
				<div class="row">
					<div class="control-group span8">
		            	<label class="control-label">地址</label>
		            	<div class="controls">
		              		<input name="ownerAddress" type="text" class="input-normal">
		            	</div>
		          	</div>
			  		<div class="control-group span8">
			            <label class="control-label">车主单位</label>
			            <div class="controls">
			              	<input name="ownerCompany" type="text" class="input-normal">
			            </div>
			      	</div>
		  			<div class="control-group span8">
            			<label class="control-label">单位地址</label>
            			<div class="controls">
              				<input name="ownerCompanyAddress" type="text" class="input-normal">
            			</div>
          			</div>
        		</div>
			</div>
		</div>
	   	<div class="panel">
	        <div class="panel-header">
	           	<h2>司机信息</h2>
	        </div>
	        <div class="panel-body">  	
				<div class="row">
          			<div class="control-group span8">
	            		<label class="control-label">姓名</label>
	            		<div class="controls">
	              			<input name="driverName" type="text" class="input-normal control-text">
	            		</div>
          			</div>
		  			<div class="control-group span8">
            			<label class="control-label">身份证号</label>
            			<div class="controls">
              				<input name="idCard" type="text" data-rules="{v_IDC:true}" class="input-normal control-text">
            			</div>
          			</div>
         			<div class="control-group span8">
            			<label class="control-label">手机号</label>
            			<div class="controls">
              				<input name="phoneNumber" type="text" data-rules="{v_phone:true}" class="input-normal control-text">
            			</div>
          			</div>
          		</div>
          		<div class="row">
		  			<div class="control-group span8">
            			<label class="control-label">驾驶证号</label>
            			<div class="controls">
              				<input name="driverLicenseNo" type="text" data-rules="{v_jsz:true}" class="input-normal control-text">
            			</div>
          			</div>
          			<div class="control-group span8">
            			<label class="control-label">开户行</label>
			            <div class="controls">
			              	<input name="bankName" type="text" class="input-normal control-text">
			            </div>
          			</div>
		  			<div class="control-group span8">
            			<label class="control-label">银行卡号</label>
            			<div class="controls">
              				<input name="bankNumber" type="text" data-rules="{v_bkc:true}" class="input-normal control-text" placeholder="请输入17数字组合">
            			</div>
          			</div>
				</div>
				<div class="row">
	         		<div class="control-group span8">
	            		<label class="control-label">地址</label>
	            		<div class="controls">
	              			<input name="address" type="text" class="input-normal control-text">
	            		</div>
	          		</div>
				</div>
·			</div>
		</div>
	</form>
	<div class="row form-actions actions-bar">
        <div class="span13 offset3 ">
          	<button id="submit" type="submit" class="button button-primary">提交</button>
          	<button type="reset" class="button">重置</button>
        </div>
    </div>
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/newbui/js/bui.js"></script>
	<script>
		BUI.use(['bui/calendar','bui/overlay','bui/form','bui/tooltip'],function(Calendar,Overlay,Form,Tooltip){
			form = new Form.Form({
				srcNode:'#add_vehicle',
				errorTpl : '<span class="x-icon x-icon-small x-icon-error" data-title="{error}">!</span>'
			}).render();
		   	$('#submit').on('click',function () {
		   		form.valid();
		   		if(form.isValid()){
		   			$.ajax({
						url:"${configProps['project']}/tms/vehicle/ajax/procinsert.shtml",
						data:$("#add_vehicle").serialize(),
						type:'post',
						success:function(data){
							if(data.result==true){
								BUI.Message.Alert('车辆添加成功',function(){
									window.location.href="${configProps['project']}/tms/vehicle/list.shtml";
								},'success');
							}else{
								alert(data.msg);
							}
						},
						error:function(){
							BUI.Message.Alert('系统错误','error');
						}
					})
		   		}
		   	});

	        var datepicker = new Calendar.DatePicker({
	        	trigger:'.calendar',
	        	autoRender : true
	    	});
	        
	        /**********自定义验证规则***********/
	        var mobReg = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/,//验证固定电话
	        	phnReg = /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/,//验证手机号
	        	idCardReg = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/,
	        	cjReg = /^[a-zA-Z0-9]{17}$/,//验证车架号
	        	fdjReg = /^[a-zA-Z0-9]{8}$/,
	        	cpReg = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/,//验证车牌号
	        	gcpReg = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}[\u4e00-\u9fa5]{0,1}$/ ;//验证挂车号
	        	var bkcReg = /^(\d{16}|\d{19})$/;//验银行卡号
		         
	        //验证银行卡号
	    	Form.Rules.add({
	      		name : 'v_bkc',  //规则名称
	            msg : '银行卡号错误',//默认显示的错误信息
	            validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息、goup控件
	            	if(value != null && value != ''){
	              		if(!bkcReg.test(value)){
		            		return formatMsg;
		            	} 
	              	}
	            }
	          }); 
	    	Form.Rules.add({
				name : 'isExistVehicle',
				msg : '车牌号已存在',
				validator : function(value, baseValue, formatMsg){
					if(value != null && value != ''){
						if(!validIsExistVehicle(value)){
							return formatMsg;
						}
					}
				}
			});
	        //验证固定电话
	    	Form.Rules.add({
	      		name : 'v_mobile',  //规则名称
	            msg : '固定电话格式错误',//默认显示的错误信息
	            validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息、goup控件
	              	if(value != null && value != ''){
	              		if(!mobReg.test(value)){
		            		return formatMsg;
		            	} 
	              	}
	            }
	          }); 
	        	
	    	//验证身份证
	    	Form.Rules.add({
	      		name : 'v_IDC',  //规则名称
	            msg : '身份证号不正确',//默认显示的错误信息
	            validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息、goup控件
	              	if(value != null && value != ''){
	              		if(!idCardReg.test(value)){
		            		return formatMsg;
		            	} 
	              	}
	            }
	          }); 
	    	
	    	//验证手机号
	    	Form.Rules.add({
	      		name : 'v_phone',  //规则名称
	            msg : '手机号不正确',//默认显示的错误信息
	            validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息、goup控件
	              	if(value != null && value != ''){
	              		if(!phnReg.test(value)){
		            		return formatMsg;
		            	} 
	              	}
	            }
	          }); 
	    	//验证车牌号是否正确
	    	Form.Rules.add({
	      		name : 'v_cpNum',  //规则名称
	            msg : '车牌号不正确',//默认显示的错误信息
	            validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息、goup控件
	              	if(value != null && value != ''){
	              		if(!cpReg.test(value)){
		            		return formatMsg;
		            	} 
	              	}
	            }
	          }); 
	    	//验证挂车号是否正确
	    	Form.Rules.add({
	      		name : 'v_gcpNum',  //规则名称
	            msg : '挂车号不正确',//默认显示的错误信息
	            validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息、goup控件
	              	if(value != null && value != ''){
	              		if(!gcpReg.test(value)){
		            		return formatMsg;
		            	} 
	              	}
	            }
	          }); 
	    	//验证车架号是否正确
	    	Form.Rules.add({
	      		name : 'v_cjNum',  //规则名称
	            msg : '车架号不正确',//默认显示的错误信息
	            validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息、goup控件
	              	if(value != null && value != ''){
	              		if(!cjReg.test(value)){
		            		return formatMsg;
		            	} 
	              	}
	            }
	          }); 
	    	//验证发动机号是否正确
	    	Form.Rules.add({
	      		name : 'v_fdjNum',  //规则名称
	            msg : '发动机号不正确',//默认显示的错误信息
	            validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息、goup控件
	              	if(value != null && value != ''){
	              		if(!fdjReg.test(value)){
		            		return formatMsg;
		            	} 
	              	}
	            }
	          }); 
	          //不使用模板的，左侧显示
	          var tips = new Tooltip.Tips({
	            tip : {
	              trigger : '.x-icon-error', //出现此样式的元素显示tip
	              alignType : 'top', //默认方向
	              elCls : 'tips tips-warning tips-no-icon tip1',
	              offset : 10 //距离左边的距离
	            }
	          });
	        //验证驾驶证号
		    	Form.Rules.add({
		      		name : 'v_jsz',  //规则名称
		            msg : '驾驶证号不正确',//默认显示的错误信息
		            validator : function(value,baseValue,formatMsg){ //验证函数，验证值、基准值、格式化后的错误信息、goup控件
		              	if(value != null && value != ''){
		              		if(!idCardReg.test(value)){
			            		return formatMsg;
			            	} 
		              	}
		            }
		          });
	          tips.render();
	    });
		
		// 验证 是否已存在 车牌号
		function validIsExistVehicle(vehicleNumber){
			var flag = false;
			$.ajax({
				type : 'post',
				async : false,
				url : '<%=request.getContextPath()%>/tms/vehicle/validisexistvehicle.shtml',
				data : { 'vehicleNumber' : vehicleNumber },
				success : function(result){
					flag = result;
				}
			});
			return flag;
		}
	</script>
</body>
</html>