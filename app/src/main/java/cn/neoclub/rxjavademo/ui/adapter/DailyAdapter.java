package cn.neoclub.rxjavademo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.neoclub.rxjavademo.R;
import cn.neoclub.rxjavademo.model.bean.DailyListBean;

/**
 * Created by renjialiang on 16/12/2.
 */
public class DailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<DailyListBean.StoriesBean> mList = new ArrayList<>();
    private LayoutInflater inflater;

    public DailyAdapter(Context context, List<DailyListBean.StoriesBean> list) {
        this.mContext = context;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContentViewHolder(inflater.inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DailyListBean.StoriesBean storiesBean = mList.get(position);
        if (holder instanceof ContentViewHolder) {
            String title = storiesBean.getTitle();
            ((ContentViewHolder) holder).mTitle.setText(title);
            ((ContentViewHolder) holder).cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_title)
        TextView mTitle;
        @BindView(R.id.card)
        CardView cardView;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void addDaily(DailyListBean dailyListBean) {
        mList = dailyListBean.getStories();
        notifyDataSetChanged();
    }
}
