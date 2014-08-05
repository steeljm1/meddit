package ExamRevision;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import otago.Midwifery.R;

/**
 * Created by Bo on 5/08/2014.
 */
public class ExamRevisionPagerAdapter extends FragmentStatePagerAdapter {
    public ExamRevisionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new StaticSheet();
            case 2:
                return new StaticSheet();
            case 3:
                return new StaticSheet();
            case 0:
                return new PlaceholderFragment();

            default:
                return new PlaceholderFragment();
        }
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_exam_revision, container, false);
            return rootView;
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            WebView mywebview = (WebView) getActivity().findViewById(R.id.webviewExamRevision);
            mywebview.setWebViewClient(new MyBrowser());

            //mywebview.setScrollBarStyle(view.SCROLLBARS_INSIDE_OVERLAY);

            WebSettings webSettings = mywebview.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setLoadsImagesAutomatically(true);
            webSettings.setSupportZoom(true);
            webSettings.setBuiltInZoomControls(true);
            //webSettings.setDisplayZoomControls(true);
            webSettings.setUseWideViewPort(true);
            String url = getResources().getString(R.string.moodleURL)+"10";
            mywebview.loadUrl(url);
        }
        private class MyBrowser extends WebViewClient {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        }
    }

    public static class StaticSheet extends Fragment
    {
        public StaticSheet()
        {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_staticsheet, container, false);
            return rootView;
        }
    }
}
