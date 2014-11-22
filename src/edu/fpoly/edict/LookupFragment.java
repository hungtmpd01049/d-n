package edu.fpoly.edict;

import edu.fpoly.edict.data.WordDataAdapter;
import edu.fpoly.edict.data.table.WordTable;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LookupFragment extends Fragment {
	public static LookupFragment newInstance(String param1, String param2) {
		LookupFragment fragment = new LookupFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	public LookupFragment() {
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
		}
	}
	Button btn ;
	EditText etLookupWord;
	WebView wv;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_lookup, container, false);
		btn = (Button) view.findViewById(R.id.btnSearch);
		etLookupWord = (EditText) view.findViewById(R.id.etLookupWord);
		wv = (WebView) view.findViewById(R.id.wvWordBoard);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (etLookupWord.getText().toString().trim()==""){
					Toast.makeText(getActivity(), "Search word must be entered", Toast.LENGTH_LONG).show();
					return;
				}
				WordDataAdapter adapter = null;
				try {
					adapter = new WordDataAdapter(getActivity());
					adapter.open();
					Cursor cursor = adapter.getWord(etLookupWord.getText().toString());
					if (cursor != null && cursor.isFirst()){
						String meaning = cursor.getString(cursor.getColumnIndex(WordTable.WORD_MEANING));
						String data = "<b>"+ cursor.getString(cursor.getColumnIndex(WordTable.WORD_KEY)) + ":</b>";
						data += "<blockquote>" + meaning +"</blockquote>";
						
						wv.loadData(data, "text/html", "UTF-8");
					}else{
						Toast.makeText(getActivity(), "The word doesn't exited in the dictionary", Toast.LENGTH_LONG).show();
					}
				} catch (Exception e) {
					Toast.makeText(getActivity(), "There is an error."+ e.getMessage(), Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}finally{
					try{
						adapter.close();
					}catch(Exception ex){}
				}
			}
		});
		
		return view;
	}
}
