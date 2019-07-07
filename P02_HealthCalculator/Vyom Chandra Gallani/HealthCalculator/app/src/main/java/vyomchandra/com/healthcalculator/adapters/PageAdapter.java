package vyomchandra.com.healthcalculator.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vyomchandra.com.healthcalculator.fragments.gain;
import vyomchandra.com.healthcalculator.fragments.loose;
import vyomchandra.com.healthcalculator.fragments.normal;


public class PageAdapter extends FragmentPagerAdapter {
    private int numoftabs;
    public PageAdapter(FragmentManager fm,int numoftabs){
        super(fm);
        this.numoftabs=numoftabs;
    }
    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new loose();
            case 1:
                return new gain();
            case 2:
                return new normal();
        }
        return null;
    }

    @Override
    public int getCount() {
        return numoftabs;
    }
}
