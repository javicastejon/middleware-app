package com.tfg.backend.api.request;

import com.tfg.backend.data.ErrorMessages.ErrorMessageRequests;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(
    @NotBlank(message = ErrorMessageRequests.USER_EMAIL_NULL)
    String userEmailRq,

    @NotBlank(message = ErrorMessageRequests.USER_PASS_HASH_NULL)
    String passHashRq
){}