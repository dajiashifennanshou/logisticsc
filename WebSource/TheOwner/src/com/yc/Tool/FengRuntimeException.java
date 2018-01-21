package com.yc.Tool;
/**
 *Good Luck
 *
*/
public class FengRuntimeException extends RuntimeException {

	/**
	 * Author:FENG
	 * 2016年7月21日
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	public FengRuntimeException(){
		this.msg="异常";
	}
	public FengRuntimeException(String _msg){
		this.msg=_msg;
		//this.printErrorInfo();
	}
	public void printErrorInfo(){
		StackTraceElement ste = new Throwable().getStackTrace()[1];
		System.err.println("异常名称："+this.getMsg());
		System.err.println("异常类型：自定义异常");
		System.err.println("异常类名："+ste.getFileName()+"    所在行："+ste.getLineNumber());
		System.err.println("----------------------------------------------------------------By_Feng");
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
