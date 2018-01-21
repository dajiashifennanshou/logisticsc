package com.brightsoft.utils.yeepay;

import java.io.BufferedInputStream;
import java.io.BufferedReader;  
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
  














import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.methods.HttpGet;   
import org.apache.http.impl.client.DefaultHttpClient;

import com.brightsoft.common.enums.POSOrderStatusEnum;
import com.brightsoft.common.enums.POSResponseEnum;
import com.brightsoft.controller.payment.yeepay.TestMeetingInterface;
import com.brightsoft.utils.Const;
import com.brightsoft.utils.yeepay.JaxbUtil;
import com.brightsoft.utils.yeepay.POSCODMS;
import com.brightsoft.utils.yeepay.POSExtendAtt;
import com.brightsoft.utils.yeepay.POSHealer;
import com.brightsoft.utils.yeepay.POSLoginRequestBody;
import com.brightsoft.utils.yeepay.POSLoginResponseBody;
import com.brightsoft.utils.yeepay.POSOrderRequestBody;
import com.brightsoft.utils.yeepay.POSRequestHealer;
import com.brightsoft.utils.yeepay.JaxbUtil.CollectionWrapper;
import com.brightsoft.utils.yeepay.POSResponseHealer;

public class PostTest {
	
	private final String url = "http://localhost:8080/logisticsc/yeePayPost/getData.pay";

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		// 创建HttpClient实例     
        HttpClient httpclient = new DefaultHttpClient();  
        // 创建Get方法实例     
        HttpGet httpgets = new HttpGet("http://localhost:8080/logisticsc/yeePayPost/getOrderPost.pay");    
        HttpResponse response = httpclient.execute(httpgets);    
        HttpEntity entity = response.getEntity();    
        if (entity != null) {    
            InputStream instreams = entity.getContent();    
            String str = convertStreamToString(instreams);  
            System.out.println("Do something");   
            System.out.println(str);  
            // Do not need the rest    
            httpgets.abort(); 
        }
	}
	
	 public static String convertStreamToString(InputStream is) {      
	        BufferedReader reader = new BufferedReader(new InputStreamReader(is));      
	        StringBuilder sb = new StringBuilder();      
	       
	        String line = null;      
	        try {      
	            while ((line = reader.readLine()) != null) {  
	                sb.append(line + "\n");      
	            }      
	        } catch (IOException e) {      
	            e.printStackTrace();      
	        } finally {      
	            try {      
	                is.close();      
	            } catch (IOException e) {      
	               e.printStackTrace();      
	            }      
	        }      
	        return sb.toString();      
	    } 
	 
	 	private POSRequestHealer createRequestHeader(){
	 		POSRequestHealer posRequestHealer = new POSRequestHealer();
	 		posRequestHealer.setVersion("1.0");
	 		posRequestHealer.setServiceCode("COD407");
	 		posRequestHealer.setTransactionID("CODTESTCOD402201605277466877490");
	 		posRequestHealer.setSrcSysID("yeepay");
	 		posRequestHealer.setDstSysID("CODTEST");
	 		posRequestHealer.setReqTime("20160527100440");
	 		//posRequestHealer.setHmac("asdasdsdfsd");
	 		//POSExtendAtt attBody = new POSExtendAtt();
	 		//posRequestHealer.setPosExtendAtt(attBody);
	 		return posRequestHealer;
	 	}
	 	
	 	public POSResponseHealer createResponseHeader(){
	 		POSResponseHealer posResponseHealer = new POSResponseHealer();
	 		//posResponseHealer.setVersion("1");
	 		posResponseHealer.setServiceCode("COD201");
	 		posResponseHealer.setTransactionID("2");
	 		posResponseHealer.setDstSysID("1");
	 		//posResponseHealer.setReqTime("1");
	 		posResponseHealer.setHmac("");
	 		POSExtendAtt attBody = new POSExtendAtt();
	 		attBody.setEmployee_ID("345345");
	 		posResponseHealer.setPosExtendAtt(attBody);
	 		posResponseHealer.setResultCode("A01");
	 		posResponseHealer.setResultMsg("报错了");
	 		//posResponseHealer.setRespTime("2012-09-34");
	 		//posResponseHealer.set
	 		return posResponseHealer;
	 	}
	 	
	 	@org.junit.Test 
	    public void testLoginRequestBody() { 
	 		try {
				
				
		 		POSCODMS poscodms = new POSCODMS();
		 		//poscodms.setPosLoginRe);
		 		//poscodms.setSessionHead(this.createRequestHeader());
		 		poscodms.setRequestHead(this.createRequestHeader());;
		 		
		 		POSLoginRequestBody posRequestBody = new POSLoginRequestBody();
		 		
		 		posRequestBody.setEmployee_ID("1");
		 		posRequestBody.setCustomerNo("asdasd");
		 		posRequestBody.setPassword("asdasdasade@SFSAD");
		 		posRequestBody.setPosSn("8287319237");
		 		poscodms.setPosLoginRequestBody(posRequestBody);
		 		//poscodms.setpos
		 		JaxbUtil requestBinder = new JaxbUtil(POSCODMS.class,POSHealer.class,POSBody.class,POSLoginRequestBody.class);  
		        String retXml = requestBinder.toXml(poscodms, "utf-8");  
		        TestMeetingInterface tmi = new TestMeetingInterface();  
		        String response =  this.post(url,retXml); 
		        System.out.println(retXml);  
		        
		        JaxbUtil resultBinder = new JaxbUtil(POSCODMS.class);  
		        POSCODMS hotelObj = resultBinder.fromXml(response); 
		        System.out.println(hotelObj.getPosLoginRequestBody().getEmployee_ID());
	 		} catch (Exception e) {
				e.printStackTrace();
			}
	 	}
	 
	 
	 	@org.junit.Test 
	    public void testLoginResponseBody() {  
	 		try {
				
			
	 			POSCODMS poscodms = new POSCODMS();
	 	        
	 	 		
	 	 		
	 	 		POSResponseHealer posResponseHealer = new POSResponseHealer();//报文头
	 	 		//posResponseHealer.setVersion("1");
	 	 		posResponseHealer.setServiceCode(Const.POS_SERVICE_CODE_LOGIN);
	 	 		//posResponseHealer.setTransactionID("2");
	 	 		//posResponseHealer.setDstSysID("1");
	 	 		//posResponseHealer.setReqTime("1");
	 	 		//posResponseHealer.setHmac("");
	 	 		POSExtendAtt attHeader = new POSExtendAtt();
	 	 		attHeader.setEmployee_ID("345345");
	 	 		posResponseHealer.setPosExtendAtt(attHeader);
	 	 		posResponseHealer.setResultCode(POSResponseEnum.NO_USER.getResult_code());
	 	 		posResponseHealer.setResultMsg(POSResponseEnum.NO_USER.getResult_msg());
	 	 		
	 	 		poscodms.setResponseHead(posResponseHealer);
	 	 		
	 	 		POSExtendAtt attBody = new POSExtendAtt();
	 	 		POSLoginResponseBody posResponseBody = new POSLoginResponseBody();
	 	 		attBody.setPosLoginResponseBody(posResponseBody);
	 	 		attBody.setPosLoginResponseBody(posResponseBody);
	 	 		posResponseBody.setEmployee_ID("1");//必填
	 	 		posResponseBody.setEmployee_Name("aaa");
	 	 		posResponseBody.setCompany_Code("A1234");//必填
	 	 		posResponseBody.setCompany_Name("阿萨德");
	 	 		posResponseBody.setCompany_Tel("123123123123");
	 	 		posResponseBody.setCompany_Addr("天府大道");
	 	 		
	 	 		poscodms.setPosExtendAtt(attBody);
	 	 		JaxbUtil requestBinder = new JaxbUtil(POSCODMS.class,POSHealer.class);  
		        String retXml = requestBinder.toXml(poscodms, "utf-8");  
		        
		        System.out.println(retXml);  
		        
		        JaxbUtil resultBinder = new JaxbUtil(POSCODMS.class,CollectionWrapper.class);  
		        POSCODMS hotelObj = resultBinder.fromXml(retXml); 
		        System.out.println();
	 		} catch (Exception e) {
				e.printStackTrace();
			}
	    } 
	 	
	 	
	 	@org.junit.Test 
	    public void testOrderRequestBody() { 
	 		try {
				
				
		 		POSCODMS poscodms = new POSCODMS();
		 		poscodms.setRequestHead(this.createRequestHeader());;
		 		
		 		POSOrderRequestBody posRequestBody = new POSOrderRequestBody();
		 		poscodms.setPosOrderRequestBody(posRequestBody);
		 		posRequestBody.setEmployeeID("123456");
		 		posRequestBody.setCustomerNo("10013368774");
		 		posRequestBody.setOrderNo("123456");
		 		posRequestBody.setPosSn("82485539");
		 		//poscodms.setpos
		 		JaxbUtil requestBinder = new JaxbUtil(POSCODMS.class,POSHealer.class);  
		        String retXml = requestBinder.toXml(poscodms, "UTF-8");  
		        System.out.println(retXml);  
		        String response =  this.post(url,retXml);
		        JaxbUtil resultBinder = new JaxbUtil(POSCODMS.class,CollectionWrapper.class);  
		        POSCODMS hotelObj = resultBinder.fromXml(retXml); 
		        System.out.println();
	 		} catch (Exception e) {
				e.printStackTrace();
			}
	 	}
	 	
	 	
	 	@org.junit.Test 
	    public void testOrderResponseBody() {  
	 		try {
				
				
		 		POSCODMS poscodms = new POSCODMS();
		 		
		 		POSResponseHealer posResponseHealer = new POSResponseHealer();//报文头
	 	 		//posResponseHealer.setVersion("1");
	 	 		posResponseHealer.setServiceCode(Const.POS_SERVICE_CODE_ORDER);
	 	 		//posResponseHealer.setTransactionID("2");
	 	 		//posResponseHealer.setDstSysID("1");
	 	 		//posResponseHealer.setReqTime("1");
	 	 		//posResponseHealer.setHmac("");
	 	 		//POSExtendAtt attHeader = new POSExtendAtt();
	 	 		//attHeader.setEmployee_ID("345345");
	 	 		//posResponseHealer.setPosExtendAtt(attHeader);
	 	 		posResponseHealer.setResultCode(POSResponseEnum.FAIL_XML.getResult_code());
	 	 		posResponseHealer.setResultMsg(POSResponseEnum.FAIL_XML.getResult_msg());
	 	 		
	 	 		poscodms.setResponseHead(posResponseHealer);
		 		
		 		POSExtendAtt attBody = new POSExtendAtt();
		 		POSOrderResponseBody posResponseBody = new POSOrderResponseBody();
		 		attBody.setPosOrderResponseBody(posResponseBody);
		 		//posResponseBody.setEmployee_ID("1");
		 		posResponseBody.setEmployeeID("aaa");//必填
		 		posResponseBody.setOrderNo("aaaaa");//必填
		 		posResponseBody.setReceiverOrderNo(posResponseBody.getOrderNo());//必填 切同orderNo
		 		posResponseBody.setRceiverTel("1254788232");//必填
		 		posResponseBody.setAmount("1");//必填
		 		posResponseBody.setOrderStatus(POSOrderStatusEnum.PAYED_UNDIGN.getOrderStatus());//必填
//		 		posResponseBody.setCompanyAddr("阿萨德");
//		 		posResponseBody.setCompanyCode("123123123123");
//		 		posResponseBody.setCompanyTel("12312123123");
//		 		posResponseBody.setCustomerNo("aasdasdasd");
//		 		posResponseBody.setPosSn("78968njyh");		 		
//		 		posResponseBody.setReceiverName("力王");
		 		
		 		poscodms.setPosExtendAtt(attBody);
		 		JaxbUtil requestBinder = new JaxbUtil(POSCODMS.class,POSHealer.class);  
		        String retXml = requestBinder.toXml(poscodms, "utf-8");  
		        System.out.println(retXml);  
		        
		        JaxbUtil resultBinder = new JaxbUtil(POSCODMS.class,CollectionWrapper.class);  
		        POSCODMS hotelObj = resultBinder.fromXml(retXml); 
		        System.out.println();
	 		} catch (Exception e) {
				e.printStackTrace();
			}
	    } 
	 	
	 	
	 	@org.junit.Test 
	    public void testPayRequestBody() { 
	 		try {
				
				
		 		POSCODMS poscodms = new POSCODMS();
		 		poscodms.setRequestHead(this.createRequestHeader());;
		 		
		 		POSPayRequestBody posRequestBody = new POSPayRequestBody();
		 		poscodms.setPosPayRequestBody(posRequestBody);
		 		posRequestBody.setEmployeeID("1");
		 		posRequestBody.setAmount("10");
		 		posRequestBody.setOrderNo("asdasdasade@SFSAD");
		 		posRequestBody.setPosSn("8287319237");
		 		posRequestBody.setBankCardNo("4564651675894654");
		 		posRequestBody.setReferNo("asdasdasdasd");
		 		//poscodms.setpos
		 		JaxbUtil requestBinder = new JaxbUtil(POSCODMS.class,POSHealer.class);  
		        String retXml = requestBinder.toXml(poscodms, "utf-8");  
		        System.out.println(retXml);  
		        String response =  this.post(url,retXml);
		        JaxbUtil resultBinder = new JaxbUtil(POSCODMS.class,CollectionWrapper.class);  
		        POSCODMS hotelObj = resultBinder.fromXml(retXml); 
		        System.out.println();
	 		} catch (Exception e) {
				e.printStackTrace();
			}
	 	}
	 	
	 	
	 	@org.junit.Test 
	    public void testPayResponseBody() {  
	 		try {

		        
		        POSCODMS poscodms = new POSCODMS();
		        POSResponseHealer posResponseHealer = new POSResponseHealer();//报文头
	 	 		//posResponseHealer.setVersion("1");
	 	 		posResponseHealer.setServiceCode(Const.POS_SERVICE_CODE_PAY);
	 	 		//posResponseHealer.setTransactionID("2");
	 	 		//posResponseHealer.setDstSysID("1");
	 	 		//posResponseHealer.setReqTime("1");
	 	 		//posResponseHealer.setHmac("");
	 	 		//POSExtendAtt attHeader = new POSExtendAtt();
	 	 		//attHeader.setEmployee_ID("345345");
	 	 		//posResponseHealer.setPosExtendAtt(attHeader);
	 	 		posResponseHealer.setResultCode(POSResponseEnum.RECEIVE_FAIL.getResult_code());
	 	 		posResponseHealer.setResultMsg(POSResponseEnum.RECEIVE_FAIL.getResult_msg());
	 	 		
	 	 		poscodms.setResponseHead(posResponseHealer);

		 		POSPayResponseBody posResponseBody = new POSPayResponseBody();
		 		poscodms.setPosPayResponseBody(posResponseBody);
		 		//posResponseBody.setEmployee_ID("1");
		 		posResponseBody.setOrderNo("asdasdasade@SFSAD");
		 		posResponseBody.setReferNo("asdasdasdasd");//必填 交易参考号
		 		JaxbUtil requestBinder = new JaxbUtil(POSCODMS.class,POSHealer.class);  
		        String retXml = requestBinder.toXml(poscodms, "utf-8");  
		        System.out.println(retXml);  
		        
		        JaxbUtil resultBinder = new JaxbUtil(POSCODMS.class,CollectionWrapper.class);  
		        POSCODMS hotelObj = resultBinder.fromXml(retXml); 
		        System.out.println();
	 		} catch (Exception e) {
				e.printStackTrace();
			}
	    } 
	 	
	 	
	 	@org.junit.Test 
	    public void testSignRequestBody() { 
	 		try {
				
				
		 		POSCODMS poscodms = new POSCODMS();
		 		poscodms.setRequestHead(this.createRequestHeader());
		 		
		 		POSSignRequestBody posRequestBody = new POSSignRequestBody();
		 		poscodms.setPosSignRequestBody(posRequestBody);
//		 		posRequestBody.setEmployeeID("1");
//		 		posRequestBody.setAmount("10");
		 		posRequestBody.setOrderNo("asdasdasade@SFSAD");//必填
//		 		posRequestBody.setPosSn("8287319237");
//		 		posRequestBody.setBankCardNo("4564651675894654");
//		 		posRequestBody.setReferNo("asdasdasdasd");
		 		//poscodms.setpos
		 		JaxbUtil requestBinder = new JaxbUtil(POSCODMS.class,POSHealer.class);  
		        String retXml = requestBinder.toXml(poscodms, "utf-8");  
		        System.out.println(retXml);  
		        String response =  this.post(url,retXml);
		        JaxbUtil resultBinder = new JaxbUtil(POSCODMS.class,CollectionWrapper.class);  
		        POSCODMS hotelObj = resultBinder.fromXml(retXml); 
		        System.out.println();
	 		} catch (Exception e) {
				e.printStackTrace();
			}
	 	}
	 	
	 	
	 	@org.junit.Test 
	    public void testSignResponseBody() {  
	 		try {

		        
		        POSCODMS poscodms = new POSCODMS();
		 		
		 		POSResponseHealer posResponseHealer = new POSResponseHealer();//报文头
	 	 		//posResponseHealer.setVersion("1");
	 	 		posResponseHealer.setServiceCode(Const.POS_SERVICE_CODE_SIGN);
	 	 		//posResponseHealer.setTransactionID("2");
	 	 		//posResponseHealer.setDstSysID("1");
	 	 		//posResponseHealer.setReqTime("1");
	 	 		//posResponseHealer.setHmac("");
	 	 		//POSExtendAtt attHeader = new POSExtendAtt();
	 	 		//attHeader.setEmployee_ID("345345");
	 	 		//posResponseHealer.setPosExtendAtt(attHeader);
	 	 		posResponseHealer.setResultCode(POSResponseEnum.RECEIVE_FAIL.getResult_code());
	 	 		posResponseHealer.setResultMsg(POSResponseEnum.RECEIVE_FAIL.getResult_msg());
	 	 		
	 	 		poscodms.setResponseHead(posResponseHealer);

		 		POSSignResponseBody posResponseBody = new POSSignResponseBody();
		 		poscodms.setPosSignResponseBody(posResponseBody);
		 		//posResponseBody.setEmployee_ID("1");
		 		posResponseBody.setOrderNo("asdasdasade@SFSAD");
		 		JaxbUtil requestBinder = new JaxbUtil(POSCODMS.class,POSHealer.class);  
		        String retXml = requestBinder.toXml(poscodms, "utf-8");  
		        System.out.println(retXml);  
		        
		        JaxbUtil resultBinder = new JaxbUtil(POSCODMS.class,CollectionWrapper.class);  
		        POSCODMS hotelObj = resultBinder.fromXml(retXml); 
		        System.out.println();
	 		} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	 	
	 	
	 	@org.junit.Test 
	    public void testReimburseRequestBody() { 
	 		try {
				
				
		 		POSCODMS poscodms = new POSCODMS();
		 		poscodms.setRequestHead(this.createRequestHeader());;
		 		
		 		POSPayRequestBody posRequestBody = new POSPayRequestBody();
		 		poscodms.setPosPayRequestBody(posRequestBody);
		 		posRequestBody.setEmployeeID("1");
		 		posRequestBody.setAmount("10");
		 		posRequestBody.setOrderNo("asdasdasade@SFSAD");
		 		posRequestBody.setPosSn("8287319237");
		 		posRequestBody.setBankCardNo("4564651675894654");
		 		posRequestBody.setReferNo("asdasdasdasd");
		 		//poscodms.setpos
		 		JaxbUtil requestBinder = new JaxbUtil(POSCODMS.class,POSHealer.class);  
		        String retXml = requestBinder.toXml(poscodms, "utf-8");  
		        System.out.println(retXml);  
		        String response =  this.post(url,retXml);
		        JaxbUtil resultBinder = new JaxbUtil(POSCODMS.class,CollectionWrapper.class);  
		        POSCODMS hotelObj = resultBinder.fromXml(retXml); 
		        System.out.println();
	 		} catch (Exception e) {
				e.printStackTrace();
			}
	 	}
	 	
	 	
	 	/**  
	     * 发送xml数据请求到server端  
	     * @param url xml请求数据地址  
	     * @param xmlString 发送的xml数据流  
	     * @return null发送失败，否则返回响应内容  
	     */    
	    public String post(String url,String data){    
	        //关闭   
	        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");     
	        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");     
	        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient", "stdout");    
	          
	        //创建httpclient工具对象   
	        org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();    
	        //创建post请求方法   
	        PostMethod myPost = new PostMethod(url);    
	        //设置请求超时时间   
	        client.setConnectionTimeout(300*1000);  
	        String responseString = null;    
	        try{    
	            //设置请求头部类型   
	            myPost.setRequestHeader("Content-Type","text/xml");  
	            myPost.setRequestHeader("charset","utf-8");  
	              
	            //设置请求体，即xml文本内容，注：这里写了两种方式，一种是直接获取xml内容字符串，一种是读取xml文件以流的形式   
//	          myPost.setRequestBody(xmlString);   
	              
	            //InputStream body=this.getClass().getResourceAsStream("/"+xmlFileName);  
	            InputStream body=new ByteArrayInputStream(data.getBytes("UTF-8"));    
	            myPost.setRequestBody(body);  
//	            myPost.setRequestEntity(new StringRequestEntity(xmlString,"text/xml","utf-8"));     
	            int statusCode = client.executeMethod(myPost);    
	            if(statusCode == HttpStatus.SC_OK){    
	                BufferedInputStream bis = new BufferedInputStream(myPost.getResponseBodyAsStream());    
	                byte[] bytes = new byte[1024];    
	                ByteArrayOutputStream bos = new ByteArrayOutputStream();    
	                int count = 0;    
	                while((count = bis.read(bytes))!= -1){    
	                    bos.write(bytes, 0, count);    
	                }    
	                byte[] strByte = bos.toByteArray();    
	                responseString = new String(strByte,0,strByte.length,"utf-8");    
	                bos.close();    
	                bis.close();    
	            }    
	        }catch (Exception e) {    
	            e.printStackTrace();    
	        }    
	        myPost.releaseConnection();    
	        return responseString;    
	    }

}
