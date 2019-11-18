package s.sensordemo

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import kotlinx.android.synthetic.main.activity_touch.*

@TargetApi(Build.VERSION_CODES.M)
class TouchActivity : AppCompatActivity(), GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener,
    GestureDetector.OnContextClickListener {
    override fun onContextClick(p0: MotionEvent?): Boolean {
        actionString.text = getString(R.string.on_context_click)
        return true
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        actionString.text = getString(R.string.dispatch_touch_event)
        return super.dispatchTouchEvent(ev)
    }

    override fun onShowPress(p0: MotionEvent?) {
        actionString.text = getString(R.string.on_show_press)
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        actionString.text = getString(R.string.on_single_tap_up)
        return true
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        actionString.text = getString(R.string.on_down)
        return true
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        actionString.text = getString(R.string.on_fling)
        return true
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        actionString.text = getString(R.string.on_scroll)
        return true
    }

    override fun onLongPress(p0: MotionEvent?) {
        actionString.text = getString(R.string.on_long_press)
    }

    override fun onDoubleTap(p0: MotionEvent?): Boolean {
        actionString.text = getString(R.string.on_double_tap)
        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent?): Boolean {
        actionString.text = getString(R.string.on_double_tap_event)
        return true
    }

    override fun onSingleTapConfirmed(p0: MotionEvent?): Boolean {
        actionString.text = getString(R.string.on_single_tap_confirmed)
        return true
    }

    private var gDetector: GestureDetectorCompat? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch)

        this.gDetector = GestureDetectorCompat(this, this)
        gDetector?.setOnDoubleTapListener(this)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        this.gDetector?.onTouchEvent(event)
        return layout.onTouchEvent(event)
    }
}
