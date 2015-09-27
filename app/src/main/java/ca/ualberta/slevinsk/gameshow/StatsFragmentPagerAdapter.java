package ca.ualberta.slevinsk.gameshow;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by john on 15-09-19.
 */
public class StatsFragmentPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] { "Reaction Times", "Gameshow Buzzer" };
    final int PAGE_COUNT = tabTitles.length;
    private Context context;

    public StatsFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return StatsFragment.newInstance();
            case 1:
                return BuzzerStatsFragment.newInstance();
            default:
                throw new RuntimeException(String.format("Invalid page: %d", position));
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}