package org.wikipedia.feed.mostread;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.wikipedia.R;
import org.wikipedia.feed.view.CardHeaderView;
import org.wikipedia.feed.view.FeedViewCallback;
import org.wikipedia.feed.view.ListCardView;
import org.wikipedia.feed.view.PageTitleListCardItemView;
import org.wikipedia.feed.view.PageTitleRecyclerAdapter;
import org.wikipedia.history.HistoryEntry;
import org.wikipedia.views.DefaultViewHolder;
import org.wikipedia.views.ItemTouchHelperSwipeAdapter;

import java.util.List;

public class MostReadCardView extends ListCardView<MostReadListCard>
        implements ItemTouchHelperSwipeAdapter.SwipeableView {
    public MostReadCardView(Context context) {
        super(context);
    }

    @Override public void setCard(@NonNull MostReadListCard card) {
        super.setCard(card);
        header(card);
        set(new RecyclerAdapter(card.items(), getCallback()));
    }

    private void header(@NonNull MostReadListCard card) {
        CardHeaderView header = new CardHeaderView(getContext())
                .setTitle(card.title())
                .setSubtitle(card.subtitle())
                .setImage(R.drawable.ic_most_read)
                .setImageCircleColor(R.color.foundation_blue)
                .setCard(card)
                .setCallback(getCallback());
        header(header);
    }

    private static class RecyclerAdapter extends PageTitleRecyclerAdapter<MostReadItemCard> {
        @Nullable private FeedViewCallback callback;

        RecyclerAdapter(@NonNull List<MostReadItemCard> items, @Nullable FeedViewCallback callback) {
            super(items);
            this.callback = callback;
        }

        @Override
        public void onBindViewHolder(DefaultViewHolder<PageTitleListCardItemView> holder, int position) {
            MostReadItemCard card = item(position);
            holder.getView().setHistoryEntry(new HistoryEntry(card.pageTitle(), HistoryEntry.SOURCE_FEED_MOST_READ));
            holder.getView().setCallback(callback);
        }
    }
}
