package com.example.myapplication;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    EditText editText;
    EditText editText1;
    ClipboardManager myClipboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.contextEditText1);
        editText1 = (EditText) findViewById(R.id.contextEditText2);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Choose a color");
        menu.add(0, v.getId(), 0, "Copy");
        menu.add(0, v.getId(), 0, "Paste");
        menu.add(0, v.getId(), 0, "Cut");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Copy") {


                    myClipboard =
                    (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
            ClipData myClip;
            myClip = ClipData.newPlainText("text",
                    editText.getText().toString());
            myClipboard.setPrimaryClip(myClip);
        } else if (item.getTitle() == "Paste") {
            ClipData abc = myClipboard.getPrimaryClip();
            ClipData.Item item1 = abc.getItemAt(0);
            String text = item1.getText().toString();
            editText1.setText(text);
        } else if (item.getTitle() == "Cut") {
            myClipboard =
                    (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
            ClipData myClip;
            myClip = ClipData.newPlainText("text",
                    editText.getText().toString());
            editText.setText("");
            myClipboard.setPrimaryClip(myClip);
        }
        return true;
    }
}