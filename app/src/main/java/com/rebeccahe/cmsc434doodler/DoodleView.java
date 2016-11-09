package com.rebeccahe.cmsc434doodler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Stack;

/**
 * TODO: document your custom view class.
 */
public class DoodleView extends View {

    private Paint _paintDoodle = new Paint();
    private Path _path = new Path();

    private int _color = Color.RED;
    private float _size = 2.0f;
    private int _opacity = 255;

    private ArrayList<Paint> _paintDoodles = new ArrayList<Paint>();
    private ArrayList<Path> _paths = new ArrayList<Path>();

    private Stack<Paint> _paintDoodlesUNDO = new Stack<Paint>();
    private Stack<Path> _pathsUNDO = new Stack<Path>();

    public DoodleView(Context context) {
        super(context);
        init(null, 0);
    }

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        _path = new Path();
        _paintDoodle = new Paint();

        _paintDoodle.setAntiAlias(true);
        _paintDoodle.setStyle(Paint.Style.STROKE);

        _paintDoodle.setColor(_color);
        _paintDoodle.setAlpha(_opacity);
        _paintDoodle.setStrokeWidth(_size);
    }

    @Override
    public void onDraw(Canvas canvas) {
       super.onDraw(canvas);

        for(int i = 0; i < _paintDoodles.size(); i++) {
            canvas.drawPath(_paths.get(i), _paintDoodles.get(i));
        }
        canvas.drawPath(_path, _paintDoodle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float touchX = motionEvent.getX();
        float touchY = motionEvent.getY();

        switch(motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                init(null, 0);
                _path.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                _path.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                _paths.add(_path);
                _paintDoodles.add(_paintDoodle);
                break;
        }
        invalidate();
        return true;
    }

    public void clearCanvas() {
        _paintDoodles.clear();
        _paths.clear();
        _path = new Path();
        invalidate();
    }

    public void undo() {
        if (_paintDoodles.size() > 0) {
            Paint paintUndo = _paintDoodles.remove(_paintDoodles.size() - 1);
            Path pathUndo = _paths.remove(_paths.size() - 1);

            _paintDoodlesUNDO.push(paintUndo);
            _pathsUNDO.push(pathUndo);

            _path = new Path();
            invalidate();
        }
    }

    public void redo() {
        if (!_paintDoodlesUNDO.isEmpty()) {
            _paintDoodles.add(_paintDoodlesUNDO.pop());
            _paths.add(_pathsUNDO.pop());

            _path = new Path();
            invalidate();
        }
    }

    public void setBrushSize(float size) {
        _size = (float) size;
    }

    public void setBrushColor(int color) {
        _color = color;
    }

    public void setOpacity(int opacity) {
        _opacity = opacity * 255 / 100;
    }

}
