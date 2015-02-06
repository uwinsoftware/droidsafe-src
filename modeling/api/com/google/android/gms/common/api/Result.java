package com.google.android.gms.common.api;

import droidsafe.annotations.*;
import droidsafe.runtime.*;
import droidsafe.helpers.*;
public abstract interface Result
{

    public abstract @DSSafe(DSCat.SAFE_OTHERS)
    com.google.android.gms.common.api.Status getStatus();
}