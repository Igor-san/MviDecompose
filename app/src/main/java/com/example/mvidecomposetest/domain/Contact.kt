package com.example.mvidecomposetest.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
    val id: Int = -1,
    val username: String,
    val phone: String
): Parcelable
