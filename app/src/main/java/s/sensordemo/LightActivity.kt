package s.sensordemo

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_light.*

class LightActivity : AppCompatActivity() {
    private val lightSensorEventListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(sensorEvent: SensorEvent) {
            text_demo_light_data.text =
                ("""
                            """ + this@LightActivity.getString(R.string.current_lignt_on_screen) + sensorEvent.values[0] + """
                            """).trimIndent()
            if (sensorEvent.values[0] > 30f) {
                window.decorView.setBackgroundColor(
                    ContextCompat.getColor(
                        this@LightActivity,
                        R.color.control_background
                    )
                )
                text_demo_light.setTextColor(
                    ContextCompat.getColor(
                        this@LightActivity,
                        R.color.color_yellow
                    )
                )
                text_demo_light_data.setTextColor(
                    ContextCompat.getColor(
                        this@LightActivity,
                        R.color.color_yellow
                    )
                )
            } else {
                window.decorView.setBackgroundColor(
                    ContextCompat.getColor(
                        this@LightActivity,
                        R.color.color_yellow
                    )
                )
                text_demo_light.setTextColor(
                    ContextCompat.getColor(
                        this@LightActivity,
                        R.color.control_background
                    )
                )
                text_demo_light_data.setTextColor(
                    ContextCompat.getColor(
                        this@LightActivity,
                        R.color.control_background
                    )
                )
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            this@LightActivity.showToast("Accuracy is changed to $accuracy")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_light)
        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val lightSensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        sensorManager.registerListener(
            this.lightSensorEventListener,
            lightSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(lightSensorEventListener)
    }
}
