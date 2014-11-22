package edu.fpoly.edict;

import edu.fpoly.edict.adapter.WordListAdapter;
import edu.fpoly.edict.adapter.WordListItem;
import edu.fpoly.edict.dialog.DetailNotifyFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;

public class WordListFragment extends Fragment implements
		PopupMenu.OnMenuItemClickListener {
	WordListAdapter mAdapter;

	public WordListFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_word_list, container,
				false);
		mAdapter = new WordListAdapter(this.getActivity());
		mAdapter.loadData();
		ListView lv = (ListView) view.findViewById(R.id.lvWordList);
		lv.setAdapter(mAdapter);
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				showDetailDialog(position);
			}
		});

		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				showMenu(view);
				return true;
			}

		});

		return view;
	}

	protected void showDetailDialog(int position) {
		DetailNotifyFragment dialog = DetailNotifyFragment.newInstance(
				((WordListItem) mAdapter.getItem(position)).getWordKey(),
				((WordListItem) mAdapter.getItem(position)).getId());
		FragmentManager fragmentManager = getFragmentManager();
		dialog.show(fragmentManager, "Word Detail");
	}

	public void showMenu(View v) {
		PopupMenu popup = new PopupMenu(getActivity(), v);
		popup.setOnMenuItemClickListener(this);
		popup.inflate(R.menu.word_list_context_menu);
		popup.show();
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menuUpdate:
			udpate(item);
			return true;
		case R.id.menuDelete:
			delete(item);
			return true;
		default:
			return false;
		}
	}

	private void udpate(MenuItem item) {
		// TODO

	}

	private void delete(MenuItem item) {
		// TODO

	}

}
