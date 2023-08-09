package com.example.themovie.presentation.movies_list.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.themovie.R
import com.example.themovie.presentation.common.DefaultPreview
import com.example.themovie.presentation.common.FullScreenPreview
import com.example.themovie.presentation.ui.theme.OuterSpace
import com.example.themovie.presentation.ui.theme.TheMovieTheme
import com.example.themovie.util.MoviesFilter

@Composable
fun MovieFilterItem(
    title: String,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.width(IntrinsicSize.Max)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = if (selected) MaterialTheme.colorScheme.onBackground else OuterSpace,
            ),
            modifier = Modifier.selectable(
                selected = selected,
                role = Role.Tab,
                onClick = onSelect
            )
        )
        Spacer(modifier = Modifier.height(4.dp))

        AnimatedVisibility(selected) {
            Divider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }

}

@DefaultPreview
@Composable
fun MovieFilterItemPreview() {
    TheMovieTheme {
        MovieFilterItem(
            title = stringResource(R.string.now_playing),
            selected = true,
            onSelect = {})
    }
}


@Composable
fun MoviesFilterList(
    selectedItem: MoviesFilter,
    onItemSelected: (MoviesFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .fillMaxWidth()
            .selectableGroup()
    ) {
        item {
            MovieFilterItem(
                title = stringResource(R.string.now_playing),
                selected = MoviesFilter.NOW_PLAYING == selectedItem,
                onSelect = { onItemSelected(MoviesFilter.NOW_PLAYING) }
            )
        }
        item {
            MovieFilterItem(
                title = stringResource(R.string.popular),
                selected = MoviesFilter.POPULAR == selectedItem,
                onSelect = { onItemSelected(MoviesFilter.POPULAR) }
            )
        }
        item {
            MovieFilterItem(
                title = stringResource(R.string.top_rated),
                selected = MoviesFilter.TOP_RATED == selectedItem,
                onSelect = { onItemSelected(MoviesFilter.TOP_RATED) }
            )
        }
        item {
            MovieFilterItem(
                title = stringResource(R.string.upcoming),
                selected = MoviesFilter.UPCOMING == selectedItem,
                onSelect = { onItemSelected(MoviesFilter.UPCOMING) }
            )
        }
    }
}

@FullScreenPreview
@Composable
fun MoviesFilterPreview() {
    TheMovieTheme {
        MoviesFilterList(
            selectedItem = MoviesFilter.NOW_PLAYING,
            onItemSelected = {}
        )
    }
}