package com.example.schedule.ui.today;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schedule.R;
import com.example.schedule.po.Task;

import java.util.List;



public class TodayAdapter extends RecyclerView.Adapter<TodayAdapter.ViewHolder>{
    private Context mContext;
    private List<Task> mtaskList;


    public TodayAdapter(List<Task> taskList) {
        mtaskList = taskList;
    }
    // 定义是否重要的按钮
    //第一步 定义接口
    private OnItemImportanceClickListener importance_Listener;

    public interface OnItemImportanceClickListener {
        void onClick(int id,int importance);
    }

    //第二步， 写一个公共的方法
    public void setImportanceOnClick(OnItemImportanceClickListener listener) {
        this.importance_Listener = listener;
    }

    //定义是否完成的按钮
    public interface OnItemFinishClickListener {
        void onClick(int id, int finish);
    }

    private OnItemFinishClickListener finishClickListener;

    public void setOnItemFinishClickListener(OnItemFinishClickListener listener) {
        this.finishClickListener = listener;
    }

    //定义一整条item
    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onClick(int id);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_content;
        TextView tv_item_date;
        TextView tv_item_time;
        TextView tv_item_type;
        TextView tv_item_important;
        TextView tv_item_finish;
        ImageView is_important;
        ImageView is_ready;
        LinearLayout ll_item;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            tv_content = (TextView) itemView.findViewById(R.id.item_content);
            tv_item_date = (TextView) itemView.findViewById(R.id.item_date);
            tv_item_time = (TextView) itemView.findViewById(R.id.item_time);
            tv_item_type = (TextView) itemView.findViewById(R.id.item_type);
            tv_item_important = (TextView) itemView.findViewById(R.id.tv_item_important);
            tv_item_finish = (TextView) itemView.findViewById(R.id.tv_item_finish);

            is_important = (ImageView) itemView.findViewById(R.id.is_important);
            is_ready = (ImageView) itemView.findViewById(R.id.is_ready);
            tv_item_important = (TextView) itemView.findViewById(R.id.tv_item_important);

            ll_item = (LinearLayout)itemView.findViewById(R.id.ll_item);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = mtaskList.get(position);
        holder.tv_content.setText(task.getContent());
        holder.tv_item_date.setText(task.getRemind_date());
        holder.tv_item_time.setText(task.getRemind_time());
        holder.tv_item_type.setText(task.getSort());

        int is_importance  = task.getIs_importance();
        holder.tv_item_important.setText(Integer.toString(is_importance));
        if (is_importance == 1){
            holder.is_important.setBackgroundDrawable(mContext.getDrawable(R.drawable.ic_important_yes_foreground));
        }else{
            holder.is_important.setBackgroundDrawable(mContext.getDrawable(R.drawable.ic_important_no_foreground));
        }

        holder.is_important.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_is_importance = holder.tv_item_important.getText().toString().trim();
                if (importance_Listener != null) {
                    importance_Listener.onClick(task.getId(),Integer.parseInt(new_is_importance));
                    if(new_is_importance.equals("0")){
                        holder.tv_item_important.setText("1");
                        holder.is_important.setBackgroundDrawable(mContext.getDrawable(R.drawable.ic_important_yes_foreground));

                    }else{
                        holder.tv_item_important.setText("0");
                        holder.is_important.setBackgroundDrawable(mContext.getDrawable(R.drawable.ic_important_no_foreground));
                    }

                }
            }
        });

        int is_finish = task.getIs_finish();
        holder.tv_item_finish.setText(Integer.toString(is_finish));

        if (is_finish == 1){
            holder.is_ready.setBackgroundDrawable(mContext.getDrawable(R.drawable.ic_finish_yes_foreground));
        }else{
            holder.is_ready.setBackgroundDrawable(mContext.getDrawable(R.drawable.ic_finish_no));
        }

        holder.is_ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_is_finish = holder.tv_item_finish.getText().toString().trim();

                if (finishClickListener != null) {
                    Log.d("test",new_is_finish);

                    if(new_is_finish.equals("0")){
                        holder.tv_item_finish.setText("1");
                        holder.is_ready.setBackgroundDrawable(mContext.getDrawable(R.drawable.ic_finish_yes_foreground));

                    }else{
                        holder.tv_item_finish.setText("0");
                        holder.is_ready.setBackgroundDrawable(mContext.getDrawable(R.drawable.ic_finish_no));
                    }
                    finishClickListener.onClick(task.getId(),Integer.parseInt(new_is_finish));

                }
            }
        });

        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onClick(task.getId());
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return mtaskList.size();
    }



}
