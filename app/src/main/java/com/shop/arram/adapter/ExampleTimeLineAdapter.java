package com.shop.arram.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;
import com.shop.arram.R;
import com.shop.arram.model.OrderStatus;
import com.shop.arram.model.TimeLineModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ExampleTimeLineAdapter extends RecyclerView.Adapter<ExampleTimeLineAdapter.MyViewHolder> {

    private List<TimeLineModel> mFeedList;
    private Context context;

    public ExampleTimeLineAdapter(Context context, List<TimeLineModel> mFeedList) {
        this.mFeedList = mFeedList;
        this.context = context;


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView text_timeline_date, text_timeline_title;
        TimelineView timeline;

        public MyViewHolder(View itemView,int Viewtype) {
            super(itemView);
            // get the reference of item view's
            text_timeline_date = (TextView) itemView.findViewById(R.id.text_timeline_date);
            text_timeline_title = (TextView) itemView.findViewById(R.id.text_timeline_title);
            timeline = itemView.findViewById(R.id.timeline);
            timeline.initLine(Viewtype);
            timeline.setStartLineColor(context.getColor(R.color.black),Viewtype);
            timeline.setEndLineColor(context.getColor(R.color.black),Viewtype);


        }
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timeline, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v,viewType); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(ExampleTimeLineAdapter.MyViewHolder holder, int position) {
        TimeLineModel timeLineModel = mFeedList.get(position);

        switch (timeLineModel.getStatus()) {
            case ACTIVE:
                setMarker(holder, R.drawable.ic_marker_active, R.color.yello);
                break;
            case INACTIVE:
                setMarker(holder, R.drawable.ic_marker_inactive, R.color.material_grey);
                break;
            case COMPLETED:
                setMarker(holder, R.drawable.ic_marker, R.color.material_grey);
                break;
        }


        if (!timeLineModel.getDate().isEmpty()) {
            holder.text_timeline_date.setVisibility(View.VISIBLE);
            holder.text_timeline_date.setText(formatDateTime("yyyy-MM-dd'T'HH:mm:ss", "hh:mm a, dd-MMM-yyyy", timeLineModel.getDate()));
        } else
            holder.text_timeline_date.setVisibility(View.GONE);

        holder.text_timeline_title.setText(timeLineModel.getMessage());
    }

    private void setMarker(MyViewHolder holder, int drawableResId, int colorFilter) {

        holder.timeline.setMarker(context.getDrawable(drawableResId), ContextCompat.getColor(context, colorFilter));
    }

    @Override
    public int getItemCount() {
        return mFeedList.size();
    }

    public String formatDateTime(String originalFormat, String ouputFormat, String dates) {
        LocalDateTime date = LocalDateTime.parse(dates, DateTimeFormatter.ofPattern(originalFormat, Locale.ENGLISH));
        return date.format(DateTimeFormatter.ofPattern(ouputFormat, Locale.ENGLISH));
    }
}
