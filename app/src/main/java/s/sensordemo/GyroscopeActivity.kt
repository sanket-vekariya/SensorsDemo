package s.sensordemo

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_gyroscope.*


class GyroscopeActivity : AppCompatActivity() {

    private val gyroscopeSensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(sensorEvent: SensorEvent) {
            if (sensorEvent.values[2] > 0.5f) {
                window.decorView.setBackgroundColor(
                    ContextCompat.getColor(
                        this@GyroscopeActivity,
                        R.color.control_background
                    )
                )
                text_demo_gyroscope.setTextColor(
                    ContextCompat.getColor(
                        this@GyroscopeActivity,
                        R.color.color_yellow
                    )
                )
            } else if (sensorEvent.values[2] < -0.5f) {
                window.decorView.setBackgroundColor(
                    ContextCompat.getColor(
                        this@GyroscopeActivity,
                        R.color.color_yellow
                    )
                )
                text_demo_gyroscope.setTextColor(
                    ContextCompat.getColor(
                        this@GyroscopeActivity,
                        R.color.control_background
                    )
                )
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            this@GyroscopeActivity.showToast("Accuracy is changed to $accuracy")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gyroscope)

        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        sensorManager.registerListener(
            gyroscopeSensorEventListener,
            gyroscopeSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(gyroscopeSensorEventListener)
    }
}
