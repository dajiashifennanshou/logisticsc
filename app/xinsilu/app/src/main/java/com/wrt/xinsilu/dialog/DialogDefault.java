
package com.wrt.xinsilu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wrt.xinsilu.R;


/**
 * 默认对话框<br/>
 * 简易的退出，提示功能，只有上面message提示跟下面的确认取消按钮<br/>
 * 有隐藏按钮的功能 setEnterVisible() 隐藏确认按钮setCancleVisible()隐藏取消按钮
 * @author wangsong
 * @since  2016-3-16
 */
public class DialogDefault extends Dialog {
	/**msg内容*/
	private TextView msg;
	/**确认按钮*/
	private Button enter;
	/**取消按钮*/
	private Button cancle;

	public DialogDefault(Context context) {
		super(context, R.style.Base_Theme_AppCompat_Light);
		getWindow().getAttributes().gravity = Gravity.CENTER;
		setContentView(R.layout.dialog_default);
		setCanceledOnTouchOutside(true);
		msg = (TextView) findViewById(R.id.dialog_default_msg);
		enter = (Button) findViewById(R.id.dialog_default_enter);
		cancle = (Button) findViewById(R.id.dialog_default_cancle);
		cancle.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogDefault.this.dismiss();
			}
		});
	}
	/**
	 * 设置内容对其方式
	 * @param gravity
	 */
	public void setMsgGravity(int gravity){
		msg.setGravity(gravity);
	}
	/** 设置消息 */
	public void setMsg(String msg) {
		this.msg.setText(msg);
	}

	/** 设置消息 */
	public void setMsg(int msgId) {
		this.msg.setText(msgId);
	}

	/**设置确认事件*/
	public void setEnterOnClickListener(View.OnClickListener listener) {
		enter.setOnClickListener(listener);
	}

	/**设置取消事件*/
	public void setCancleOnClickListener(View.OnClickListener listener) {
		cancle.setOnClickListener(listener);
	}

	/**显示/隐藏EnterButton*/
	public void setEnterVisible(int isVisible){
		enter.setVisibility(isVisible);
	}

	/**显示/隐藏CancleButton*/
	public void setCancleVisible(int isVisible){
		cancle.setVisibility(isVisible);
	}

}
