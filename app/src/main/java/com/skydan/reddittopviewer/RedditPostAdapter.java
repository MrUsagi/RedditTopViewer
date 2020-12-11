package com.skydan.reddittopviewer;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public class RedditPostAdapter extends RecyclerView.Adapter<RedditPostAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView author;
        TextView date;
        TextView comments;
        ImageView thumbnail;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            author = (TextView) itemView.findViewById(R.id.postAuthorText);
            date = (TextView) itemView.findViewById(R.id.postDateText);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnailImage);
            comments = (TextView) itemView.findViewById(R.id.commentsText);
        }
    }

    private List<RedditPost> _posts;
    private Context _context;

    public RedditPostAdapter(List<RedditPost> posts, Context context){
        this._posts = posts;
        this._context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        long diff = System.currentTimeMillis() - _posts.get(position).getDateOfPost().getTime();
        int hours = (int)diff / (60 * 60 * 1000);
        int commentNumber = _posts.get(position).getPostNumber();
        holder.author.setText(_posts.get(position).getAuthor());
        holder.date.setText(_context.getResources().getQuantityString(R.plurals.hours, hours, hours));
        holder.comments.setText(_context.getResources().getQuantityString(R.plurals.comments, commentNumber, commentNumber));
        Picasso.get().load(_posts.get(position).getThumbnailImageURL()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return _posts.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
