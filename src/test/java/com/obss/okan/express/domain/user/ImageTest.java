package com.obss.okan.express.domain.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ImageTest {

    @Test
    void when_image_created_expected_toString_with_adress(){
        final var image = new Image("some-image-address");
        assertThat(image).hasToString("some-image-address");
    }
    @Test
    void when_image_has_same_address_expect_equal_and_hashcode() {
        final var image = new Image("address");
        final var imageWithSameAddress = new Image("address");

        assertThat(imageWithSameAddress)
                .isEqualTo(image)
                .hasSameHashCodeAs(image);
    }
}
