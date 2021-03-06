package myapplication.pc1.com.homeworkthirdex;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>{

    private ArrayList<String> mAdapterData;
    private ArrayList<String> mAdapterData2;
    private ArrayList<String> mAdapterData3;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        TextView mTextView2;
        TextView mTextView3;

        public ViewHolder(View itemView){
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.textView);
            mTextView2 = (TextView) itemView.findViewById(R.id.textView2);
            mTextView3 = (TextView) itemView.findViewById(R.id.textView3);
        }
    }

    public RecycleViewAdapter(ArrayList<String> data, ArrayList<String> data2, ArrayList<String> data3, ArrayList<ImageButton> data4){
        this.mAdapterData = data;
        this.mAdapterData2 = data2;
        this.mAdapterData3 = data3;
    }

    @Override
    public int getItemCount(){
        return mAdapterData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_template, parent, false);

        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder != null){
            holder.mTextView.setText(mAdapterData.get(position));
            holder.mTextView2.setText(mAdapterData2.get(position));
            holder.mTextView3.setText(mAdapterData3.get(position));
        }
    }
}
