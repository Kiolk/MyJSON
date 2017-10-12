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
    ArrayList<String> afePerson;
    ArrayList<String> mPersonPhone;
    LayoutInflater mLayoutInflater;

    public CustomAdapter(final Context applicationContext, final ArrayList<String> namePerson, final ArrayList<String> afePerson, final ArrayList<String> mPersonPhone) {
        this.context = applicationContext;
        this.namePerson = namePerson;
        this.afePerson = afePerson;
        this.mPersonPhone = mPersonPhone;
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
        final TextView age = (TextView) pView.findViewById(R.id.age_text_view);
        final TextView phone = (TextView) pView.findViewById(R.id.phone_text_view);
        phone.setText(mPersonPhone.get(pI));
        name.setText(namePerson.get(pI));
        age.setText(afePerson.get(pI));
        return pView;
    }
}
