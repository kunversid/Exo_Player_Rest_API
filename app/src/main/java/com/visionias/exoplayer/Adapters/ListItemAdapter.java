package com.visionias.exoplayer.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.visionias.exoplayer.ListItem;
import com.visionias.exoplayer.PlayerActivity;
import com.visionias.exoplayer.R;

import java.util.ArrayList;


public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ListViewHolder> {

    private ArrayList<ListItem> mListItems;
    private final Context mContext;

    public ListItemAdapter(ArrayList<ListItem> listItems,Context context){
        mListItems = listItems;
        mContext = context;
    }


    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.playlist_item,parent,false);
        return new ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        ListItem listItem = mListItems.get(position);

        String thumbnailUrl = listItem.getThumbnail();
        String videoTitle = listItem.getTitle();
        String publishDate = listItem.getPublishDate();
        final String videoURL = listItem.getVideoUrl();
        holder.videoTitle.setText(videoTitle);
        holder.publishDate.setText("Date : "+publishDate);
        Picasso.with(mContext).load(thumbnailUrl).fit().centerInside().into(holder.thumbnail);
        holder.rlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (mContext, PlayerActivity.class);
                intent.putExtra("VideoURL",videoURL);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListItems.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{

        TextView videoTitle;
        ImageView thumbnail;
        TextView publishDate;
        RelativeLayout rlItem;

        ListViewHolder(View itemView) {
            super(itemView);
            rlItem = itemView.findViewById(R.id.rl_item);
            videoTitle = itemView.findViewById(R.id.vid_title);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            publishDate = itemView.findViewById(R.id.publish_date);
        }

    }
}
