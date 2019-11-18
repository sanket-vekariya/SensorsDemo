package s.sensordemo

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.Sensor.TYPE_ORIENTATION
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_compass.*
import kotlin.math.roundToLong

class CompassActivity : AppCompatActivity() {
    private val compassEventListener = object : SensorEventListener {
        @SuppressLint("SetTextI18n")
        override fun onSensorChanged(sensorEvent: SensorEvent) {
            text_demo_compass_data.text =
                "current direction is (in Float) \nx: ${sensorEvent.values[0]} \ny: ${sensorEvent.values[1]} \nz: ${sensorEvent.values[2]}"
            val degree = sensorEvent.values[0].roundToLong()// - 52.24103674f
            val currentDegree = -degree.toFloat()
            val ra = RotateAnimation(
                currentDegree,
                -degree.toFloat(),
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )
            ra.duration = 1
            ra.fillAfter = true
            if (sensorEvent.values[1] < -40f || sensorEvent.values[2] > 30f) {
                image_compass.setImageResource(R.drawable.ic_compass_error)
                image_compass.clearAnimation()
            } else {
                image_compass.setImageResource(R.drawable.compass)
                image_compass.startAnimation(ra)
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            this@CompassActivity.showToast("Accuracy is changed to $accuracy")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compass)
        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val compassSensor = sensorManager.getDefaultSensor(TYPE_ORIENTATION)
        sensorManager.registerListener(
            this.compassEventListener,
            compassSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(compassEventListener)
    }
}