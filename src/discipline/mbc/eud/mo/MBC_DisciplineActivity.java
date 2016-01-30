package discipline.mbc.eud.mo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MBC_DisciplineActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view01); 
        findViews();
        setListensers();
    }
   
    private Button button_0;
    private Button button_1;
    private Button button_2;
    private Button button_3;

  
    private void findViews()
    {
    	button_0 = (Button) findViewById(R.id.button1);
    	button_1 = (Button) findViewById(R.id.button2);
    	button_2 = (Button) findViewById(R.id.button3);
    	button_3 = (Button) findViewById(R.id.button4);

    }
    private void setListensers() {
	     button_0.setOnClickListener(btn0_click);
	     button_1.setOnClickListener(btn1_click);
	     button_2.setOnClickListener(btn2_click);
	     button_3.setOnClickListener(btn3_click);
	}
	       
	 private Button.OnClickListener btn0_click = new Button.OnClickListener()
	 {
	          public void onClick(View v)
	          {
                  Intent intent = new Intent();
                  intent.setClass(MBC_DisciplineActivity.this, MBC_DiscActivity.class);
                  startActivity(intent);
	          }
	 };
	 private Button.OnClickListener btn1_click = new Button.OnClickListener()
	 {
	          public void onClick(View v)
	          {
                  Intent intent = new Intent();
                  intent.setClass(MBC_DisciplineActivity.this, MBC_DiscStudActivity.class);
                  startActivity(intent);              
	          }
	 };
	 private Button.OnClickListener btn2_click = new Button.OnClickListener()
	 {
	          public void onClick(View v)
	          {
	          	Intent intent = new Intent();
	          	
	        	intent.setClass(MBC_DisciplineActivity.this, MBC_ClassWrong_ListForm.class);
	        	startActivity(intent);       
	          }
	 };
	 private Button.OnClickListener btn3_click = new Button.OnClickListener()
	 {
	          public void onClick(View v)
	          {
	          	Intent intent = new Intent();
	          	intent.setClass(MBC_DisciplineActivity.this, MBC_ClassStudWrong_ListForm.class);
	        	startActivity(intent);            
	          }
	 };

	 
}
