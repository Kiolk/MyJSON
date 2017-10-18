package comkiolk.github.myjson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> namePerson;
    ArrayList<String> mSentences;
    ArrayList<Long> mId;
    LayoutInflater mLayoutInflater;

    public CustomAdapter(final Context applicationContext, final ArrayList<String> namePerson, final ArrayList<String> mSentences, final ArrayList<Long> mIdNote) {
        this.context = applicationContext; //final ArrayList<String> afePerson, final ArrayList<String> mPersonPhone,
        this.namePerson = namePerson;
        this.mSentences = mSentences;
        this.mId = mIdNote;
        mLayoutInflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return namePerson.size();
    }

    @Override
    public Object getItem(final int pI) {
        return null;
    }

    @Override
    public long getItemId(int pI) {
        return 0;
    }

    @Override
    public View getView(final int pI, View pView, final ViewGroup pViewGroup) {
        pView = mLayoutInflater.inflate(R.layout.list_activity, null);
        final TextView name = (TextView) pView.findViewById(R.id.name_text_view);
        final TextView id = (TextView) pView.findViewById(R.id.id_text_view);
        final TextView sentences = (TextView) pView.findViewById(R.id.sentences_text_view);
       // phone.setText(mPersonPhone.get(pI));
        name.setText(namePerson.get(pI));
       // age.setText(afePerson.get(pI));
        sentences.setText(mSentences.get(pI));
        id.setText("" + mId.get(pI));
        return pView;
    }
}
