package com.example.fontkeyboard;

import android.inputmethodservice.InputMethodService;
import android.view.View;
import android.inputmethodservice.InputConnection;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Typeface;
import java.io.File;

public class CustomFontKeyboardService extends InputMethodService {

    private View keyboardRootView;
    private Button btnAddTtf;
    private Button btnSampleKey;
    private TextView txtStatus;
    private Typeface customFont;

    @Override
    public View onCreateInputView() {
        keyboardRootView = getLayoutInflater().inflate(R.layout.keyboard_view, null);

        btnAddTtf = keyboardRootView.findViewById(R.id.btn_add_ttf);
        btnSampleKey = keyboardRootView.findViewById(R.id.btn_sample_key);
        txtStatus = keyboardRootView.findViewById(R.id.txt_status);

        btnAddTtf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCustomFont();
            }
        });

        btnSampleKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputConnection ic = getCurrentInputConnection();
                if (ic != null) {
                    ic.commitText("A", 1);
                }
            }
        });

        return keyboardRootView;
    }

    private void loadCustomFont() {
        try {
            File fontFile = new File("/sdcard/Download/myfont.ttf");
            if (fontFile.exists()) {
                customFont = Typeface.createFromFile(fontFile);
                btnSampleKey.setTypeface(customFont);
                txtStatus.setText("Font Loaded!");
            } else {
                txtStatus.setText("Put 'myfont.ttf' in Download");
            }
        } catch (Exception e) {
            txtStatus.setText("Error loading font");
        }
    }
}

