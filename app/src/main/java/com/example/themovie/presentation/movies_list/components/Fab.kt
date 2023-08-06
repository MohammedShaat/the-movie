package com.example.themovie.presentation.movies_list.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.themovie.R

@Composable
fun FAB(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilledIconButton(
        onClick = onClick,
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = modifier.size(48.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_up_arrow),
            contentDescription = stringResource(R.string.cd_scroll_up),
        )
    }

}