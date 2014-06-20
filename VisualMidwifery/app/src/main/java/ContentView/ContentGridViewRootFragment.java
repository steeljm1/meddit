package ContentView;



import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;

import otago.Midwifery.R;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 *
 */
public class ContentGridViewRootFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.content_gridview_root, container, false);

        Fragment contentFrag = new ContentGridViewFragment();
        contentFrag.setArguments(getArguments());

        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.GridRootContainer, contentFrag, "GridViewFragment");
        ft.commit();

        return v;
    }
}
