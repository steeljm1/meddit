package ExamRevision;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joanzapata.pdfview.PDFView;

import otago.Arb.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class StaticSheetMP extends Fragment {
    //this fragment is contained a pdf and using pdf reader library
    PDFView pdfViewMP;
    private String SSMP;

    public StaticSheetMP() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_staticsheet_mp, container, false);
        SSMP = getString(R.string.static_sheet_name2);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        pdfViewMP =(PDFView) getActivity().findViewById(R.id.pdfviewssmp);
        pdfViewMP.fromAsset(SSMP)
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .load();
    }
}
