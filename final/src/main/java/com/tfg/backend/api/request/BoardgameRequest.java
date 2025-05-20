package com.tfg.backend.api.request;

import com.tfg.backend.config.DataBaseConfig;
import com.tfg.backend.data.ErrorMessages.ErrorMessageRequests;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BoardgameRequest(
    @NotBlank(message = ErrorMessageRequests.BOARDGAME_NAME_NULL)
    @Size(  min = DataBaseConfig.BOARDGAME_NAME_MIN_CHARACTERS, 
            max = DataBaseConfig.BOARDGAME_NAME_MAX_CHARACTERS, 
            message = ErrorMessageRequests.BOARDGAME_NAME_SIZE)
    String boardgameNameRq,

    @NotNull(message = ErrorMessageRequests.BOARDGAME_PLAYERS_MIN_NULL)
    Integer minPlayersRq,

    @NotNull(message = ErrorMessageRequests.BOARDGAME_PLAYERS_MAX_NULL)
    Integer maxPlayersRq,

    Integer releaseYearRq,

    @Size(  max = DataBaseConfig.BOARDGAME_DESCRIPTION_MAX_CHARACTERS, 
            message = ErrorMessageRequests.BOARDGAME_DESCRIPTION_SIZE)
    String boardGameDescriptionRq,

    @Size(  max = DataBaseConfig.BOARDGAME_IMAGE_ENDPOINT_MAX_CHARACTERS)
    String boardgameImageUrlRq,

    @NotBlank(message = ErrorMessageRequests.BOARDGAME_REF_API_NULL)
    @Size(  max = DataBaseConfig.BOARDGAME_API_BGG_REF_MAX_CHARACTERS)
    String refApiBggRq,

    @NotNull(message = ErrorMessageRequests.BOARDGAME_TYPE_NULL)
    Integer boardgameTypeRq,

    Integer boardGameBaseRq,

    @NotNull(message = ErrorMessageRequests.BOARDGAME_GENDER_NULL)
    Integer boardgameGenderRq
) {}
