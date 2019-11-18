package s.sensordemo

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_accelerometer.setOnClickListener {
            startActivity(Intent(this, AccelerometerActivity::class.java))
        }
        btn_gyroscope.setOnClickListener {
            startActivity(Intent(this, GyroscopeActivity::class.java))
        }
        btn_light.setOnClickListener {
            startActivity(Intent(this, LightActivity::class.java))
        }
        btn_magnetometer.setOnClickListener {
            startActivity(Intent(this, MagnetometerActivity::class.java))
        }
        btn_temperature.setOnClickListener {
            startActivity(Intent(this, TemperatureActivity::class.java))
        }
        btn_compass.setOnClickListener {
            startActivity(Intent(this, CompassActivity::class.java))
        }
        btn_camera.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
        btn_touch.setOnClickListener {
            startActivity(Intent(this, TouchActivity::class.java))
        }
        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val list = sensorManager.getSensorList(Sensor.TYPE_ALL)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
        list_sensors.adapter = adapter
    }
}
