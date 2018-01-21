package com.yc.Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.yc.Canstant.Constant;
import com.yc.Entity.LcPlatformUser;
import com.yc.Tool.DES;
import com.yc.Tool.FengUtil;

public class DistributorFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain fc)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String url = request.getRequestURI();//获取请求路径，过滤一些请求
			url = url.substring(url.lastIndexOf("/")+1, url.lastIndexOf("."));
			//如果是上传文件接口，则直接放行，无需sign此为-临时解决办法
			if(url.equals("uploadBankImg")
					|| url.equals("PayCallBackSpecialTransportation")
					|| url.equals("getVersion")//版本 
					|| url.equals("getDeliveryOrderPayCallBack") 
					|| url.equals("id_card_front") //身份证正面
					|| url.equals("id_ccard_back") //身份证反面
					|| url.equals("bank_card_front")//银行卡正面 
					|| url.equals("bank_card_back")//银行卡反面
					){
				fc.doFilter(request, response);//放行
			}else{
				String sign = request.getParameter("sign");
				//验证签名
				if(DES.decrypt(sign).equals(Constant.SIGN)){
					//放行的接口
					if("dealerLogin".equals(url) //登录 
							|| "getBulletinInfo".equals(url)//获取公告
							|| "validCode".equals(url)//获取短信验证码
							|| "getCode".equals(url)//获取短信验证码
							|| "registerDealer".equals(url)//经销商注册
							|| "forgetPassword".equals(url)//忘记密码
					){
						fc.doFilter(request, response);//放行
					}else{
						LcPlatformUser user = (LcPlatformUser) request.getSession().getAttribute(request.getParameter("token"));
						//验证token是否失效
						if(user!=null){
							fc.doFilter(request, response);//放行
						}else{
							FengUtil.OUTAPPSUCCESS(Constant.APP_Permission, response);
						}
					}
				}else{
					FengUtil.RuntimeExceptionFeng("没有访问权限");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			FengUtil.OUTAPPERROR(Constant.APP_ERROR, e.getMessage(), response);
		}
	}

}
