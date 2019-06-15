package com.example.huarongdao;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class GameActivity extends AppCompatActivity {

    private RelativeLayout mRLayout;
    private static TextView stepCount;

    public static GameChess cao;
    public static GameChess guan;
    public static GameChess zhang;
    public static GameChess zhao;
    public static GameChess huang;
    public static GameChess ma;
    public static GameChess zu1;
    public static GameChess zu2;
    public static GameChess zu3;
    public static GameChess zu4;

    private static int size; //棋子的尺寸

    public static int mCount; //步数

    public static Context mContext;
    public static GameActivity mActivity;

    private class Mode {
        //1到10分别代表曹、关、张、赵、黄、马、卒1、卒2、卒3、卒4
        public int x1, x2, x3, x4, x5, x6, x7, x8, x9, x10;
        public int y1, y2, y3, y4, y5, y6, y7, y8, y9, y10;

        public Mode(int x1, int x2, int x3, int x4, int x5, int x6, int x7, int x8, int x9, int x10,
                     int y1, int y2, int y3, int y4, int y5, int y6, int y7, int y8, int y9, int y10) {
            this.x1 = x1; this.x2 = x2; this.x3 = x3; this.x4 = x4; this.x5 = x5;
            this.x6 = x6; this.x7 = x7; this.x8 = x8; this.x9 = x9; this.x10 = x10;
            this.y1 = y1; this.y2 = y2; this.y3 = y3; this.y4 = y4; this.y5 = y5;
            this.y6 = y6; this.y7 = y7; this.y8 = y8; this.y9 = y9; this.y10 = y10;
        }
    }

    public static class Step {
        public GameChess chess;
        public int beforX, beforY, afterX, afterY;

        public Step(GameChess g, int beforX, int beforY, int afterX, int afterY) {
            this.chess = g;
            this.beforX = beforX;
            this.beforY = beforY;
            this.afterX = afterX;
            this.afterY = afterY;
        }
    }

    private LinkedList<Mode> mMode = new LinkedList<>();
    public static LinkedList<Step> mStep = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mContext = this.getBaseContext();
        mActivity = this;

        mRLayout = findViewById(R.id.game_ground);
//        mRLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        size = dm.widthPixels / 5 + 4;

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(size*4, size*5);
        params.gravity = Gravity.CENTER;
        params.setMargins(0,size,0,0);
        mRLayout.setLayoutParams(params);
        mRLayout.setPadding(4,4,0,0);

        mCount = 0;

        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);
        String modeName = intent.getStringExtra("mode");
        this.setTitle(modeName);
//        Toast.makeText(GameActivity.mContext, position, Toast.LENGTH_SHORT).show();

        initMode();

        createChess(position);

        addChess(cao);
        addChess(guan);
        addChess(zhang);
        addChess(zhao);
        addChess(huang);
        addChess(ma);
        addChess(zu1);
        addChess(zu2);
        addChess(zu3);
        addChess(zu4);

        addButtons();

    }

    private void initMode() {
        mMode.addLast(new Mode (0,2,0,1,2,3,0,1,2,3,
                                2,2,0,0,0,0,4,4,4,4));
        mMode.addLast(new Mode (1,1,0,3,0,3,0,1,2,3,
                                0,2,0,0,2,2,4,3,3,4));
        mMode.addLast(new Mode (1,1,0,0,3,3,0,1,2,3,
                                0,4,1,3,1,3,0,3,3,0));
        mMode.addLast(new Mode (1,1,0,1,2,3,0,0,3,3,
                                0,2,2,3,3,2,0,1,0,1));
        mMode.addLast(new Mode (1,0,0,0,2,3,1,1,2,3,
                                1,0,1,3,3,3,3,4,0,0));
        mMode.addLast(new Mode (1,1,0,1,2,3,0,0,3,3,
                                1,0,3,3,3,3,1,2,1,2));
        mMode.addLast(new Mode (1,0,0,1,2,3,0,2,3,3,
                                2,4,0,0,0,0,3,4,3,4));
        mMode.addLast(new Mode (1,1,0,0,3,3,1,0,2,3,
                                0,2,0,3,0,3,3,2,3,2));
        mMode.addLast(new Mode (1,1,0,0,3,3,0,1,2,3,
                                0,3,0,3,0,3,2,2,2,2));
        mMode.addLast(new Mode (0,0,0,1,2,3,2,2,3,3,
                                0,2,3,3,0,0,2,3,2,3));
    }

    private void createChess(int i) {
        cao = new GameChess(this, mMode.get(i).x1, mMode.get(i).y1, "曹操" ,size);
        guan = new GameChess(this, mMode.get(i).x2, mMode.get(i).y2, "关羽" ,size);
        zhang = new GameChess(this, mMode.get(i).x3, mMode.get(i).y3, "张飞" ,size);
        zhao = new GameChess(this, mMode.get(i).x4, mMode.get(i).y4, "赵云" ,size);
        huang = new GameChess(this, mMode.get(i).x5, mMode.get(i).y5, "黄忠" ,size);
        ma = new GameChess(this, mMode.get(i).x6, mMode.get(i).y6, "马超" ,size);
        zu1 = new GameChess(this, mMode.get(i).x7, mMode.get(i).y7, "卒" ,size);
        zu2 = new GameChess(this, mMode.get(i).x8, mMode.get(i).y8, "卒" ,size);
        zu3 = new GameChess(this, mMode.get(i).x9, mMode.get(i).y9, "卒" ,size);
        zu4 = new GameChess(this, mMode.get(i).x10, mMode.get(i).y10, "卒" ,size);
    }

    private void addChess(final GameChess g) {
        setChess(g);
        g.setTextSize(20);
        switch (g.name) {
            case "曹操":
                g.setBackgroundResource(R.drawable.cao);
                break;
            case "关羽":
                g.setBackgroundResource(R.drawable.guan);
                break;
            case "张飞":
                g.setBackgroundResource(R.drawable.zhang);
                break;
            case "赵云":
                g.setBackgroundResource(R.drawable.zhao);
                break;
            case "黄忠":
                g.setBackgroundResource(R.drawable.huang);
                break;
            case "马超":
                g.setBackgroundResource(R.drawable.ma);
                break;
            case "卒":
                g.setBackgroundResource(R.drawable.ball);
                break;
            default:
                g.setBackgroundResource(R.drawable.white_wood2);
                break;
        }

        mRLayout.addView(g);
    }

    private static void setChess(final GameChess g) {
        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(size * g.width - 4, size * g.height - 4);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.setMargins(size*g.x,size*g.y,0,0);
        g.setLayoutParams(params);
    }

    public void addButtons() {
        RelativeLayout mCountLayout = findViewById(R.id.count_layout);
        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(size*4, size*3/4);
        p.gravity = Gravity.CENTER;
        p.setMargins(0, 32, 0, 0);
        mCountLayout.setLayoutParams(p);
        //  步数显示
        stepCount = findViewById(R.id.step_count);
        Button undoButton = findViewById(R.id.undo_button);
        Button restartButton = findViewById(R.id.restart_button);

        RelativeLayout.LayoutParams pStepCount =
                new RelativeLayout.LayoutParams(size*2, size*3/4);
        pStepCount.setMargins(size, size/6, 0, 0);
        stepCount.setLayoutParams(pStepCount);
        stepCount.setTextColor(getResources().getColor(R.color.colorWhite));
        stepCount.setText("步数："+mCount);
        stepCount.setTextSize(30);
        stepCount.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        //  回退按钮
        RelativeLayout.LayoutParams pUndo =
                new RelativeLayout.LayoutParams(size, size*3/4);
        pUndo.setMargins(size*3, 0, 0, 0);
        undoButton.setLayoutParams(pUndo);
        undoButton.setTextSize(20);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCount > 0) {
                    Step step = mStep.pop();
//                    Toast.makeText(GameActivity.mContext, step.chessId.toString(), Toast.LENGTH_SHORT).show();
                    ViewGroup.MarginLayoutParams p =
                            (ViewGroup.MarginLayoutParams) step.chess.getLayoutParams();
                    p.setMargins(step.beforX, step.beforY, p.rightMargin, p.bottomMargin);
                    step.chess.setLayoutParams(p);
                    mCount--;
                    stepCount.setText("步数："+mCount);
                }
            }
        });
        //  重置按钮
        RelativeLayout.LayoutParams pRestart =
                new RelativeLayout.LayoutParams(size, size*3/4);
        pRestart.setMargins(0, 0, 0, 0);
        restartButton.setLayoutParams(pRestart);
        restartButton.setTextSize(20);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChess(cao);
                setChess(guan);
                setChess(zhang);
                setChess(zhao);
                setChess(huang);
                setChess(ma);
                setChess(zu1);
                setChess(zu2);
                setChess(zu3);
                setChess(zu4);
                mCount = 0;
                stepCount.setText("步数："+mCount);
            }
        });
    }

    public static void addCount() {
        mCount++;
        stepCount.setText("步数："+mCount);
    }

    public static void winGame() {
        AlertDialog.Builder myAlertBuilder = new
                AlertDialog.Builder(GameActivity.mActivity);
        myAlertBuilder.setTitle("胜利！");
        myAlertBuilder.setMessage("恭喜你获得游戏的胜利！");
        myAlertBuilder.setPositiveButton("重玩", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        setChess(cao);
                        setChess(guan);
                        setChess(zhang);
                        setChess(zhao);
                        setChess(huang);
                        setChess(ma);
                        setChess(zu1);
                        setChess(zu2);
                        setChess(zu3);
                        setChess(zu4);
                        mCount = 0;
                        stepCount.setText("步数："+mCount);
                    }
                });
        myAlertBuilder.setNegativeButton("选择关卡", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mActivity.finish();
                    }
                });
        myAlertBuilder.show();
    }

}
