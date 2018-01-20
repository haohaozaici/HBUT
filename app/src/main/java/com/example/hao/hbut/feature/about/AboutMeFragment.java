package com.example.hao.hbut.feature.about;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.customtabs.CustomTabsIntent;

import com.example.hao.hbut.R;


/**
 * Created by wwfm on 2017/3/18.
 */

public class AboutMeFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {

    private Preference mPrefVersion, mPrefSina,
            mPrefGithub, mPrefWexin, mPrefLicense;
    private Preference mPrefIconDesigner, mPrefContributors;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_main);

        mPrefVersion = findPreference("version");
        mPrefGithub = findPreference("github");
        mPrefWexin = findPreference("weixin");
        mPrefSina = findPreference("sina");
        mPrefIconDesigner = findPreference("designer");
        mPrefLicense = findPreference("license");
        mPrefContributors = findPreference("contributors");

        String versionName = null;
        int versionCode = 0;
        try {
            versionName = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
            versionCode = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        mPrefVersion.setSummary(versionName + "  " + versionCode);

        mPrefVersion.setOnPreferenceClickListener(this);
        mPrefGithub.setOnPreferenceClickListener(this);
        mPrefWexin.setOnPreferenceClickListener(this);
        mPrefSina.setOnPreferenceClickListener(this);
        mPrefLicense.setOnPreferenceClickListener(this);
        mPrefIconDesigner.setOnPreferenceClickListener(this);
        mPrefContributors.setOnPreferenceClickListener(this);


    }

    @Override
    public boolean onPreferenceClick(Preference preference) {

        if (preference == mPrefGithub) {
            openWebsite(getString(R.string.github_repo_url));
            return true;
        }
        if (preference == mPrefSina) {
            openWebsite(getString(R.string.author_sina_desc));
            return true;
        }
        return false;
    }

    public void openWebsite(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(getResources().getColor(R.color.colorPrimary));
        builder.build().launchUrl(getActivity(), Uri.parse(url));
    }

}
