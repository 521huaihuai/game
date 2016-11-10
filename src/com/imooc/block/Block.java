package com.imooc.block;

import com.imooc.snake.Node;

import android.graphics.Bitmap;

public abstract class Block
{

	// �ϰ�����ɫ
	private int color;
	private Bitmap smallBitmap;
	private int mPadding = 10;


	public int getColor()
	{
		return color;
	}

	public abstract void crossSafeBlock(int color2);

	public abstract void touchDengerousBlock(int color2);

	public abstract void crossNiceBlock(int color2);

	public abstract void touchNormalBlock(int color2);

	/**
	 * �ж��Ƿ����߽���
	 * 
	 * @param firstNode
	 * @return <code>True</code> if it touchs block
	 */
	public boolean judgeIsInBlock(Node firstNode)
	{
		
		return false;
	}

}
