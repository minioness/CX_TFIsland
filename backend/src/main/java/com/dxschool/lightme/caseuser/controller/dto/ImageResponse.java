package com.dxschool.lightme.caseuser.controller.dto;

import com.dxschool.lightme.caseuser.domain.Image;

public record ImageResponse(
        Long imageId,
        String imageUrl
) {
    public static ImageResponse from(Image image) {
        return new ImageResponse(image.getImageId(), image.getImageUrl());
    }
}
