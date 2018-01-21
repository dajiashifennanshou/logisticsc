<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>加盟申请</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- jquery -->
<%-- <script src="${configProps['resources']}/platform/jquery/jquery-1.11.1.min.js"></script> --%>
<script src="/logisticsc/Clound/js/jquery-2.2.4.min.js"></script>
<script src="${configProps['resources']}/platform/jquery/jquery-form.js"></script>
<!-- 全局样式 -->
<link href="${configProps['resources']}/platform/css/external-introduction.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<!-- bootstrap-->
<script src="${configProps['resources']}/platform/bootstrap-js/bootstrap.min.js"></script>
<link href="${configProps['resources']}/platform/bootstrap-css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap兼容IE -->
<script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
<script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>
<script src="/logisticsc/Clound/js/JoinElseJs.js"></script>
<script src="/logisticsc/Clound/js/yc_public.js"></script>
<script src="/logisticsc/Clound/js/ValidateForm.js"></script>
<link href="${configProps['resources']}/platform/css/register1.css" rel="stylesheet">
<script type="text/javascript">
	var _url="";
	//经销商加盟
	function dealerJoin(form){
		var _form=$('#'+form);
		var valited=_form.FengValid();
		if(valited){
			//经销商申请填写的数据
			var data=yc_public.getData('dealerForm');
			yc_public.confirm({msg:'提交后不可更改,确定提交吗?',callback:function(){
				yc_public.ajax({url:'../addTmsJoin.yc',data:data,load:true,success:function(data){
					if(data.code==0){
						yc_public.dialog({msg:data.msg||'成功',callback:function(){
							window.location="toMyCloud.shtml";
						}});
					}else{
						yc_public.tips({msg:data.msg});
					}
				}})
			}})
			
		}else{
			alert(1);
		}
		
	}
	//专线加盟
	function lineJoin(form){
		var _form=$('#'+form);
		var valited=_form.FengValid();
		if(valited){
			//经销商申请填写的数据
			var data=yc_public.getData('dealerForm');
			yc_public.confirm({msg:'提交后不可更改,确定提交吗?',callback:function(){
				yc_public.ajax({url:'../addTmsJoin.yc',data:data,load:true,success:function(data){
					if(data.code==0){
						yc_public.dialog({msg:data.msg||'成功',callback:function(){
							window.location="toMyCloud.shtml";
						}});
					}else{
						yc_public.tips({msg:data.msg});
					}
				}})
			}})
		}
	}
</script>
</head>
<body>
<div id="container">
<jsp:include page="../top.jsp"></jsp:include>
	<div class="container-self">
		<div class="frame_left">
			<jsp:include page="peronal_center_left.jsp"></jsp:include>
		</div>
		<div class="demo-content platformStyle">
			<ul id="myTab" class="nav nav-tabs">
			    <li class="active"><a href="#basicInform" data-toggle="tab">经销商加盟</a></li>
			   <li ><a href="#companyInform" data-toggle="tab">专线加盟</a></li>
			</ul>
			<div id="myTabContent" class="tab-content">
				
				<div class="tab-pane fade in active" id="basicInform">
				<c:if test="${msg_ ==0}">
					<label style="color:green;">您已填写申请,请等待审核通过..</label>
				</c:if>
				<c:if test="${msg_ !=0}">
	    			 <form id="dealerForm" class="form-horizontal form-bordered"  >
	    			 <input type="hidden" group="val" name="joinType" value="1"/>
	    			 <fieldset>
	    			 	<legend style="font-size:12px;color:green;">你还未加盟,请填写申请信息..</legend>
			    			 	<div class="modal-body add-role-body">
			    			 		 <div class="form-group" style="margin-top: 14px;">
				                        <label class="control-label col-sm-4" for="joinName" >商铺名称：</label>
				                        <div class="col-sm-2">
				                            <input type="text" group="val" valited="required" style="position:relative;display:inline-block;width:250px"  name="joinName" class="form-control required" />
				                        </div>
				                    </div>
				                    <div class="form-group" style="margin-top: 28px;">
				                        <label class="control-label col-sm-4" for="branchNo">加盟网点：</label>
				                        <div class="col-sm-2">
				                            <select group="val" name="branchNo" valited="required" style="width:250px;border:1px solid #ccc;">
				                            	<c:forEach items="${branchs}" var="bNo">
				                            		<option value="${bNo.branchNo }">${bNo.branchName }</option>
				                            	</c:forEach>
				                            </select>
				                        </div>
				                    </div>
				                    <div class="form-group" style="margin-top: 28px;">
				                        <label class="control-label col-sm-4" for="days">使用时长(月)：</label>
				                        <div class="col-sm-2">
				                            <input type="text" group="val"  valited="required,number" length="5" style="position:relative;display:inline-block;width:250px"  name="days" class="form-control required" />&nbsp;&nbsp;
				                        </div>
				                    </div>
			    			 	</div>
			    			 	<div class="modal-footer">
				                    <button class="btn btn-success btn-save" type="button" onclick="dealerJoin('dealerForm');">提交申请</button>
		               			</div>
               			 </fieldset>
	    			 </form>
			    </c:if>	
			    </div>
			    <div class="tab-pane fade" id="companyInform">
			    	<c:if test="${ msg_ ==0}">
						<label style="color:green;">您已填写申请,请等待审核通过..</label>
					</c:if>
					<c:if test="${ msg_ !=0}">
			    	 <form id="lineForm" class="form-horizontal form-bordered">
			    	  <input type="hidden" group="val" name="joinType" value="0"/>
	    			 <fieldset>
	    			 	<legend style="font-size:12px;color:green;">你还未加盟,请填写申请信息..</legend>
			    			 	<div class="modal-body add-role-body">
			    			 		 <div class="form-group" style="margin-top: 14px;">
				                        <label class="control-label col-sm-4" for="joinName">专线名称：</label>
				                        <div class="col-sm-2">
				                            <input type="text" group="val" style="position:relative;display:inline-block;width:250px"  name="joinName" class="form-control required" />
				                        </div>
				                    </div>
				                    <div class="form-group" style="margin-top: 28px;">
				                        <label class="control-label col-sm-4" for="branchNo">加盟网点：</label>
				                        <div class="col-sm-2">
				                            <select group="val" name="branchNo" style="width:250px;border:1px solid #ccc;">
				                            	<c:forEach items="${branchs}" var="bNo">
				                            		<option value="${bNo.branchNo }">${bNo.branchName }</option>
				                            	</c:forEach>
				                            </select>
				                        </div>
				                    </div>
				                    <div class="form-group" style="margin-top: 28px;">
				                        <label class="control-label col-sm-4" for="days" >使用时长(月)：</label>
				                        <div class="col-sm-2">
				                            <input type="text" group="val" style="position:relative;display:inline-block;width:250px"  name="days" class="form-control required" />&nbsp;&nbsp;
				                        </div>
				                    </div>
			    			 	</div>
			    			 	<div class="modal-footer">
				                    <button class="btn btn-success btn-save" type="button" onclick="lineJoin('lineForm');">提交申请</button>
		               			</div>
               			 </fieldset>
	    			 </form>
	    			 </c:if>
			    </div>
			 </div>  
	    </div>
   </div>
 </div>  

</body>
</html>