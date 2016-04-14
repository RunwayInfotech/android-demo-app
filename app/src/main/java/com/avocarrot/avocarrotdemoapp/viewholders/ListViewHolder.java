package com.avocarrot.avocarrotdemoapp.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.avocarrot.avocarrotdemoapp.R;
import com.avocarrot.avocarrotdemoapp.models.DemoListItem;
import com.avocarrot.avocarrotdemoapp.utils.Toolbox;
import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

/**
 * Created by theokir on 06/04/16.
 */
@LayoutId(R.layout.item_list)
public class ListViewHolder extends ItemViewHolder<DemoListItem> {

    @ViewId(R.id.icon)
    ImageView icon;
    @ViewId(R.id.comments)
    TextView comments;
    @ViewId(R.id.likes)
    TextView likes;
    @ViewId(R.id.title)
    TextView title;


    public ListViewHolder(View view) {
        super(view);
    }

    public ListViewHolder(View view, DemoListItem item) {
        super(view, item);
    }

    @Override
    public void onSetValues(DemoListItem item, PositionInfo positionInfo) {
        title.setText(item.getTitle());
        likes.setText(item.getLikes());
        comments.setText(item.getComments());
        icon.setImageDrawable(Toolbox.loadDataFromAsset(item.getIcon(),getContext()));

    }

    @Override
    public void onSetListeners() {
        getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get your custom listener and call the method.
                DemoListItemListener listener = getListener(DemoListItemListener.class);
                if (listener != null) {
                    listener.onDemoListItemClicked(getItem());
                }
            }
        });
    }

    //Define your custom interface
    public interface DemoListItemListener {
        public void onDemoListItemClicked(DemoListItem demoListItem);
    }
}
