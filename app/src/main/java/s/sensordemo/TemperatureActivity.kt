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
import kotlinx.android.synthetic.main.activity_temperature.*

class TemperatureActivity : AppCompatActivity() {
    private val humiditySensorEventListener = object : SensorEventListener {
        @SuppressLint("SetTextI18n")
        override fun onSensorChanged(sensorEvent: SensorEvent) {
            text_demo_temperature_data.text =
                "current Temperature around this phone is (in Float) : ${sensorEvent.values[0]}"
            if (sensorEvent.values[0] > 30f) {
                window.decorView.setBackgroundColor(
                    ContextCompat.getColor(
                        this@TemperatureActivity,
                        R.color.control_background
                    )
                )
                text_demo_temperature.setTextColor(
                    ContextCompat.getColor(
                        this@TemperatureActivity,
                        R.color.color_yellow
                    )
                )
                text_demo_temperature_data.setTextColor(
                    ContextCompat.getColor(
                        this@TemperatureActivity,
                        R.color.color_yellow
                    )
                )
            } else {
                window.decorView.setBackgroundColor(
                    ContextCompat.getColor(
                        this@TemperatureActivity,
                        R.color.color_yellow
                    )
                )
                text_demo_temperature.setTextColor(
                    ContextCompat.getColor(
                        this@TemperatureActivity,
                        R.color.control_background
                    )
                )
                text_demo_temperature_data.setTextColor(
                    ContextCompat.getColor(
                        this@TemperatureActivity,
                        R.color.control_background
                    )
                )
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            this@TemperatureActivity.showToast("Accuracy is changed to $accuracy")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperature)
        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)

        sensorManager.registerListener(
            this.humiditySensorEventListener,
            humiditySensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(humiditySensorEventListener)
    }
}
