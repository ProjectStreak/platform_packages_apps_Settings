/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.os.SystemProperties;
import androidx.annotation.VisibleForTesting;

import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;

public class StreakVersionPreferenceController extends BasePreferenceController {

    @VisibleForTesting
    private static final String ROM_VERSION_PROP = "ro.streak.version";
    private static final String ROM_RELEASETYPE_PROP = "ro.streak.build.type";
    private static final String ROM_CODENAME_PROP = "ro.streak.codename";

    public StreakVersionPreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public CharSequence getSummary() {
        String streakVersion = SystemProperties.get(ROM_VERSION_PROP,
                mContext.getString(R.string.device_info_default));
        String streakReleasetype =  SystemProperties.get(ROM_RELEASETYPE_PROP,
                this.mContext.getString(R.string.device_info_default));
        String streakCodename = SystemProperties.get(ROM_CODENAME_PROP,
                mContext.getString(R.string.device_info_default));
        if (!streakVersion.isEmpty() && !streakReleasetype.isEmpty())
            return streakVersion + " | " + streakCodename;
        else
            return mContext.getString(R.string.device_info_default);
    }
}
