package com.obss.okan.express.domain.jwt;

public interface JWTDeserializer {
    JWTPayload jwtPayloadFromJWT(String jwtToken);
}
