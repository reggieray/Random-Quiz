package com.matthewregis.randomquiz.ui.topscores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.matthewregis.randomquiz.R;
import com.matthewregis.randomquiz.data.models.GameMode;
import com.matthewregis.randomquiz.data.models.ScoreModel;

import java.util.List;

/**
 * Created by reg on 05/12/2016.
 */

public class TopScoresListAdapter extends RecyclerView.Adapter<TopScoresListAdapter.ViewHolder> {
    private List<ScoreModel> mTopScoresList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;
        public LinearLayout mViewLayout;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.top_score_info_text);
            mImageView = (ImageView) v.findViewById(R.id.top_score_info_image);
            mViewLayout = (LinearLayout) v.findViewById(R.id.top_score_layout);
        }
    }

    public TopScoresListAdapter(List<ScoreModel> topScoresList) {
        mTopScoresList = topScoresList;
    }

    public void SetTopScoresList(List<ScoreModel> topScoresList){
        mTopScoresList = topScoresList;
    }

    @Override
    public TopScoresListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_score_list_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        ScoreModel scoreModel =  mTopScoresList.get(position);
        String scoreText = String.format("Name: %s\nScore: %s", scoreModel.getName(), scoreModel.getScore());
        holder.mTextView.setText(scoreText);
        holder.mViewLayout.setBackgroundResource((scoreModel.getGameMode().equalsIgnoreCase(GameMode.RANDOM_10.toString())) ? R.color.green : R.color.amber);
        holder.mImageView.setImageResource(((scoreModel.getGameMode().equalsIgnoreCase(GameMode.RANDOM_10.toString()) ? R.drawable.rolling_dices : R.drawable.reaper_scythe)));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mTopScoresList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}