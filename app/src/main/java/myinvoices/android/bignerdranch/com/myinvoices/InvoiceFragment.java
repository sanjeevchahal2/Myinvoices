package myinvoices.android.bignerdranch.com.myinvoices;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class InvoiceFragment  extends Fragment {
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_PHOTO= 1;
    private static final String DIALOG_DATE = "DialogDate";
    private static final String ARG_INVOICE_ID = "invoice_id";
    private Invoice invoInvoice;
    private EditText invoTitleField;
    private EditText invoShopName;
    private EditText invoComment;
    private Location invoLocation;
    private Button invoDateButton;
    private ImageButton invoButtonPhoto;
    private ImageView invoViewPhoto;
    private Button invoSave;
    private Button invoCancel;
    private CheckBox mSolvedCheckBox;
    private File invoPhotoFile;
    private PackageManager packageManager;


    public static InvoiceFragment newInstance(UUID invoiceId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_INVOICE_ID, invoiceId);
        InvoiceFragment fragment = new InvoiceFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID invoiceId = (UUID) getArguments().getSerializable(ARG_INVOICE_ID);
        invoInvoice = InvoiceDetail.get(getActivity()).getInvoices(invoiceId);

    }
    @Override public void onPause() {
        super.onPause();
        InvoiceDetail.get(getActivity()).updateInvoice(invoInvoice);}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_invoice, container, false);
        /**
         * For the title field
         */
        invoTitleField = (EditText) v.findViewById(R.id.invoice_title);
        invoTitleField.setText(invoInvoice.getTitle());
        invoTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                invoInvoice.setTitle(s.toString());            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        /**
         * for the shop field
         */
        invoShopName = (EditText) v.findViewById(R.id.invoice_shop);
        invoShopName.setText(invoInvoice.getShopName());
        invoShopName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                invoInvoice.setShopName(s.toString());            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        /**
         * for Invoice Comment
         */
        invoComment = (EditText) v.findViewById(R.id.invoice_cmnt);
        invoComment.setText(invoInvoice.getComment());
        invoComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                invoInvoice.setComment(s.toString());            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        /**
         * for Invoice location
         */

/**
 * for Invoice Type
 */


        /**
         * for Date Button
         */
        invoDateButton = (Button) v.findViewById(R.id.invoice_date);
        updateDate();
        invoDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(invoInvoice.getDate());
                dialog.setTargetFragment(InvoiceFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });
        /**
         * not needed actually
         */
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.invoice_solved);
        mSolvedCheckBox.setChecked(invoInvoice.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                invoInvoice.setSolved(isChecked);
            }        });
        /**
         *
         *for cancel button
         */
        invoCancel = (Button) v.findViewById(R.id.cancel_button);
        invoCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        /**
         * for save button
         *
         */
        invoSave = (Button) v.findViewById(R.id.save_button);
        invoSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;

        }

    }

    private void updateDate() {

        invoDateButton.setText(invoInvoice.getDate().toString());
    }

}
