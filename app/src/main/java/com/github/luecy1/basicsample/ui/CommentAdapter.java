package com.github.luecy1.basicsample.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.luecy1.basicsample.R;
import com.github.luecy1.basicsample.databinding.CommentItemBinding;
import com.github.luecy1.basicsample.model.Comment;

import java.util.List;
import java.util.Objects;

/**
 * Created by you on 2018/03/10.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private List<? extends Comment> mCommentList;

    @Nullable
    private final CommentClickCallback mCommentClickCallback;

    public CommentAdapter(CommentClickCallback commentClickCallback) {
        this.mCommentClickCallback = commentClickCallback;
    }

    public void setmCommentList(final List<? extends Comment> comments) {
        if (mCommentList == null) {
            mCommentList = comments;
            notifyItemRangeChanged(0, comments.size());
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mCommentList.size();
                }

                @Override
                public int getNewListSize() {
                    return comments.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    Comment old = mCommentList.get(oldItemPosition);
                    Comment comment = comments.get(newItemPosition);
                    return old.getId() == comment.getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Comment old = mCommentList.get(oldItemPosition);
                    Comment comment = comments.get(newItemPosition);
                    return old.getId() == comment.getId()
                            && old.getPostedAt() == comment.getPostedAt()
                            && old.getProductId() == comment.getProductId()
                            && Objects.equals(old.getText(), comment.getText());
                }
            });
            mCommentList = comments;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CommentItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.comment_item,
                        parent, false);
        binding.setCallback(mCommentClickCallback);
        return new CommentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        holder.binding.setComment(mCommentList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mCommentList == null ? 0 : mCommentList.size();
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder {

        final CommentItemBinding binding;

        CommentViewHolder(CommentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
