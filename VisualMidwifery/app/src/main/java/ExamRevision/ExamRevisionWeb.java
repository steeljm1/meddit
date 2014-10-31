package ExamRevision;

import android.app.ProgressDialog;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import otago.Arb.R;

//page for examrevisionpager

public class ExamRevisionWeb extends Fragment {
    //this fragment is contained a webview
    //the webview will load certain url
    WebView mywebview;
    String url;
    WebSettings webSettings;
    ProgressDialog mProgress;

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
        mywebview = (WebView) getActivity().findViewById(R.id.webviewExamRevision);
        mywebview.setWebViewClient(new MyBrowser());
        mProgress = ProgressDialog.show(getActivity(), "Loading", "Please wait for a moment...");
        mProgress.setCanceledOnTouchOutside(false);

        webSettings = mywebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setSupportZoom(true);
        webSettings.setSavePassword(false);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setDisplayZoomControls(false);

        url = getResources().getString(R.string.moodleURL)+"10";
        //hard code the url because the id of exam on moodle with not change
        mywebview.loadUrl(url);

    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, final String url) {
            if(mProgress.isShowing()) {
                mProgress.dismiss();
            }
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();//if users are not login the page will be locked by login page
            //the login page has certificate user must accept so if the webview get certificate "error" the default is yes
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            mywebview.loadUrl("file:///android_asset/myerrorpage.html");
        }
    }
}
