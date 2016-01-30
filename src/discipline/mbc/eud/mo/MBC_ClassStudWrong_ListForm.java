package discipline.mbc.eud.mo;
import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MBC_ClassStudWrong_ListForm extends Activity {
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.listform);
	        findViews();
	        Initalize();
	 }
	 public void findViews()
	 {
		 lv=(android.widget.ListView) findViewById(R.id.listView1);
	 }
	 public void Initalize()
	 {
	        File file=mo.edu.mbc.lib.Pub.getCurrentDateFileFromSDCard("disciplineStud");
	        MyArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
	        lv.setAdapter(MyArrayAdapter);
	        mo.edu.mbc.lib.Pub.readFileToArrayAdapter(MyArrayAdapter, file );
	 }
	 private ListView lv;
	 private ArrayAdapter<String> MyArrayAdapter;
}
