package com.example.frintezza;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RouterAdapter extends RecyclerView.Adapter<RouterAdapter.ViewHolder>{
    private final List<Routers> data;
    private OnClick listener;
    public RouterAdapter(List<Routers> data) {this.data = data;}

    @NonNull
    @Override
    public RouterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.router_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RouterAdapter.ViewHolder holder, int position) {
        Routers item = data.get(position);
        holder.bind(item);
        holder.view.setOnClickListener(v->{
            if(listener != null){
                listener.onClick(item);
            }
        });
        holder.view.setOnLongClickListener(v->{
            if(listener != null){
                listener.onLongClick(item);
            }
            return false;
        });
    }
    public void setListener(OnClick listener) {
        this.listener = listener;
    }

    public  interface OnClick{
        void onClick(Routers router);
        void onLongClick(Routers router);

    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        ImageView routerImg;
        TextView routerName;
        TextView routerIp;
        ImageView setNat;
        TextView setwinbox;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setClickable(true);
            routerImg = itemView.findViewById(R.id.ivRouterImage);
            routerName = itemView.findViewById(R.id.tvRounterName);
            routerIp = itemView.findViewById(R.id.tvRounterip);
            setNat = itemView.findViewById(R.id.ivNatImage);
            setwinbox = itemView.findViewById(R.id.ivSetWinbox);
            view = itemView;

        }
        public void bind(Routers item){
            routerImg.setImageResource(getOnlineImage(item.isOnline()));
            routerName.setText(item.getName());
            routerIp.setText(item.getIpaddress());
            setNat.setImageResource(item.isOnline() ? R.drawable.baseline_settings_24 : R.drawable.baseline_settings_24_off);
            setwinbox.setText("winbox");
        }
        @DrawableRes
        public int getOnlineImage(boolean online){
            if (online) {
                return R.drawable.baseline_router_24;
            } else {
                return R.drawable.baseline_router_24_off;
            }
        }

        @Override
        public void onClick(View view) {

        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }

}
