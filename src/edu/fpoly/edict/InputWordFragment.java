package edu.fpoly.edict;

import edu.fpoly.edict.data.WordDataAdapter;
import edu.fpoly.edict.data.table.WordTable;
import edu.fpoly.edict.dialog.MessageDialog;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class InputWordFragment extends Fragment {
	EditText etWordKey ;
	EditText etWordMeaning;
	
	public static InputWordFragment newInstance(String param1, String param2) {
		InputWordFragment fragment = new InputWordFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	public InputWordFragment() {
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_input_word, container, false);
		Button btn = (Button) view.findViewById(R.id.btnSave);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				onClick_Save(v);
			}
		});
		
		etWordKey = (EditText) view.findViewById(R.id.etWordKey);
		etWordMeaning = (EditText) view.findViewById(R.id.etWordMeaning);
		
		return view;
	}
	
	public void onClick_Save(View v){
		MessageDialog msgDialog = new MessageDialog();
		DialogInterface.OnClickListener onPositiveHandler = new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				WordDataAdapter adapter = null;
				try{
					adapter = new WordDataAdapter(getActivity());
					adapter.open();
					adapter.addWord(etWordKey.getText().toString(), etWordMeaning.getText().toString());
					
					Toast.makeText(getActivity(), "New word has been saved!", Toast.LENGTH_SHORT).show();
					
					etWordKey.setText("");
					etWordMeaning.setText("");
				}catch(Exception ex){
					Log.d("eDictionary", ex.getMessage());
					Toast.makeText(getActivity(), "There is an error.", Toast.LENGTH_SHORT).show();
				}finally{
					adapter.close();
				}
			}
		};

		if (!(msgDialog.showConfirmDialog(this.getActivity(), "Confirm","Do you want to save?", "Yes", "No", false, onPositiveHandler))){
			return;
		}
	}
}
