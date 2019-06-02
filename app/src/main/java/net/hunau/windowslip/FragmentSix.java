package net.hunau.windowslip;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.hunau.windowslip.DBAdapter.DBAdapter;
import net.hunau.windowslip.R;
import net.hunau.windowslip.entity.User;


public class FragmentSix extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 第一个参数是这个Fragment将要显示的界面布局,第二个参数是这个Fragment所属的Activity,第三个参数是决定此fragment是否附属于Activity
        View view=inflater.inflate(R.layout.item6, container, false);
        String msg="";
        TextView displayView = (TextView) view.findViewById(R.id.display);
        msg=((MainActivity)getActivity()).getMsg();
        displayView.setText(msg);
        return view;
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("FragmentSix onCreate");
    }*/

    public void onResume(){
        super.onResume();
        System.out.println("FragmentSix onResume");
    }

    @Override
    public void onPause(){
        super.onPause();
        System.out.println("FragmentSix onPause");
    }

    @Override
    public void onStop(){
        super.onStop();
        System.out.println("FragmentSix onStop");
    }

}
