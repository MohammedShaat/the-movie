package com.example.themovie.presentation.movies_list.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.themovie.R
import com.example.themovie.presentation.common.DefaultPreview
import com.example.themovie.presentation.ui.theme.TheMovieTheme

@Composable
fun FAB(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        shape = MaterialTheme.shapes.extraLarge,
        containerColor = MaterialTheme.colorScheme.secondary,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_up_arrow),
            contentDescription = stringResource(R.string.cd_scroll_up),
        )
    }
}

@DefaultPreview
@Composable
fun FABPreview() {
    TheMovieTheme {
        FAB(onClick = { /*TODO*/ })
    }
}