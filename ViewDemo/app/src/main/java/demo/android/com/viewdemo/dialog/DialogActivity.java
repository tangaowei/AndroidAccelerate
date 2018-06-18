package demo.android.com.viewdemo.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import demo.android.com.viewdemo.R;

/**
 * Created by tangaowei on 18/5/26.
 */

public class DialogActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_activity_layout);

        findViewById(R.id.dialog1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FireMissilesDialogFragment dialogFragment = new FireMissilesDialogFragment();
                dialogFragment.show(getFragmentManager(), "dialog");
            }
        });

        findViewById(R.id.dialog2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showDeleteDialog();
                final SelfDialog selfDialog = new SelfDialog(DialogActivity.this);
                selfDialog.setTitle("提示");
                selfDialog.setMessage("确定退出应用?");
                selfDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        Toast.makeText(DialogActivity.this,"点击了--确定--按钮",Toast.LENGTH_LONG).show();
                        selfDialog.dismiss();
                    }
                });
                selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        Toast.makeText(DialogActivity.this,"点击了--取消--按钮",Toast.LENGTH_LONG).show();
                        selfDialog.dismiss();
                    }
                });
                selfDialog.show();
            }
        });
    }

    public static class FireMissilesDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("确认删除文件？")
                    .setTitle("提示")
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }

    private void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("确认删除文件");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
