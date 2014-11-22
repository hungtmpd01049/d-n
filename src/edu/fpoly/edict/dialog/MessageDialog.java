package edu.fpoly.edict.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class MessageDialog {
	boolean isOk = false;

	public boolean showConfirmDialog(Context context, String title,
			String message, String positiveButtonTitle,
			String negativeButtonTitle, boolean isCancelable,
			DialogInterface.OnClickListener onPositiveHandler) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
		alertDialogBuilder.setTitle(title);
		alertDialogBuilder
				.setMessage(message)
				.setCancelable(isCancelable)
				.setPositiveButton(positiveButtonTitle, onPositiveHandler)
				.setNegativeButton(negativeButtonTitle,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								isOk = false;
								dialog.cancel();
							}
						});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();

		return isOk;
	}

	public void showAlertDialog(Context context, String title, String message,
			String positiveButtonTitle, boolean isCancelable) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
		alertDialogBuilder.setTitle(title);
		alertDialogBuilder
				.setMessage(message)
				.setCancelable(isCancelable)
				.setPositiveButton(positiveButtonTitle,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								isOk = true;
								dialog.cancel();
							}
						});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
}
