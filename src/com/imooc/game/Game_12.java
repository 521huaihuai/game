package com.imooc.game;

import java.util.LinkedList;

import com.imooc.myBaseGame.CommonGuideGame_00_20;
import com.imooc.myConstant.MyConstant;
import com.imooc.mySnake.Node;
import com.imooc.mySnake.Snake;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_12 extends CommonGuideGame_00_20{

	public Game_12() {
	}

	@Override
	public String[] getGuideString() {
		String[] text = { "在30秒内收集15个" };
		return text;
	}

	@Override
	public int[] getGuideIndexTime() {
		return new int[] { 6 };
	}

	@Override
	public void detailDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight) {
	}

	@Override
	public void detailLogic() {
		if (mCollectionNUM == 15) {
			enterNextCheckPoint("下一关", "在20秒内收集斑点20个");
		}
	}

	@Override
	public long setTimeLimite() {
		return 30000;
	}

	@Override
	public void hpIsOver() {
		Utils.reStartCheckPoint("失败", "注意不要收集自己相克的颜色","否则会降低自己的生命值");
	}
	

	@Override
	public int gameOverPos() {
		return 12;
	}


	@Override
	public void timeIsOver(long usedTime) {
		Utils.reStartCheckPoint("失败", "超时了!","请在30秒内完成哦!");
	}

	@Override
	public Snake getSnake() {
		return new Snake() {
			
			@Override
			public void initBody(LinkedList<Node> mLinkedList) {
				Node node = null;
				for (int i = 0; i < 10; i++) {
					node = new Node(MyConstant.COLOR_BLACK, MainActivity.screenWidth / 2, MainActivity.screenHeight / 2);
					mLinkedList.add(node);
				}
				
			}
			
			@Override
			public int getInitHp() {
				return 15;
			}
		};
	}

	@Override
	public void sameColorCrossHandle(int color) {
		mCollectionNUM++;
	}

	@Override
	public void oppositeColorCrossHandle(int color) {
		mSnake.setHp(mSnake.getCurrentHp() - 5);
	}

	@Override
	public void birthColorCrosshandle(int color) {
		mCollectionNUM++;
	}

	@Override
	public void differentColorCrossHandle(int color) {
		mCollectionNUM++;
	}
}
