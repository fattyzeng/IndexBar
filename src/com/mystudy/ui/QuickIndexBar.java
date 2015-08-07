package com.mystudy.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 这是一个快速索引的控件 this is a quick index bar that is assistant for user quickly
 * search by character
 * 
 * @author Administrator
 * 
 */
public class QuickIndexBar extends View {

	/**
	 * 每个条目匹配的高度 this is a variable for the item's height
	 */
	private float cellHeight;
	/**
	 * 每个条目匹配的宽度 this is a variable for the item's width
	 */
	private int cellWidth;
	private Paint mpaint;
	/**
	 * 你所需要 画的 字母列表 this is a array variable for drawing character on the bar
	 */
	public static String[] characters = { "A", "B", "C", "D", "E", "F", "G",
			"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z" };

	/**
	 * the last position of the character pressed
	 */
	private int lastindex = -1;
	/**
	 * the is variable for observe while character changed
	 */
	private LetterChangedListener oListener;

	/**
	 * 一个参数的构造函数用以调用两个参数的构造函数 this is one paramter constructor that called two
	 * parameter constructor
	 * 
	 * @param context
	 *            上下文对象的参数 this is a parameter for context
	 */
	public QuickIndexBar(Context context) {
		this(context, null);
	}

	/**
	 * 两个参数的构造函数用以调用三个参数的构造函数 this is two parameter constructor that called
	 * three parameter constructor
	 * 
	 * @param context
	 *            上下文对象参数 this is a parameter for context
	 * @param attrs
	 *            样式列表参数 this is a paramter for attributeset
	 */
	public QuickIndexBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	/**
	 * 三个参数的构造函数 用以数据的初始化 this is three parameter constructor that is used to
	 * initial datas
	 * 
	 * @param context
	 *            上下文对象参数 this is a parameter for context
	 * @param attrs
	 *            样式列表参数 this is a paramter for attributeset
	 * @param defStyle
	 *            默认样式参数 this is a parameter for default style
	 */
	public QuickIndexBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initPaint();
	}

	/**
	 * 初始化画笔信息 this is a function that initial paint
	 */
	private void initPaint() {
		// initial paint for anting alias
		mpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		// set paint color whit
		mpaint.setColor(Color.WHITE);
		// set paint typeface default_bold
		mpaint.setTypeface(Typeface.DEFAULT_BOLD);
		mpaint.setTextSize(28);
	}

	/**
	 * this is a function that get the cell's width and the cell's height
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		// get the cell's width
		this.cellWidth = getMeasuredWidth();
		// get the cell's height
		this.cellHeight = getMeasuredHeight() * 1.0f / this.characters.length;

	}

	/**
	 * this is a function that make the array's character draw on the bar
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		/**
		 * make the character's character draw on the bar the x-axis point is
		 * cellwidth/2-measuredwidth/2 the y-axix point is
		 * cellheight/2+measuredheight/2+index*cellheight
		 */
		for (int i = 0; i < characters.length; i++) {
			String character = characters[i];
			int measureWidth = (int) mpaint.measureText(character);
			Rect rect = new Rect();
			mpaint.getTextBounds(character, 0, character.length(), rect);
			int measureHeight = rect.height();
			// if you presses the character that the character's color changed
			// gray otherwise the character's color change white
			mpaint.setColor(lastindex == i ? Color.GRAY : Color.WHITE);
			canvas.drawText(character, cellWidth / 2 - measureWidth / 2,
					cellHeight / 2 + measureHeight / 2 + cellHeight * i, mpaint);
		}
	}

	/**
	 * this is a function that process touch event
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// the local variable for record position of character when user pressed
		int index = -1;
		switch (event.getAction()) {
		// do sth while action_down
		case MotionEvent.ACTION_DOWN:
			dosth(event);
			break;
		// do sth while action_move
		case MotionEvent.ACTION_MOVE:
			dosth(event);
			break;
		// do sth while action_up
		case MotionEvent.ACTION_UP:
			lastindex = -1;
			break;
		default:
			break;
		}
		return true;
	}

	/**
	 * do sth while process touch event
	 * 
	 * @param event
	 */
	private void dosth(MotionEvent event) {
		int index;
		index = (int) (event.getY() / cellHeight);
		if (index != lastindex) {
			if (oListener != null) {
				oListener.OnLetterChanged(characters[index]);
			}

			lastindex = index;
		}
	}

	public LetterChangedListener getoListener() {
		return oListener;
	}

	public void setoListener(LetterChangedListener oListener) {
		this.oListener = oListener;
	}

	/**
	 * the interface that observer while the character change
	 */
	public interface LetterChangedListener {
		/**
		 * this is a function that observe while the character change
		 * 
		 * @param character
		 *            the parameter changed character
		 */
		void OnLetterChanged(String character);
	}

}
