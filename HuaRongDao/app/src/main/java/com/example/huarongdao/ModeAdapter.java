
package com.example.huarongdao;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huarongdao.MainActivity;
import com.example.huarongdao.R;

import java.util.LinkedList;

public class ModeAdapter extends
        RecyclerView.Adapter<ModeAdapter.WordViewHolder> {

    private final LinkedList<String> mModeList;
    private final LayoutInflater mInflater;

    class WordViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public final TextView buttonItemView;
        final ModeAdapter mAdapter;


        public WordViewHolder(View itemView, ModeAdapter adapter) {
            super(itemView);
            buttonItemView = itemView.findViewById(R.id.select_button);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }

    public ModeAdapter(Context context, LinkedList<String> mModeList) {
        mInflater = LayoutInflater.from(context);
        this.mModeList = mModeList;
    }

    @Override
    public ModeAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(
                R.layout.list_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(ModeAdapter.WordViewHolder holder,
                                 final int position) {
        // Retrieve the data for that position.
        final String mCurrentButton = mModeList.get(position);
        // Add the data to the view holder.
        holder.buttonItemView.setText(mCurrentButton);
        holder.buttonItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game = new Intent(SelectActivity.mContext, GameActivity.class);
                game.putExtra("position", position);
                game.putExtra("mode", mCurrentButton);
                SelectActivity.mactivity.startActivity(game);
            }
        });

        int size = SelectActivity.size;
        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(size*4, size*3/2);
        p.gravity = Gravity.CENTER;
        holder.buttonItemView.setLayoutParams(p);
    }


    @Override
    public int getItemCount() {
        return mModeList.size();
    }
}
