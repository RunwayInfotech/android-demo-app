package com.avocarrot.avocarrotdemoapp.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.avocarrot.avocarrotdemoapp.R;
import com.avocarrot.avocarrotdemoapp.models.DemoTileItem;
import com.avocarrot.avocarrotdemoapp.utils.Toolbox;
import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

/**
 * Created by theokir on 06/04/16.
 */
@LayoutId(R.layout.item_tile)
public class TileViewHolder extends ItemViewHolder<DemoTileItem> {

    @ViewId(R.id.tile_image)
    ImageView tile_image;
    @ViewId(R.id.tile_title)
    TextView tile_title;
    @ViewId(R.id.tile_number)
    TextView tile_number;
    @ViewId(R.id.tile_location)
    TextView tile_location;


    public TileViewHolder(View view) {
        super(view);
    }

    public TileViewHolder(View view, DemoTileItem item) {
        super(view, item);
    }

    @Override
    public void onSetValues(DemoTileItem item, PositionInfo positionInfo) {
        tile_location.setText(item.getLocation());
        tile_number.setText(item.getNumber());
        tile_title.setText(item.getTitle());
        tile_image.setImageDrawable(Toolbox.loadDataFromAsset(item.getImage(),getContext()));
    }

    @Override
    public void onSetListeners() {
        getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get your custom listener and call the method.
                DemoTileItemListener listener = getListener(DemoTileItemListener.class);
                if (listener != null) {
                    listener.onDemoTileItemClicked(getItem());
                }
            }
        });
    }

    //Define your custom interface
    public interface DemoTileItemListener {
        public void onDemoTileItemClicked(DemoTileItem demoTileItem);
    }

}
