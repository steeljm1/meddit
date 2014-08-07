package ExamRevision;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joanzapata.pdfview.PDFView;

import otago.Midwifery.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class StaticSheetMP extends Fragment {
    PDFView pdfViewMP;
    private static final String SSMP = "SSMP.pdf";

    public StaticSheetMP() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_staticsheet_mp, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        pdfViewMP =(PDFView) getActivity().findViewById(R.id.pdfviewssmp);
        pdfViewMP.fromAsset(SSMP)
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
