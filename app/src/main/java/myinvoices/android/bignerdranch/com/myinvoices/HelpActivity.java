package myinvoices.android.bignerdranch.com.myinvoices;

import android.support.v4.app.Fragment;

public class HelpActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new Help();
    }
}
