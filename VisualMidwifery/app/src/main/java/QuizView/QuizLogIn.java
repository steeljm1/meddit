package QuizView;



import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;

import java.sql.SQLException;
import java.util.ArrayList;

import DBController.DatabaseController;
import DBTables.MainCategoryTable;
import Fragments.BaseFragment;
import Models.ContentCategoryModel;
import Models.MainCategoryModel;
import otago.Midwifery.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class QuizLogIn extends BaseFragment {

    public QuizLogIn() {
        // Required empty public constructor
    }

    int mainID;
    DatabaseController dataSource;
    ArrayList<MainCategoryModel> allMain;
    ProgressDialog mProgress;
    WebView mywebview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz_log_in, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        mainID = mActivity.getmMainID();
        dataSource = new DatabaseController(getActivity());
        try {
            allMain = dataSource.GetAllMainCategory();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mywebview = (WebView) mActivity.findViewById(R.id.webview);
        mywebview.setWebViewClient(new MyBrowser());

        //mywebview.setScrollBarStyle(view.SCROLLBARS_INSIDE_OVERLAY);
        mProgress = ProgressDialog.show(getActivity(), "Loading", "Please wait for a moment...");


        WebSettings webSettings = mywebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        //webSettings.setDisplayZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setDisplayZoomControls(false);
        String url = getResources().getString(R.string.loginURL);
        if(mainID == 1){
            url = getResources().getString(R.string.moodleURL)+"2";
        }
        if(mainID == 2){
            url = getResources().getString(R.string.moodleURL)+"7";
        }
        if(mainID == 3){
            url = getResources().getString(R.string.moodleURL)+"9";
        }
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
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            mywebview.loadUrl("file:///android_asset/myerrorpage.html");
        }
    }
}
