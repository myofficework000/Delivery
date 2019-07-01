package net.app.delivery.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Class which provides a model for post
 * @constructor Sets all properties of the post
 * @property id the unique identifier of the post
 * @property title the title of the post
 * @property imageUrl the content of the post
 */
@Entity
data class Post(
        @field:PrimaryKey
        val id: Int,
        val description: String,
        val imageUrl: String,
        @Embedded        val location: LocationDetails
) :Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readParcelable(LocationDetails::class.java.classLoader)) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(id)
                parcel.writeString(description)
                parcel.writeString(imageUrl)
                parcel.writeParcelable(location, flags)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Post> {
                override fun createFromParcel(parcel: Parcel): Post {
                        return Post(parcel)
                }

                override fun newArray(size: Int): Array<Post?> {
                        return arrayOfNulls(size)
                }
        }
}