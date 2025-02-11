package xyz.teamgravity.composeanimationpulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import xyz.teamgravity.composeanimationpulse.ui.theme.ComposeAnimationPulseTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeAnimationPulseTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { padding ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {
                        val transition = rememberInfiniteTransition(
                            label = "transition"
                        )
                        val progress by transition.animateFloat(
                            initialValue = 0F,
                            targetValue = 1F,
                            animationSpec = infiniteRepeatable(
                                animation = tween(
                                    durationMillis = 1_000
                                )
                            ),
                            label = "progress"
                        )

                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .graphicsLayer {
                                    scaleX = progress
                                    scaleY = progress
                                    alpha = 1F - progress
                                }
                                .border(
                                    width = 5.dp,
                                    color = Color.Red,
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }
        }
    }
}