package com.example.myapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.myapp.R;
import com.example.myapp.db.TimeDB;
import com.example.myapp.model.Item;
import com.example.myapp.model.Plan;
import com.example.myapp.util.ItemAdapter;
import com.example.myapp.util.CurrentTime;
import com.example.myapp.util.TimeApplication;

/**
 * Created by Administrator on 2016/4/30.
 */
public class PlanActivity extends Activity implements OnClickListener{

    private TimeDB timeDB ;
    private Plan plan;
    private EditText edit;
    private Button button;
    private ItemAdapter adapter;
    private ListView listView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {//to onstart
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.plan_layout);
    	timeDB = TimeDB.getInstance(TimeApplication.getContext());
    	//*******
    	timeDB.newDB();
    	plan = null;
      	plan = timeDB.loadPlan(CurrentTime.getTime()); 
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new ItemAdapter(PlanActivity.this, R.layout.plan_item, plan.getPlanItem());
    	listView.setAdapter(adapter);
        button = (Button) findViewById(R.id.add_button);
        edit = (EditText) findViewById(R.id.add_edit);
        button.setOnClickListener(this); 
        listView.setOnItemClickListener(new OnItemClickListener() {
        	@Override
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		Item item = plan.getPlanItem().get(position);
        		////////////////////////short long left short
        		////////////////////////delete update 
        	}
        });
    }
    
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_button:
                String content = edit.getText().toString();
                if (!"".equals(content)) {
                    plan.insertPlanItem(content);
                    adapter.notifyDataSetChanged();
                    listView.setSelection(plan.getPlanItem().size());
                    edit.setText("");
                    edit.clearFocus();
    				button.requestFocus();
                }
                break;
            default:
                break;
        }
    }

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		if (timeDB.loadPlan(CurrentTime.getTime()).getPlanItem().get(0).getContent() == null) {
			Log.v("ok","load");
			timeDB.savePlan(plan);
		}
		else {
			timeDB.updatePlan(plan);
			Log.v("ok","update");
		}	
	}
}
