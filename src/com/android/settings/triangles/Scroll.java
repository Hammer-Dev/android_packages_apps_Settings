
package com.android.settings.triangles;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceScreen;
import android.provider.Settings;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.triangles.SeekBarPreference;

public class Scroll extends SettingsPreferenceFragment implements OnPreferenceChangeListener {
	
	private static final String OVERSCROLL_PREF = "pref_overscroll_effect";
	
	private static final String OVERSCROLL_WEIGHT_PREF = "pref_overscroll_weight";

	private static final String PREF_SCROLL_FRICTION = "scroll_friction";

	private static final String PREF_CUSTOM_FLING_VELOCITY = "custom_fling_velocity";
	
	private ListPreference mOverscrollPref;
	private ListPreference mOverscrollWeightPref;
	private SeekBarPreference mScrollFriction;
	private SeekBarPreference mCustomFlingVelocity;
	
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

	    float defaultFriction = Settings.System.getFloat(getActivity().getContentResolver(), Settings.System.SCROLL_FRICTION, 0.015f);

	    mScrollFriction = (SeekBarPreference) findPreference(PREF_SCROLL_FRICTION);
	    mScrollFriction.setInitValue((int) (defaultFriction * 5000));
	    mScrollFriction.setOnPreferenceChangeListener(this);

	    int defaultVelocity = Settings.System.getInt(getActivity().getContentResolver(), Settings.System.CUSTOM_FLING_VELOCITY, 8000);

	    mCustomFlingVelocity = (SeekBarPreference) findPreference(PREF_CUSTOM_FLING_VELOCITY);
	    mCustomFlingVelocity.setInitValue((int) (defaultVelocity / 100));
	    mCustomFlingVelocity.setOnPreferenceChangeListener(this);
	
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
		} else if (preference == mScrollFriction) {
		    float val = Float.parseFloat((String) newValue);
		    Settings.System.putFloat(getActivity().getContentResolver(), Settings.System.SCROLL_FRICTION, val / 5000);
		    return true;
		} else if (preference == mCustomFlingVelocity) {
		    int val = Integer.parseInt((String) newValue);
		    Settings.System.putInt(getActivity().getContentResolver(), Settings.System.CUSTOM_FLING_VELOCITY, val * 100);
		    return true;
		}
		return false;
	}
	
}
