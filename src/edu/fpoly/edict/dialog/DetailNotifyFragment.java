package edu.fpoly.edict.dialog;

import edu.fpoly.edict.R;
import edu.fpoly.edict.R.layout;
import edu.fpoly.edict.data.WordDataAdapter;
import edu.fpoly.edict.data.table.WordTable;
import android.R.mipmap;
import android.app.Activity;
import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailNotifyFragment extends DialogFragment {
	private static final String ARG_KEY = "WORD_KEY";
	private static final String ARG_ID = "WORD_ID";
	private String mKey;
	private int mId;

	public static DetailNotifyFragment newInstance(String key, int mId) {
		DetailNotifyFragment fragment = new DetailNotifyFragment();
		Bundle args = new Bundle();
		args.putString(ARG_KEY, key);
		args.putInt(ARG_ID, mId);
		fragment.setArguments(args);
		return fragment;
	}

	public DetailNotifyFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mKey = getArguments().getString(ARG_KEY);
			mId = getArguments().getInt(ARG_ID);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_detail_notify,
				container, false);
		Button btn = (Button) view.findViewById(R.id.btnClose);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				closeDialog();
			}
		});

		getDialog().setTitle("Word: " + mKey);
		loadDataToWebView(view);

		return view;
	}

	private void loadDataToWebView(View view) {
		WebView tv = (WebView) view.findViewById(R.id.wvWordItem);
		tv.getSettings().setSupportZoom(true);
		try {
			String meaning = null, image = null;
			WordDataAdapter adapter = new WordDataAdapter(getActivity());
			adapter.open();
			Cursor cursor = adapter.getWord(mId);
			if (cursor != null) {
				meaning = cursor.getString(cursor
						.getColumnIndex(WordTable.WORD_MEANING));
				image = cursor.getString(cursor
						.getColumnIndex(WordTable.WORD_IMAGE));
				cursor.close();
			}

			adapter.close();

			String start = "<html><head><meta http-equiv='Content-Type' content='text/html' charset='UTF-8' /></head><body>";
			String end = "</body></html>";
			if (image != null) {
				image = "<br><img src='images/" + image
						+ "' width='100px' height='100px'>";
				meaning = start + meaning + image + end;
			} else {
				meaning = start + meaning + end;
			}
			tv.loadDataWithBaseURL("file:///android_asset/", meaning,
					"text/html", "utf-8", null);

		} catch (Exception ex) {
			Toast.makeText(getActivity(),
					"There is an error." + ex.getMessage(), Toast.LENGTH_SHORT)
					.show();
			ex.printStackTrace();
		}
	}

	private void closeDialog() {
		this.dismiss();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}
}
