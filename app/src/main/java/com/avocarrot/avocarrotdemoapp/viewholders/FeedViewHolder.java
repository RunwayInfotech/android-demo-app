package com.avocarrot.avocarrotdemoapp.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.avocarrot.avocarrotdemoapp.R;
import com.avocarrot.avocarrotdemoapp.models.DemoFeedItem;
import com.avocarrot.avocarrotdemoapp.utils.Toolbox;
import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

/**
 * Created by theokir on 06/04/16.
 */
@LayoutId(R.layout.item_feed)
public class FeedViewHolder extends ItemViewHolder<DemoFeedItem> {

    @ViewId(R.id.feedImage)
    ImageView feedImage;

    @ViewId(R.id.avatarImage)
    ImageView avatarImage;

    @ViewId(R.id.personName)
    TextView personName;

    @ViewId(R.id.feedDesrciption)
    TextView feedDesrciption;


    public FeedViewHolder(View view) {
        super(view);
    }

    public FeedViewHolder(View view, DemoFeedItem item) {
        super(view, item);
    }

    @Override
    public void onSetValues(DemoFeedItem item, PositionInfo positionInfo) {
        feedDesrciption.setText(item.getDescription());
        feedImage.setImageDrawable(Toolbox.loadDataFromAsset(item.getImage(), getContext()));
        avatarImage.setImageDrawable(Toolbox.loadDataFromAsset(item.getAvatar(), getContext()));
        personName.setText(item.getName());
    }

    @Override
    public void onSetListeners() {
        getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get your custom listener and call the method.
                DemoFeedItemListener listener = getListener(DemoFeedItemListener.class);
                if (listener != null) {
                    listener.onDemoFeedItemClicked(getItem());
                }
            }
        });
    }

    //Define your custom interface
    public interface DemoFeedItemListener {
        public void onDemoFeedItemClicked(DemoFeedItem demoFeedItem);
    }
}
