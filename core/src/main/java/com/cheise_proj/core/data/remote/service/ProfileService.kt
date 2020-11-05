package com.cheise_proj.core.data.remote.service

import com.cheise_proj.core.data.remote.model.dto.StudentDto

interface ProfileService {
    suspend fun studentProfile():StudentDto
}