/**
 * 
 */
package slab.haqq.api.result;

import slab.haqq.R;
import slab.haqq.lib.GlobalController;
import slab.haqq.lib.ResultProvider;
import slab.haqq.lib.adapter.ResultAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * @author rasxen
 * <p><h1>Result Fragment</h1></p>
 * <p>a fragment that shown sura list from {@link ResultProvider}</p>
 */
public class ResultAPI extends Fragment {
	private ListView lv;
	
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.activity_result, container,
				false);
		
		lv = (ListView) rootView.findViewById(R.id.api_result_lv);
		GlobalController.resultAdapter = new ResultAdapter(this.getActivity());
		lv.setAdapter(GlobalController.resultAdapter);
		//lv.setOnItemClickListener(resultListener);
		return rootView;
	}
	
	
	/**
	 * TODO : Documentation
	 */
	/*
	private OnItemClickListener resultListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(parent.getContext(), ResultDetail.class);
			intent.putExtra("resultParsel",
					GlobalController.resultProvider.resultList.get(position));
			startActivity(intent);
			
		}
	};*/
}
