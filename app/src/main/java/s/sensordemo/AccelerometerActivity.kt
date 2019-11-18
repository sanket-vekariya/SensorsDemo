package s.sensordemo

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_accelerometer.*


class AccelerometerActivity : AppCompatActivity() {

    private val accelerometerSensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            this@AccelerometerActivity.showToast("Accuracy is changed to $accuracy")
        }

        @RequiresApi(Build.VERSION_CODES.KITKAT)
        override fun onSensorChanged(event: SensorEvent) {
            text_accelerometer.text = ("""
                        x: """ + event.values[0] + """
                        y: """ + event.values[1] + """
                        z: """ + event.values[2] + """
                        """).trimIndent()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accelerometer)
        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        val list = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER) as List<Sensor>
        if (list.isNotEmpty())
            sensorManager.registerListener(
                accelerometerSensorEventListener,
                accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        else
            this@AccelerometerActivity.showToast("No Accelerometer available in device")
    }

    override fun onDestroy() {
        super.onDestroy()
        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(accelerometerSensorEventListener)
    }
}
