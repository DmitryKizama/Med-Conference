package com.stkizema.medconference.activities.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.stkizema.medconference.R;
import com.stkizema.medconference.db.DbTopicHelper;
import com.stkizema.medconference.model.Topic;
import com.stkizema.medconference.model.User;

public class CreateTopicDialog extends Dialog {

    private EditText etName, etDescription;
    private Button btnAdd, btnCancel;
    private User user;
    private Long confId;
    private CreateTopicDialogListener listener;

    public interface CreateTopicDialogListener {
        void onAddClick(Topic topic);
    }

    public CreateTopicDialog(Context context, User user, Long id, CreateTopicDialogListener listener) {
        super(context, R.style.AppTheme);
        this.user = user;
        this.confId = id;
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.topic_create_dialog);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.dimAmount = 0.7f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        ViewGroup parent = (ViewGroup) findViewById(R.id.parent);

//        parent.getLayoutParams().width = UIhelper.getW() - UIhelper.getPixel(30);

        etName = (EditText) findViewById(R.id.edtName);
        etDescription = (EditText) findViewById(R.id.edtDescription);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String description = etDescription.getText().toString();

                Topic topic = new Topic();
                topic.setName(name);
                topic.setDescription(description);
                topic.setCreatorId(user.getId());
                topic.setConferenceId(confId);
                DbTopicHelper.getTopicDao().insert(topic);
                listener.onAddClick(topic);
                dismiss();
            }
        });

    }
}