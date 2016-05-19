package com.baseapp.framework.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baseapp.framework.R;
import com.baseapp.framework.bean.ImageBucket;
import com.baseapp.framework.util.ViewHolder;
/**
 * 选择相册Apae
 * @author Administrator
 *
 */
public class CommonAdapter extends BaseAdapter
{
    protected LayoutInflater mInflater;

    protected Context mContext;

    protected List<ImageBucket> mDatas;

    private int tag;

    public CommonAdapter(Context context, List<ImageBucket> mDatas )
    {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
    }

    @Override
    public int getCount()
    {
        return mDatas.size();
    }

    @Override
    public ImageBucket getItem(int position)
    {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    public void setTagindex(int index){
        tag = index;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.photo_ablun_list_dir_item, parent, false);
        }
        ImageBucket bucket = getItem(position);
        TextView dirName = ViewHolder.get(convertView, R.id.id_dir_item_name);
        TextView dirImageCount = ViewHolder.get(convertView, R.id.id_dir_item_count);
        ImageView imageShow = ViewHolder.get(convertView, R.id.id_dir_item_image);
        ImageView imageTag = ViewHolder.get(convertView, R.id.img_tag_show);
//        ImageManager.from(mContext).displayImage(imageShow, bucket.getFirstPicPath(), R.drawable.default_footprint_image, 200, 200);
//        MyApp lication.fb.displayImage("file://"+bucket.getFirstPicPath(), imageShow);
        dirName.setText(bucket.getAlbumName());
        dirImageCount.setText(bucket.getPicCount() + "张");
        if(tag==position){
            imageTag.setVisibility(View.VISIBLE);
        }else{
            imageTag.setVisibility(View.GONE);
        }
        return convertView;
    }

}
