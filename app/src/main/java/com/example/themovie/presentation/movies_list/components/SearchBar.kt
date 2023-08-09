package com.example.themovie.presentation.movies_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.themovie.R
import com.example.themovie.presentation.common.FullScreenPreview
import com.example.themovie.presentation.ui.theme.TheMovieTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = stringResource(R.string.placeholder_search_here))
        },
        singleLine = true,
        shape = MaterialTheme.shapes.extraLarge,
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    )
}

@FullScreenPreview
@Composable
fun SearchBarPreview() {
    TheMovieTheme() {
        SearchBar("", {})
    }
}