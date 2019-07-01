package net.app.delivery.ui.post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import net.app.delivery.R
import net.app.delivery.databinding.ActivityMapMarkerBinding
import net.app.delivery.model.Post

class MapMarkerActivity : AppCompatActivity() , OnMapReadyCallback {

    private var mMap: GoogleMap? = null
    var post : Post?=null
    private lateinit var viewModel: PostViewModel
    private lateinit var binding: ActivityMapMarkerBinding


    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0
        val latLngData = post?.location?.lat?.let { post?.location?.lng?.let { it1 -> LatLng(it, it1) } }
        mMap?.let {
            it.addMarker(latLngData?.let { it1 -> MarkerOptions().position(it1).title(post?.location?.address) })
            it.moveCamera(CameraUpdateFactory.newLatLng(latLngData))
            it.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngData, 18f))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map_marker)

        viewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)
        binding.viewModel=viewModel

        //call getIntent
        getIntentData()
        post?.let { viewModel.bind(it) }

        //find map
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    fun getIntentData()
    {
        post = intent.extras.getParcelable<Post>("deliveryData")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out)
    }
}
