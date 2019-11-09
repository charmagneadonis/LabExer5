package com.adonis.labexer4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] companies, countries, industries, ceonames, description;
    int[] logo = {R.drawable.icbc, R.drawable.jpmorgan, R.drawable.chinaconstruction, R.drawable.agriculturalbank, R.drawable.newbankofamerica, R.drawable.apple, R.drawable.pingan, R.drawable.bankofchina, R.drawable.shell, R.drawable.wellsfargo, R.drawable.exxon, R.drawable.att, R.drawable.samsung, R.drawable.citigroup};
    ArrayList<ItemData> companydata = new ArrayList<>();
    ListView listcompanies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        companies = getResources().getStringArray(R.array.topCompanies);
        countries = getResources().getStringArray(R.array.countryNames);
        industries = getResources().getStringArray(R.array.industryNames);
        ceonames = getResources().getStringArray(R.array.ceoNames);
        description = getResources().getStringArray(R.array.companyInfo);

        for (int i = 0; i < companies.length; i++) {
            companydata.add(i, new ItemData(logo[i], companies[i], countries[i], industries[i], ceonames[i]));
        }

        ItemAdapter adapter = new ItemAdapter(this, R.layout.item, companydata);
        listcompanies = findViewById(R.id.lvCompanies);
        listcompanies.setAdapter(adapter);
        listcompanies.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int i, long id) {
        final File folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder, "company.txt");
        File file2 = new File(folder, "country.txt");
        File file3 = new File(folder, "ceo.txt");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            String choice = companies[i];
            fos.write(choice.getBytes());

            FileOutputStream fos2 = new FileOutputStream(file2);
            String choice2 = countries[i];
            fos2.write(choice2.getBytes());

            FileOutputStream fos3 = new FileOutputStream(file3);
            String choice3 = ceonames[i];
            fos3.write(choice3.getBytes());

            //Toast.makeText(this, companies[i], Toast.LENGTH_LONG).show();
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setIcon(logo[i]);
            dialog.setTitle(companies[i]);
            dialog.setMessage(description[i]);
            dialog.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    //Toast.makeText(MainActivity.this, companies[i] + " | " + countries[i] + " | " + ceonames[i], Toast.LENGTH_LONG).show();
                    try {
                        FileInputStream fin;
                        fin = new FileInputStream(new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/company.txt"));
                        int i;
                        String str = "";
                        while ((i = fin.read()) != -1) {
                            str += Character.toString((char) i);
                        }
                        fin.close();

                        fin = new FileInputStream(new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/country.txt"));
                        String str2 = "";
                        while ((i = fin.read()) != -1) {
                            str2 += Character.toString((char) i);
                        }
                        fin.close();

                        fin = new FileInputStream(new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/ceo.txt"));
                        String str3 = "";
                        while ((i = fin.read()) != -1) {
                            str3 += Character.toString((char) i);
                        }
                        fin.close();
                        Toast.makeText(MainActivity.this, str + " | " + str2 + " | " + str3, Toast.LENGTH_LONG).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            dialog.create().show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
