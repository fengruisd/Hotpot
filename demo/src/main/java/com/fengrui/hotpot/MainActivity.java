package com.fengrui.hotpot;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fengrui.hotpot.demo.TabLayoutActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter();
        recyclerAdapter.addItem(new ItemInfo("自定义TabLayout", TabLayoutActivity.class));
        recyclerView.setAdapter(recyclerAdapter);
    }

    class ItemInfo {
        String desc;
        Class clz;

        ItemInfo(String desc, Class clz) {
            this.desc = desc;
            this.clz = clz;
        }
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        public RecyclerHolder(View itemView) {
            super(itemView);
        }
    }

    class RecyclerAdapter extends RecyclerView.Adapter<RecyclerHolder> {

        private List<ItemInfo> infoList = new ArrayList<>();

        void addItem(ItemInfo itemInfo) {
            infoList.add(itemInfo);
        }


        @Override
        public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(parent.getContext());
            textView.setPadding(16, 16, 16, 16);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.DKGRAY);
            return new RecyclerHolder(textView);
        }

        @Override
        public void onBindViewHolder(RecyclerHolder holder, final int position) {
            ((TextView)holder.itemView).setText(infoList.get(position).desc);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, infoList.get(position).clz));
                }
            });
        }

        @Override
        public int getItemCount() {
            return infoList.size();
        }
    }
}
