package com.rebeccahe.cmsc434doodler;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;

public class MainActivity extends AppCompatActivity {

    private com.rebeccahe.cmsc434doodler.DoodleView _doodleView;
    private int _brushSize = 2;
    private int _opacity = 100;

    // TODO: INITIALIZE THESE SOMEWHERE
    private int _red = 0, _green = 0, _blue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _doodleView = (com.rebeccahe.cmsc434doodler.DoodleView) findViewById(R.id.doodle_view);

        final SeekBar seekBar = (SeekBar) findViewById(R.id.seek_bar_size);
        final TextView seekLabel = (TextView) findViewById(R.id.seek_label);

        seekLabel.setText("" + _brushSize);
        seekBar.setProgress(_brushSize);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                _brushSize = progress;
                seekLabel.setText("" + _brushSize);
                _doodleView.setBrushSize(_brushSize);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        final SeekBar seekBarOpacity = (SeekBar) findViewById(R.id.seek_bar_opacity);
        final TextView seekLabelOpacity = (TextView) findViewById(R.id.seek_label_opacity);

        seekLabelOpacity.setText("" + _opacity + "%");
        seekBarOpacity.setProgress(_opacity);

        seekBarOpacity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                _opacity = progress;
                seekLabelOpacity.setText("" + (_opacity) + "%");
                _doodleView.setOpacity(_opacity);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

    }

    public void onClearButtonClick(View v) {
        publishToastMessage("CANVAS CLEARED");
        _doodleView.clearCanvas();
    }

    public void onUndoButtonClick(View v) {
        publishToastMessage("ACTION UNDONE");
        _doodleView.undo();
    }

    public void onRedoButtonClick(View v) {
        publishToastMessage("ACTION REDONE");
        _doodleView.redo();
    }

    public void onColorButtonClick(View v) {
        final ColorPicker colorPicker = new ColorPicker(this, _red, _green, _blue);
        colorPicker.show();

        Button select = (Button) colorPicker.findViewById(R.id.okColorButton);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _red = colorPicker.getRed();
                _green = colorPicker.getGreen();
                _blue = colorPicker.getBlue();

                _doodleView.setBrushColor(colorPicker.getColor());
                colorPicker.dismiss();
            }
        });
    }

    public void publishToastMessage(String message) {
        Context context = getApplicationContext();
        CharSequence text = "Button Clicked: " + message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
