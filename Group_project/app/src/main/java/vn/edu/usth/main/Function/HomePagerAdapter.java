package vn.edu.usth.main.Function;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class HomePagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 3;
    private String titles[] = new String[] { "Home", "Foods", "Drinks" };
    public HomePagerAdapter(FragmentManager fm){

        super (fm);
    }
    @Override
    public Fragment getItem(int page) {
        // returns an instance of Fragment corresponding to the specified page
        switch (page) {
            case 0:
                HomeFragment home_site= new HomeFragment();
                return home_site;
            case 1:
                FoodFragment food_site= new FoodFragment();
                return food_site;
            case 2:
                DrinkFragment drink_site= new DrinkFragment();
                return drink_site;
        }
        return null; // failsafe
        }


    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
    @Override
    public CharSequence getPageTitle(int position) {
// returns a tab title corresponding to the specified page
        return titles[position];
    }

}
