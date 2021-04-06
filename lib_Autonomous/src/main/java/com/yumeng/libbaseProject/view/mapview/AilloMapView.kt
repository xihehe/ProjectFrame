package com.yumeng.libbaseProject.view.mapview

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.yumeng.libbaseProject.R
import com.yumeng.libcommon.listener.ListenableFuture
import com.yumeng.libcommon.listener.SettableFuture
import kotlinx.android.synthetic.main.widget_map_view.view.*

class AilloMapView : LinearLayout {

    private var mapView: MapView? = null
    private var imageView: ImageView? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initialize(context)
    }

    private fun initialize(context: Context) {
        orientation = VERTICAL
        val inflate = LayoutInflater.from(context).inflate(R.layout.widget_map_view, this, true)
        mapView = inflate.findViewById(R.id.map)
        imageView = inflate.findViewById(R.id.imageView)
    }

    fun display(place: ChatPlace): ListenableFuture<Bitmap> {
        val future = SettableFuture<Bitmap>()

        this.mapView?.onCreate(null)
        this.mapView?.onResume()

        this.mapView?.visibility = View.VISIBLE
        this.imageView?.visibility = View.GONE
        val latLng = LatLng(place.latitude ?: 0.0, place.longitude ?: 0.0)
        this.mapView?.getMapAsync { googleMap ->
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
            googleMap.addMarker(MarkerOptions().position(latLng))
            googleMap.isBuildingsEnabled = true
            googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            googleMap.uiSettings.setAllGesturesEnabled(false)
            googleMap.setOnMapLoadedCallback {
                googleMap.snapshot { bitmap ->
                    future.set(bitmap)
                    imageView?.setImageBitmap(bitmap)
                    imageView?.visibility = View.VISIBLE
                    mapView?.visibility = View.GONE
                    mapView?.onPause()
                    mapView?.onDestroy()
                }
            }
        }

        this.textView.text = "${place.name}${place.address}"

        return future
    }

}