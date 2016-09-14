package myapplication.pc1.com.homeworkfirstex;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentOne extends Fragment implements View.OnClickListener {

    public interface IBtnPressed
    {
        public void onButtonPressed(String dataToExchange);
        public void onButtonReplacePressed();
    }

    Button btnClick,btnReplace;
    IBtnPressed callback;

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        callback = (IBtnPressed)context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_layout,container,false);
        btnClick = (Button)view.findViewById(R.id.btnAdd);
        btnReplace = (Button)view.findViewById(R.id.btnReplace);

        btnClick.setOnClickListener(this);
        btnReplace.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnAdd)
        {
            if(callback != null)
                callback.onButtonPressed("Android is so cool !");
        }

        if(v.getId() == R.id.btnReplace)
        {
            if(callback != null)
                callback.onButtonReplacePressed();
        }
    }
}
