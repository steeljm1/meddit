package ExamRevision;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import otago.Arb.R;

/**
 * Created by Bo on 5/08/2014.
 * this fragment contains a pagerview
 * this fragment will also use pdf library
 * pdfs are static so hard code those pages
 */
public class ExamRevisionPagerAdapter extends FragmentStatePagerAdapter {
    Context context;

    public ExamRevisionPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;
        if(position == 0)
        {
            f = new ExamRevisionWeb();
        }

        if(position == 1)
        {
            f = new StaticSheetFS();
        }
        if(position == 2)
        {
            f = new StaticSheetMP();
        }
        if(position == 3)
        {
            f = new StaticSheetML();
        }
        return f;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Resources resources = context.getResources();

        String title= "Exam Revision";
        switch (position) {
            case 1:
                title = resources.getString(R.string.ER_page1);
                break;
            case 2:
                title = resources.getString(R.string.ER_page2);
                break;
            case 3:
                title = resources.getString(R.string.ER_page3);
                break;
            case 0:
                title = resources.getString(R.string.ER_default);
                break;
            default:
                title = resources.getString(R.string.ER_default);
                break;
        }
        return title;
    }
}
