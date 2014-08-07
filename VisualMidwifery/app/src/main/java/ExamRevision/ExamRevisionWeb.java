package ExamRevision;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import otago.Midwifery.R;

public class ExamRevisionWeb extends Fragment {

    public ExamRevisionWeb() {
        // Required empty public constructor
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
