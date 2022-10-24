package com.example.year4project

import android.os.Parcel
import android.os.Parcelable

data class speconRecy(

    val specialistFirstname:String?= null,
    val specialistProfessionPractised: String?= null,
    val rate: String?= null,
    val specialistYearsOfExperience:String?= null,
    val specialistAge: String?= null

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(specialistFirstname)
        parcel.writeString(specialistProfessionPractised)
        //parcel.writeString(specialistHospitalPractisng)
        parcel.writeString(specialistYearsOfExperience)
        parcel.writeString(specialistAge)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<speconRecy> {
        override fun createFromParcel(parcel: Parcel): speconRecy {
            return speconRecy(parcel)
        }

        override fun newArray(size: Int): Array<speconRecy?> {
            return arrayOfNulls(size)
        }
    }
}
