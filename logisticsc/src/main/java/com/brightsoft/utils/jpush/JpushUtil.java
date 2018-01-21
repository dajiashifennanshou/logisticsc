package com.brightsoft.utils.jpush;

import com.brightsoft.entity.JpushUserInfo;
import com.brightsoft.utils.yc.LogUtil;

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
import cn.jpush.api.schedule.ScheduleResult;
/**
 * 消息推送
 * @Author:luojing
 * @2016年7月21日 上午10:54:48
 */
public class JpushUtil {

	private static String SECRET="8ede55de282431a6224d281f";
	private static String APPKEY="ef5956a311df6c8b35b95389";
	
	public static void main(String[] args) {
		String str = "101d855909464276dad";
		sendMessage(str,"测试推送");
	}
	

	/**
	 * 推送所有人（目前只有安卓和IOS平台）
	 * @param content 推送内容
	 * @Author:luojing
	 * @2016年7月15日 上午11:49:57
	 * @return i=200 成功  否则失败
	 */
	public static Integer sendMessage(JpushUserInfo jpush){
		//连接客户端
		JPushClient jpushClient = new JPushClient(SECRET, APPKEY);
		//推送所有
		//PushPayload payload = PushPayload.alertAll("content");
		PushPayload payload = PushPayload.newBuilder()
						        .setPlatform(Platform.android_ios())
						        .setAudience(Audience.all())//推送目标
						        .setNotification(Notification.alert(jpush.getContent()))
						        .setOptions(Options.newBuilder()
					                 .setApnsProduction(false)//false 为测试环境
					                 .setBigPushDuration(jpush.getTimeFinish())
					                 .build())
						        .build();
		Integer i = 0;
		try{
			//getResponseCode==200表示成功，其他则失败
			if(jpush.getSendType().equals("0")){
				//立即发送
				PushResult result = jpushClient.sendPush(payload);
				i =result.getResponseCode();
			}else{//定时发送
				ScheduleResult result = jpushClient.createSingleSchedule("xsl", jpush.getSendTime(), payload);
				i =result.getResponseCode();
			}
		}catch(APIConnectionException e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getMessage());
		}catch(APIRequestException e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getMessage());
		}
		return i;
	}
	
	
	/**
	 * 根据账号推送（多个）,全平台
	 * @param content 内容
	 * @param str 注册ID 字符数组
	 * @Author:luojing
	 * @2016年7月21日 上午9:30:02
	 * @return 200  成功    否则失败
	 */
	public static Integer sendMessage(String[] str,String content){
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
		PushResult result = null;
		try{
			result = jpushClient.sendPush(payload);
		}catch(APIConnectionException e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getMessage());
		}catch(APIRequestException e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getMessage());
		}
		return result.getResponseCode();
	}
	
	/**
	 * 指定账号推送,全平台
	 * @param content 内容
	 * @param str 注册ID 字符数组
	 * @Author:luojing
	 * @2016年7月21日 上午9:30:02
	 * @return i=200 成功  否则失败
	 */
	public static Integer sendMessage(String regId,String content){
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
		PushResult result = null;
		try{
			result = jpushClient.sendPush(payload);
		}catch(APIConnectionException e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getMessage());
		}catch(APIRequestException e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getMessage());
		}
		return result.getResponseCode();
	}
	
	/**
	 * 单个推送
	 * IOS平台推送，根据registrationId（注册ID）进行推送
	 * @param registrationId 注册ID
	 * @param content 推送内容
	 * @Author:luojing
	 * @2016年7月15日 上午11:20:34
	 * @return i=200 成功  否则失败
	 */
	public static Integer sendSingleIOSMessage(String registrationId,String content){
		//连接客户端
		JPushClient jpushClient = new JPushClient(SECRET, APPKEY);
		PushPayload payload = PushPayload.newBuilder()
								.setPlatform(Platform.ios())//货运交易系统
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
		PushResult result = null;
		try{
			result = jpushClient.sendPush(payload);
		}catch(APIConnectionException e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getMessage());
		}catch(APIRequestException e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getMessage());
		}
		return result.getResponseCode();
	}
	
	/**
	 * 单个推送
	 * Android平台推送，根据registrationId（注册ID）进行推送
	 * @param registrationId 注册ID
	 * @param content 推送内容
	 * @Author:luojing
	 * @2016年7月15日 上午11:20:34
	 * @return i=200 成功  否则失败
	 */
	public static Integer sendSingleAndroidMessage(String registrationId,String content){
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
		PushResult result = null;
		try{
			result = jpushClient.sendPush(payload);
		}catch(APIConnectionException e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getMessage());
		}catch(APIRequestException e){
			e.printStackTrace();
			LogUtil.LogError.error(e.getMessage());
		}
		return result.getResponseCode();
	}
	
}
