package ExamRevision;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joanzapata.pdfview.PDFView;

import otago.Midwifery.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class StaticSheetFS extends Fragment {

    PDFView pdfViewFS;
    private static final String SSFS = "SSFS.pdf";
    public StaticSheetFS() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_staticsheet_fs, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        pdfViewFS =(PDFView) getActivity().findViewById(R.id.pdfviewssfs);
        pdfViewFS.fromAsset(SSFS)
                //.pages(0, 2, 1, 3, 3, 3)
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                        //.onDraw(onDrawListener)
                        //.onLoad(onLoadCompleteListener)
                        //.onPageChange(onPageChangeListener)
                .load();
    }
}