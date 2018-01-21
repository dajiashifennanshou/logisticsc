package com.yc.Tool.jpush;

import com.yc.Tool.LogUtil;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JpushUtil {

	private static String SECRET="8ede55de282431a6224d281f";
	private static String APPKEY="ef5956a311df6c8b35b95389";

	/**
	 * 推送所有人（目前只有安卓和IOS平台）
	 * @param content 推送内容
	 * @Author:luojing
	 * @2016年7月15日 上午11:49:57
	 */
	public static void sendMessage(String content){
		//连接客户端
		JPushClient jpushClient = new JPushClient(SECRET, APPKEY);
		//推送所有
		//PushPayload payload = PushPayload.alertAll("content");
		PushPayload payload = PushPayload.newBuilder()
						        .setPlatform(Platform.android_ios())
						        .setAudience(Audience.all())//推送目标
						        .setNotification(Notification.alert(content))
						        .setOptions(Options.newBuilder()
					                 .setApnsProduction(false)//false 为测试环境
					                 .build())
						        .build();
		try{
			PushResult result = jpushClient.sendPush(payload);
			System.out.println(result);
		}catch(APIConnectionException e){
			LogUtil.LogError.error(e.getMessage());
		}catch(APIRequestException e){
			LogUtil.LogError.error(e.getMessage());
		}
	}
	
	
	/**
	 * 根据账号推送（多个）,全平台
	 * @param content 内容
	 * @param str 注册ID 字符数组
	 * @Author:luojing
	 * @2016年7月21日 上午9:30:02
	 */
	public static void sendMessage(String[] str,String content){
		//连接客户端
		JPushClient jpushClient = new JPushClient(SECRET, APPKEY);
		//根据推送目标
		PushPayload payload = PushPayload.newBuilder()
						        .setPlatform(Platform.all())
						        .setAudience(Audience.registrationId(str))//推送目标
						        .setNotification(Notification.alert(content))
						        .setOptions(Options.newBuilder()
					                 .setApnsProduction(false)//false 为测试环境
					                 .build())
						        .build();
		try{
			PushResult result = jpushClient.sendPush(payload);
			System.out.println(result);
		}catch(APIConnectionException e){
			LogUtil.LogError.error(e.getMessage());
		}catch(APIRequestException e){
			LogUtil.LogError.error(e.getMessage());
		}
	}
	
	/**
	 * 指定账号推送,全平台
	 * @param content 内容
	 * @param str 注册ID 字符数组
	 * @Author:luojing
	 * @2016年7月21日 上午9:30:02
	 */
	public static void sendMessage(String regId,String content){
		//连接客户端
		JPushClient jpushClient = new JPushClient(SECRET, APPKEY);
		//根据推送目标
		PushPayload payload = PushPayload.newBuilder()
						        .setPlatform(Platform.all())
						        .setAudience(Audience.registrationId(regId))//推送目标
						        .setNotification(Notification.alert(content))
						        .setOptions(Options.newBuilder()
					                 .setApnsProduction(false)//false 为测试环境
					                 .build())
						        .build();
		try{
			PushResult result = jpushClient.sendPush(payload);
			System.out.println(result);
		}catch(APIConnectionException e){
			LogUtil.LogError.error(e.getMessage());
		}catch(APIRequestException e){
			LogUtil.LogError.error(e.getMessage());
		}
	}
	
	/**
	 * 单个推送
	 * IOS平台推送，根据registrationId（注册ID）进行推送
	 * @param registrationId 注册ID
	 * @param content 推送内容
	 * @Author:luojing
	 * @2016年7月15日 上午11:20:34
	 */
	public static void sendSingleIOSMessage(String registrationId,String content){
		//连接客户端
		JPushClient jpushClient = new JPushClient(SECRET, APPKEY);
		PushPayload payload = PushPayload.newBuilder()
								.setPlatform(Platform.ios())//平台
						        .setAudience(Audience.registrationId(registrationId))//推送ID
						        .setNotification(Notification.newBuilder()
						                .addPlatformNotification(IosNotification.newBuilder()
							                .setAlert(content)//推送消息类型
							                .setBadge(5)//应用角标
							              //.setSound("happy.caf")//提示声音
							              //.addExtra("from", "JPush")
							                .build())
						                .build())
						         //.setMessage(Message.content("IOS"))通知内容体。Notification、与 message 一起二者必须有其一，可以二者并存
						         .setOptions(Options.newBuilder()
						                 .setApnsProduction(false)//false 为测试环境
						                 .build())
						         .build();
		try{
			PushResult result = jpushClient.sendPush(payload);
			System.out.println(result);
		}catch(APIConnectionException e){
			LogUtil.LogError.error(e.getMessage());
		}catch(APIRequestException e){
			LogUtil.LogError.error(e.getMessage());
		}
	}
	
	/**
	 * 单个推送
	 * Android平台推送，根据registrationId（注册ID）进行推送
	 * @param registrationId 注册ID
	 * @param content 推送内容
	 * @Author:luojing
	 * @2016年7月15日 上午11:20:34
	 */
	public static void sendSingleAndroidMessage(String registrationId,String content){
		//连接客户端
		JPushClient jpushClient = new JPushClient(SECRET, APPKEY);
		PushPayload payload = PushPayload.newBuilder()
				                .setPlatform(Platform.android())
				                .setAudience(Audience.registrationId(registrationId))
				                .setNotification(Notification.android(content, "title", null))
				                .setOptions(Options.newBuilder()
						                 .setApnsProduction(false)//false 为测试环境
						                 .build())
				                .build();
		try{
			PushResult result = jpushClient.sendPush(payload);
			System.out.println(result);
		}catch(APIConnectionException e){
			LogUtil.LogError.error(e.getMessage());
		}catch(APIRequestException e){
			LogUtil.LogError.error(e.getMessage());
		}
	}
	
	/**
	 * 单个推送
	 * WinPhone平台推送，根据registrationId（注册ID）进行推送
	 * @param registrationId 注册ID
	 * @param content 推送内容
	 * @Author:luojing
	 * @2016年7月15日 上午11:20:34
	 */
	public static void sendSingleWinPhoneMessage(String registrationId,String content){
		//连接客户端
		JPushClient jpushClient = new JPushClient(SECRET, APPKEY);
		PushPayload payload = PushPayload.newBuilder()
				                .setPlatform(Platform.winphone())
				                .setAudience(Audience.registrationId(registrationId))
				                .setNotification(Notification.winphone(content, null))
				                .setOptions(Options.newBuilder()
						                 .setApnsProduction(false)//false 为测试环境
						                 .build())
				                .build();
		try{
			PushResult result = jpushClient.sendPush(payload);
			System.out.println(result);
		}catch(APIConnectionException e){
			LogUtil.LogError.error(e.getMessage());
		}catch(APIRequestException e){
			LogUtil.LogError.error(e.getMessage());
		}
	}
}
