package net.hunau.windowslip.Fragment;



import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import net.hunau.windowslip.FragmentSix;

public class FragmentAdapter extends FragmentPagerAdapter {

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment);
    }*/

    List<Fragment> fragments;

    /**
     * Fragment实际只有Zero、One、Two这三张
     */
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<Fragment>();
        fragments.add(new FragmentSix());
        fragments.add(new FragmentFive());
        fragments.add(new FragmentFour());
        fragments.add(new FragmentThree());
        fragments.add(new FragmentTwo());
        fragments.add(new FragmentOne());
        fragments.add(new FragmentTwo());
        fragments.add(new FragmentThree());
        fragments.add(new FragmentFour());
        fragments.add(new FragmentFive());
        fragments.add(new FragmentSix());
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
