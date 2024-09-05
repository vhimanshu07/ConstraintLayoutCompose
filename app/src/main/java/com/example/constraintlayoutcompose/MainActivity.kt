package com.example.constraintlayoutcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.constraintlayoutcompose.ui.theme.ConstraintLayoutComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         *
         *  dependency for constraintlayout added in gradle
         *
         */
        setContent {
            constraintLayout()
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun constraintLayout() {
        val constraints = ConstraintSet {
            /**
             *
             * id like in android:id in XML
             *
             */
            val greenBox = createRefFor("greenBox")
            val redBox = createRefFor("redBox")

            /**
             *
             *
             * We can make guideline too just like XML
             *
             */

            val guideLine = createGuidelineFromTop(0.5f)

            /**
             *
             * top to top of
             *
             */
            constrain(greenBox) {
                top.linkTo(guideLine)
                start.linkTo(parent.start)
                width = Dimension.value(100.dp)
                height = Dimension.value(100.dp)
            }

            constrain(redBox) {
                top.linkTo(parent.top)
                start.linkTo(greenBox.end)
                end.linkTo(parent.end)
                //  Dimension.fillToConstraints it is like 0 dp in XML
                //      width = Dimension.fillToConstraints
                width = Dimension.value(100.dp)
                height = Dimension.value(100.dp)
            }

            /**
             *
             * Creating chain (vertical or Horizontal)
             *
             */
            createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Spread)

        }
        ConstraintLayout(
            constraintSet = constraints,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Green)
                    .layoutId("greenBox")
            )
            Box(
                modifier = Modifier
                    .background(Color.Red)
                    .layoutId("redBox")
            )

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun GreetingPreview() {
    ConstraintLayoutComposeTheme {
        Greeting("Android")
    }
}