package ca.ualberta.slevinsk.gameshow;
/**
 * Copyright 2015 John Slevinsky
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
                return ReactionStatsFragment.newInstance();
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