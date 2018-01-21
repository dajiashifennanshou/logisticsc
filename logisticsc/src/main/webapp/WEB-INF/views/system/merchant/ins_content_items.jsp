<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>保险保单管理</title>
<link href="${configProps['resources']}/newbui/css/bs3/dpl-min.css" rel="stylesheet">
<link href="${configProps['resources']}/newbui/css/bs3/bui-min.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/table.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/dingdanzx.css" rel="stylesheet">
<link href="${configProps['resources']}/platform/css/order-detail.css" rel="stylesheet">
<style type="text/css">
.bui-grid-table .bui-grid-cell-inner{padding: 5px 0px;}
.bui-grid-table .bui-grid-hd-inner{padding: 6px 0px;}
.form-horizontal .controls{height: 40px;}
.form-horizontal .control-label{width: 120px;}
.form-horizontal .control-detail{display: inline-block;line-height: 30px;}
.form-horizontal select{width: 166px;}
.button-box{ width: auto; margin: auto; }
.button-box .button{ padding: 2px 15px; margin: 0px 10px;}
</style>
</head>
<body>
	<div class="panel">
        <div class="panel-header">
           	<h2>保单详情</h2>
        </div>
        <div class="panel-body">
        <form class="form-horizontal">
			<div class="row-fluid">
				<div class="control-group span8">
					<label class="control-label">订单号：</label>
					<div class="controls">
						<span class="control-detail">${platIns.insOrderNum}</span>
					</div>
				</div>
				<div class="control-group span8">
					<label class="control-label">伊恩方订单号：</label>
					<div class="controls">
						<span class="control-detail">${platIns.insSysBaodan}</span>
					</div>
				</div>
				<div class="control-group span8">
					<label class="control-label">保险单号：</label>
					<div class="controls">
						<span class="control-detail">${platIns.insLastBaodan }</span>
					</div>
				</div>
			</div>
		</form>
		<form class="form-horizontal">
			<div class="row-fluid">
				<div class="control-group span8">
					<label class="control-label">保单状态：</label>
					<div class="controls">
						<span class="control-detail">
							<c:if test="${platIns.insStatus == 0}">
								以保存
							</c:if>
							<c:if test="${platIns.insStatus == 1}">
								审核中
							</c:if>
							<c:if test="${platIns.insStatus == 2}">
								以生效
							</c:if>
							<c:if test="${platIns.insStatus == 3}">
								已退保
							</c:if>
							<c:if test="${platIns.insStatus == 4}">
								以作废
							</c:if>
							</span>
					</div>
				</div>
				<div class="control-group span8">
					<label class="control-label">保险公司：</label>
					<div class="controls">
						<span class="control-detail">
							<c:if test="${platIns.insComTag == 'yangguang'}">
								阳光保险
							</c:if>
							<c:if test="${platIns.insComTag == 'taipingyang'}">
								太平洋保险
							</c:if>
							<c:if test="${platIns.insComTag == 'renbao'}">
								中国人保财险
							</c:if>
							<c:if test="${platIns.insComTag == 'pingan'}">
								中国平安
							</c:if>
						</span>
					</div>
				</div>
				<div class="control-group span8">
					<label class="control-label">险种：</label>
					<div class="controls">
						<span class="control-detail">
							<c:if test="${platIns.insType == 'jb'}">
								基本险
							</c:if>
							<c:if test="${platIns.insType == 'xh'}">
								鲜活险
							</c:if>
							<c:if test="${platIns.insType == 'ys'}">
								易碎险
							</c:if>
							<c:if test="${platIns.insType == 'zh'}">
								综合险
							</c:if>
							<c:if test="${platIns.insType == 'lc'}">
								冷藏险
							</c:if>
							<c:if test="${platIns.insType == 'sz'}">
								失踪险
							</c:if>
						</span>
					</div>
				</div>
			</div>
		</form>
		<form class="form-horizontal">
			<div class="row-fluid">
				<div class="control-group span8">
					<label class="control-label">保单费用：</label>
					<div class="controls">
						<span class="control-detail">${platIns.insFee }</span>
					</div>
				</div>
				<div class="control-group span8">
					<label class="control-label">提交时间：</label>
					<div class="controls">
						<span class="control-detail">
							<fmt:formatDate value="${platIns.insCreateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</span>
					</div>
				</div>
				<div class="control-group span8">
					<label class="control-label">被保险人/单位：</label>
					<div class="controls">
						<span class="control-detail">${platIns.insTrueName}</span>
					</div>
				</div>
			</div>
		</form>
		<form class="form-horizontal">
			<div class="row-fluid">
				<div class="control-group span8">
					<label class="control-label">联系电话：</label>
					<div class="controls">
						<span class="control-detail">${platIns.insTel}</span>
					</div>
				</div>
				<div class="control-group span8">
					<label class="control-label">被保险人证件号：</label>
					<div class="controls">
						<span class="control-detail">${platIns.insCardNum }</span>
					</div>
				</div>
				<div class="control-group span8">
					<label class="control-label">联系地址：</label>
					<div class="controls">
						<span class="control-detail">${platIns.insAddress}</span>
					</div>
				</div>
			</div>
		</form>
		<form class="form-horizontal">
			<div class="row-fluid">
				<div class="control-group span8">
					<label class="control-label">车牌号：</label>
					<div class="controls">
						<span class="control-detail">${platIns.insCheNum}</span>
					</div>
				</div>
				<div class="control-group span8">
					<label class="control-label">合同发票号：</label>
					<div class="controls">
						<span class="control-detail">${platIns.insHeTongNum}</span>
					</div>
				</div>
				<div class="control-group span8">
					<label class="control-label">起运时间：</label>
					<div class="controls">
						<span class="control-detail">
							<fmt:formatDate value="${platIns.insStartTime }" pattern="yyyy-MM-dd"/>
						</span>
					</div>
				</div>
			</div>
		</form>
		<form class="form-horizontal">
			<div class="row-fluid">
				<div class="control-group span8">
					<label class="control-label">货物：</label>
					<div class="controls">
						<span class="control-detail">${platIns.insHuoWu}</span>
					</div>
				</div>
				<div class="control-group span8">
					<label class="control-label">包装：</label>
					<div class="controls">
						<span class="control-detail">${platIns.insBaoZhuang}</span>
					</div>
				</div>
				<div class="control-group span8">
					<label class="control-label">金额：</label>
					<div class="controls">
						<span class="control-detail">
							${platIns.insJine }
						</span>
					</div>
				</div>
			</div>
		</form>
		<form class="form-horizontal">
			<div class="row-fluid">
				<div class="control-group span12">
					<label class="control-label">起始地址：</label>
					<div class="controls">
						<span class="control-detail">
							${platIns.insStartAdd }
						</span>
					</div>
				</div>
				<div class="control-group span12">
					<label class="control-label">到达地址：</label>
					<div class="controls">
						<span class="control-detail">
							${platIns.insEndAdd }
						</span>
					</div>
				</div>
			</div>
		</form>
	</div>
	
</body>
</html>