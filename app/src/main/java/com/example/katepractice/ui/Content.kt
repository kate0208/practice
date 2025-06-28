package com.example.katepractice.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.katepractice.ui.theme.AppTheme
import com.example.katepractice.ui.theme.PastelBlue
import com.example.katepractice.ui.theme.PastelGreen
import com.example.katepractice.ui.theme.PastelMauve
import com.example.katepractice.ui.theme.PastelOrange
import com.example.katepractice.ui.theme.PastelPink
import com.example.katepractice.ui.theme.PastelPurple
import com.example.katepractice.ui.theme.PastelRed
import com.example.katepractice.ui.theme.PastelYellow

@Composable
fun ContentA(onNext: () -> Unit) = ContentBase(
    title = "Content A Title",
    modifier = Modifier.Companion.background(PastelRed),
    onNext = onNext,
) {
    Text(
        modifier =
            Modifier.Companion.padding(16.dp),
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eleifend dui non orci eleifend bibendum. Nulla varius ultricies dolor sit amet semper. Sed accumsan, dolor id finibus rhoncus, nisi nibh suscipit augue, vitae gravida dui justo et ex. Maecenas eget suscipit lacus. Mauris ac rhoncus lacus. Suspendisse placerat eleifend magna at ornare. Duis efficitur euismod felis, vel porttitor eros hendrerit nec."
    )
}

@Composable
fun ContentB() = ContentBase(
    title = "Content B Title",
    modifier = Modifier.Companion.background(PastelGreen)
) {
    Text(
        modifier =
            Modifier.Companion.padding(16.dp),
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eleifend dui non orci eleifend bibendum. Nulla varius ultricies dolor sit amet semper. Sed accumsan, dolor id finibus rhoncus, nisi nibh suscipit augue, vitae gravida dui justo et ex. Maecenas eget suscipit lacus. Mauris ac rhoncus lacus. Suspendisse placerat eleifend magna at ornare. Duis efficitur euismod felis, vel porttitor eros hendrerit nec."
    )
}

@Composable
fun SampleContent(title: String, backgroundColor: Color, onNext: (() -> Unit)? = null) =
    ContentBase(
        title = title,
        modifier = Modifier.Companion.background(backgroundColor),
        onNext = onNext,
    ) {
        Text(
            modifier =
                Modifier.Companion.padding(16.dp),
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eleifend dui non orci eleifend bibendum. Nulla varius ultricies dolor sit amet semper. Sed accumsan, dolor id finibus rhoncus, nisi nibh suscipit augue, vitae gravida dui justo et ex. Maecenas eget suscipit lacus. Mauris ac rhoncus lacus. Suspendisse placerat eleifend magna at ornare. Duis efficitur euismod felis, vel porttitor eros hendrerit nec."
        )
    }


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ContentBase(
    title: String,
    modifier: Modifier = Modifier.Companion,
    onNext: (() -> Unit)? = null,
    content: (@Composable () -> Unit)? = null,
) {
    Column(
        horizontalAlignment = Alignment.Companion.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .clip(RoundedCornerShape(48.dp))
    ) {
        Title(title)
        if (content != null) content()
        if (onNext != null) {
            Button(
                modifier = Modifier.Companion.align(Alignment.Companion.CenterHorizontally),
                onClick = onNext
            ) {
                Text("Next")
            }
        }
    }
}

@Composable
fun ColumnScope.Title(title: String) {
    Text(
        modifier = Modifier.Companion
            .padding(24.dp)
            .align(Alignment.Companion.CenterHorizontally),
        fontWeight = FontWeight.Companion.Bold,
        text = title
    )
}

@Composable
@Preview
fun ContentAPreview() {
    AppTheme {
        ContentA({})
    }
}

@Composable
fun Count(name: String) {
    var count: Int by rememberSaveable { mutableIntStateOf(0) }
    Column {
        Text("Count $name is $count")
        Button(onClick = { count++ }) {
            Text("Increment")
        }
    }
}


@Composable
fun ContentRed(
    title: String,
    modifier: Modifier = Modifier.Companion,
    onNext: (() -> Unit)? = null,
    content: (@Composable () -> Unit)? = null,
) = ContentBase(
    title = title,
    modifier = modifier.background(PastelRed),
    onNext = onNext,
    content = content
)

@Composable
fun ContentOrange(
    title: String,
    modifier: Modifier = Modifier.Companion,
    onNext: (() -> Unit)? = null,
    content: (@Composable () -> Unit)? = null,
) = ContentBase(
    title = title,
    modifier = modifier.background(PastelOrange),
    onNext = onNext,
    content = content
)

@Composable
fun ContentYellow(
    title: String,
    modifier: Modifier = Modifier.Companion,
    onNext: (() -> Unit)? = null,
    content: (@Composable () -> Unit)? = null,
) = ContentBase(
    title = title,
    modifier = modifier.background(PastelYellow),
    onNext = onNext,
    content = content
)

@Composable
fun ContentGreen(
    title: String,
    modifier: Modifier = Modifier.Companion,
    onNext: (() -> Unit)? = null,
    content: (@Composable () -> Unit)? = null,
) = ContentBase(
    title = title,
    modifier = modifier.background(PastelGreen),
    onNext = onNext,
    content = content
)

@Composable
fun ContentBlue(
    title: String,
    modifier: Modifier = Modifier.Companion,
    onNext: (() -> Unit)? = null,
    content: (@Composable () -> Unit)? = null,
) = ContentBase(
    title = title,
    modifier = modifier.background(PastelBlue),
    onNext = onNext,
    content = content
)

@Composable
fun ContentMauve(
    title: String,
    modifier: Modifier = Modifier.Companion,
    onNext: (() -> Unit)? = null,
    content: (@Composable () -> Unit)? = null,
) = ContentBase(
    title = title,
    modifier = modifier.background(PastelMauve),
    onNext = onNext,
    content = content
)

@Composable
fun ContentPurple(
    title: String,
    modifier: Modifier = Modifier.Companion,
    onNext: (() -> Unit)? = null,
    content: (@Composable () -> Unit)? = null,
) = ContentBase(
    title = title,
    modifier = modifier.background(PastelPurple),
    onNext = onNext,
    content = content
)

@Composable
fun ContentPink(
    title: String,
    modifier: Modifier = Modifier.Companion,
    onNext: (() -> Unit)? = null,
    content: (@Composable () -> Unit)? = null,
) = ContentBase(
    title = title,
    modifier = modifier.background(PastelPink),
    onNext = onNext,
    content = content
)