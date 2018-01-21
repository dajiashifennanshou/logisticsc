<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
<link rel="shortcut icon" type="image/x-icon" href="/logisticsc/resources/platform/img/xslwl56.ico" />
<%-- <script type="text/javascript" src="${configProps['resources']}/boot.js"></script> --%>
<link href="${configProps['resources']}/js/login/css/bs3/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="${configProps['resources']}/js/login/css/bs3/bui-min.css" rel="stylesheet" type="text/css" />
<!-- bootstrap3 上用的Glyphicons字体图标-->
<link href="${configProps['resources']}/js/login/css/glyphicon.css" rel="stylesheet" type="text/css" />
<!-- alibaba图标库 iconfont.cn
<link href="${configProps['resources']}/js/login/css/iconfont.css" rel="stylesheet" type="text/css" />-->

<style>
        html{
            height: 100%;
        }
        body{
           /*  background-color: #eee; */
            background-color: #49A3DF; 
    background: url('${configProps['resources']}/js/login/img/login/top-bg.jpg') repeat 0 0 ; 
    background-size:cover;
            min-height: 100%;
            _height:100%;
            position: relative;
            overflow-x: hidden;
        }
        .top-container .header{
           /*  background: url('${configProps['resources']}/js/login/img/login/login-bg.png') repeat 0 0 ; */
            width: 100%;
            height: 100px;
        }
        .top-container .header > header{
            padding:40px;width: 280px;margin-left: 10%;
        }
        .top-container .header .l-logo{
            font-size: 3.5em;
            color:#fff;
        }
        .top-container .content{
           /*  background: url('${configProps['resources']}/js/login/img/login/login-bg.png') repeat 0 0 ; */
            background-origin: content-box;
            height: 200px;
        }
        .bottom-container{
            min-height: 100%;
            _height:100%;
        }
        .bottom-container > .bottom-content{
            height: 245px;
        }
        /*copyright*/
        .bottom-container > .foot{
            position:absolute;
            bottom: 0;
            text-align: center;
            width:1000px;
            color:#fff;
            word-spacing: 3px;
            left: 50%;
            margin-left: -500px;
        }
        #theForm{
            border: 1px solid #ddd;
            background-color: #fff;
            margin-top: 0;
            border-radius: 5px;
            width: 400px;
            padding: 0;
            box-shadow: 0 6px 12px rgba(0,0,0,.175);
            top:100px;
            position:relative;
            margin-right: auto;
            margin-left: auto;
        }
        #theForm #owl{
            width: 211px;
            height: 108px;
          /*   background: url('${configProps['resources']}/js/login/img/login/owl.png') -51px 0 no-repeat; */
            position: absolute;
            top: -100px;
            left: 50%;
            margin-left: -111px;
        }
        #owl > .arms{
            top:58px;
            position: absolute;
            width: 100%;height: 40px;
            overflow: hidden;
        }
        #owl > .arms > .arm{
            width: 40px;
            height: 65px;
            position: absolute;
            left: 20px;
            top: 40px;
            background: url('${configProps['resources']}/js/login/img/login/owl.png') 0 0 no-repeat;
        }
        #owl > .arms > .arm-r{
            background: url('${configProps['resources']}/js/login/img/login/owl.png') -278px 0 no-repeat;
            left: 160px;
        }

        #theForm .fields{
            color: #444;
            overflow: hidden;
            padding: 30px 40px;
        }
        #theForm .control-group{
            margin-bottom: 13px;
        }
        #theForm .control-group:last-child{
            margin-bottom: 0;
        }
        #theForm .controls{
            position: relative;
        }
        #theForm .controls .control-label{
            position: absolute;
            top: 12px;
            left: 12px;
            font-size: 16px;
            color: #b7b7b7;
        }
        #theForm .controls .form-control-input{
            padding: 9px 6px 9px 40px;
            height: auto;
            color: #444;
            width:320px;*width:275px;
            font-size: 14px;
            line-height: 17px;
            vertical-align: middle;
            background-color: #fff;
            border:1px solid #ccc;
            border-radius: 4px;
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }

        #theForm .controls .input-captcha{ width: 220px;*width:173px;}
        #theForm .buttons{
            border-top: 1px solid #e4e4e4;
            background-color: #f7f7f7;
            padding: 15px 40px;
            text-align: right;
        }
        #theForm .buttons .remember{
            display: inline;float: left;color: #999999;line-height: 30px;margin-left: 5px;
        }
        #theForm .buttons .remember > .fn-vm{
            height:15px; vertical-align:text-top; margin-top:0;
        }
        #captchaImg{vertical-align:middle;cursor:pointer;margin-left: 5px;}
        .tips-notice{border-color:#EDD8AD}
        .fn-pd5{
            padding:5px;
        }
        .fn-placeholder{
            color:#aaa !important;
        }

        /*动画*/
        .a-fadeinT,.a-fadeinR{
            -webkit-animation: 1s ease-out backwards;
            -moz-animation: 1s ease-out backwards;
            -ms-animation: 1s ease-out backwards;
            animation: 1s ease-out backwards;
        }
        .a-fadeinT{
            -webkit-animation-name: fadeinT;
            -moz-animation-name: fadeinT;
            -ms-animation-name: fadeinT;
            animation-name: fadeinT;
        }
        .a-fadeinR {
            -webkit-animation-name: fadeinR;
            -moz-animation-name: fadeinR;
            -ms-animation-name: fadeinR;
            animation-name: fadeinR;
        }
        
        /* 淡入-从上 */
        @-webkit-keyframes fadeinT{
            0%{opacity:0;-webkit-transform:translateY(-100px);}
            100%{opacity:1;-webkit-transform:translateY(0);}
        }
        @-moz-keyframes fadeinT{
            0%{opacity:0;-moz-transform:translateY(-100px);}
            100%{opacity:1;-moz-transform:translateY(0);}
        }
        @-ms-keyframes fadeinT{
            0%{opacity:0;-ms-transform:translateY(-100px);}
            100%{opacity:1;-ms-transform:translateY(0);}
        }
        @keyframes fadeinT{
            0%{opacity:0;transform:translateY(-100px);}
            100%{opacity:1;transform:translateY(0);}
        }
        /* 淡入-从右 */
        @-webkit-keyframes fadeinR{
            0%{opacity:0;-webkit-transform:translateX(100px);}
            100%{opacity:1;-webkit-transform:translateX(0);}
        }
        @-moz-keyframes fadeinR{
            0%{opacity:0;-moz-transform:translateX(100px);}
            100%{opacity:1;-moz-transform:translateX(0);}
        }
        @-ms-keyframes fadeinR{
            0%{opacity:0;-ms-transform:translateX(100px);}
            100%{opacity:1;-ms-transform:translateX(0);}
        }
        @keyframes fadeinR{
            0%{opacity:0;transform:translateX(100px);}
            100%{opacity:1;transform:translateX(0);}
        }
    </style>
    
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="top-container">
        <div class="header">
            <header>
                <label class="l-logo">
                    <span class="a-fadeinT">Log</span>
                    <span class="a-fadeinR">in</span>
                </label>
            </header>
        </div>

        <div class="content">
            <div id="theForm" >
                <div id="owl" class="owl">
                  <!--   <div class="arms">
                        <div class="arm"></div>
                        <div class="arm arm-r"></div>
                    </div> -->
                </div>
                <div class="fields">
                    <!-- 信息提示区域 -->
                    <div class="control-group">
                        <div class="controls" id="noticeTip">
                            <div class="tips tips-small tips-notice fn-pd5" >
                                <span class="x-icon x-icon-small x-icon-warning">
                                    <i class="icon icon-white icon-volume-up"></i>
                                </span>
                                <div class="tips-content">请输入正确的用户名和密码。</div>
                            </div>
                        </div>
                    </div>
                    <!-- /信息提示区域 -->
                    <div class="control-group">
                        <div class="controls">
                            <label for="username" class="control-label  glyphicon glyphicon-user"></label>
                            <input type="text" value="" name="username" id="username" placeholder="用户名" tabindex="1" autofocus="true"
                                   class="form-control-input" autocomplete="off" data-rules="{required:true}"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <label for="password" class="control-label  glyphicon glyphicon-lock"></label>
                            <input type="text" value="" onfocus="this.type='password'" name="password" id="password" placeholder="密码" tabindex="2" autocomplete="off"
                                   class="form-control-input" />
                        </div>
                    </div>
                    <!-- <div class="control-group">
                        <div class="controls">
                            <label for="captcha" class="control-label glyphicon glyphicon-check"></label>
                            <input type="text" name="captcha" id="captcha" placeholder="验证码" tabindex="3" autocomplete="off"
                                   class="form-control-input  input-captcha"/>
                            <img id="captchaImg" src="assets/img/login/captcha.jpg" width="90" height="37" >
                        </div>
                    </div> -->
                </div>
                <div class="buttons">
                    <div class="remember">
                        <input type="checkbox" value="1" id="remember" class="fn-vm" tabindex="4"/>
                        <label for="remember">&nbsp;记住我</label>
                        
                    </div>
                    <button class="button button-primary " id="loginBtn" tabindex="5">登录</button>
                </div>
            </div>
        </div>
    </div>
    <div class="bottom-container">
        <div class="bottom-content"></div>
        <div class="foot">
            <p>&copy; 2013-2017 中工储运 版权所有 粤ICP备15048227号-1</p>
        </div>
    </div>
    
    
    <!-- jQuery -->
	<script type="text/javascript" src="${configProps['resources']}/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/js/login/js/sea.js"></script>
	<script type="text/javascript" src="${configProps['resources']}/js/login/js/config.js"></script>
	<!--[if lt IE 10]>
	<script src="./assets/js/jquery.placeholder.min.js"></script>
	<script >$('input, textarea').placeholder({customClass:'fn-placeholder'});</script>
	<![endif]-->
	
	<script type="text/javascript">

    $(function(){
    	
        // 闭眼动画
        $('#password').focus(function() {
            $('#owl').find('.arm').stop().first().animate({top:'5px',left:'65px'},300).next().animate({top:'5px',left:'120px'},300);
        }).blur(function() {
            $('#owl').find('.arm').stop().first().animate({top:'40px',left:'20px'},300).next().animate({top:'40px',left:'160px'},300);
        });

        // use cookie, tooltip, overlay
        seajs.use(['bui/cookie', 'bui/tooltip' ,'bui/overlay'], function( cookie, Tooltip ){

            var username = $("#username").focus(),
                remember = $('#remember'),
                c_username = cookie.get('username');

            if( !!c_username ){
                // 如果已经记住账号
                username.val( c_username );
                remember.attr('checked',true );
            }

            // 表单回车事件
            $('#theForm').keydown(function(e){
                if( e.keyCode == 13 ){
                    $("#loginBtn").trigger('click');
                }
            });
            // 登录事件
            $("#loginBtn").click(function(){
            	//alert("ok");
                var $this = $(this);
                var Admininfo = {
            			"username" : $("#username").val(),
            			"password" : $("#password").val()
            	};
                
                var username=$("#username").val();
                var password=$("#password").val();
                $.ajax({
                    url : "${configProps['project']}/tms/sys/login.shtml",
                    type : 'POST',
                    cache : false,
                    data : JSON.stringify(Admininfo),
                    dataType : 'json',
                    contentType : "application/json;charset=UTF-8",
                    success:function(data){
                        if (data.result) {
                        	
                            // 如果选择了记住账号，账号记住于cookies
                            if($('#remember').is(':checked') ){
                                cookie.set('username', $('#username').val(), 1);// 1天
                            } else {
                                cookie.remove('username');
                            }
                            // 登录成功
                            window.location = "${configProps['project']}/tms/sys/main.shtml";
                            //BUI.Message.Alert(data.msg,'success');
                        } else {
                            // 登录失败提示 - 改变提示样式
                            $("#noticeTip").slideUp(200, function(){
                                var $this = $(this), tip = $this.find('.tips-notice');
                                if( tip[0] ){
                                    tip.removeClass('tips-notice').addClass('tips-warning')
                                       .find('.x-icon-warning').addClass('x-icon-error')
                                       .find('.icon-volume-up').addClass('icon-bell');
                                }
                                $this.find('.tips-content').text(data.msg);
                                $this.slideDown(200);
                            });
                        }
                    }
                });
            });
        });

    });
</script>
	
</body>
</html>