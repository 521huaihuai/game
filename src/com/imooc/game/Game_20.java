package com.imooc.game;

import java.util.LinkedList;
import java.util.Vector;

import com.imooc.control.IMoveListener;
import com.imooc.myConstant.MyConstant;
import com.imooc.mySufaceView.MainActivity;
import com.imooc.particle.PieceParticle;
import com.imooc.snake.Node;
import com.imooc.snake.Snake;
import com.imooc.utils.Utils;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game_20 extends GuideCommonGame implements IMoveListener{

	private boolean isStart;
	private boolean isSuccess;
	private boolean isFail;
	public Game_20() {
		setOnMoveListener(this);
	}

	@Override
	public String[] getGuideString() {
		String[] text = { "���������ͣ���ƶ������Ҳ�", "15�����ռ�15��" };
		return text;
	}

	@Override
	public int[] getGuideIndexTime() {
		return new int[] { 3, 3 };
	}

	@Override
	public Vector<PieceParticle> createPartice() {
		PieceParticle.Manager manager = PieceParticle.newInstance();
		return manager.createParticleColors(150,
				new int[] { MyConstant.COLOR_GOLD, MyConstant.COLOR_GREEN, MyConstant.COLOR_BLACK });
	}

	@Override
	public void detailDraw(Canvas canvas, Paint paint, int screenWidth, int screenHeight) {
	}

	@Override
	public void detailLogic() {
		if (mAlpha < 230) {
			isStart = true;
		}
		if (mCollectionNUM >= 15) {
			if (mSnake.getList().getFirst().getX() >= MainActivity.screenWidth) {
				isSuccess = true;
				Utils.enterNextCheckPoint("׼��������?", "���������ǽ�չ�����ӷḻ���淨(��ĺ��� =_=)");
			}
		}
	}

	@Override
	public long setTimeLimite() {
		return 15000;
	}

	@Override
	public void hpIsOver() {
		isFail = true;
		Utils.reStartCheckPoint("ʧ��", "ע�ⲻҪ�ռ��Լ���˵���ɫ", "����ή���Լ�������ֵ");
	}

	@Override
	public int gameOverPos() {
		return 20;
	}

	@Override
	public void timeIsOver(long usedTime) {
		Utils.reStartCheckPoint("ʧ��", "��ʱ��!", "����15�������Ŷ!");
	}

	@Override
	public Snake getSnake() {
		return new Snake() {

			@Override
			public void initBody(LinkedList<Node> mLinkedList) {
				Node node = new Node(MyConstant.COLOR_GREEN, 0, MainActivity.screenHeight / 2);
				for (int i = 0; i < 20; i++) {
					mLinkedList.add(node);
				}
			}

			@Override
			public int getInitHp() {
				return 5;
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

	@Override
	public void actionMOVE(float xm, float ym) {

	}

	@Override
	public void actionDOWN(int x, int y) {

	}

	@Override
	public void actionUP(int x, int y) {
		if (isStart && !isSuccess && !isFail) {
			Utils.reStartCheckPoint("ʧ����", "��ͣ��Ŷ!");
		}
	}

}