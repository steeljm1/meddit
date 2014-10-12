package ExamRevision;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Bo on 5/08/2014.
 * this fragment contains a pagerview
 * this fragment will also use pdf library
 * pdfs are static so hard code those pages
 */
public class ExamRevisionPagerAdapter extends FragmentStatePagerAdapter {
    public ExamRevisionPagerAdapter(FragmentManager fm) {
        super(fm);
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
        String title= "Exam Revision";
        switch (position) {
            case 1:
                title = "Fetal Skull";
                break;
            case 2:
                title = "Maternal Pelvis";
                break;
            case 3:
                title = "Mechanism of Labor";
                break;
            case 0:
                title = "Exam Revision";
                break;
            default:
                title = "Exam Revision";
                break;
        }
        return title;
    }
}
