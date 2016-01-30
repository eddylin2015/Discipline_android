package discipline.mbc.eud.mo;
import java.io.File;

import java.io.IOException;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MBC_DiscStudActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_stud_wrong_rec); 
        findViews();
        //Toast.makeText(this,"onCreate24", Toast.LENGTH_LONG).show();
        setListensers();
    }
    
    private Spinner spinner_wrongtype;
    private Spinner spinner_class;
    private Spinner spinner_students;
    private String wrong_type="";
    private String wrong_class="";
    private String wrong_stud="";
    private String pre_str="";
    private EditText edittext_note;
    private TextView  tv4;
    private Button button_submit;
    private Button button_ret;

    private void findViews()
    {
    	spinner_wrongtype = (Spinner) findViewById(R.id.spinner1);
    	spinner_class = (Spinner) findViewById(R.id.spinner2);
    	spinner_students=(Spinner) findViewById(R.id.spinner3);
    	edittext_note=(EditText) findViewById(R.id.editText1);
    	button_submit=(Button)findViewById(R.id.button2);
    	button_ret=(Button)findViewById(R.id.button_ret0);
    	tv4 = (TextView) findViewById(R.id.textView4);
    }
    private void setListensers() {
    	ArrayAdapter<CharSequence> adapter_wrongtype = ArrayAdapter.createFromResource(
    	        this, R.array.wrong_type_arr, android.R.layout.simple_spinner_item);
    	adapter_wrongtype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinner_wrongtype.setAdapter(adapter_wrongtype);
    	spinner_wrongtype.setOnItemSelectedListener(
    			new AdapterView.OnItemSelectedListener()  	
    			{
    				public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) 
    				{
   					/* if(FirstLoad){ FirstLoad = false; return; }*/
    					wrong_type = adapterView.getItemAtPosition(i).toString();
    					return;
    				} 
    				public void onNothingSelected(AdapterView<?> adapterView) 
    				{
    					return;
    				} 
    			});
    	ArrayAdapter<CharSequence> adapter_class = ArrayAdapter.createFromResource(
    	        this, R.array.class_arr, android.R.layout.simple_spinner_item);
    	adapter_class.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinner_class.setAdapter(adapter_class);
    	spinner_class.setOnItemSelectedListener(
    			new AdapterView.OnItemSelectedListener()
    			{
    				public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) 
    				{
    					wrong_class = adapterView.getItemAtPosition(i).toString();
    					SelectClassStudents(wrong_class);
    					return;
    				} 
    				public void onNothingSelected(AdapterView<?> adapterView) 
    				{
    					return;
    				} 
    			});
    	ArrayAdapter<CharSequence> adapter_classstud = ArrayAdapter.createFromResource(
    	        this, R.array.SG1A_STUDENTS, android.R.layout.simple_spinner_item);
    	adapter_classstud.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinner_students.setAdapter(adapter_class);
    	spinner_students.setOnItemSelectedListener(
    			new AdapterView.OnItemSelectedListener() 
    			{
    				public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) 
    				{
    					wrong_stud = adapterView.getItemAtPosition(i).toString();
    					return;
    				} 
    				public void onNothingSelected(AdapterView<?> adapterView) 
    				{
    					return;
    				} 
    			});                       
    	button_submit.setOnClickListener(submit_click);    
    	button_ret.setOnClickListener(backMain);
	}
    private void SelectClassStudents(String class_no_str)
    {
    	//Toast.makeText(this,"selectClassStudents109", Toast.LENGTH_LONG).show();
    	String classno=class_no_str.split("_")[1];
    	ArrayAdapter<String> adapter_classstud_temp =new  ArrayAdapter<String>(
    	        this, android.R.layout.simple_spinner_item);
    	adapter_classstud_temp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinner_students.setAdapter(adapter_classstud_temp);
    	File extDir1 = Environment.getExternalStorageDirectory();
    	//Toast.makeText(this,extDir1.toString(), Toast.LENGTH_LONG).show();
        String dbname="STUDMAINSQLITE.db";
        File db_file=new File(extDir1,dbname);       
        String[] fields={"CLASSNO","C_NO","NAME_C","STUD_ID"};
        StringBuilder sql=new StringBuilder();
        sql.append("Select GRADE||CLASS CLASSNO,C_NO,NAME_C,STUD_ID from student where GRADE||CLASS='");
        sql.append(classno);
        sql.append("';");
        //Toast.makeText(this,db_file.toString(), Toast.LENGTH_LONG).show();
    	mo.edu.mbc.lib.Pub.readDBToArrayAdapter(adapter_classstud_temp, db_file,sql.toString(), fields);
    }
    private Button.OnClickListener backMain = new Button.OnClickListener()
    {
         public void onClick(View v)
         {
             // Close this Activity
        	 MBC_DiscStudActivity.this.finish();              
         }
    };
    private OnClickListener submit_click = new OnClickListener()
    {
        public void onClick(View v)
        {

       	    try 
       	    {
           	   File file=mo.edu.mbc.lib.Pub.getCurrentDateFileFromSDCard("disciplineStud");  
       		   if(!file.exists())
       		   {  
       			   if(!file.createNewFile()){
       				   Toast.makeText(v.getContext(),"未能建立檔案!", Toast.LENGTH_LONG).show();
       				   return;
       			   }
       		   }

               String rec_str=wrong_type+"_"+wrong_stud;
               if(rec_str.equals(pre_str))
               {
                   Toast.makeText(v.getContext(),"前一筆記錄相同!", Toast.LENGTH_LONG).show();
               } else
               {
                   String[] wrong_arr=wrong_type.split("_");
                   String wrgid=wrong_arr[0];
                   String wrgtype=wrong_arr[1];
                   String wrg=wrong_arr[2];
                   String[] stud_arr=wrong_stud.split("_");
                   String classno=stud_arr[0];
                   String seat=stud_arr[1];
                   String name=stud_arr[2];
                   String stud_ref=stud_arr[3];
                   mo.edu.mbc.lib.Wrg.writeStudWrg2File(file, wrgid, wrgtype, wrg, classno, seat, name, stud_ref, edittext_note.getText().toString());
                   edittext_note.setText("",TextView.BufferType.EDITABLE);
                   Toast.makeText(v.getContext(),"新增記錄:" + rec_str, Toast.LENGTH_LONG).show();
                   tv4.setText(rec_str+"   !!writed");
                   
                   pre_str=rec_str;
               }
            } catch (IOException e) 
            {  
               e.printStackTrace();  
               Toast.makeText(v.getContext(),e.getMessage(), Toast.LENGTH_LONG).show();
            }  
        }
    };    
}
