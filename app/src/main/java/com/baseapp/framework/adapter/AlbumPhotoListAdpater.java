package com.baseapp.framework.adapter;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.baseapp.framework.R;
import com.baseapp.framework.bean.ImageItem;
import com.baseapp.framework.util.task.BitmaoLocalLoadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class AlbumPhotoListAdpater extends BaseAdapter
{

    private Activity context;

    private ArrayList<ImageItem> list = new ArrayList<ImageItem>();

    private int selectTotal = 0;

    private TextCallback textCallback;

    private HashMap<String, String> selectImageMap = new LinkedHashMap<String, String>();

    private ArrayList<String> hasSelectedImages;

    private int hasSelectedSize;

    private int imageLimit;
    
    private BitmaoLocalLoadTask task;
    
    public AlbumPhotoListAdpater(Activity context, ArrayList<String> hasSelectedImages)
    {
        this.context = context;
        this.hasSelectedImages = hasSelectedImages;
        task = new BitmaoLocalLoadTask(context, BitmapFactory.decodeResource(context.getResources(), R.drawable.default_footprint_image));
    }

    public void updataList(ArrayList<ImageItem> list)
    {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public ImageItem getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        final ViewHolder viewHolder;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.album_photo_list_grid_item, null);
            viewHolder = new ViewHolder();
            viewHolder.photoIv = (ImageView) convertView.findViewById(R.id.album_bucket_photo_iv);
            viewHolder.selectCb = (CheckBox) convertView.findViewById(R.id.album_bucket_photo_select_cb);
            viewHolder.coverLl = (LinearLayout) convertView.findViewById(R.id.album_bucket_photo_select_ll);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ImageItem imageItem = getItem(position);
        String sourcePath = imageItem.getImagePath();
        viewHolder.photoIv.setTag(sourcePath);
        viewHolder.selectCb.setEnabled(false);
        task.doDisplay(viewHolder.photoIv, sourcePath);
        if (hasSelectedImages != null && hasSelectedImages.size() > 0)
        {
            hasSelectedSize = hasSelectedImages.size();
        }
        if (imageItem.isSelected())
        {
            viewHolder.coverLl.setVisibility(View.VISIBLE);
            viewHolder.selectCb.setChecked(true);
        }
        else
        {
            viewHolder.coverLl.setVisibility(View.GONE);
            viewHolder.selectCb.setChecked(false);
        }
        viewHolder.photoIv.setOnClickListener(new OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub
                
                boolean isChecked = viewHolder.selectCb.isChecked();
                if (isChecked)
                {
                    viewHolder.selectCb.setChecked(false);
                    isChecked = false;
                }
                else
                {
                    viewHolder.selectCb.setChecked(true);
                    isChecked = true;
                }

                String path = imageItem.getImagePath();
                if (isChecked)
                {
                    if (selectTotal == imageLimit || selectTotal == (imageLimit - hasSelectedSize))
                    {
                        Toast.makeText(context, "图片最多可选择5张!", Toast.LENGTH_SHORT).show();
                        viewHolder.selectCb.setChecked(false);
                        return;
                    }
                    imageItem.setSelected(true);
                    list.get(position).setSelected(true);
                    viewHolder.coverLl.setVisibility(View.VISIBLE);
                    selectTotal++;
                    if (textCallback != null)
                    {
                        textCallback.onChangeText(selectTotal);
                    }
                    selectImageMap.put(path, path);

                }
                else
                {
                    imageItem.setSelected(false);
                    list.get(position).setSelected(false);
                    viewHolder.coverLl.setVisibility(View.GONE);
                    selectTotal--;
                    if (textCallback != null)
                        textCallback.onChangeText(selectTotal);
                    selectImageMap.remove(path);
                }
            }
        });
        return convertView;
    }

    class ViewHolder
    {
        ImageView photoIv;

        CheckBox selectCb;

        LinearLayout coverLl;
    }

    public interface TextCallback
    {
        public void onChangeText(int count);
    }

    public interface PhotoClickCallback
    {
        public void onPhotoClick(String file);
    }

    public void setTextCallback(TextCallback listener)
    {
        this.textCallback = listener;
    }

    public HashMap<String, String> getSelectImageMap()
    {
        return selectImageMap;
    }

    public void setSelectImageMap(HashMap<String, String> selectImageMap)
    {
        this.selectImageMap = selectImageMap;
    }

    public int getImageLimit()
    {
        return imageLimit;
    }

    public void setImageLimit(int imageLimit)
    {
        this.imageLimit = imageLimit;
    }

}
