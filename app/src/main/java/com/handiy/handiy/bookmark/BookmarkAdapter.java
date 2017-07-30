package com.handiy.handiy.bookmark;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.handiy.handiy.R;
import com.handiy.handiy.data.BookmarkModel;

import java.util.List;

/**
 * Created by FitriFebriana on 7/1/2017.
 */

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {

    private Context context;
    private List<Object> bookmarkDataSet;
    private BookmarkItemClickListener bookmarkItemClickListener;

    public BookmarkAdapter(Context context, List<Object> bookmarkDataSet, BookmarkItemClickListener bookmarkItemClickListener) {
        this.context = context;
        this.bookmarkDataSet = bookmarkDataSet;
        this.bookmarkItemClickListener = bookmarkItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new BookmarkAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BookmarkModel bookmarkData = (BookmarkModel) bookmarkDataSet.get(position);
        holder.txtBookmarkTitle.setText(bookmarkData.getTutorial().getTitle());
        Glide.with(context).load(bookmarkData.getTutorial().getThumbnail()).into((holder).imgGridBookmark);
    }

    @Override
    public int getItemCount() {
        return bookmarkDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgGridBookmark;
        private TextView txtBookmarkTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            imgGridBookmark = (ImageView) itemView.findViewById(R.id.imageview_grid);
            txtBookmarkTitle = (TextView) itemView.findViewById(R.id.tutorial_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            BookmarkModel bookmarkData = (BookmarkModel) bookmarkDataSet.get(getAdapterPosition());
            bookmarkItemClickListener.onBookmarkClick(new Gson().toJson(bookmarkData.getTutorial()));
        }
    }

    public void replaceData(List<Object> bookmarkDataSet){
        this.bookmarkDataSet = bookmarkDataSet;
        notifyDataSetChanged();
    }

    public interface BookmarkItemClickListener {
        void onBookmarkClick(String extras);
    }

}
