package com.hienqp.cuocduakythu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textViewScore;
    CheckBox checkBoxOne, checkBoxTwo, checkBoxThree;
    SeekBar seekBarOne, seekBarTwo, seekBarThree;
    ImageButton imageButtonPlay;
    int score = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        associateViews();

        textViewScore.setText(String.valueOf(score));

        final CountDownTimer countDownTimer = new CountDownTimer(60000, 50) {
            @Override
            public void onTick(long millisUntilFinished) {
                int boundRandom = 2;
                Random random = new Random();
                int random1 = random.nextInt(boundRandom);
                int random2 = random.nextInt(boundRandom);
                int random3 = random.nextInt(boundRandom);

                // kiểm tra WIN ONE
                if (seekBarOne.getProgress() >= seekBarOne.getMax()) {
                    // hủy đối tượng countDownTimer này
                    this.cancel();
                    imageButtonPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "ONE WIN", Toast.LENGTH_SHORT).show();

                    // kiểm tra đặt cược
                    if (checkBoxOne.isChecked()) {
                        score += 10;
                        Toast.makeText(MainActivity.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                        enableCheckBox();
                    } else {
                        score -= 5;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai", Toast.LENGTH_SHORT).show();
                    }
                    textViewScore.setText(String.valueOf(score));
                }
                // kiểm trả WIN TWO
                if (seekBarTwo.getProgress() >= seekBarTwo.getMax()) {
                    // hủy đối tượng countDownTimer này
                    this.cancel();
                    imageButtonPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "TWO WIN", Toast.LENGTH_SHORT).show();

                    // kiểm tra đặt cược
                    if (checkBoxTwo.isChecked()) {
                        score += 10;
                        Toast.makeText(MainActivity.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                        enableCheckBox();
                    } else {
                        score -= 5;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai", Toast.LENGTH_SHORT).show();
                    }
                    textViewScore.setText(String.valueOf(score));
                }
                // kiểm tra WIN THREE
                if (seekBarThree.getProgress() >= seekBarThree.getMax()) {
                    // hủy đối tượng countDownTimer này
                    this.cancel();
                    imageButtonPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "THREE WIN", Toast.LENGTH_SHORT).show();

                    // kiểm tra đặt cược
                    if (checkBoxThree.isChecked()) {
                        score += 10;
                        Toast.makeText(MainActivity.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                        enableCheckBox();
                    } else {
                        score -= 5;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai", Toast.LENGTH_SHORT).show();
                    }
                    textViewScore.setText(String.valueOf(score));
                }

                seekBarOne.setProgress(seekBarOne.getProgress() + random1);
                seekBarTwo.setProgress(seekBarTwo.getProgress() + random2);
                seekBarThree.setProgress(seekBarThree.getProgress() + random3);
            }

            @Override
            public void onFinish() {

            }
        };

        imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxOne.isChecked() || checkBoxTwo.isChecked() || checkBoxThree.isChecked()) {
                    seekBarOne.setProgress(0);
                    seekBarTwo.setProgress(0);
                    seekBarThree.setProgress(0);

                    imageButtonPlay.setVisibility(View.INVISIBLE);
                    countDownTimer.start();

                    disableCheckBox();
                } else {
                    Toast.makeText(MainActivity.this, "Please choose one animal", Toast.LENGTH_SHORT).show();
                }
            }
        });

        checkBoxOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxOne.isChecked()) {
                    checkBoxTwo.setChecked(false);
                    checkBoxThree.setChecked(false);
                }
            }
        });

        checkBoxTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxTwo.isChecked()) {
                    checkBoxOne.setChecked(false);
                    checkBoxThree.setChecked(false);
                }
            }
        });

        checkBoxThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBoxThree.isChecked()) {
                    checkBoxOne.setChecked(false);
                    checkBoxTwo.setChecked(false);
                }
            }
        });
    }

    private void associateViews() {
        textViewScore   = (TextView) findViewById(R.id.textView_DIEMSO);
        checkBoxOne     = (CheckBox) findViewById(R.id.checkbox1);
        checkBoxTwo     = (CheckBox) findViewById(R.id.checkbox2);
        checkBoxThree   = (CheckBox) findViewById(R.id.checkbox3);
        seekBarOne      = (SeekBar) findViewById(R.id.seekbar1);
        seekBarTwo      = (SeekBar) findViewById(R.id.seekbar2);
        seekBarThree    = (SeekBar) findViewById(R.id.seekbar3);
        imageButtonPlay = (ImageButton) findViewById(R.id.imageButton_CHAY);

        // hạn chế người dùng tác động đến SeekBar
        seekBarOne.setEnabled(false);
        seekBarTwo.setEnabled(false);
        seekBarThree.setEnabled(false);
    }

    private void enableCheckBox() {
        checkBoxOne.setEnabled(true);
        checkBoxTwo.setEnabled(true);
        checkBoxThree.setEnabled(true);
    }

    private void disableCheckBox() {
        checkBoxOne.setEnabled(false);
        checkBoxTwo.setEnabled(false);
        checkBoxThree.setEnabled(false);
    }
}