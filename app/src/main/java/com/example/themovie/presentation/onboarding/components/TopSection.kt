package com.example.themovie.presentation.onboarding.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.themovie.R
import com.example.themovie.presentation.common.DefaultPreview
import com.example.themovie.presentation.ui.theme.TheMovieTheme

@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    onSkipClicked: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        TextButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = onSkipClicked
        ) {
            Text(text = stringResource(R.string.skip))
        }
    }

}

@DefaultPreview
@Composable
fun TopSectionPreview() {
    TheMovieTheme {
        TopSection(onSkipClicked = {})
    }
}