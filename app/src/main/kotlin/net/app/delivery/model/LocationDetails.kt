package net.app.delivery.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity

/**
 * Class which provides a model for location
 * @property lat the lattitude of the location
 * @property lng the lattitude of the location
 * @property address the lattitude of the location
 */
@Entity
data class LocationDetails(
        val lat: Double,
        val lng: Double,
        val address: String
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(lat)
        parcel.writeDouble(lng)
        parcel.writeString(address)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LocationDetails> {
        override fun createFromParcel(parcel: Parcel): LocationDetails {
            return LocationDetails(parcel)
        }

        override fun newArray(size: Int): Array<LocationDetails?> {
            return arrayOfNulls(size)
        }
    }
}