package s.sensordemo

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_magnetometer.*

class MagnetometerActivity : AppCompatActivity() {
    private val magnetometerSensorEventListener = object : SensorEventListener {
        @SuppressLint("SetTextI18n")
        override fun onSensorChanged(sensorEvent: SensorEvent) {
            text_demo_magnetometer_data.text =
                "current magnetic field with your phone is (in Float): ${sensorEvent.values[0]}"
            if (sensorEvent.values[0] > 30f) {
                window.decorView.setBackgroundColor(
                    ContextCompat.getColor(
                        this@MagnetometerActivity,
                        R.color.control_background
                    )
                )
                text_demo_magnetometer.setTextColor(
                    ContextCompat.getColor(
                        this@MagnetometerActivity,
                        R.color.color_yellow
                    )
                )
                text_demo_magnetometer_data.setTextColor(
                    ContextCompat.getColor(
                        this@MagnetometerActivity,
                        R.color.color_yellow
                    )
                )
            } else {
                window.decorView.setBackgroundColor(
                    ContextCompat.getColor(
                        this@MagnetometerActivity,
                        R.color.color_yellow
                    )
                )
                text_demo_magnetometer.setTextColor(
                    ContextCompat.getColor(
                        this@MagnetometerActivity,
                        R.color.control_background
                    )
                )
                text_demo_magnetometer_data.setTextColor(
                    ContextCompat.getColor(
                        this@MagnetometerActivity,
                        R.color.control_background
                    )
                )
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            this@MagnetometerActivity.showToast("Accuracy is changed to $accuracy")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_magnetometer)
        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        sensorManager.registerListener(
            this.magnetometerSensorEventListener,
            magnetometer,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(magnetometerSensorEventListener)
    }
}
