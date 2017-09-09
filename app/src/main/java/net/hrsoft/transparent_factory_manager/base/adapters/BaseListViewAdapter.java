package net.hrsoft.transparent_factory_manager.base.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author abtion.
 * @since 17/9/2 09:50.
 * email caiheng@hrsoft.net
 */

public abstract class BaseListViewAdapter<T> extends BaseAdapter {
    protected List<T> dataList = new ArrayList<>();
    protected Context context;
    protected LayoutInflater inflater;


    public BaseListViewAdapter(Context context,List<T> dataList) {
        this.context = context;
        this.dataList = dataList;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * 设置数据
     *
     * @param data 数据
     */
    public void setData(Collection<T> data) {
        this.dataList.clear();
        this.dataList.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 添加一条数据
     *
     * @param t 数据
     */
    public void add(T t) {
        this.dataList.add(t);
        notifyDataSetChanged();
    }

    /**
     * 添加多条数据
     *
     * @param collection 数据
     */
    public void addAll(Collection<T> collection) {
        this.dataList.addAll(collection);
        notifyDataSetChanged();
    }

    /**
     * 移除数据
     *
     * @param t 移除的数据
     */
    public void remove(T t) {
        this.dataList.remove(t);
        notifyDataSetChanged();
    }

    /**
     * 清空列表
     */
    public void clear() {
        this.dataList.clear();
        notifyDataSetChanged();
    }

    /**
     * 刷新页面
     */
    public void refresh() {
        notifyDataSetChanged();
    }

    /**
     * 获取当前列表的数据
     *
     * @return
     */
    public List<T> getListData() {
        return this.dataList;
    }


    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public T getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
