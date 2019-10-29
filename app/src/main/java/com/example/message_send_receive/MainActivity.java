package com.example.message_send_receive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.app.Activity;

import android.app.PendingIntent;

import android.content.BroadcastReceiver;

import android.content.Context;

import android.content.Intent;

import android.os.Bundle;

import android.telephony.gsm.SmsManager;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;

import android.widget.Toast;

         

public class TinySMS extends Activity {

        public static final String SMS_ACTION = "com.android.TinySMS.RESULT";

// private TextView message;

        private Button snd;

        private EditText tel;

        private EditText txt;

        private SentReceiver receiver = new SentReceiver();

         

        private class SentReceiver extends BroadcastReceiver {

@Override

public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(SMS_ACTION)) {

         int code = getResultCode();

         //短消息发送成功

         if(code == Activity.RESULT_OK)

         Toast.makeText(TinySMS.this, R.string.msg_sent,

         Toast.LENGTH_SHORT).show();

        }

        }

        };

         

        /** Called when the activity is first created. */

        @Override

public void onCreate(Bundle savedInstanceState) {

         super.onCreate(savedInstanceState);

         setContentView(R.layout.activity_main);

         tel = (EditText) findViewById(R.id.EditText01);

         tel.setText("5554");  //模拟器之间互发短信

         txt = (EditText) findViewById(R.id.EditText02);

         txt.setText("我用自己的程序试试发短信。");

         snd = (Button) findViewById(R.id.Button01);

        

         snd.setOnClickListener(new View.OnClickListener() {

         public void onClick(View arg0) {

          String phoneNo = tel.getText().toString();

          String message = txt.getText().toString();

          if (phoneNo.length()>0 && message.length()>0){

          sendSMS(phoneNo, message);

          } else {

          Toast.makeText(TinySMS.this,

          "请重新输入电话号码和短信内容",

          Toast.LENGTH_LONG).show();

          }

         }  

         });

        }

         

        private void sendSMS(String address, String content)

        {

         SmsManager manager = SmsManager.getDefault();

         Intent i = new Intent(SMS_ACTION);

         //生成PendingIntent，当消息发送完成，接收到广播

         PendingIntent sentIntent = PendingIntent.getBroadcast(

         this,

         0,

         i,

         PendingIntent.FLAG_ONE_SHOT);

         manager.sendTextMessage(

         address,

         null,

         content,

         sentIntent,

         null);

        }
      
}
