package com.example.huarongdao;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class GameChess extends android.support.v7.widget.AppCompatButton {

    public int x;
    public int y;
    public int width;
    public int height;
    public String name;
    private int size;

    private float beforeX;
    private float beforeY;
    private int beforeLeft;
    private int beforeTop;

    public GameChess(Context context, int x, int y, String type, int size) {
        super(context);
        this.x = x;
        this.y = y;
        this.size = size;
        switch (type) {
            case "曹操":
                this.width =  2;
                this.height =  2;
                this.name = "曹操";
                break;
            case "关羽":
                this.width = 2;
                this.height = 1;
                this.name = "关羽";
                break;
            case "张飞":
                this.width = 1;
                this.height = 2;
                this.name = "张飞";
                break;
            case "赵云":
                this.width = 1;
                this.height = 2;
                this.name = "赵云";
                break;
            case "黄忠":
                this.width = 1;
                this.height = 2;
                this.name = "黄忠";
                break;
            case "马超":
                this.width = 1;
                this.height = 2;
                this.name = "马超";
                break;
            case "卒":
                this.width = 1;
                this.height = 1;
                this.name = "卒";
                break;
            default:
                this.width = 0;
                this.height = 0;
                this.name = "错误！";
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                beforeX = event.getX();
                beforeY = event.getY();

                ViewGroup.MarginLayoutParams p =
                        (ViewGroup.MarginLayoutParams) this.getLayoutParams();
                beforeLeft = p.leftMargin;
                beforeTop = p.topMargin;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = event.getX() - beforeX;
                float dy = event.getY() - beforeY;
                if (Math.abs(dx) > Math.abs(dy)) {
                    if (dx > size / 2 && canGoRight()) {
                        goRight();
                    } else if (dx < size / -2 && canGoLeft()) {
                        goLeft();
                    }
                } else {
                    if (dy > size / 2 && canGoDown()) {
                        goDown();
                    } else if (dy < size / -2 && canGoUp()) {
                        goUp();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                ViewGroup.MarginLayoutParams p2 =
                        (ViewGroup.MarginLayoutParams) this.getLayoutParams();
                if (beforeLeft != p2.leftMargin || beforeTop != p2.topMargin) {
                    GameActivity.addCount();
                    GameActivity.mStep.addFirst(new GameActivity.Step(
                            this, beforeLeft, beforeTop, p2.leftMargin, p2.topMargin));
                }
                if (isWin()) {
//                    Toast.makeText(GameActivity.mContext, "WIN!", Toast.LENGTH_SHORT).show();
                    GameActivity.mActivity.winGame();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }

        return true;
    }

    private boolean isWin() {
        ViewGroup.MarginLayoutParams pCao =
                (ViewGroup.MarginLayoutParams) GameActivity.cao.getLayoutParams();
        return pCao.leftMargin == size && pCao.topMargin ==size * 3;
    }

    private void goLeft() {
        ViewGroup.MarginLayoutParams p =
                (ViewGroup.MarginLayoutParams) this.getLayoutParams();
        p.setMargins(p.leftMargin - size, p.topMargin, p.rightMargin, p.bottomMargin);
        this.setLayoutParams(p);
    }

    private void goRight() {
        ViewGroup.MarginLayoutParams p =
                (ViewGroup.MarginLayoutParams) this.getLayoutParams();
        p.setMargins(p.leftMargin + size, p.topMargin, p.rightMargin, p.bottomMargin);
        this.setLayoutParams(p);
    }

    private void goUp() {
        ViewGroup.MarginLayoutParams p =
                (ViewGroup.MarginLayoutParams) this.getLayoutParams();
        p.setMargins(p.leftMargin, p.topMargin - size, p.rightMargin, p.bottomMargin);
        this.setLayoutParams(p);
    }

    private void goDown() {
        ViewGroup.MarginLayoutParams p =
                (ViewGroup.MarginLayoutParams) this.getLayoutParams();
        p.setMargins(p.leftMargin, p.topMargin + size, p.rightMargin, p.bottomMargin);
        this.setLayoutParams(p);
    }

    private boolean canGoLeft() {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
        ViewGroup.MarginLayoutParams pCao =
                (ViewGroup.MarginLayoutParams) GameActivity.cao.getLayoutParams();
        ViewGroup.MarginLayoutParams pGuan =
                (ViewGroup.MarginLayoutParams) GameActivity.guan.getLayoutParams();
        ViewGroup.MarginLayoutParams pZhang =
                (ViewGroup.MarginLayoutParams) GameActivity.zhang.getLayoutParams();
        ViewGroup.MarginLayoutParams pZhao =
                (ViewGroup.MarginLayoutParams) GameActivity.zhao.getLayoutParams();
        ViewGroup.MarginLayoutParams pHuang =
                (ViewGroup.MarginLayoutParams) GameActivity.huang.getLayoutParams();
        ViewGroup.MarginLayoutParams pMa =
                (ViewGroup.MarginLayoutParams) GameActivity.ma.getLayoutParams();
        ViewGroup.MarginLayoutParams pZu1 =
                (ViewGroup.MarginLayoutParams) GameActivity.zu1.getLayoutParams();
        ViewGroup.MarginLayoutParams pZu2 =
                (ViewGroup.MarginLayoutParams) GameActivity.zu2.getLayoutParams();
        ViewGroup.MarginLayoutParams pZu3 =
                (ViewGroup.MarginLayoutParams) GameActivity.zu3.getLayoutParams();
        ViewGroup.MarginLayoutParams pZu4 =
                (ViewGroup.MarginLayoutParams) GameActivity.zu4.getLayoutParams();
        int left = params.leftMargin - size;
        int top = params.topMargin;
        int bottom = params.topMargin + size * height;
        int tCao = pCao.topMargin;
        int tGuan = pGuan.topMargin;
        int tZhang = pZhang.topMargin;
        int tZhao = pZhao.topMargin;
        int tHuang = pHuang.topMargin;
        int tMa = pMa.topMargin;
        int tZu1 = pZu1.topMargin;
        int tZu2 = pZu2.topMargin;
        int tZu3 = pZu3.topMargin;
        int tZu4 = pZu4.topMargin;
        return params.leftMargin > 0
                && (tCao < top - size||tCao >= bottom||pCao.leftMargin != left - size)
                && (tGuan < top||tGuan >= bottom||pGuan.leftMargin != left - size)
                && (tZhang < top - size||tZhang >= bottom||pZhang.leftMargin != left)
                && (tZhao < top - size||tZhao >= bottom||pZhao.leftMargin != left)
                && (tHuang < top - size||tHuang >= bottom||pHuang.leftMargin != left)
                && (tMa < top - size||tMa >= bottom||pMa.leftMargin != left)
                && (tZu1 < top||tZu1 >= bottom||pZu1.leftMargin != left)
                && (tZu2 < top||tZu2 >= bottom||pZu2.leftMargin != left)
                && (tZu3 < top||tZu3 >= bottom||pZu3.leftMargin != left)
                && (tZu4 < top||tZu4 >= bottom||pZu4.leftMargin != left);
    }

    private boolean canGoRight() {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
        ViewGroup.MarginLayoutParams pCao =
                (ViewGroup.MarginLayoutParams) GameActivity.cao.getLayoutParams();
        ViewGroup.MarginLayoutParams pGuan =
                (ViewGroup.MarginLayoutParams) GameActivity.guan.getLayoutParams();
        ViewGroup.MarginLayoutParams pZhang =
                (ViewGroup.MarginLayoutParams) GameActivity.zhang.getLayoutParams();
        ViewGroup.MarginLayoutParams pZhao =
                (ViewGroup.MarginLayoutParams) GameActivity.zhao.getLayoutParams();
        ViewGroup.MarginLayoutParams pHuang =
                (ViewGroup.MarginLayoutParams) GameActivity.huang.getLayoutParams();
        ViewGroup.MarginLayoutParams pMa =
                (ViewGroup.MarginLayoutParams) GameActivity.ma.getLayoutParams();
        ViewGroup.MarginLayoutParams pZu1 =
                (ViewGroup.MarginLayoutParams) GameActivity.zu1.getLayoutParams();
        ViewGroup.MarginLayoutParams pZu2 =
                (ViewGroup.MarginLayoutParams) GameActivity.zu2.getLayoutParams();
        ViewGroup.MarginLayoutParams pZu3 =
                (ViewGroup.MarginLayoutParams) GameActivity.zu3.getLayoutParams();
        ViewGroup.MarginLayoutParams pZu4 =
                (ViewGroup.MarginLayoutParams) GameActivity.zu4.getLayoutParams();
        int right = params.leftMargin + size * width;
        int top = params.topMargin;
        int bottom = params.topMargin + size * height;
        int tCao = pCao.topMargin;
        int tGuan = pGuan.topMargin;
        int tZhang = pZhang.topMargin;
        int tZhao = pZhao.topMargin;
        int tHuang = pHuang.topMargin;
        int tMa = pMa.topMargin;
        int tZu1 = pZu1.topMargin;
        int tZu2 = pZu2.topMargin;
        int tZu3 = pZu3.topMargin;
        int tZu4 = pZu4.topMargin;
        boolean k = (tCao < top - size||tCao >= bottom||pCao.leftMargin != right)
                && (tGuan < top||tGuan >= bottom||pGuan.leftMargin != right)
                && (tZhang < top - size||tZhang >= bottom||pZhang.leftMargin != right)
                && (tZhao < top - size||tZhao >= bottom||pZhao.leftMargin != right)
                && (tHuang < top - size||tHuang >= bottom||pHuang.leftMargin != right)
                && (tMa < top - size||tMa >= bottom||pMa.leftMargin != right)
                && (tZu1 < top||tZu1 >= bottom||pZu1.leftMargin != right)
                && (tZu2 < top||tZu2 >= bottom||pZu2.leftMargin != right)
                && (tZu3 < top||tZu3 >= bottom||pZu3.leftMargin != right)
                && (tZu4 < top||tZu4 >= bottom||pZu4.leftMargin != right);
        if (this.name.equals("曹操")||this.name.equals("关羽")) {
            return params.leftMargin < size * 2 && k;
        } else {
            return params.leftMargin < size * 3 && k;
        }
    }

    private boolean canGoUp() {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
        ViewGroup.MarginLayoutParams pCao =
                (ViewGroup.MarginLayoutParams) GameActivity.cao.getLayoutParams();
        ViewGroup.MarginLayoutParams pGuan =
                (ViewGroup.MarginLayoutParams) GameActivity.guan.getLayoutParams();
        ViewGroup.MarginLayoutParams pZhang =
                (ViewGroup.MarginLayoutParams) GameActivity.zhang.getLayoutParams();
        ViewGroup.MarginLayoutParams pZhao =
                (ViewGroup.MarginLayoutParams) GameActivity.zhao.getLayoutParams();
        ViewGroup.MarginLayoutParams pHuang =
                (ViewGroup.MarginLayoutParams) GameActivity.huang.getLayoutParams();
        ViewGroup.MarginLayoutParams pMa =
                (ViewGroup.MarginLayoutParams) GameActivity.ma.getLayoutParams();
        ViewGroup.MarginLayoutParams pZu1 =
                (ViewGroup.MarginLayoutParams) GameActivity.zu1.getLayoutParams();
        ViewGroup.MarginLayoutParams pZu2 =
                (ViewGroup.MarginLayoutParams) GameActivity.zu2.getLayoutParams();
        ViewGroup.MarginLayoutParams pZu3 =
                (ViewGroup.MarginLayoutParams) GameActivity.zu3.getLayoutParams();
        ViewGroup.MarginLayoutParams pZu4 =
                (ViewGroup.MarginLayoutParams) GameActivity.zu4.getLayoutParams();
        int top = params.topMargin - size;
        int left = params.leftMargin;
        int right = params.leftMargin + size * width;
        int lCao = pCao.leftMargin;
        int lGuan = pGuan.leftMargin;
        int lZhang = pZhang.leftMargin;
        int lZhao = pZhao.leftMargin;
        int lHuang = pHuang.leftMargin;
        int lMa = pMa.leftMargin;
        int lZu1 = pZu1.leftMargin;
        int lZu2 = pZu2.leftMargin;
        int lZu3 = pZu3.leftMargin;
        int lZu4 = pZu4.leftMargin;
        return params.topMargin > 0
                && (lCao < left - size||lCao >= right||pCao.topMargin != top - size)
                && (lGuan < left - size||lGuan >= right||pGuan.topMargin != top)
                && (lZhang < left||lZhang >= right||pZhang.topMargin != top - size)
                && (lZhao < left||lZhao >= right||pZhao.topMargin != top - size)
                && (lHuang < left||lHuang >= right||pHuang.topMargin != top - size)
                && (lMa < left||lMa >= right||pMa.topMargin != top - size)
                && (lZu1 < left||lZu1 >= right||pZu1.topMargin != top)
                && (lZu2 < left||lZu2 >= right||pZu2.topMargin != top)
                && (lZu3 < left||lZu3 >= right||pZu3.topMargin != top)
                && (lZu4 < left||lZu4 >= right||pZu4.topMargin != top);
    }

    private boolean canGoDown() {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) this.getLayoutParams();
        ViewGroup.MarginLayoutParams pCao =
                (ViewGroup.MarginLayoutParams) GameActivity.cao.getLayoutParams();
        ViewGroup.MarginLayoutParams pGuan =
                (ViewGroup.MarginLayoutParams) GameActivity.guan.getLayoutParams();
        ViewGroup.MarginLayoutParams pZhang =
                (ViewGroup.MarginLayoutParams) GameActivity.zhang.getLayoutParams();
        ViewGroup.MarginLayoutParams pZhao =
                (ViewGroup.MarginLayoutParams) GameActivity.zhao.getLayoutParams();
        ViewGroup.MarginLayoutParams pHuang =
                (ViewGroup.MarginLayoutParams) GameActivity.huang.getLayoutParams();
        ViewGroup.MarginLayoutParams pMa =
                (ViewGroup.MarginLayoutParams) GameActivity.ma.getLayoutParams();
        ViewGroup.MarginLayoutParams pZu1 =
                (ViewGroup.MarginLayoutParams) GameActivity.zu1.getLayoutParams();
        ViewGroup.MarginLayoutParams pZu2 =
                (ViewGroup.MarginLayoutParams) GameActivity.zu2.getLayoutParams();
        ViewGroup.MarginLayoutParams pZu3 =
                (ViewGroup.MarginLayoutParams) GameActivity.zu3.getLayoutParams();
        ViewGroup.MarginLayoutParams pZu4 =
                (ViewGroup.MarginLayoutParams) GameActivity.zu4.getLayoutParams();
        int bottom = params.topMargin + size * height;
        int left = params.leftMargin;
        int right = params.leftMargin + size * width;
        int lCao = pCao.leftMargin;
        int lGuan = pGuan.leftMargin;
        int lZhang = pZhang.leftMargin;
        int lZhao = pZhao.leftMargin;
        int lHuang = pHuang.leftMargin;
        int lMa = pMa.leftMargin;
        int lZu1 = pZu1.leftMargin;
        int lZu2 = pZu2.leftMargin;
        int lZu3 = pZu3.leftMargin;
        int lZu4 = pZu4.leftMargin;
        boolean k = (lCao < left - size||lCao >= right||pCao.topMargin != bottom)
                && (lGuan < left - size||lGuan >= right||pGuan.topMargin != bottom)
                && (lZhang < left||lZhang >= right||pZhang.topMargin != bottom)
                && (lZhao < left||lZhao >= right||pZhao.topMargin != bottom)
                && (lHuang < left||lHuang >= right||pHuang.topMargin != bottom)
                && (lMa < left||lMa >= right||pMa.topMargin != bottom)
                && (lZu1 < left||lZu1 >= right||pZu1.topMargin != bottom)
                && (lZu2 < left||lZu2 >= right||pZu2.topMargin != bottom)
                && (lZu3 < left||lZu3 >= right||pZu3.topMargin != bottom)
                && (lZu4 < left||lZu4 >= right||pZu4.topMargin != bottom);
        if (this.name.equals("卒")||this.name.equals("关羽")) {
            return params.topMargin < size * 4 && k;
        } else {
            return params.topMargin < size * 3 && k;
        }
    }

}
