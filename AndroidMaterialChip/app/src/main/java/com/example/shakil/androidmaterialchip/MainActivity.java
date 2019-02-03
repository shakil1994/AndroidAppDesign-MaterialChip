package com.example.shakil.androidmaterialchip;

import android.support.design.button.MaterialButton;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Chip chip;
    MaterialButton btn_add_tag, btn_get_selected;
    ChipGroup chipGroup;
    TextInputEditText textInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add_tag = findViewById(R.id.btn_add_tag);
        btn_get_selected = findViewById(R.id.btn_show_selected);
        chipGroup = findViewById(R.id.chip_group);
        textInputEditText = findViewById(R.id.input);

        //When client add tag , we will split text from input to tags form
        btn_add_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] tags = textInputEditText.getText().toString().split(" ");
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                for (String text : tags) {
                    chip = (Chip) inflater.inflate(R.layout.chip_item, null, false);
                    chip.setText(text);
                    chip.setOnCloseIconClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chipGroup.removeView(v);
                        }
                    });

                    //Add the group
                    chipGroup.addView(chip);
                }
            }
        });

        //When click get selected ,we will show all tags has been checked
        btn_get_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < chipGroup.getChildCount(); i++) {
                    chip = (Chip) chipGroup.getChildAt(i);
                    if (chip.isChecked()) {
                        if (i < chipGroup.getChildCount() - 1) {
                            result.append(chip.getText()).append(",");
                        } else {
                            result.append(chip.getText());
                        }
                    }
                }
                Toast.makeText(MainActivity.this, "" + result.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
