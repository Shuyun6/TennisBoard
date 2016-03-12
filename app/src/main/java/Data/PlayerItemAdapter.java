package Data;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import MenuItem.PlayerDataItemView;

/**
 * Created by Shuyun on 2/19/2016/019.
 */
public class PlayerItemAdapter extends BaseAdapter {

    public PlayerDataItemView playerDataItemView;
    private List<ItemBean> itemBeanList;
    private LayoutInflater layoutInflater;
    private ItemBean itemBean;
    private Context context;
    private Bitmap bitmap;
    private Database database;
    public int flage;
    public FragmentManager fragmentManager;

    public PlayerItemAdapter(Context context, List<ItemBean> itemBeanList) {
        this.itemBeanList = itemBeanList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        database = new Database(context);
    }

    @Override
    public int getCount() {
        return itemBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

//        View view=layoutInflater.inflate(R.layout.playerdata, null);
//        ImageView imageView= (ImageView) view.findViewById(R.id.head);
//        TextView textViewName= (TextView) view.findViewById(R.id.playername);
//        TextView textViewHeight= (TextView) view.findViewById(R.id.height2);
//        TextView textViewWeight= (TextView) view.findViewById(R.id.weight2);
//        TextView textViewPhoneNumber= (TextView) view.findViewById(R.id.phonenumber);

//        ItemBean itemBean=itemBeanList.get(position);
//        Uri uri=Uri.parse(itemBean.imageUriStr);
//        try {
//            bitmap= MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        imageView.setImageBitmap(bitmap);
//        textViewName.setText(itemBean.name);
//        textViewHeight.setText(itemBean.height);
//        textViewWeight.setText(itemBean.weight);
//        textViewPhoneNumber.setText(itemBean.phone);
//        return view;

        /**
         * normal
         * */
//        if(convertView==null){
//            convertView=layoutInflater.inflate(R.layout.playerdata, null);
//        }
//
//        ImageView imageView= (ImageView) convertView.findViewById(R.id.head);
//        TextView textViewName= (TextView) convertView.findViewById(R.id.playername);
//        TextView textViewHeight= (TextView) convertView.findViewById(R.id.height2);
//        TextView textViewWeight= (TextView) convertView.findViewById(R.id.weight2);
//        TextView textViewPhoneNumber= (TextView) convertView.findViewById(R.id.phonenumber);
//
//        ItemBean itemBean=itemBeanList.get(position);
//        Uri uri=Uri.parse(itemBean.imageUriStr);
//        try {
//            bitmap= MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        imageView.setImageBitmap(bitmap);
//        textViewName.setText(itemBean.name);
//        textViewHeight.setText(itemBean.height);
//        textViewWeight.setText(itemBean.weight);
//        textViewPhoneNumber.setText(itemBean.phone);
//        return  convertView;

        /**
         * Best
         * */
        ViewHolder viewHolder;
        playerDataItemView = (PlayerDataItemView) convertView;

        if (convertView == null) {
            viewHolder = new ViewHolder();
//            convertView=layoutInflater.inflate(R.layout.playerdata, null);
//            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.head);
//            viewHolder.textViewName= (TextView) convertView.findViewById(R.id.playername);
//            viewHolder.textViewHeight= (TextView) convertView.findViewById(R.id.height2);
//            viewHolder.textViewWeight= (TextView) convertView.findViewById(R.id.weight2);
//            viewHolder.textViewPhoneNumber= (TextView) convertView.findViewById(R.id.phonenumber);
//            convertView.setTag(viewHolder);

            /**
             * Interface recall
             * */
            playerDataItemView = new PlayerDataItemView(context);
            playerDataItemView.flage=flage;
            playerDataItemView.fragmentManager=fragmentManager;
            playerDataItemView.setOnDeleteClickListener(new PlayerDataItemView.deleteClickListener() {
                @Override
                public void delete() {

                    try {
                        database.open();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    database.deletePlayer(itemBeanList.get(position).name, itemBeanList.get(position).sex);
                    database.close();
                    //
                    itemBeanList.remove(position);
                    notifyDataSetChanged();
                }
            });

            viewHolder.imageView = playerDataItemView.imageView;
            viewHolder.textViewName = playerDataItemView.textViewName;
            viewHolder.textViewHeight = playerDataItemView.textViewHeight;
            viewHolder.textViewWeight = playerDataItemView.textViewWeight;
            viewHolder.textViewPhoneNumber = playerDataItemView.textViewPhoneNumber;
            viewHolder.textViewSex = playerDataItemView.textViewSex;
            playerDataItemView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) playerDataItemView.getTag();
        }

        itemBean = itemBeanList.get(position);
        Uri uri = Uri.parse(itemBean.imageUriStr);
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        viewHolder.imageView.setImageBitmap(bitmap);
        viewHolder.textViewName.setText(itemBean.name);
        String stringHeight = itemBean.height;
        String stringWeight = itemBean.weight;
        if (stringHeight.equals("")) {
            viewHolder.textViewHeight.setText(itemBean.height);
        } else {
            viewHolder.textViewHeight.setText(itemBean.height + "cm");
        }
        if (stringWeight.equals("")) {
            viewHolder.textViewWeight.setText(itemBean.weight);
        } else {
            viewHolder.textViewWeight.setText(itemBean.weight + "kg");
        }
        viewHolder.textViewPhoneNumber.setText(itemBean.phone);
        viewHolder.textViewSex.setText(itemBean.sex);
        return playerDataItemView;
    }

    class ViewHolder {
        public ImageView imageView;
        public TextView textViewName, textViewHeight, textViewWeight, textViewPhoneNumber, textViewSex;
    }

}
