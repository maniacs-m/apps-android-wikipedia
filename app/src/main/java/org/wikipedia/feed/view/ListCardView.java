package org.wikipedia.feed.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.wikipedia.R;
import org.wikipedia.feed.model.Card;
import org.wikipedia.util.ResourceUtil;
import org.wikipedia.views.DrawableItemDecoration;
import org.wikipedia.views.ViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class ListCardView<T extends Card> extends DefaultFeedCardView<T> {
    @BindView(R.id.view_list_card_header) View headerView;
    @BindView(R.id.view_list_card_large_header) View largeHeaderView;
    @BindView(R.id.view_list_card_list) RecyclerView recyclerView;

    public ListCardView(Context context) {
        super(context);

        inflate(getContext(), R.layout.view_list_card, this);
        ButterKnife.bind(this);
        initRecycler(recyclerView);
    }

    protected void set(@Nullable RecyclerView.Adapter<?> adapter) {
        recyclerView.setAdapter(adapter);
    }

    protected void update() {
        if (recyclerView.getAdapter() != null) {
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    protected void header(@NonNull View view) {
        ViewUtil.replace(headerView, view);
        headerView = view;
    }

    protected void largeHeader(@NonNull View view) {
        ViewUtil.replace(largeHeaderView, view);
        largeHeaderView = view;
    }

    protected void initRecycler(@NonNull RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DrawableItemDecoration(getContext(),
                ResourceUtil.getThemedAttributeId(getContext(), R.attr.list_separator_drawable), true));
        recyclerView.setNestedScrollingEnabled(false);
    }
}