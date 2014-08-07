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
public class StaticSheetML extends Fragment {

    PDFView pdfViewML;
    private static final String SSML = "SSML.pdf";
    public StaticSheetML() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_staticsheet_ml, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        pdfViewML =(PDFView) getActivity().findViewById(R.id.pdfviewssml);
        pdfViewML.fromAsset(SSML)
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
