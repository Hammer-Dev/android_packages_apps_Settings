package com.android.settings.triangles;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceScreen;
import android.provider.Settings;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.triangles.widgets.SeekBarPreference;

public class Scroll extends SettingsPreferenceFragment implements OnPreferenceChangeListener {
	
	private static final String OVERSCROLL_PREF = "pref_overscroll_effect";
	
	private static final String OVERSCROLL_WEIGHT_PREF = "pref_overscroll_weight";

	private ListPreference mOverscrollPref;
	private ListPreference mOverscrollWeightPref;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    addPreferencesFromResource(R.xml.scroll_settings);
		
	    PreferenceScreen prefSet = getPreferenceScreen();
		
	    mOverscrollPref = (ListPreference) prefSet.findPreference(OVERSCROLL_PREF);
            int overscrollEffect = Settings.System.getInt(getContentResolver(), Settings.System.OVERSCROLL_EFFECT, 1);
            mOverscrollPref.setValue(String.valueOf(overscrollEffect));
            mOverscrollPref.setOnPreferenceChangeListener(this);

            mOverscrollWeightPref = (ListPreference) prefSet.findPreference(OVERSCROLL_WEIGHT_PREF);
            int overscrollWeight = Settings.System.getInt(getContentResolver(), Settings.System.OVERSCROLL_WEIGHT, 5);
            mOverscrollWeightPref.setValue(String.valueOf(overscrollWeight));
            mOverscrollWeightPref.setOnPreferenceChangeListener(this);

	}
	
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		if (preference == mOverscrollPref) {
		    int overscrollEffect = Integer.valueOf((String) newValue);
	            Settings.System.putInt(getContentResolver(), Settings.System.OVERSCROLL_EFFECT, overscrollEffect);
		    return true;
		} else if (preference == mOverscrollWeightPref) {
		    int overscrollWeight = Integer.valueOf((String) newValue);
           	    Settings.System.putInt(getContentResolver(), Settings.System.OVERSCROLL_WEIGHT, overscrollWeight);
           	    return true;
		}
		return false;
	}
	
}
