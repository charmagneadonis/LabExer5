package com.adonis.labexer4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<ItemData> {
    private Context context;
    private int resource;

    public ItemAdapter(@NonNull Context context, int resource, @NonNull List<ItemData> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override

    public View getView(int i, @Nullable View convertView, @NonNull ViewGroup parent) {
        int logo = getItem(i).getLogo();
        String company = getItem(i).getName();
        String country = getItem(i).getCountry();
        String industry = getItem(i).getIndustry();
        String ceo = getItem(i).getCeo();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        ImageView img = convertView.findViewById(R.id.ivLogo);
        TextView companyname = convertView.findViewById(R.id.tvName);
        TextView countryname = convertView.findViewById(R.id.tvCountry);
        TextView industryname = convertView.findViewById(R.id.tvIndustry);
        TextView ceoname = convertView.findViewById(R.id.tvCEO);

        img.setImageResource(logo);
        companyname.setText(company);
        countryname.setText(country);
        industryname.setText(industry);
        ceoname.setText(ceo);
        return convertView;


    }
}
