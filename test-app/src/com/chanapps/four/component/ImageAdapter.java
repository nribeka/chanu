package com.chanapps.four.component;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.chanapps.four.data.ChanBoard;
import com.chanapps.four.test.BoardSelectorActivity;
import com.chanapps.four.test.R;

/**
* Created with IntelliJ IDEA.
* User: arley
* Date: 11/20/12
* Time: 12:23 PM
* To change this template use File | Settings | File Templates.
*/
public class ImageAdapter extends BaseAdapter {
    LayoutInflater infater = null;
    ChanBoard.Type selectedBoardType;
    int columnWidth;

public ImageAdapter(Context c, ChanBoard.Type selectedBoardType, int columnWidth) {
        infater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.selectedBoardType = selectedBoardType;
        this.columnWidth = columnWidth;
}

public int getCount() {
return ChanBoard.getBoardsByType(selectedBoardType).size();
}

public Object getItem(int position) {
return position;
}

public long getItemId(int position) {
return position;
}

public View getView(int position, View convertView, ViewGroup parent) {
        View itemLayout = null;
if (convertView == null) {
            Log.d(BoardSelectorActivity.TAG, "Creating new item view for " + position);
            itemLayout = infater.inflate(R.layout.grid_item, parent, false);
            itemLayout.setTag(selectedBoardType.toString());
} else {
            Log.d(BoardSelectorActivity.TAG, "Using existing view for " + position);
            itemLayout = convertView;
}

itemLayout.setLayoutParams(new AbsListView.LayoutParams(columnWidth, columnWidth));

ChanBoard board = ChanBoard.getBoardsByType(selectedBoardType).get(position);

ImageView imageView = (ImageView)itemLayout.findViewById(R.id.grid_item_image);
imageView.setLayoutParams(new RelativeLayout.LayoutParams(columnWidth, columnWidth));

int imageId = 0;
try {
            imageId = R.drawable.class.getField(board.link).getInt(null);
        } catch (Exception e) {
            try {
                imageId = R.drawable.class.getField("board_" + board.link).getInt(null);
            } catch (Exception e1) {
                imageId = R.drawable.stub_image;
            }
        }
        imageView.setImageResource(imageId);

TextView textView = (TextView)itemLayout.findViewById(R.id.grid_item_text);
textView.setText(board.name);

return itemLayout;
}
}
