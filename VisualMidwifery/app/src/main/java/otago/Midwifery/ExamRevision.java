package otago.Midwifery;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import Fragments.BaseFragment;
import otago.Midwifery.R;

public class ExamRevision extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_revision);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
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

}
