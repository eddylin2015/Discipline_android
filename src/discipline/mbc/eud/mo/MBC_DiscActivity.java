package discipline.mbc.eud.mo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MBC_DiscActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_wrong_rec); 
        findViews();
        setListensers();
    }
    private void findViews()
    {
    	spinner_wrongtype = (Spinner) findViewById(R.id.spinner1);
    	spinner_class = (Spinner) findViewById(R.id.spinner2);
    	edittext_cnt=(EditText) findViewById(R.id.editText1);
    	button_submit=(Button)findViewById(R.id.button2);
    	button_ret=(Button)findViewById(R.id.button_ret0);
    }

    private void setListensers() {
    	ArrayAdapter<CharSequence> adapter_wrongtype = ArrayAdapter.createFromResource(
    	        this, R.array.wrong_type_arr, android.R.layout.simple_spinner_item);
    	adapter_wrongtype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinner_wrongtype.setAdapter(adapter_wrongtype);
    	spinner_wrongtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    	    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    	       /* if(FirstLoad){ FirstLoad = false; return; }*/
    	    	wrong_type = adapterView.getItemAtPosition(i).toString();
    	    	return;
    	    } 
    	    public void onNothingSelected(AdapterView<?> adapterView) {
    	        return;
    	    } 
    	});
    	ArrayAdapter<CharSequence> adapter_class = ArrayAdapter.createFromResource(
    	        this, R.array.class_arr, android.R.layout.simple_spinner_item);
    	adapter_class.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinner_class.setAdapter(adapter_class);
    	spinner_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    		    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    		       /* if(FirstLoad){
    		                FirstLoad = false;
    		                return;                         
    		        }*/
    		    	wrong_class = adapterView.getItemAtPosition(i).toString();
    		    	return;
    		    } 
    		    public void onNothingSelected(AdapterView<?> adapterView) {
    		        return;
    		    } 
    		});                       
    	button_submit.setOnClickListener(submit_click);    
    	button_ret.setOnClickListener(backMain);
	}
    Spinner spinner_wrongtype;
    Spinner spinner_class;
    String wrong_type="";
    String wrong_class="";
    EditText edittext_cnt;
    Button button_submit;
    Button button_ret;
    private Button.OnClickListener backMain = new Button.OnClickListener()
    {
         public void onClick(View v)
         {
            // Close this Activity
        	 MBC_DiscActivity.this.finish();              
         }
    };
    private OnClickListener submit_click = new OnClickListener()
    {
        public void onClick(View v)
        {
       	    SimpleDateFormat formatter_time = new SimpleDateFormat("yyyy-MM-dd_H:mm", new Locale("en", "US"));
       	    Date today = new Date();
       	    String time_str = formatter_time.format(today);
       	    try 
       	    {  
       		   File file=mo.edu.mbc.lib.Pub.getCurrentDateFileFromSDCard("discipline");
       		   if(!file.exists())
       		   {  
       			   if(!file.createNewFile())
       			   {
       				   Toast.makeText(v.getContext(),"未能建立檔案!", Toast.LENGTH_LONG).show();
       				   return;
       			   }
       		   }
       		   FileOutputStream out = new FileOutputStream(file,true);
               String rec_str="";
               String cnt=edittext_cnt.getText().toString().trim();
               TextView  tv4 = (TextView) findViewById(R.id.textView4);
               if(cnt.equals("")) 
               {
                   Toast.makeText(v.getContext(),"填寫人數!", Toast.LENGTH_LONG).show();
               }else{
            	   rec_str=wrong_type+"_"+wrong_class+"_"+edittext_cnt.getText().toString()+"_"+time_str;           	   
            	   tv4.setText(rec_str);
            	   out.write((rec_str+"\n").getBytes());
               }
               out.flush();
               out.close();
               edittext_cnt.setText("",TextView.BufferType.EDITABLE);
               Toast.makeText(v.getContext(),"新增記錄:" + rec_str, Toast.LENGTH_LONG).show();
               tv4.setText(rec_str+"   !writed");
           } catch (IOException e) 
           {  
               e.printStackTrace();  
               Toast.makeText(v.getContext(),e.getMessage(), Toast.LENGTH_LONG).show();
           }  
        }
    };    
}



