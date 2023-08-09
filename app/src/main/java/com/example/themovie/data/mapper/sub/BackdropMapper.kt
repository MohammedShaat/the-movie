package com.example.themovie.data.mapper.sub

import com.example.themovie.data.remote.dto.sub.BackdropDto
import com.example.themovie.domain.model.sub.Backdrop
import com.example.themovie.util.Constants.IMAGE_BASE_URL

fun BackdropDto.toBackdrop(): Backdrop {
    return Backdrop(
        url = "$IMAGE_BASE_URL/$filePath"
    )
}