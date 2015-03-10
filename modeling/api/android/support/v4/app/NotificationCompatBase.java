/*
 * Copyright (C) 2015,  Massachusetts Institute of Technology
 * 
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 * Please email droidsafe@lists.csail.mit.edu if you need additional
 * information or have any questions.
 * 
 * 
 * This file incorporates work covered by the following copyright and
 * permission notice:
 *
 * Copyright (C) 2014 The Android Open Source Project
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


/***** THIS FILE HAS BEEN MODIFIED FROM THE ORIGINAL BY THE DROIDSAFE PROJECT. *****/


package android.support.v4.app;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
import android.app.PendingIntent;
import android.os.Bundle;

class NotificationCompatBase {

    public static abstract class Action {
        @DSSafe(DSCat.SAFE_LIST)
        protected abstract int getIcon();
        @DSSafe(DSCat.SAFE_LIST)
        protected abstract CharSequence getTitle();
        @DSSafe(DSCat.SAFE_LIST)
        protected abstract PendingIntent getActionIntent();
        @DSSafe(DSCat.SAFE_LIST)
        protected abstract Bundle getExtras();
        @DSSafe(DSCat.SAFE_LIST)
        protected abstract RemoteInputCompatBase.RemoteInput[] getRemoteInputs();

        public interface Factory {
            Action build(int icon, CharSequence title, PendingIntent actionIntent,
                    Bundle extras, RemoteInputCompatBase.RemoteInput[] remoteInputs);
            public Action[] newArray(int length);
        }
    }
}