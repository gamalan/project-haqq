package slab.haqq;

import java.util.ArrayList;
import java.util.List;

import slab.haqq.lib.GlobalController;
import slab.haqq.lib.adapter.model.Sura;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class GotoAyaActivity extends Activity {

	private Spinner suraDropdown, ayaDropdown;
	private ArrayAdapter<String> ayaAdapter;
	private Sura suradata;
	private Button jumpBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_goto_aya);
		Log.v("Goto", "layoutComplete");
		setTitle("Go to Aya");
		suraDropdown = (Spinner)findViewById(R.id.suraDrop);
		ayaDropdown = (Spinner)findViewById(R.id.ayaDrop);
		
		List<String> sura = new ArrayList<String>();
		for(int i=0;i < GlobalController.SuraList.size(); i++){
			sura.add(GlobalController.SuraList.get(i).getName());
		}
		ArrayAdapter<String> suraadapter = new ArrayAdapter<String>(this, R.layout.spinner_simple, R.id.spinnerText1, sura);
		suraDropdown.setAdapter(suraadapter);
		suraDropdown.setOnItemSelectedListener(suraListener);
		Log.v("Goto", "Sura Dropdown Complete");
		suradata = GlobalController.SuraList.get(0); 
		
		List<String> aya = new ArrayList<String>();
		for(int i=0;i < suradata.getAyaCount(); i++){
			aya.add(String.valueOf(i+1));
		}
		ayaAdapter = new ArrayAdapter<String>(this, R.layout.spinner_simple, R.id.spinnerText1, aya);
		ayaDropdown.setAdapter(ayaAdapter);
		Log.v("Goto", "Aya Dropdown Complete");
		jumpBtn = (Button)findViewById(R.id.jumpButton);
		jumpBtn.setOnClickListener(gotoListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.goto_aya, menu);
		return true;
	}
	
	private OnItemSelectedListener suraListener = new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int pos,
				long id) {
			// TODO Auto-generated method stub
			suradata = GlobalController.SuraList.get(pos);
			ayaAdapter.clear();
			List<String> aya = new ArrayList<String>();
			for(int i=0;i < suradata.getAyaCount(); i++){
				aya.add(String.valueOf(i+1));
			}
			ayaAdapter.addAll(aya);
			//ayaAdapter = new ArrayAdapter<String>(GotoAyaActivity.this, R.id.simpleStringSpinner2, R.layout.simple_spinner2_adapter, aya);
			//ayaDropdown.setAdapter(ayaAdapter);
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	};

	private OnClickListener gotoListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(GotoAyaActivity.this,ReadAya.class);
			intent.putExtra("suraData", suradata);
			intent.putExtra("suraNumber", suradata.getIdn());
			intent.putExtra("ayaNumber", ayaDropdown.getSelectedItemPosition() + 1);
			startActivity(intent);
		}
	};
}
