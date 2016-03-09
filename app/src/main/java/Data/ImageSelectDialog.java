package Data;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.shuyun.tennisboard.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shuyun on 2/14/2016/014.
 */
public class ImageSelectDialog {
    private Context context;
    private Dialog dialog;
    private TextView title, cancel;
    private boolean showTitle;
    private LinearLayout linearLayout;
    private ScrollView scrollView;
    private Display display;
    private List<Item> ItemList;

    public ImageSelectDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public ImageSelectDialog builder() {
        /**
         * Set view by layout
         * */
        View view = LayoutInflater.from(context).inflate(R.layout.imageselect, null);
        view.setMinimumWidth(display.getWidth());
        scrollView = (ScrollView) view.findViewById(R.id.scrollContent);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearlayout);
        title = (TextView) view.findViewById(R.id.title);
        cancel = (TextView) view.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        /**
         * customizing Dialog's layout and params
         * */
        dialog = new Dialog(context, R.style.ImageSelectDialogStyle);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.x = 0;
        params.y = 0;
        window.setAttributes(params);

        return this;
    }

    public ImageSelectDialog setTitle(String string) {
        showTitle = true;
        title.setVisibility(View.VISIBLE);
        title.setText(string);
        return this;
    }

    public ImageSelectDialog setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
        return this;
    }

    public ImageSelectDialog setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        dialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        return this;
    }

    public ImageSelectDialog addItem(String string, ItemColor itemColor, OnItemClickListener itemClickListener) {
        if (ItemList == null) {
            ItemList = new ArrayList<Item>();
        }
        ItemList.add(new Item(string, itemColor, itemClickListener));
        return this;
    }

    private void setItem() {
        if (ItemList == null || ItemList.size() <= 0) {
            return;
        }

        int size = ItemList.size();
        if (size >= 7) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) scrollView.getLayoutParams();
            params.height = display.getHeight() / 2;
            scrollView.setLayoutParams(params);
        }

        for (int i = 1; i <= size; i++) {
            final int index = i;
            Item item = ItemList.get(i - 1);
            String string = item.name;
            ItemColor itemColor = item.itemColor;
            final OnItemClickListener listener = item.itemClickListener;
            TextView textView = new TextView(context);
            textView.setText(string);
            textView.setTextSize(18);
            textView.setGravity(Gravity.CENTER);

            textView.setBackgroundResource(R.drawable.itemshape);

            if (itemColor == null) {
                textView.setTextColor(Color.parseColor(ItemColor.Blue.getName()));
            } else {
                textView.setTextColor(Color.parseColor(itemColor.getName()));
            }

            float scale = context.getResources().getDisplayMetrics().density;
            int height = (int) (45 * scale + 0.5f);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(index);
                    dialog.dismiss();
                }
            });
            linearLayout.addView(textView);

        }

    }

    public void show() {
        setItem();
        dialog.show();
    }

    public enum ItemColor {
        Blue("#037bff"), Red("#fd4a2e");

        private String name;

        ItemColor(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public interface OnItemClickListener {
        void onClick(int click);
    }

    public class Item {
        String name;
        OnItemClickListener itemClickListener;
        ItemColor itemColor;

        public Item(String name, ItemColor itemColor, OnItemClickListener itemClickListener) {
            this.name = name;
            this.itemColor = itemColor;
            this.itemClickListener = itemClickListener;
        }
    }

}
