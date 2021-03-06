package com.imooc.game;

import java.util.LinkedList;
import java.util.Vector;

import com.imooc.control.IMoveListener;
import com.imooc.gameMenu.SimpleGameMenuFail;
import com.imooc.myBaseGame.CommonGame_00_20;
import com.imooc.myConstant.MyConstant;
import com.imooc.myParticle.ParticleManager;
import com.imooc.myParticle.PieceParticle;
import com.imooc.mySnake.Node;
import com.imooc.mySnake.Snake;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.mySufaceView.MyAplication;
import com.imooc.utils.Utils;
import com.imooc.utils.Utils.Position;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_4 extends CommonGame_00_20 implements IMoveListener
{

	private float alpha = 255;
	private float decreaseAlpha;
	private String text;
	private boolean isStart;
	private boolean isSuccess;
	private boolean isOver;


	public Game_4()
	{
		decreaseAlpha = Utils.alphaDecreaseInNearBytime(6);
		isStart = false;
		isOver = false;
		text = "尝试一下从最左侧无碰撞的移动到最右端,中途不能停顿~";
		setOnMoveListener(this);
	}

	@Override
	public long setTimeLimite()
	{
		return 111111110;
	}

	@Override
	public Snake getSnake()
	{
		return new Snake()
		{

			@Override
			public void initBody(LinkedList<Node> mLinkedList)
			{
				Node node = new Node(MyConstant.COLOR_BLACK, 0, MainActivity.screenHeight / 2);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
				mLinkedList.add(node);
			}

			@Override
			public int getInitHp()
			{
				return 0;
			}
		};
	}

	@Override
	public int gameOverPos()
	{
		return 4;
	}

	@Override
	protected Vector<PieceParticle> createPartice()
	{
		ParticleManager manager = ParticleManager.newInstance();
		return manager.createParticle(200);
	}

	@Override
	public void childDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight)
	{
		Utils.drawAlphaText(Position.CEN_UP, canvas, text, paint, alpha);
		if (alpha < 230)
		{
			isStart = true;
		}
	}

	@Override
	public void childLogic()
	{
		if (mCollectionNUM > 0)
		{
			isOver = true;
			try
			{
				Thread.sleep(500);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			MyAplication.getSurfaceView().setOnISurfaceViewCallBack(new SimpleGameMenuFail("抱歉, 你与斑点不期而遇了~~", "不能与任何斑点有接触"));
		}
		if (alpha > 0)
		{
			alpha -= decreaseAlpha;
		}
		if (mSnake.getList().getFirst().getX() >= MainActivity.screenWidth)
		{
			isSuccess = true;
			try
			{
				Thread.sleep(500);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			enterNextCheckPoint("你成功到了", "所用时间 : " + getUsedTime(), "任务 : 很好, 你的控制力还是不错的, 接下来考考你的脑力了");
		}
	}

	@Override
	public void sameColorCrossHandle(int color)
	{
		mCollectionNUM++;
	}

	@Override
	public void oppositeColorCrossHandle(int color)
	{
		mCollectionNUM++;
	}

	@Override
	public void differentColorCrossHandle(int color)
	{
		mCollectionNUM++;
	}

	@Override
	public void birthColorCrosshandle(int color)
	{
		mCollectionNUM++;
	}

	@Override
	public void actionMOVE(float xm, float ym)
	{
	}

	@Override
	public void actionDOWN(int x, int y)
	{
	}

	@Override
	public void actionUP(int x, int y)
	{
		if (isStart && !isSuccess)
		{
			if (!isOver)
			{
				try
				{
					Thread.sleep(500);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				MyAplication.getSurfaceView().setOnISurfaceViewCallBack(new SimpleGameMenuFail("抱歉, 你中途停顿了~~", "尝试一下从最左侧无碰撞的移动到最右端,中途无停顿哟~"));
			}
		}
	}

	@Override
	public void hpIsOver()
	{
	}

	@Override
	public void timeIsOver(long usedTime)
	{
		// TODO Auto-generated method stub

	}


}
